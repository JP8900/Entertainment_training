package comp3350.entertainment_tracker.persistence;

/* WARNING: if you make changes to the script, database location, or database name,
            you MUUUUST (!!!) uninstall the app on the phone and reinstall, otherwise the local database will stay! */
import java.util.ArrayList;

import comp3350.entertainment_tracker.objects.EntertainmentItem;

public interface DataAccess
{
	void open(String string);

	void close();

	String insertWatchedHistory(EntertainmentItem item);
	String insertWishList(EntertainmentItem item);
	String removeWatchedHistory(EntertainmentItem item);
	String removeWishList(EntertainmentItem item);

	String setUserRating(EntertainmentItem item, float rating);

	ArrayList<EntertainmentItem> getBookList();
	ArrayList<EntertainmentItem> getMovieList();
	ArrayList<EntertainmentItem> getTVShowList();

	ArrayList<EntertainmentItem> getWatchedHistory();
	ArrayList<EntertainmentItem> getWishList();

	ArrayList<EntertainmentItem> getFullItemList();

	ArrayList<EntertainmentItem> getRandomItem(String name);

	float getUserRating(EntertainmentItem item);
    float getAvgRating(EntertainmentItem item);
}
