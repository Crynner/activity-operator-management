package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;
import nz.ac.auckland.se281.Types.ReviewType;

public class OperatorManagementSystem {

  private Map<Location, Integer> locationIdTracker;
  private Map<Location, ArrayList<String>> locationActNames;
  private ArrayList<Operator> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationIdTracker = new HashMap<>();
    locationActNames = new HashMap<>();
    operatorList = new ArrayList<>();

    // initialise location maps
    for (Location location : Location.values()) {
      locationIdTracker.put(location, 0);
      locationActNames.put(location, new ArrayList<>());
    }
  }

  public Location findLocation(String input) {
    input = input.toLowerCase();
    // if input corresponds to Location
    if (Location.fromString(input) != null) {
      return Location.fromString(input);
    } else { // otherwise match substrings
      for (Location location : Location.values()) {
        if (location.getNameEnglish().toLowerCase().contains(input)
            || location.getNameTeReo().toLowerCase().contains(input)
            || location.getLocationAbbreviation().toLowerCase().contains(input)) {
          
          return location;
        }
      }
      // if input does not match at all
      return null;
    }
  }

  // could be made static
  public void printActivityNumber(Integer totalNumber) {
    // prints activity number message based on passed number for 0, 1, and more respectively.
    switch (totalNumber) {
      case 0:
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
        return;
      case 1:
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
        break;
      default:
        MessageCli.ACTIVITIES_FOUND.printMessage("are",
            totalNumber.toString(),
            "ies",
            ":");
    }
  }

  public void addReviewToActivity(Map<String, String> reviewDetails,
                                  String activityId,
                                  ReviewType reviewType) {
    for (Operator operator : operatorList) {
      Activity foundActivity = operator.findActivity(activityId);
      if (foundActivity != null) {
        foundActivity.addReview(reviewDetails, activityId, reviewType);
        return;
      }
    }
    // if id matches nothing
    MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
  }

  public Review getReviewById(String reviewId) {
    for (Operator operator : operatorList) {
      Review foundReview = operator.getReviewById(reviewId);
      if (foundReview != null) {
        return foundReview;
      }
    }
    return null; // if not found
  }

  public void searchOperators(String keyword) {
    keyword = keyword.trim();

    ArrayList<Operator> filteredOperators = new ArrayList<>();

    if (keyword.equals("*")) {
      filteredOperators = operatorList;
    } else {
      // if keyword corresponds to valid location
      if (findLocation(keyword) != null) {
        Location location = findLocation(keyword);
  
        // add all operators with matching location
        for (Operator operator : operatorList) {
          if (operator.getLocation() == location) {
            filteredOperators.add(operator);
          }
        }
      }

      for (Operator op : operatorList) {
        // if keyword is found as substring and not already in filteredOperators, add it
        if (op.getName().toLowerCase().contains(keyword.toLowerCase())
            && !filteredOperators.contains(op)) {
          filteredOperators.add(op);
        }
      }
    }
    
    if (!filteredOperators.isEmpty()) {
      // if exactly one operators match
      if (filteredOperators.size() == 1) {
        MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      } else { // if more than one operators match
        MessageCli.OPERATORS_FOUND.printMessage("are",
            String.valueOf(filteredOperators.size()),
            "s",
            ":");
      }
      
      // "finally", print each Operator entry
      for (Operator operator : filteredOperators) {
        MessageCli.OPERATOR_ENTRY.printMessage(operator.getName(),
            operator.getId(),
            operator.getLocation().getFullName());
      }
    } else { // if no operators match
      MessageCli.OPERATORS_FOUND.printMessage("are",
          "no",
          "s",
          ".");
    }
  }

  public void createOperator(String operatorName, String location) {

    // remove whitespace and internal double spaces for operatorName
    operatorName = operatorName.trim();
    operatorName = operatorName.replaceAll("\\s{2,}", " ");
    location = location.trim();


    // names less than 3 characters are invalid.
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    Location foundLocation = Location.fromString(location);
    if (foundLocation == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }

    // checks if any operators exist, and if operator is already catalogued (same name and location)
    if (!operatorList.isEmpty()
        && locationActNames.get(foundLocation).contains(operatorName)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName,
          foundLocation.getFullName());
      return;
    }

    // increment location id and initialise operator
    locationIdTracker.put(foundLocation, locationIdTracker.get(foundLocation) + 1);
    int idNumber = locationIdTracker.get(foundLocation);

    // add operator to list, activity name to location list, outputs to terminal
    operatorList.add(new Operator(operatorName, foundLocation, idNumber));
    locationActNames.get(operatorList.getLast().getLocation()).add(operatorName);

    MessageCli.OPERATOR_CREATED.printMessage(operatorName,
        operatorList.getLast().getId(),
        foundLocation.getFullName());
  }

  public void viewActivities(String operatorId) {
    operatorId = operatorId.trim();
    // check operator exists
    for (Operator operator : operatorList) {
      if (operator.getId().equalsIgnoreCase(operatorId)) {

        // print grammatically accurate message
        printActivityNumber(operator.getActivityNumber());

        for (String activityMsg : operator.addAllActivityMsgs()) {
          System.out.println(activityMsg);
        }
        return;
      }
    }
    // otherwise output cli error
    MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    activityName = activityName.trim();
    activityType = activityType.trim();
    operatorId = operatorId.trim();

    // check operator exists
    for (Operator operator : operatorList) {
      if (operator.getId().equalsIgnoreCase(operatorId)) {
        // error checking for short activity name
        if (activityName.length() < 3) {
          MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
          return;
        }

        operator.createActivity(activityName, ActivityType.fromString(activityType));
        return;
      }
    }
    // otherwise output cli error
    MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
  }

  public void searchActivities(String keyword) {
    // standardise formatting
    keyword = keyword.trim().toLowerCase();

    ArrayList<String> filteredActivityMsgs = new ArrayList<>();

    if (keyword.equals("*")) {
      // get all activities in all operators
      for (Operator operator : operatorList) {
        filteredActivityMsgs.addAll(operator.addAllActivityMsgs());
      }

    } else {
      for (Operator operator : operatorList) {
        // all activities are added (matched location), or some filtered activities are added.
        if (operator.getLocation() == findLocation(keyword)) {
          filteredActivityMsgs.addAll(operator.addAllActivityMsgs());
        } else {
          filteredActivityMsgs.addAll(operator.addFilteredActivityMsgs(keyword));
        }
      }
    }

    // print message for number of activities, and list of activities
    printActivityNumber(filteredActivityMsgs.size());
    for (String activityMsg : filteredActivityMsgs) {
      System.out.println(activityMsg);
    }
  }

  public void addPublicReview(String activityId, String[] options) {
    Map<String, String> reviewDetails = new HashMap<>();
    reviewDetails.put("name", options[0]);
    reviewDetails.put("anonymous", options[1]);
    reviewDetails.put("rating", options[2]);
    reviewDetails.put("comment", options[3]);

    addReviewToActivity(reviewDetails, activityId, ReviewType.PUBLIC);
  }

  public void addPrivateReview(String activityId, String[] options) {
    // binding options to String keys for standardised access
    Map<String, String> reviewDetails = new HashMap<>();
    reviewDetails.put("name", options[0]);
    reviewDetails.put("email", options[1]);
    reviewDetails.put("rating", options[2]);
    reviewDetails.put("comment", options[3]);
    reviewDetails.put("followup", options[4]);

    addReviewToActivity(reviewDetails, activityId, ReviewType.PRIVATE);
  }

  public void addExpertReview(String activityId, String[] options) {
    // binding options to String keys for standardised access
    Map<String, String> reviewDetails = new HashMap<>();
    reviewDetails.put("name", options[0]);
    reviewDetails.put("rating", options[1]);
    reviewDetails.put("comment", options[2]);
    reviewDetails.put("recommend", options[3]);

    addReviewToActivity(reviewDetails, activityId, ReviewType.EXPERT);
  }

  public void displayReviews(String activityId) {
    // find activity to match id
    for (Operator operator : operatorList) {
      Activity targetActivity = operator.findActivity(activityId);
      if (targetActivity != null) {
        targetActivity.printReviews();
        return;
      }
    }
    // if activity id does not match any
    MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
  }

  public void endorseReview(String reviewId) {
    Review reviewToEndorse = getReviewById(reviewId);
    if (reviewToEndorse == null) {
      // if review is not found
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    } else if (!(reviewToEndorse instanceof PublicReview)) {
      // if found review is not a Public subclass of Review
      MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
    } else {
      // downcast to call method (exclusive to PublicReview)
      ((PublicReview) reviewToEndorse).endorseReview();
      MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
    }
  }

  public void resolveReview(String reviewId, String response) {
    Review reviewToResolve = getReviewById(reviewId);
    if (reviewToResolve == null) {
      // if review not found
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    } else if (!(reviewToResolve instanceof PrivateReview)) {
      // if found review is not a Private review
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
    } else {
      ((PrivateReview) reviewToResolve).resolveReview(response);
      MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
    }
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
