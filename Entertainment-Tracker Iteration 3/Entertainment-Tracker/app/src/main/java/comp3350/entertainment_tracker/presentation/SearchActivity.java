package comp3350.entertainment_tracker.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.business.ItemSearch;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class SearchActivity extends AppCompatActivity {
	public final static String SEARCH_QUERY = "comp3350.entertainment_tracker.SearchActivity.extra.SEARCH_QUERY";

	private final static int ROOT_VIEW_ID = View.generateViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		RelativeLayout rootView = new RelativeLayout(getApplicationContext());
		rootView.setId(ROOT_VIEW_ID);
		setContentView(rootView);

        Bundle extras = getIntent().getExtras();
        String searchQuery = extras.getString(SEARCH_QUERY);

		this.setTitle("Search Results for '"+searchQuery+"'");

		AccessItems accessItems = new AccessItems();
		ArrayList<EntertainmentItem> searchList = accessItems.getFullItemList();
		ArrayList<EntertainmentItem> matchingList = ItemSearch.searchListForSubstring(searchList, searchQuery);
		FragmentFactory.createItemGridFragment(this, ROOT_VIEW_ID, matchingList);
    }
}