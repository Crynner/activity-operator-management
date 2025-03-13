package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import nz.ac.auckland.se281.MessageCli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperatorManagementSystem {

  Map<Location, Integer> locationIdTracker;
  ArrayList<Operator> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationIdTracker = new HashMap<>();
    for (Location location : Location.values()) {
      locationIdTracker.put(location, 0);
    }
    operatorList = new ArrayList<>();
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
        operatorInitials += word.charAt(0);
      }
      
      this.operatorId = operatorInitials + "-" +location.getLocationAbbreviation() + "-" + String.format("%03d", id_num);

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
    if (keyword == "*"){
      filteredOperators = operatorList;
    }
    if (filteredOperators.size() > 0){
      for (Operator operator: filteredOperators){
        MessageCli.OPERATOR_ENTRY.printMessage(operator.getName(), operator.getId(), operator.getLocation().getFullName());
      }
    } else{
      System.out.println("There are no matching operators found.");
    }
    
  }

  public void createOperator(String operatorName, String location) {
    // increment location id and initialise operator
    locationIdTracker.put(Location.fromString(location), locationIdTracker.get(Location.fromString(location)) + 1);
    int idNumber = locationIdTracker.get(Location.fromString(location));

    operatorList.add(new Operator(operatorName, Location.fromString(location), idNumber));
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
