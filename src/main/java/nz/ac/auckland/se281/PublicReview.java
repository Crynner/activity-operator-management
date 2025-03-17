package nz.ac.auckland.se281;

public class PublicReview extends Review{
  private boolean reviewAnon;

  PublicReview(String[] reviewDetails, String id) {
    super(reviewDetails, id);
    reviewAnon = (reviewDetails[3].equals("y")) ? true : false;
  }
}
