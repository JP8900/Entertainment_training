package comp3350.entertainment_tracker.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import comp3350.entertainment_tracker.objects.EntertainmentItem;


public class Sorter {
	//corresponds to the position of the A-Z, Z-A or Rating element in the view
    private final static int TITLE_ASCENDING = 1;
	private final static int TITLE_DESCENDING = 2;
    private final static int RATING_HIGH = 3;

    public static int sortOrder;

    public Sorter() {}

    public ArrayList<EntertainmentItem> sortItems(ArrayList<EntertainmentItem> list, int order) {
        sortOrder = order;

        if(!list.isEmpty())
            Collections.sort(list, Sorter.EntertainmentTitle);

        return list;
    }

    public static Comparator<EntertainmentItem> EntertainmentTitle = new Comparator<EntertainmentItem>() {
        public int compare(EntertainmentItem item1, EntertainmentItem item2) {
            int result = 0;

            String itemName = item1.getItemName().toUpperCase();
            String itemName2 = item2.getItemName().toUpperCase();
            Float itemRating = item1.getAvgRating();
            Float itemRating2 = item2.getAvgRating();

            switch (sortOrder) {
                case TITLE_DESCENDING:
                    result = itemName2.compareTo(itemName);
                    break;
                case TITLE_ASCENDING:
                    result = itemName.compareTo(itemName2);
                    break;
                case RATING_HIGH:
                    result = itemRating2.compareTo(itemRating);
                    break;
            }

            return result;

        }
    };
}
