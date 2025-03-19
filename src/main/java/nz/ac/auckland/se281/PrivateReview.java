package nz.ac.auckland.se281;

import java.util.Map;

public class PrivateReview extends Review{
  private boolean reviewFollowup;
  private String reviewEmail;

  PrivateReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id);
    reviewFollowup = (reviewDetails.get("followup").equals("y"));
    reviewEmail = reviewDetails.get("email");
    
  }

  @Override
  public void printReview() {
    super.printReview();
    if (reviewFollowup) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(reviewEmail);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage("-");
    }
  }
  
}
