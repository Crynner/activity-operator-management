package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

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
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
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
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
