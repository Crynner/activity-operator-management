package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {
  private String activityName;
  private ActivityType activityType;
  private String activityId;

  Activity(String activityName, ActivityType activityType, String operatorId) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.activityId = operatorId;
  }

  public String getName() {
    return this.activityName;
  }

  public ActivityType getType() {
    return this.activityType;
  }

  public String getId() {
    return this.activityId;
  }

}
