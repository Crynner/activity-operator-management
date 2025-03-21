package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String operatorName;
  private Location location;
  private String operatorId;

  private ArrayList<Activity> activityList = new ArrayList<>();

  Operator(String operatorName, Location location, int idNum) {
    this.operatorName = operatorName;
    this.location = location;

    // construct initials by splitting words and taking the first letter
    String operatorInitials = "";
    for (String word : operatorName.split(" ")) {
      operatorInitials += Character.toUpperCase(word.charAt(0));
    }
    this.operatorId = operatorInitials
        + "-"
        + location.getLocationAbbreviation()
        + "-"
        + String.format("%03d", idNum);

  }

  public String getName() {
    return this.operatorName;
  }

  public Location getLocation() {
    return this.location;
  }

  public String getId() {
    return this.operatorId;
  }

  public Integer getActivityNumber() {
    return this.activityList.size();
  }

  public Activity findActivity(String activityId) {
    for (Activity activity : activityList) {
      if (activity.getId().equalsIgnoreCase(activityId)) {
        return activity;
      }
    }
    return null;
  }

  public void createActivity(String activityName, ActivityType activityType) {
    // passes name, type, and id with size-based incrementer
    activityList.add(new Activity(activityName,
        activityType,
        getId() + "-" + String.format("%03d", activityList.size() + 1)));
    
    MessageCli.ACTIVITY_CREATED.printMessage(activityName,
        activityList.getLast().getId(),
        activityType.getName(),
        getName());
  }

  public ArrayList<String> addAllActivityMsgs() {
    // helper method that returns all activities
    return addFilteredActivityMsgs("");
  }

  public ArrayList<String> addFilteredActivityMsgs(String matchPhrase) {
    ArrayList<String> activityMsgs = new ArrayList<>();
    for (Activity activity : activityList) {
      // uses abstracted conditional for readability, adding formatted string to returned ArrayList
      if (activity.contains(matchPhrase)) {
        activityMsgs.add(MessageCli.ACTIVITY_ENTRY.getMessage(activity.getName(),
            activity.getId(),
            activity.getType().getName(),
            operatorName));
      }
    }
    return activityMsgs;
  }

  public Review getReviewById(String reviewId) {
    for (Activity activity : activityList) {
      Review foundReview = activity.getReviewById(reviewId);
      if (foundReview != null) {
        return foundReview;
      }
    }
    return null; // if not found
  }

  public Activity findHighestRatedActivity() {
    return null;
  }
}
