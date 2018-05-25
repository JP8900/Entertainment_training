package comp3350.entertainment_tracker.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class DataAccessObject implements DataAccess
{
	private final static String ALL_ITEMS_TABLE = "ITEMS";
	private final static String WATCHED_HISTORY_TABLE = "WATCHED";
	private final static String WISH_LIST_TABLE = "WATCHLIST";

	// NOTE: all the different fields in the above tables
	private final static String ITEM_TYPE = "ITEMTYPE";
	private final static String ITEM_NAME = "NAME";
	private final static String ITEM_IMAGE = "IMAGE";
	private final static String ITEM_DESCRIPTION = "DESCRIPTION";
	private final static String ITEM_AVGRATING = "AVGRATING";
    private final static String ITEM_USERRATING = "USERRATING";

	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;

	private String dbName;
	private String dbType;

	private int updateCount;

	public DataAccessObject(String dbName) { this.dbName = dbName; }

	public void open(String dbPath) {
		String url;
		try {
			// Setup for HSQL
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
			connection = DriverManager.getConnection(url, "SA", "");

			statement = connection.createStatement();
		}
		catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Opened " +dbType +" database " +dbPath);
	}

	public void close() {
		try {
			// commit all changes to the database
			String query = "shutdown compact";
			resultSet = statement.executeQuery(query);
			connection.close();
		}
		catch (Exception e) {
			processSQLError(e);
		}
		System.out.println("Closed " +dbType +" database " +dbName);
	}

	public String insertWishList(EntertainmentItem item) { return insertItemInTable(item, WISH_LIST_TABLE); }
	public String insertWatchedHistory(EntertainmentItem item) { return insertItemInTable(item, WATCHED_HISTORY_TABLE); }

	public String removeWatchedHistory(EntertainmentItem item) { return removeItemInTable(item, WATCHED_HISTORY_TABLE); }
	public String removeWishList(EntertainmentItem item) { return removeItemInTable(item, WISH_LIST_TABLE); }

//<<<<<<< .mine
//	public float getUserRating(EntertainmentItem theItem) {
//		float rating = 0;
//		String query;
//		String result = null;
//
//		try {
//			query = "SELECT * FROM ITEMS" + " WHERE NAME = '" + theItem.getItemName()+"'";
//			resultSet = statement.executeQuery(query);
//
//			while (resultSet.next()) {
//				EntertainmentItem item = createItemFromResultSet(resultSet);
//				rating = item.getUserRating();
//			}
//			resultSet.close();
//		}
//		catch (Exception e) {
//			result = processSQLError(e);
//		}
//
//		return rating;
//	}


	public float getUserRating(EntertainmentItem item) {
		float rating = -1;
		String itemName = item.getItemName();
		ArrayList<EntertainmentItem> itemList = getRandomItem(itemName);

		for (int i = 0; i < itemList.size() && rating == -1; i++) {
			if (itemName.equalsIgnoreCase(itemList.get(i).getItemName()))
				rating = itemList.get(i).getUserRating();
		}

		return rating;
	}

	public float getAvgRating(EntertainmentItem item) {
		float rating = -1;
		String itemName = item.getItemName();
		ArrayList<EntertainmentItem> itemList = getRandomItem(itemName);

		for (int i = 0; i < itemList.size() && rating == -1; i++) {
			if (itemName.equalsIgnoreCase(itemList.get(i).getItemName()))
				rating = itemList.get(i).getAvgRating();
		}

		return rating;
	}


	public ArrayList<EntertainmentItem> getBookList() { return getItemsOfType(EntertainmentItem.TYPE_BOOK); }
	public ArrayList<EntertainmentItem> getMovieList() { return getItemsOfType(EntertainmentItem.TYPE_MOVIE); }
	public ArrayList<EntertainmentItem> getTVShowList() { return getItemsOfType(EntertainmentItem.TYPE_TVSHOW); }

	public ArrayList<EntertainmentItem> getWatchedHistory() {
		ArrayList<EntertainmentItem> result = new ArrayList<>();
		getAllFromTable(result, WATCHED_HISTORY_TABLE);
		return result;
	}

	public ArrayList<EntertainmentItem> getWishList() {
		ArrayList<EntertainmentItem> planToWatchList = new ArrayList<>();
		getAllFromTable(planToWatchList, WISH_LIST_TABLE);
		return planToWatchList;
	}

	public ArrayList<EntertainmentItem> getFullItemList() {
		ArrayList<EntertainmentItem> result = new ArrayList<>();
		getAllFromTable(result, ALL_ITEMS_TABLE);
		return result;
	}

	public String setUserRating(EntertainmentItem item, float rating) {
        String query;
        String result = "";


        try {
			query = "UPDATE ITEMS SET USERRATING = " + rating + " WHERE NAME = '"+item.getItemName()+"'";
            statement.executeQuery(query);
			query = "UPDATE WATCHED SET USERRATING = " + rating + " WHERE NAME = '"+item.getItemName()+"'";
			statement.executeQuery(query);
			query = "UPDATE WATCHLIST SET USERRATING = " + rating + " WHERE NAME = '"+item.getItemName()+"'";
			statement.executeQuery(query);
        }
        catch (Exception e) {
            result = processSQLError(e);
        }
        return result;
    }

//<<<<<<< .mine
//	public ArrayList<EntertainmentItem> getRandomItem(String itemName) {
//		ArrayList<EntertainmentItem> entertainmentItems = new ArrayList<EntertainmentItem>();
//		String query;
//
//		try {
//			query = "SELECT * FROM ITEMS" + " WHERE NAME = '" + itemName+"'";
//			resultSet = statement.executeQuery(query);
//
//			while (resultSet.next()) {
//				EntertainmentItem item = createItemFromResultSet(resultSet);
//				entertainmentItems.add(item);
//
//			}
//			resultSet.close();
//		}
//		catch (Exception e) {
//			processSQLError(e);
//		}
//
//		return entertainmentItems;
//	}
//
//
//
//=======
	public ArrayList<EntertainmentItem> getRandomItem(String itemName) {
		ArrayList<EntertainmentItem> result = new ArrayList<>();
		getItemByName(result, ALL_ITEMS_TABLE, itemName);
		return result;
	}

	private String getItemByName(List<EntertainmentItem> itemList, final String TABLE, String itemName) {
		ArrayList<EntertainmentItem> fullList = new ArrayList<>();
		String result = getAllFromTable(fullList, TABLE);

		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getItemName().equalsIgnoreCase(itemName))
				itemList.add(fullList.get(i));
		}

		return result;
	}

	private String getAllFromTable(List<EntertainmentItem> itemList, final String TABLE) {
		String result = null;
		String query;

		try {
			query = "SELECT * FROM " + TABLE + " ORDER BY " + ITEM_NAME;
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				EntertainmentItem item = createItemFromResultSet(resultSet);
				itemList.add(item);
			}
			resultSet.close();
		}
		catch (Exception e) {
			result = processSQLError(e);
		}

		return result;
	}

    private EntertainmentItem createItemFromResultSet(ResultSet resultSet) {
		EntertainmentItem result = null;

		try {
			int itemType = Integer.parseInt(resultSet.getString(ITEM_TYPE));
			String itemName = resultSet.getString(ITEM_NAME);
			String itemImage = resultSet.getString(ITEM_IMAGE);
			String itemDescription = resultSet.getString(ITEM_DESCRIPTION);
			float itemAvgRating = Float.parseFloat(resultSet.getString(ITEM_AVGRATING));
            float itemUserRating = Float.parseFloat(resultSet.getString(ITEM_USERRATING));

			result = new EntertainmentItem(itemType, itemName, itemImage, itemDescription, itemAvgRating, itemUserRating);
		}
		catch (Exception e) {
			processSQLError(e);
		}

		return result;
	}

    private String insertItemInTable(EntertainmentItem item, final String TABLE) {
		String result;

		try {
			String valuesQuery = item.getItemType()
					+", '" +item.getItemName()
					+"', '" +item.getItemImage()
					+"', '" +item.getItemDescription()
					+"', " +item.getAvgRating()
					+ "," + item.getUserRating();
			String fullQuery = "Insert into " + TABLE + " Values(" + valuesQuery +")";

			updateCount = statement.executeUpdate(fullQuery);
			result = checkWarning(statement, updateCount);
		}
		catch (Exception e) {
			result = processSQLError(e);
		}

		return result;
	}

	private String removeItemInTable(EntertainmentItem item, final String TABLE) {
		String result;
		String itemName;

		try {
			itemName = item.getItemName();
			String query = "Delete from " + TABLE + " WHERE Name='"+ itemName +"'";
			updateCount = statement.executeUpdate(query);
			result = checkWarning(statement, updateCount);
		}
		catch (Exception e) {
			result = processSQLError(e);
		}

		return result;
	}

    private ArrayList<EntertainmentItem> getItemsOfType(int itemType) {
        ArrayList<EntertainmentItem> allItems = new ArrayList<>();
        getAllFromTable(allItems, ALL_ITEMS_TABLE);
        ArrayList<EntertainmentItem> result = new ArrayList<>();

        for (int i = 0; i < allItems.size(); i++) {
            if (allItems.get(i).getItemType() == itemType)
                result.add(allItems.get(i));
        }

        return result;
    }

	private String checkWarning(Statement statement, int updateCount) {
		String result;

		result = null;
		try {
			SQLWarning warning = statement.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e) {
			result = processSQLError(e);
		}
		if (updateCount != 1) {
			result = "Tuple not inserted correctly.";
		}
		return result;
	}

	private String processSQLError(Exception e) {
		String result = "*** SQL Error: " + e.getMessage();

		// Remember, this will NOT be seen by the user!
		e.printStackTrace();

		return result;
	}


}
