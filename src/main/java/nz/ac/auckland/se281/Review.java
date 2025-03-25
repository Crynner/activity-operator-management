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

    // case rating if out of limits, otherwise regular
    int rawRating = Integer.parseInt(reviewDetails.get("rating"));
    if (rawRating > 5) {
      this.reviewRating = 5;
    } else if (rawRating < 1) {
      this.reviewRating = 1;
    } else {
      this.reviewRating = rawRating;
    }
    
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
