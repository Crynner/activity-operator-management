package nz.ac.auckland.se281;

import java.util.Map;
import nz.ac.auckland.se281.Types.ReviewType;

public class PublicReview extends Review {
  private boolean reviewEndorsed = false;

  PublicReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.PUBLIC);
    // change name to anonymous if indicated in review form
    if (reviewDetails.get("anonymous").equalsIgnoreCase("y")) {
      reviewName = "Anonymous";
    }
  }

  public void endorseReview() {
    reviewEndorsed = true;
  }

  @Override
  public void printReview() {
    super.printReview();
    // denote endorsed review if chosen by admin
    if (reviewEndorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}
