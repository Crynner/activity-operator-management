package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;
import nz.ac.auckland.se281.Types.ReviewType;

public class OperatorManagementSystem {

  private Map<Location, Integer> locationIdTracker = new HashMap<>();
  private Map<Location, ArrayList<String>> locationOperatorNames = new HashMap<>();
  private ArrayList<Operator> operatorList = new ArrayList<>();

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    // initialise location maps - starting id 0 on IdTracker, 
    for (Location location : Location.values()) {
      locationIdTracker.put(location, 0);
      locationOperatorNames.put(location, new ArrayList<>()); // used only in create-operator
    }
  }

  private ArrayList<Location> findLocations(String input) {
    ArrayList<Location> matchingLocations = new ArrayList<>();
    // check for substring match in all location names (english, maori, abbrev).
    for (Location location : Location.values()) {
      if (location.getNameEnglish().toLowerCase().contains(input)
          || location.getNameTeReo().toLowerCase().contains(input)
          || location.getLocationAbbreviation().toLowerCase().contains(input)) {
        matchingLocations.add(location);
      }
    }
    // return arraylist, empty or not (shouldn't matter if used for contains())
    return matchingLocations;
  }

  private void printActivityNumber(Integer totalNumber) {
    switch (totalNumber) {
      case 0: // zero activities found
        MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
        break;
      case 1: // one activity found
        MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
        break;
      default: // more than one activity found
        MessageCli.ACTIVITIES_FOUND.printMessage("are",
            totalNumber.toString(), "ies", ":");
    }
  }

  private void addReviewToActivity(Map<String, String> reviewDetails,
                                  String activityId,
                                  ReviewType reviewType) {
    // checks all operators for particular activity id, adding review if found
    for (Operator operator : operatorList) {
      Activity foundActivity = operator.findActivity(activityId);
      if (foundActivity != null) {
        foundActivity.addReview(reviewDetails, activityId, reviewType);
        return;
      }
    }
    // if id matches nothing, relevant error message
    MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
  }

  private Review getReviewById(String reviewId) {
    // applies getReviewById to all operators until successful (i.e not null returned)
    for (Operator operator : operatorList) {
      Review foundReview = operator.getReviewById(reviewId);
      if (foundReview != null) {
        return foundReview;
      }
    }
    return null; // if not found
  }

  public void searchOperators(String keyword) {
    // standardise string for case-checking
    keyword = keyword.trim().toLowerCase();

    ArrayList<Operator> filteredOperators = new ArrayList<>();

    // first check for wildcard operator, prints all
    if (keyword.equals("*")) {
      filteredOperators = operatorList;
    } else {
      ArrayList<Location> locations = findLocations(keyword);
      for (Operator operator : operatorList) {
        // check each operator if keyword is a substring of location or name, add if true.
        if (locations.contains(operator.getLocation())
            || operator.getName().toLowerCase().contains(keyword)) {
          filteredOperators.add(operator);
        }
      }
    }

    // quantity message based on number of found operators
    if (filteredOperators.isEmpty()) { // no operators found
      MessageCli.OPERATORS_FOUND.printMessage("are",
          "no", "s", ".");
      return; // end here, no need to iterate over filtered arraylist
    } else if (filteredOperators.size() == 1) { // one operator found
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
    } else { // more than one operator found
      MessageCli.OPERATORS_FOUND.printMessage("are", String.valueOf(filteredOperators.size()),
          "s", ":");
    }

    // finally, print each operator in formatted message
    for (Operator operator : filteredOperators) {
      MessageCli.OPERATOR_ENTRY.printMessage(operator.getName(),
          operator.getId(), operator.getLocation().getFullName());
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
        && locationOperatorNames.get(foundLocation).contains(operatorName)) {
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName,
          foundLocation.getFullName());
      return;
    }

    // increment location id and initialise operator
    locationIdTracker.put(foundLocation, locationIdTracker.get(foundLocation) + 1);
    int idNumber = locationIdTracker.get(foundLocation);

    // add operator to list, activity name to location list, outputs to terminal
    operatorList.add(new Operator(operatorName, foundLocation, idNumber));
    locationOperatorNames.get(operatorList.getLast().getLocation()).add(operatorName);

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
      ArrayList<Location> locations = findLocations(keyword);
      for (Operator operator : operatorList) {
        // all activities are added (matched location), or some filtered activities are added.
        if (locations.contains(operator.getLocation())) {
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
    activityId = activityId.trim();

    // binding string array to string map for standardised access in abstract class
    Map<String, String> reviewDetails = new HashMap<>();
    reviewDetails.put("name", options[0]);
    reviewDetails.put("anonymous", options[1]);
    reviewDetails.put("rating", options[2]);
    reviewDetails.put("comment", options[3]);

    addReviewToActivity(reviewDetails, activityId, ReviewType.PUBLIC);
  }

  public void addPrivateReview(String activityId, String[] options) {
    activityId = activityId.trim();

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
    activityId = activityId.trim();

    // binding options to String keys for standardised access
    Map<String, String> reviewDetails = new HashMap<>();
    reviewDetails.put("name", options[0]);
    reviewDetails.put("rating", options[1]);
    reviewDetails.put("comment", options[2]);
    reviewDetails.put("recommend", options[3]);

    addReviewToActivity(reviewDetails, activityId, ReviewType.EXPERT);
  }

  public void displayReviews(String activityId) {
    activityId = activityId.trim();

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
    reviewId = reviewId.trim();

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
    reviewId = reviewId.trim();
    response = response.trim();

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
    reviewId = reviewId.trim();
    imageName = imageName.trim();

    Review reviewForImage = getReviewById(reviewId);
    if (reviewForImage == null) {
      // review not found
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
    } else if (!(reviewForImage instanceof ExpertReview)) {
      // review is not Expert
      MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
    } else {
      ((ExpertReview) reviewForImage).addImage(imageName);
      MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
    }
  }

  public void displayTopActivities() {
    Map<Location, Activity> topActivities = new HashMap<>();
    for (Operator operator : operatorList) {
      Location operatorLocation = operator.getLocation();
      Activity actToCheck = operator.findHighestRatedActivity();

      // if review exists, AND
      // (activity is first for location OR activity has higher rating than stored highest)
      if (actToCheck != null
          && (!topActivities.containsKey(operator.getLocation())
              || topActivities.get(operatorLocation).getAvgRating() < actToCheck.getAvgRating())) {
        topActivities.put(operatorLocation, actToCheck);
      }
    }
    // after all operators, iterate over Locations to find
    for (Location location : Location.values()) {
      if (!topActivities.containsKey(location)) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.getFullName());
      } else {
        MessageCli.TOP_ACTIVITY.printMessage(location.getFullName(),
            topActivities.get(location).getName(),
            String.format("%.1f", topActivities.get(location).getAvgRating()));
      }
    }
  }
}
