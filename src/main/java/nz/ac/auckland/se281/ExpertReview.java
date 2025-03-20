package nz.ac.auckland.se281;

import java.util.Map;

import nz.ac.auckland.se281.Types.ReviewType;

public class ExpertReview extends Review{
  public boolean reviewRecommend;

  ExpertReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.EXPERT);
    reviewRecommend = (reviewDetails.get("recommend").equals("y"));
  }

  @Override
  public void printReview() {
    super.printReview();
    MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
  }
}
