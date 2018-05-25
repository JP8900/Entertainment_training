package comp3350.entertainment_tracker.presentation;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.business.Sorter;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class ItemGridFragment extends Fragment {
	public final static String LIST_ARGUMENT = "list";
	public final static int MAX_COLUMNS = 4;

	private ArrayList<EntertainmentItem> list;
    private ArrayList<EntertainmentItem> defaultList;

	public interface OnAddToListButtonListener {
		void onAddToWishListClick(View view);
		void onAddToWatchedHistoryClick(View view);
	}

	public ItemGridFragment() {}

	@Override
	public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Serializable arguments = getArguments().getSerializable(LIST_ARGUMENT);
		if (arguments instanceof ArrayList)
			list = (ArrayList)arguments;
		else
			list = new ArrayList<>();

        defaultList = new ArrayList<>(list);

		recreateViews();

		Spinner spinner = new Spinner(getContext());
		((RelativeLayout)this.getView().findViewById(R.id.rootView)).addView(spinner);

		ArrayAdapter<CharSequence> spinnerAdapter =
				ArrayAdapter.createFromResource(getContext(), R.array.sort_by, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(spinnerAdapter);

		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Sorter sort = new Sorter();
				if (position == 0)
				    list = new ArrayList<>(defaultList);
                else
				    list = sort.sortItems(list, position);

				recreateViews();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_item_grid, container, false);
	}

	public void removeItem(EntertainmentItem item) {
		if (list.contains(item)) {
			list.remove(item);
			recreateViews();
		}
	}

	private void recreateViews() {
		AccessItems accessItems = new AccessItems();
		ArrayList<EntertainmentItem> watchedHistory = accessItems.getWatchedHistory();
		ArrayList<EntertainmentItem> wishList = accessItems.getWishList();

		GridLayout gridLayout = (GridLayout)this.getView().findViewById(R.id.gridLayout);
		gridLayout.setColumnCount(MAX_COLUMNS);

		gridLayout.removeAllViews();

		for (int i = 0; i < list.size(); i++) {
			EntertainmentItem item = list.get(i);

			String imageName = item.getItemImage();
			EntertainmentItemButton newItem = new EntertainmentItemButton(getContext(), imageName);

			gridLayout.addView(newItem);
			ViewGroup.MarginLayoutParams marginLayoutParameters = newItem.getLayoutProperties();
			GridLayout.LayoutParams gridLayoutParameters = new GridLayout.LayoutParams(marginLayoutParameters);

			newItem.setLayoutParams(gridLayoutParameters);

			newItem.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent informationIntent = new Intent(view.getContext(), InformationActivity.class);
					EntertainmentItem item = (EntertainmentItem) view.getTag();

					informationIntent.putExtra(InformationActivity.IMAGE_TAG, item.getItemImage());
					startActivity(informationIntent);
				}
			});


			newItem.setOnClickWatchedHistoryListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
					Boolean isActive = (Boolean)view.getTag();
					itemButton.setButtonState(EntertainmentItemButton.WATCHED_BUTTON_ID, !isActive);

					if (getActivity() instanceof OnAddToListButtonListener)
						((OnAddToListButtonListener)getActivity()).onAddToWatchedHistoryClick(view);
					else
						EntertainmentItemButton.onAddToWatchedHistoryClick(view);
				}
			});

			newItem.setOnClickWishListListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();
					Boolean isActive = (Boolean)view.getTag();
					itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, !isActive);

					if (getActivity() instanceof OnAddToListButtonListener)
						((OnAddToListButtonListener)getActivity()).onAddToWishListClick(view);
					else
						EntertainmentItemButton.onAddToWishListClick(view);
				}
			});

			newItem.setTag(item);

			if (watchedHistory.contains(item))
				newItem.setButtonState(EntertainmentItemButton.WATCHED_BUTTON_ID, true);
			else if (wishList.contains(item))
				newItem.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, true);
		}
	}
}
