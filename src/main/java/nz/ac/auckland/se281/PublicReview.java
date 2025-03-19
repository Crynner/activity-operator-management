package nz.ac.auckland.se281;

import java.util.Map;

public class PublicReview extends Review{
  private boolean reviewAnon;
  private boolean reviewEndorsed = false;

  PublicReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id);
    reviewAnon = (reviewDetails.get("anonymous").equals("y"));
  }

  @Override
  public void printReview() {
    if (reviewAnon) {
      super.printReview("Anonymous");
    } else {
      super.printReview();
    }
    if (reviewEndorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}
