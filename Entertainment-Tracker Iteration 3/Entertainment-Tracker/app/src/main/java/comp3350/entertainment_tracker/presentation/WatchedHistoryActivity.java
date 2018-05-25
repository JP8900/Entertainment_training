package comp3350.entertainment_tracker.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class WatchedHistoryActivity extends AppCompatActivity implements ItemGridFragment.OnAddToListButtonListener {

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
		itemGridFragment = FragmentFactory.createItemGridFragment(this, ROOT_VIEW_ID, accessItems.getWatchedHistory());
    }

	@Override
	public void onAddToWishListClick(View view) {
		EntertainmentItemButton.onAddToWishListClick(view);
	}

	@Override
	public void onAddToWatchedHistoryClick(View view) {
		EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();

		ArrayList<EntertainmentItem> watchedList = accessItems.getWatchedHistory();
		EntertainmentItem itemToDelete = (EntertainmentItem)itemButton.getTag();

		if (itemToDelete != null) {
			if (watchedList.contains(itemToDelete)) {
				Toast.makeText(getApplicationContext(), itemToDelete.getItemName() + " removed from Watched List", Toast.LENGTH_SHORT).show();
				accessItems.removeWatchedHistory(itemToDelete);
				itemGridFragment.removeItem(itemToDelete);
			}
			else
				Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
		}
	}
}
