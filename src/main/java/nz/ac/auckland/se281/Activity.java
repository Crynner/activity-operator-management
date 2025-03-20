package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Map;

import nz.ac.auckland.se281.Types.ActivityType;
import nz.ac.auckland.se281.Types.ReviewType;

public class Activity {
  private String activityName;
  private ActivityType activityType;
  private String activityId;

  private ArrayList<Review> reviewList = new ArrayList<>();

  Activity(String activityName, ActivityType activityType, String activityId) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.activityId = activityId;
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

  public boolean contains(String matchPhrase) {
    // if name or type contains matchPhrase (case insensitive)
    if (getName().toLowerCase().contains(matchPhrase)
        || getType().getName().toLowerCase().contains(matchPhrase)) {
      return true;
    }
    return false;
  }

  public void addReview(Map<String, String> details, String id, ReviewType reviewType) {
    String reviewId = id + String.format("%03d", reviewList.size()+1);
    switch (reviewType) {
      case PUBLIC:
        reviewList.add(new PublicReview(details, reviewId));
        break;
      case PRIVATE:
        reviewList.add(new PrivateReview(details, reviewId));
        break;
      case EXPERT:
        reviewList.add(new ExpertReview(details, reviewId));
        break;
    }
    MessageCli.REVIEW_ADDED.printMessage(reviewType.getName(),
        reviewList.getLast().getId(),
        getName());
  }

}
