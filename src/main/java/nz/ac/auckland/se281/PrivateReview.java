package nz.ac.auckland.se281;

import java.util.Map;
import nz.ac.auckland.se281.Types.ReviewType;

public class PrivateReview extends Review {
  private boolean reviewFollowup;
  private String reviewEmail;
  private String reviewResolveMsg = "-";

  PrivateReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.PRIVATE);
    // review constructor including emial and followup details
    reviewFollowup = (reviewDetails.get("followup").equals("y"));
    reviewEmail = reviewDetails.get("email");
  }

  public void resolveReview(String resolutionMsg) {
    // change message and need-to-followup-requirement
    reviewResolveMsg = resolutionMsg;
    reviewFollowup = false;
  }

  @Override
  public void printReview() {
    super.printReview();
    // depending on whether review has been resolved or not, change followup message
    if (reviewFollowup) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(reviewEmail);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(reviewResolveMsg);
    }
  }
  
}
