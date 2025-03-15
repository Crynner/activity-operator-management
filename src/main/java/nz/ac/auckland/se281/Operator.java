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

  public void viewAllActivities(){
    for (Activity activity : activityList) {
      MessageCli.ACTIVITY_ENTRY.printMessage(activity.getName(),
          activity.getId(),
          activity.getType().getName(),
          operatorName);
    }
  }
}
