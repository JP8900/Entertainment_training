package comp3350.entertainment_tracker.acceptance;

import com.robotium.solo.Solo;

import comp3350.entertainment_tracker.business.AccessItems;
import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.presentation.EntertainmentItemButton;
import comp3350.entertainment_tracker.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

public class WatchedListTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public WatchedListTest()
    {
        super(HomeActivity.class);
    }

    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());

        // Disable this for full acceptance test
        // System.out.println("Injecting stub database.");
        // Services.createDataAccess(new DataAccessStub(Main.dbName));
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testRegularWatched() {
        EntertainmentItem tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Deadpool", null, null, 0);

        solo.waitForActivity("HomeActivity");

        //doing this twice will reset back to where it was initially.
        for (int i = 0; i < 2; i++) {
            EntertainmentItemButton button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Captain Phillips", null, null, 0);
            button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            tag = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Breaking Bad", null, null, 0);
            button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            tag = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Arrested Development", null, null, 0);
            button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            tag = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "1984", null, null, 0);
            button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            tag = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "Animal Farm", null, null, 0);
            button = (EntertainmentItemButton) solo.getView(tag);
            solo.clickOnView(button.getChildAt(0));

            solo.clickOnActionBarHomeButton();
            solo.clickOnMenuItem("Watched");
            solo.assertCurrentActivity("Expected activity WatchedHistoryActivity", "WatchedHistoryActivity");
            solo.goBack();
        }
    }

    public void testSameItemWatched(){
        EntertainmentItem tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Arrival", null, null, 0);
        AccessItems accessItems = new AccessItems();
        ArrayList<EntertainmentItem> watchedHistory = accessItems.getWatchedHistory();
        boolean inList = watchedHistory.contains(tag);

        solo.waitForActivity("HomeActivity");

        EntertainmentItemButton button = (EntertainmentItemButton) solo.getView(tag);

        for(int i=0; i<10; i++)
            solo.clickOnView(button.getChildAt(0));

        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem("Watched");
        solo.assertCurrentActivity("Expected activity WatchedHistoryActivity", "WatchedHistoryActivity");
        if(inList)
            assertTrue(accessItems.getWatchedHistory().contains(tag));
        else
            assertFalse(accessItems.getWatchedHistory().contains(tag));

        solo.goBack();

        tag = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Black Mirror", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);
        inList = watchedHistory.contains(tag);
        for(int i=0; i<10; i++)
            solo.clickOnView(button.getChildAt(0));
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem("Watched");
        solo.assertCurrentActivity("Expected activity WatchedHistoryActivity", "WatchedHistoryActivity");
        if(inList)
            assertTrue(accessItems.getWatchedHistory().contains(tag));
        else
            assertFalse(accessItems.getWatchedHistory().contains(tag));

        solo.goBack();

        tag = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "A Wrinkle In Time", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);
        inList = watchedHistory.contains(tag);
        for(int i=0; i<10; i++)
            solo.clickOnView(button.getChildAt(0));
        solo.clickOnActionBarHomeButton();
        solo.clickOnMenuItem("Watched");
        solo.assertCurrentActivity("Expected activity WatchedHistoryActivity", "WatchedHistoryActivity");
        if(inList)
            assertTrue(accessItems.getWatchedHistory().contains(tag));
        else
            assertFalse(accessItems.getWatchedHistory().contains(tag));
    }
}
