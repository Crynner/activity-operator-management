package nz.ac.auckland.se281;

abstract class Review {
  private String reviewName;
  private int reviewRating;
  private String reviewText;
  private boolean reviewRecommended;

  Review(String[] reviewDetails) {
    this.reviewName = reviewDetails[0];
    this.reviewRating = Integer.parseInt(reviewDetails[1]);
    this.reviewText = reviewDetails[2];
    if (reviewDetails[3].equals("y")) {
      this.reviewRecommended = true;
    } else {
      this.reviewRecommended = false;
    }
  }
}
