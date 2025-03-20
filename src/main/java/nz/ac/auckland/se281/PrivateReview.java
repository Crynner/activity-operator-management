package nz.ac.auckland.se281;

import java.util.Map;
import nz.ac.auckland.se281.Types.ReviewType;

public class PrivateReview extends Review {
  private boolean reviewFollowup;
  private String reviewEmail;
  private String reviewResolveMsg = "-";

  PrivateReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.PRIVATE);
    reviewFollowup = (reviewDetails.get("followup").equals("y"));
    reviewEmail = reviewDetails.get("email");
  }

  public void resolveReview(String resolutionMsg) {
    reviewResolveMsg = resolutionMsg;
    reviewFollowup = false;
  }

  @Override
  public void printReview() {
    super.printReview();
    if (reviewFollowup) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(reviewEmail);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(reviewResolveMsg);
    }
  }
  
}
