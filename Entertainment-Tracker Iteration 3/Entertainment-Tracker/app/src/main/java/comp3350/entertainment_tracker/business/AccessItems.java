package comp3350.entertainment_tracker.business;

import java.util.ArrayList;

import comp3350.entertainment_tracker.application.Main;
import comp3350.entertainment_tracker.application.Services;
import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.persistence.DataAccess;

public class AccessItems {
	public enum ReturnResult {
		SUCCESS, ITEM_ALREADY_IN_LIST, ADDING_WATCHED_ITEM_TO_WISH_LIST
	}

    private DataAccess dataAccess;

    public AccessItems() { dataAccess = Services.getDataAccess(Main.dbName); }

    public ArrayList<EntertainmentItem> getFullItemList() { return dataAccess.getFullItemList(); }
    public ArrayList<EntertainmentItem> getWatchedHistory() { return dataAccess.getWatchedHistory(); }
	public ArrayList<EntertainmentItem> getWishList() { return dataAccess.getWishList(); }
	public float getUserRating(EntertainmentItem entertainmentItem) {return dataAccess.getUserRating(entertainmentItem);}
	public ArrayList<EntertainmentItem> getRandomItem(String name) {return dataAccess.getRandomItem(name);}

    public void setUserRating(EntertainmentItem item, float rating) {dataAccess.setUserRating(item, rating); }

	public ReturnResult insertWatchedHistory(EntertainmentItem item) {
		ReturnResult result = ReturnResult.SUCCESS;
		ArrayList<EntertainmentItem> watchedHistory = dataAccess.getWatchedHistory();
		ArrayList<EntertainmentItem> wishList = dataAccess.getWishList();

		if (!watchedHistory.contains(item)) {
			dataAccess.insertWatchedHistory(item);

			if (wishList.contains(item))
				removeWishList(item);
		}
		else
			result = ReturnResult.ITEM_ALREADY_IN_LIST;

		return result;
    }

    public ReturnResult insertWishList(EntertainmentItem item) {
		ReturnResult result = ReturnResult.SUCCESS;
		ArrayList<EntertainmentItem> wishList = dataAccess.getWishList();
		ArrayList<EntertainmentItem> watchedHistory = dataAccess.getWatchedHistory();

		if (watchedHistory.contains(item))
			result = ReturnResult.ADDING_WATCHED_ITEM_TO_WISH_LIST;
		else if (!wishList.contains(item))
			dataAccess.insertWishList(item);
		else
			result = ReturnResult.ITEM_ALREADY_IN_LIST;

		return result;
    }

    public void removeWatchedHistory(EntertainmentItem item) { dataAccess.removeWatchedHistory(item); }
	public void removeWishList(EntertainmentItem item) { dataAccess.removeWishList(item); }

    public ArrayList<EntertainmentItem> getBooks() { return dataAccess.getBookList(); }
    public ArrayList<EntertainmentItem> getTVShows() { return dataAccess.getTVShowList(); }
    public ArrayList<EntertainmentItem> getMovies() { return dataAccess.getMovieList(); }
}
