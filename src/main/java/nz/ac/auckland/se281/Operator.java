package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class Operator {
  private String operatorName;
  private Location location;
  private String operatorId;

  Operator(String operatorName, Location location, int IdNum) {
    this.operatorName = operatorName;
    this.location = location;

    // construct initials by splitting words and taking the first letter
    String operatorInitials = "";
    for (String word : operatorName.split(" ")) {
      operatorInitials += Character.toUpperCase(word.charAt(0));
    }
    this.operatorId = operatorInitials + "-"
      + location.getLocationAbbreviation() + "-"
      + String.format("%03d", IdNum);

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
}
