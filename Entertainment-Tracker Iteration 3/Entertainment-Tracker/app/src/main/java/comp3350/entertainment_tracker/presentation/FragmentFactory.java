package comp3350.entertainment_tracker.presentation;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;

import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class FragmentFactory {
	public static ItemGridFragment createItemGridFragment(Activity activity, int rootViewID, ArrayList<EntertainmentItem> itemList) {
		FragmentManager fragmentManager = activity.getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		ItemGridFragment itemGridFragment = new ItemGridFragment();
		Bundle fragmentArguments = new Bundle();
		fragmentArguments.putSerializable(ItemGridFragment.LIST_ARGUMENT, itemList);
		itemGridFragment.setArguments(fragmentArguments);

		fragmentTransaction.add(rootViewID, itemGridFragment);
		fragmentTransaction.commit();

		return itemGridFragment;
	}
}
