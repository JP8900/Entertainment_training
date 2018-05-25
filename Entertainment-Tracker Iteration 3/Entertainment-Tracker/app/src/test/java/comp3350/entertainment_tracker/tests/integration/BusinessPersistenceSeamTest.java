package comp3350.entertainment_tracker.tests.integration;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.entertainment_tracker.application.Main;
import comp3350.entertainment_tracker.application.Services;
import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.tests.persistence.DataAccessStub;

public class BusinessPersistenceSeamTest extends TestCase{

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void testAccessItems()
    {
        AccessItems accessItems;
        EntertainmentItem entertainmentItem;
        EntertainmentItem entertainmentItem2;
        ArrayList<EntertainmentItem> entertainmentItemList = null;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessItems to persistence");

        Services.createDataAccess(new DataAccessStub(Main.dbName));

        accessItems = new AccessItems();

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "The Avengers", null, null, 0);
        accessItems.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue("The Avengers".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue(entertainmentItemList.isEmpty());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "A Series Of Unfortunate Events", null, null, 0);
        accessItems.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue("A Series Of Unfortunate Events".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue(entertainmentItemList.isEmpty());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Black Mirror", null, null, 0);
        accessItems.insertWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue("Black Mirror".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWatchedHistory(entertainmentItem);
        entertainmentItemList = accessItems.getWatchedHistory();
        assertTrue(entertainmentItemList.isEmpty());



        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE,"The Matrix",null, null, 0);
        accessItems.insertWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue("The Matrix".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue(entertainmentItemList.isEmpty());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_BOOK,"The Hunger Games",null, null, 0);
        accessItems.insertWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue("The Hunger Games".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue(entertainmentItemList.isEmpty());

        entertainmentItem = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW,"The Walking Dead",null, null, 0);
        accessItems.insertWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue("The Walking Dead".equals(entertainmentItemList.get(0).getItemName()));

        accessItems.removeWishList(entertainmentItem);
        entertainmentItemList = accessItems.getWishList();
        assertTrue(entertainmentItemList.isEmpty());



        entertainmentItemList = accessItems.getBooks();
        assertFalse(entertainmentItemList.isEmpty());
        entertainmentItem = entertainmentItemList.get(0);
        accessItems.setUserRating(entertainmentItem, 3.4f);
        entertainmentItem.setUserRating(accessItems.getUserRating(entertainmentItem));
        assertEquals(3.4f, entertainmentItem.getUserRating());

        entertainmentItemList = accessItems.getMovies();
        assertFalse(entertainmentItemList.isEmpty());
        entertainmentItem = entertainmentItemList.get(0);
        accessItems.setUserRating(entertainmentItem, 4.2f);
        entertainmentItem.setUserRating(accessItems.getUserRating(entertainmentItem));
        assertEquals(4.2f, entertainmentItem.getUserRating());

        entertainmentItemList = accessItems.getTVShows();
        assertFalse(entertainmentItemList.isEmpty());
        entertainmentItem = entertainmentItemList.get(0);
        accessItems.setUserRating(entertainmentItem, 1.2f);
        entertainmentItem.setUserRating(accessItems.getUserRating(entertainmentItem));
        assertEquals(1.2f, entertainmentItem.getUserRating());

        entertainmentItemList = accessItems.getRandomItem("Fahrenheit 451");
        entertainmentItem = entertainmentItemList.get(0);
        assertEquals("Fahrenheit 451", entertainmentItem.getItemName());



        entertainmentItemList = accessItems.getMovies();

        entertainmentItem = entertainmentItemList.get(0);
        entertainmentItem2 = entertainmentItemList.get(0);
        assertTrue(entertainmentItem.equals(entertainmentItem2));

        entertainmentItem = entertainmentItemList.get(1);
        entertainmentItem2 = entertainmentItemList.get(2);
        assertFalse(entertainmentItem.equals(entertainmentItem2));

        entertainmentItemList = accessItems.getBooks();

        entertainmentItem = entertainmentItemList.get(0);
        entertainmentItem2 = entertainmentItemList.get(0);
        assertTrue(entertainmentItem.equals(entertainmentItem2));

        entertainmentItem = entertainmentItemList.get(1);
        entertainmentItem2 = entertainmentItemList.get(2);
        assertFalse(entertainmentItem.equals(entertainmentItem2));

        entertainmentItemList = accessItems.getTVShows();

        entertainmentItem = entertainmentItemList.get(0);
        entertainmentItem2 = entertainmentItemList.get(0);
        assertTrue(entertainmentItem.equals(entertainmentItem2));

        entertainmentItem = entertainmentItemList.get(1);
        entertainmentItem2 = entertainmentItemList.get(2);
        assertFalse(entertainmentItem.equals(entertainmentItem2));


        entertainmentItemList = accessItems.getFullItemList();
        assertFalse(entertainmentItemList.isEmpty());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessStudents to persistence");
    }
}
