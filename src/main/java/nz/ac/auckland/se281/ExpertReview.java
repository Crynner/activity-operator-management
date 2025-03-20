package nz.ac.auckland.se281;

import java.util.Map;
import java.util.ArrayList;
import nz.ac.auckland.se281.Types.ReviewType;

public class ExpertReview extends Review {
  private boolean reviewRecommend;
  private ArrayList<String> reviewImages = new ArrayList<>();

  ExpertReview(Map<String, String> reviewDetails, String id) {
    super(reviewDetails, id, ReviewType.EXPERT);
    reviewRecommend = (reviewDetails.get("recommend").equals("y"));
  }

  public void addImage(String image) {
    // add image to arraylist by name only 
    reviewImages.add(image);
  }

  @Override
  public void printReview() {
    super.printReview();
    if (reviewRecommend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
  }
}
