package nz.ac.auckland.se281;

import java.util.Map;
import nz.ac.auckland.se281.Types.ReviewType;

public class PublicReview extends Review {
  private boolean reviewAnon;
  private boolean reviewEndorsed = false;

  PublicReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.PUBLIC);
    reviewAnon = (reviewDetails.get("anonymous").equals("y"));
  }

  public void endorseReview() {
    reviewEndorsed = true;
  }

  @Override
  public void printReview() {
    // TODO permanent name change if not needed for future methods
    // change name to anonymous if indicated in review form
    if (reviewAnon) {
      super.printReview("Anonymous");
    } else {
      super.printReview();
    }
    // denote endorsed review if chosen by admin
    if (reviewEndorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}
