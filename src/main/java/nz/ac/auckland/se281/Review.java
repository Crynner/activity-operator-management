package nz.ac.auckland.se281;

import java.util.Map;
import nz.ac.auckland.se281.Types.ReviewType;

abstract class Review {
  private String reviewName;
  private int reviewRating;
  private String reviewText;
  private String reviewId;
  private ReviewType reviewType;

  Review(Map<String, String> reviewDetails, String id, ReviewType reviewType) {
    this.reviewName = reviewDetails.get("name");
    this.reviewRating = Integer.parseInt(reviewDetails.get("rating")); // TODO limit setting for rating
    this.reviewText = reviewDetails.get("comment");
    this.reviewId = id;
    this.reviewType = reviewType;
  }

  public String getName() {
    return reviewName;
  }

  public int getRating() {
    return reviewRating;
  }

  public String getText() {
    return reviewText;
  }

  public String getId() {
    return reviewId;
  }

  public ReviewType getType() {
    return reviewType;
  }

  public void printReview() {
    printReview(reviewName);
  }

  public void printReview(String name) {
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(String.valueOf(reviewRating),
        "5", reviewType.getName(), reviewId, name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(reviewText);
  }
}
