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
  
}
