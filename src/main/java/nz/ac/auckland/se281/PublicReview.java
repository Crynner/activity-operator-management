package nz.ac.auckland.se281;

import java.util.Map;

public class PublicReview extends Review{
  private boolean reviewAnon;

  PublicReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id);
    reviewAnon = (reviewDetails.get("anonymous").equals("y"));
  }
}
