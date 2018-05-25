package comp3350.entertainment_tracker.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class CatalogActivity extends AppCompatActivity {
    public final static String CATALOG_TITLE = "comp335.entertainment_tracker.CatalogActivity.extra.CATALOG_TITLE";
	public final static String CATALOG_LIST = "comp335.entertainment_tracker.CatalogActivity.extra.CATALOG_LIST";

	private final static int ROOT_VIEW_ID = View.generateViewId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		RelativeLayout rootView = new RelativeLayout(getApplicationContext());
		rootView.setId(ROOT_VIEW_ID);
        setContentView(rootView);

		String title = getIntent().getStringExtra(CATALOG_TITLE);
		ArrayList<EntertainmentItem> list = (ArrayList<EntertainmentItem>)getIntent().getSerializableExtra(CATALOG_LIST);

		this.setTitle(title);

		FragmentFactory.createItemGridFragment(this, ROOT_VIEW_ID, list);
    }
}