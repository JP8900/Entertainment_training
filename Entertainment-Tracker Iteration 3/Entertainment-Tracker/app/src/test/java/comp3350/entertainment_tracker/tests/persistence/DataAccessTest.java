package comp3350.entertainment_tracker.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;
import comp3350.entertainment_tracker.application.*;
import comp3350.entertainment_tracker.persistence.DataAccess;
import comp3350.entertainment_tracker.objects.EntertainmentItem;

public class DataAccessTest extends TestCase {

    private static String dbName = Main.dbName;

    public DataAccessTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
		Services.closeDataAccess();

        System.out.println("\nStarting Persistence test DataAccess (using stub)");

        // Use the following statement to run with the stub database
        // Services.createDataAccess(new DataAccessStub(dbName));

        dataAccessTest();

        System.out.println("Finished Persistence test DataAccess (using stub)");
    }

    public static void dataAccessTest() {
        ArrayList<EntertainmentItem> entertainmentItemList;
        EntertainmentItem entertainmentItem;

        DataAccess dataAccess = Services.createDataAccess(dbName);

        entertainmentItemList = dataAccess.getBookList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("1984",entertainmentItem.getItemName());

        entertainmentItemList = dataAccess.getMovieList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Antman", entertainmentItem.getItemName());


        entertainmentItemList = dataAccess.getTVShowList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Arrested Development", entertainmentItem.getItemName());

        entertainmentItemList = dataAccess.getRandomItem("Fahrenheit 451");
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Fahrenheit 451", entertainmentItem.getItemName());



        //Test watched list
        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE,"Deadpool",null,null,0);
        dataAccess.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Deadpool", entertainmentItem.getItemName());

        dataAccess.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertTrue(0 == entertainmentItemList.size());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_BOOK,"Twilight",null,null,0);
        dataAccess.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Twilight", entertainmentItem.getItemName());

        dataAccess.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertTrue(0 == entertainmentItemList.size());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW,"Silicon Valley",null,null,0);
        dataAccess.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Silicon Valley", entertainmentItem.getItemName());

        dataAccess.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = dataAccess.getWatchedHistory();
        assertTrue(0 == entertainmentItemList.size());


        //Test wish list
        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE,"Deadpool",null,null,0);
        dataAccess.insertWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Deadpool", entertainmentItem.getItemName());

        dataAccess.removeWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertTrue(0 == entertainmentItemList.size());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_BOOK,"Twilight",null,null,0);
        dataAccess.insertWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Twilight", entertainmentItem.getItemName());

        dataAccess.removeWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertTrue(0 == entertainmentItemList.size());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW,"Silicon Valley",null,null,0);
        dataAccess.insertWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertNotNull(entertainmentItemList);
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Silicon Valley", entertainmentItem.getItemName());

        dataAccess.removeWishList(entertainmentItem);
        entertainmentItemList = dataAccess.getWishList();
        assertTrue(0 == entertainmentItemList.size());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Captain Phillips", null, null, 0);
        dataAccess.setUserRating(entertainmentItem, 3.5f);
        entertainmentItemList = dataAccess.getRandomItem("Captain Phillips");

        if(entertainmentItemList.get(0) != null)
            entertainmentItem = entertainmentItemList.get(0);

        assertEquals(3.5f, entertainmentItem.getUserRating());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "1984", null, null, 0);
        dataAccess.setUserRating(entertainmentItem, 4.2f);
        entertainmentItemList = dataAccess.getRandomItem("1984");
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals(4.2f, entertainmentItem.getUserRating());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Community", null, null, 0);
        dataAccess.setUserRating(entertainmentItem, 1.5f);
        entertainmentItemList = dataAccess.getRandomItem("Community");
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals(1.5f, entertainmentItem.getUserRating());

        Services.closeDataAccess();
    }
}
