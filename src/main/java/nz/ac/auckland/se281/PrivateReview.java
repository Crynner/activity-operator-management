package nz.ac.auckland.se281;

public class PrivateReview extends Review{
  private boolean reviewFollowup;
  private String reviewEmail;

  PrivateReview(String[] reviewDetails, String id) {
    super(reviewDetails, id);
    
  }
  
}
