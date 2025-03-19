package nz.ac.auckland.se281;

import java.util.Map;

abstract class Review {
  private String reviewName;
  private int reviewRating;
  private String reviewText;
  private String reviewId;

  Review(Map<String, String> reviewDetails, String id) {
    this.reviewName = reviewDetails.get("name");
    this.reviewRating = Integer.parseInt(reviewDetails.get("rating"));
    this.reviewText = reviewDetails.get("comment");
    this.reviewId = id;
  }

  public void printReview() {
    printReview(reviewName);
  }

  public void printReview(String name) {
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(String.valueOf(reviewRating), "5", reviewId, name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(reviewText);
  }
}
