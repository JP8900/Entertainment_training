package comp3350.entertainment_tracker.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class WishListActivity extends AppCompatActivity implements ItemGridFragment.OnAddToListButtonListener {

	private final static int ROOT_VIEW_ID = View.generateViewId();

	private AccessItems accessItems;
	private ItemGridFragment itemGridFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RelativeLayout rootView = new RelativeLayout(getApplicationContext());
		rootView.setId(ROOT_VIEW_ID);
		setContentView(rootView);

		accessItems = new AccessItems();
		itemGridFragment = FragmentFactory.createItemGridFragment(this, ROOT_VIEW_ID, accessItems.getWishList());
	}

	@Override
	public void onAddToWishListClick(View view) {
		EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
		EntertainmentItem item = (EntertainmentItem)itemButton.getTag();

		accessItems.removeWishList(item);
		itemGridFragment.removeItem(item);

		Toast.makeText(getApplicationContext(),
				item.getItemName() + " was removed from your wish list.", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onAddToWatchedHistoryClick(View view) {
		EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
		EntertainmentItem item = (EntertainmentItem)itemButton.getTag();

		accessItems.removeWishList(item);
		accessItems.insertWatchedHistory(item);
		itemGridFragment.removeItem(item);

		Toast.makeText(getApplicationContext(),
				item.getItemName() + " was added to your watched history.", Toast.LENGTH_SHORT).show();
	}
}
