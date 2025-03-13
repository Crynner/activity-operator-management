package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperatorManagementSystem {

  Map<Location, Integer> locationIdTracker;
  Map<Location, ArrayList<String>> locationActNames;
  ArrayList<Operator> operatorList;

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

  // basic class for storing Operator info
  final class Operator {
    private String operatorName;
    private Location location;
    private String operatorId;

    Operator(String operatorName, Location location, int id_num) {
      this.operatorName = operatorName;
      this.location = location;

      // construct initials by splitting words and taking the first letter
      String operatorInitials = "";
      for (String word : operatorName.split(" ")) {
        operatorInitials += Character.toUpperCase(word.charAt(0));
      }
      this.operatorId = operatorInitials + "-"
        + location.getLocationAbbreviation() + "-"
        + String.format("%03d", id_num);

    }

    public String getName(){
      return this.operatorName;
    }

    public Location getLocation(){
      return this.location;
    }

    public String getId(){
      return this.operatorId;
    }
  }


  public void searchOperators(String keyword) {
    ArrayList<Operator> filteredOperators = new ArrayList<>();
    if (keyword.equals("*")){
      filteredOperators = operatorList;
    }
    else if(Location.fromString(keyword) != null){
      // add all operators with matching location
      for (Operator operator: operatorList){
        if (operator.getLocation().equals(Location.fromString(keyword))){
          filteredOperators.add(operator);
        }
      }
    }
    else {
      // supposing keyword is a valid location name
      for (Location location: Location.values()){
        if (location.getNameEnglish().contains(keyword)
            || location.getNameTeReo().contains(keyword)
            || location.getLocationAbbreviation().contains(keyword)){
          
          // add all operators with matching location
          for (Operator operator: operatorList){
            if (operator.getLocation().equals(location)){
              filteredOperators.add(operator);
            }
          }
        }
      }
    }
    if (filteredOperators.size() > 0){
      // if exactly one operators match
      if (filteredOperators.size() == 1){
        MessageCli.OPERATORS_FOUND.printMessage("is", String.valueOf(1), "", ":");
      }
      // if more than one operators match
      else{
        MessageCli.OPERATORS_FOUND.printMessage("are", String.valueOf(filteredOperators.size()), "s", ":");
      }
      
      // "finally", print each Operator entry
      for (Operator operator: filteredOperators){
        MessageCli.OPERATOR_ENTRY.printMessage(operator.getName(), operator.getId(), operator.getLocation().getFullName());
      }
    }
    // if no operators match
    else{
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
    
  }

  public void createOperator(String operatorName, String location) {
    // remove whitespace
    operatorName = operatorName.trim();
    location = location.trim();

    if (operatorName.length() < 3){
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
    }

    // checks if any operators exist, and if operator is already catalogued (same name and location)
    if (operatorList.size() > 0 && 
        locationActNames.get(Location.fromString(location)).contains(operatorName)){
      MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName, Location.fromString(location).getFullName());
      return;
    }
    // increment location id and initialise operator
    locationIdTracker.put(Location.fromString(location), locationIdTracker.get(Location.fromString(location)) + 1);
    int idNumber = locationIdTracker.get(Location.fromString(location));

    // add operator to list, activity name to location list, outputs to terminal
    operatorList.add(new Operator(operatorName, Location.fromString(location), idNumber));
    locationActNames.get(operatorList.getLast().getLocation()).add(operatorName);

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorList.getLast().getId(), Location.fromString(location).getFullName());
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
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
