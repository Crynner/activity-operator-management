package nz.ac.auckland.se281;

abstract class Review {
  private String reviewName;
  private int reviewRating;
  private String reviewText;
  private String reviewId;

  Review(String[] reviewDetails, String id) {
    this.reviewName = reviewDetails[0];
    this.reviewRating = Integer.parseInt(reviewDetails[1]);
    this.reviewText = reviewDetails[2];
    this.reviewId = id;
  }
}
