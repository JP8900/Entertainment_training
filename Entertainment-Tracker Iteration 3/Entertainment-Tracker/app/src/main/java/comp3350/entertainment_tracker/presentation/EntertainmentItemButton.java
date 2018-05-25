package comp3350.entertainment_tracker.presentation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class EntertainmentItemButton extends RelativeLayout {
	public final static int WATCHED_BUTTON_ID = View.generateViewId();
	public final static int WISH_BUTTON_ID = View.generateViewId();

	private final static int COLOUR_ACTIVE = Color.GREEN;
	private final static int COLOUR_INACTIVE = Color.RED;

	private final static int WISH_LIST_ICON = R.drawable.ic_wish_list;
	private final static int WATCHED_HISTORY_ICON = R.drawable.ic_watched_history;

	private final static int BUTTON_SIZE = 80;
	private final static int BUTTON_MARGIN = 5;

	public EntertainmentItemButton(Context context, String imageName) {
        super(context);

        int imageResourceID = getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        setBackgroundResource(imageResourceID);

		addImageButton(context, WATCHED_BUTTON_ID);
		addImageButton(context, WISH_BUTTON_ID);
    }

    public void setOnClickWatchedHistoryListener(OnClickListener onClickListener) {
		View watchedHistoryButton = findViewById(WATCHED_BUTTON_ID);
		if (watchedHistoryButton != null)
			watchedHistoryButton.setOnClickListener(onClickListener);
	}

	public void setOnClickWishListListener(OnClickListener onClickListener) {
		View wishListButton = findViewById(WISH_BUTTON_ID);
		if (wishListButton != null)
			wishListButton.setOnClickListener(onClickListener);
	}

	public void setButtonState(int buttonID, boolean newState) {
		ImageButton buttonView = (ImageButton)findViewById(buttonID);

		if (buttonView != null) {
			if (newState) {
				buttonView.setBackgroundColor(COLOUR_ACTIVE);
				Drawable background = buttonView.getBackground();
				background.setAlpha(60);
			}
			else {
				buttonView.setBackgroundColor(COLOUR_INACTIVE);
				Drawable background = buttonView.getBackground();
				background.setAlpha(0);
			}

			buttonView.setTag(newState);
		}
	}

	public static void onAddToWatchedHistoryClick(View view) {
		AccessItems accessItems = new AccessItems();
		Boolean isActive = (Boolean)view.getTag();
		EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();

		if (isActive != null && itemButton != null) {
			EntertainmentItem item = (EntertainmentItem)itemButton.getTag();

			if (isActive) {
				accessItems.insertWatchedHistory(item);
				Toast.makeText(view.getContext(), item.getItemName() + " added to your watched history", Toast.LENGTH_SHORT).show();
			}
			else {
				accessItems.removeWatchedHistory(item);
				Toast.makeText(view.getContext(), item.getItemName() + " removed from your watched history", Toast.LENGTH_SHORT).show();
			}

			itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, false);
		}
	}

	public static void onAddToWishListClick(View view) {
		AccessItems accessItems = new AccessItems();
		Boolean isActive = (Boolean)view.getTag();
		EntertainmentItemButton itemButton = (EntertainmentItemButton)view.getParent();

		if (isActive != null && itemButton != null) {
			EntertainmentItem item = (EntertainmentItem)itemButton.getTag();

			if (!isActive) {
				accessItems.removeWishList(item);
				Toast.makeText(view.getContext(), item.getItemName() + " removed from your wish list", Toast.LENGTH_SHORT).show();
			}
			else if (accessItems.insertWishList(item) == AccessItems.ReturnResult.ADDING_WATCHED_ITEM_TO_WISH_LIST) {
				Toast.makeText(view.getContext(), "Can't add item: " + item.getItemName() + " because it is in your watched history", Toast.LENGTH_LONG).show();
				itemButton.setButtonState(EntertainmentItemButton.WISH_BUTTON_ID, false);
			}
			else
				Toast.makeText(view.getContext(), item.getItemName() + " added to your wish list", Toast.LENGTH_SHORT).show();
		}
	}

    public ViewGroup.MarginLayoutParams getLayoutProperties() {
		int layoutHeight = (int)getResources().getDimension(R.dimen.entertainmentitem_height);
		int layoutWidth = (int)getResources().getDimension(R.dimen.entertainmentitem_width);

		ViewGroup.MarginLayoutParams result = new ViewGroup.MarginLayoutParams(layoutWidth, layoutHeight);

		int marginLeft = (int)getResources().getDimension(R.dimen.entertainmentitem_margin_left);
		int marginRight = (int)getResources().getDimension(R.dimen.entertainmentitem_margin_right);
		int marginTop = (int)getResources().getDimension(R.dimen.entertainmentitem_margin_top);
		int marginBottom = (int)getResources().getDimension(R.dimen.entertainmentitem_margin_bottom);

		result.setMargins(marginLeft, marginTop, marginRight, marginBottom);

		return result;
	}

	private ImageButton addImageButton(Context context, int id) {
		ImageButton result = new ImageButton(context);
		result.setId(id);

		int verticalAlignment = RelativeLayout.ALIGN_PARENT_BOTTOM;
		int horizontalAlignment = 0;
		int marginLeft = 0, marginTop = 0, marginRight = 0, marginBottom = 0;

		if (id == WISH_BUTTON_ID) {
			result.setImageResource(WISH_LIST_ICON);
			horizontalAlignment = RelativeLayout.ALIGN_PARENT_LEFT;
			marginLeft = BUTTON_MARGIN;
			marginBottom = BUTTON_MARGIN;
		}
		else if (id == WATCHED_BUTTON_ID) {
			result.setImageResource(WATCHED_HISTORY_ICON);
			horizontalAlignment = RelativeLayout.ALIGN_PARENT_RIGHT;
			marginRight = BUTTON_MARGIN;
			marginBottom = BUTTON_MARGIN;
		}

		result.setBackgroundColor(COLOUR_INACTIVE);
		Drawable background = result.getBackground();
		background.setAlpha(0);
		result.setTag(false);

		this.addView(result);

		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) result.getLayoutParams();
		layoutParams.addRule(verticalAlignment);
		layoutParams.addRule(horizontalAlignment);
		layoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
		layoutParams.height = BUTTON_SIZE;
		result.setLayoutParams(layoutParams);

		return result;
	}
}
