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
    // Activity + "-R1",  number based on arraylist size
    String reviewId = id + "-R" + (reviewList.size() + 1);
    switch (reviewType) {
      // based on passed reviewType, determine subclass to create from
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
    // confirm review has beed added
    MessageCli.REVIEW_ADDED.printMessage(reviewType.getName(),
        reviewList.getLast().getId(),
        this.activityName);
  }

  public void printReviews() {
    // different message based on number of reviews (size)
    switch (reviewList.size()) {
      case 0: // no reviews
        MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", this.activityName);
        return;
      case 1: // singular review
        MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", this.activityName);
        break;
      default: // pluralised review
        MessageCli.REVIEWS_FOUND.printMessage("are",
            String.valueOf(reviewList.size()),
            "s",
            this.activityName);
    }
    for (Review review : reviewList) {
      review.printReview();
    }
  }

  public Review getReviewById(String reviewId) {
    // iterate, checking for id match
    for (Review review : reviewList) {
      if (review.getId().equalsIgnoreCase(reviewId)) {
        return review;
      }
    }
    return null; // if not found
  }

}
