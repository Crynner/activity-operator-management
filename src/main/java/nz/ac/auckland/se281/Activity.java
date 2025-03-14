package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {
  private String activityName;
  private ActivityType activityType;

  Activity(String activityName, ActivityType activityType) {
    this.activityName = activityName;
    this.activityType = activityType;
  }

  public String getName() {
    return this.activityName;
  }

  public ActivityType getType() {
    return this.activityType;
  }
}
