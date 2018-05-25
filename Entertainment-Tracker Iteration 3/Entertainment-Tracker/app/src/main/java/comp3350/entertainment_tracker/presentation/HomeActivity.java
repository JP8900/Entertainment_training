package comp3350.entertainment_tracker.presentation;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.application.Main;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private AccessItems accessItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        copyDatabaseToDevice();

        Main.startUp();

        accessItems = new AccessItems();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(HomeActivity.this ,this.mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        populateScrollView((LinearLayout)findViewById(R.id.movieLayout), accessItems.getMovies());
        populateScrollView((LinearLayout)findViewById(R.id.tvShowLayout), accessItems.getTVShows());
        populateScrollView((LinearLayout)findViewById(R.id.bookLayout), accessItems.getBooks());
    }

    @Override
	protected void onResume() {
		super.onResume();

		LinearLayout itemLayout = (LinearLayout)findViewById(R.id.movieLayout);
		updateItemButtonsInLayout(itemLayout);

		itemLayout = (LinearLayout)findViewById(R.id.tvShowLayout);
		updateItemButtonsInLayout(itemLayout);

		itemLayout = (LinearLayout)findViewById(R.id.bookLayout);
		updateItemButtonsInLayout(itemLayout);
	}

	private void updateItemButtonsInLayout(LinearLayout itemLayout) {
		ArrayList<EntertainmentItem> watchedHistory = accessItems.getWatchedHistory();
		ArrayList<EntertainmentItem> wishList = accessItems.getWishList();

		for (int i = 0; i < itemLayout.getChildCount(); i++) {
			View view = itemLayout.getChildAt(i);
			if (view instanceof EntertainmentItemButton) {
				EntertainmentItemButton itemButton = (EntertainmentItemButton)view;
				EntertainmentItem item = (EntertainmentItem)itemButton.getTag();

				if (watchedHistory.contains(item))
					itemButton.setButtonState(EntertainmentItemButton.WATCHED_BUTTON_ID, true);
				else
					itemButton.setButtonState(EntertainmentItemButton.WATCHED_BUTTON_ID, false);

				if (wishList.contains(item))
					itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, true);
				else
					itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, false);
			}
		}
	}

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    private void populateScrollView(LinearLayout itemLayout, ArrayList<EntertainmentItem> itemList) {
		final int SCROLL_VIEW_LIMIT = 10000;

		int loopCount = itemList.size() < SCROLL_VIEW_LIMIT ? itemList.size() : SCROLL_VIEW_LIMIT;

		for (int i = 0; i < loopCount; i++) {
            EntertainmentItem item = itemList.get(i);

            String imageName = item.getItemImage();

			EntertainmentItemButton newItem = new EntertainmentItemButton(getApplicationContext(), imageName);
            
            itemLayout.addView(newItem);
            ViewGroup.MarginLayoutParams marginLayoutParameters = newItem.getLayoutProperties();
            newItem.setLayoutParams(new LinearLayout.LayoutParams(marginLayoutParameters));

			newItem.setOnClickWishListListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
					Boolean isActive = (Boolean)view.getTag();
					itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, !isActive);

					EntertainmentItemButton.onAddToWishListClick(view);
				}
			});

			newItem.setOnClickWatchedHistoryListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
					Boolean isActive = (Boolean)view.getTag();
					itemButton.setButtonState(EntertainmentItemButton.WATCHED_BUTTON_ID, !isActive);

					EntertainmentItemButton.onAddToWatchedHistoryClick(view);
				}
			});

            newItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) { itemClicked(view); }
            });

            newItem.setTag(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    Intent searchIntent = new Intent(HomeActivity.this, SearchActivity.class);
                    searchIntent.putExtra(SearchActivity.SEARCH_QUERY, query);
                    startActivity(searchIntent);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) { return false; }
            };
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    private void itemClicked(View view) {
		Intent informationIntent = new Intent(this, InformationActivity.class);
		EntertainmentItem item = (EntertainmentItem) view.getTag();

		informationIntent.putExtra(InformationActivity.IMAGE_TAG, item.getItemImage());
		startActivity(informationIntent);
    }

    public void onClickToWatched(MenuItem menuItem) {
        mDrawerLayout.closeDrawer(Gravity.START);

        Intent watchedIntent = new Intent(this, WatchedHistoryActivity.class);
        startActivity(watchedIntent);
    }

    public void onClickToWishList(MenuItem menuItem) {
		mDrawerLayout.closeDrawer(Gravity.START);
		Intent wishListIntent = new Intent(this, WishListActivity.class);
		startActivity(wishListIntent);
	}

    public void onClickToCatalog(View view) {
        Intent catalogIntent = new Intent(this, CatalogActivity.class);

        ArrayList<EntertainmentItem> catalogList = null;
		String catalogTitle = null;

        switch(view.getId()) {
            case R.id.buttonMovies:
            	catalogList = accessItems.getMovies();
                catalogTitle = "movie";
                break;
            case R.id.buttonBooks:
            	catalogList = accessItems.getBooks();
                catalogTitle = "book";
                break;
            case R.id.buttonTVShows:
            	catalogList = accessItems.getTVShows();
                catalogTitle = "tv";
                break;
        }

        catalogIntent.putExtra(CatalogActivity.CATALOG_TITLE, catalogTitle);
		catalogIntent.putExtra(CatalogActivity.CATALOG_LIST, catalogList);
        startActivity(catalogIntent);
    }
}
