package comp3350.entertainment_tracker.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class InformationActivity extends AppCompatActivity {
    public final static String IMAGE_TAG = "comp3350.entertainment_tracker.InformationActivity.extra.Image_Tag";

    private AccessItems accessItems;
	private String itemImageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

		itemImageName = getIntent().getStringExtra(IMAGE_TAG);
		getIntent().removeExtra(IMAGE_TAG);

        initializeInformation();
    }

    private void initializeInformation() {
        accessItems = new AccessItems();
        ArrayList<EntertainmentItem> allItems = accessItems.getFullItemList();

        EntertainmentItem item = null;
        String itemTitle;

        TextView itemDescriptionView = (TextView) findViewById(R.id.itemDescription);
        ImageView itemImageView = (ImageView) findViewById(R.id.itemImage);

        String image;
        RatingBar avgRating = (RatingBar) findViewById(R.id.avgRating);
        RatingBar userRating = (RatingBar) findViewById(R.id.userRating);
        for(int i = 0; i < allItems.size() && item == null; i++) {
            if(allItems.get(i).getItemImage().equals(itemImageName)){
                item = allItems.get(i);
            }
        }

        avgRating.setRating(item.getAvgRating());
        userRating.setRating(item.getUserRating());

        image = item.getItemImage();
        itemTitle = item.getItemName();

        this.setTitle(itemTitle);

        itemImageView.setBackground(getDrawable(getResources().getIdentifier(image, "drawable", getPackageName())));
        itemDescriptionView.setText(item.getItemDescription());
    }

    protected void onDestroy() {
        super.onDestroy();
        accessItems = new AccessItems();
        ArrayList<EntertainmentItem> allItems = accessItems.getFullItemList();

        EntertainmentItem item = null;
        RatingBar userRating = (RatingBar) findViewById(R.id.userRating);
        for(int i = 0; i < allItems.size() && item == null; i++) {
            if(allItems.get(i).getItemImage().equals(itemImageName)){
                item = allItems.get(i);
            }
        }
        accessItems.setUserRating(item, userRating.getRating());
    }
}
