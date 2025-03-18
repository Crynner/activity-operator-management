package nz.ac.auckland.se281;

import java.util.Map;

public class ExpertReview extends Review{
  public boolean reviewRecommend;

  ExpertReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id);
    reviewRecommend = (reviewDetails.get("recommend").equals("y"));
  }
}
