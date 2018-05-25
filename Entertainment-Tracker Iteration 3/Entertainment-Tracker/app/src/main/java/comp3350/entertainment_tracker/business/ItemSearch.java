package comp3350.entertainment_tracker.business;

import java.util.ArrayList;

import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class ItemSearch {
	public static ArrayList<EntertainmentItem> searchListForSubstring(ArrayList<EntertainmentItem> searchList, String substring) {
		ArrayList<EntertainmentItem> matchingItems = new ArrayList<>();

		if (searchList != null && substring != null && !substring.equals("")) {
			for (int i = 0; i < searchList.size(); i++) {
				EntertainmentItem currentItem = searchList.get(i);
				String currentItemName = searchList.get(i).getItemName().toLowerCase();

				if (currentItemName.contains(substring.toLowerCase()))
					matchingItems.add(currentItem);
			}
		}
		return matchingItems;
	}
}
