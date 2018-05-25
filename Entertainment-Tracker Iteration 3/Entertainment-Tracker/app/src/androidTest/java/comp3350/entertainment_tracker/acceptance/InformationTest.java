package comp3350.entertainment_tracker.acceptance;

import com.robotium.solo.Solo;

import comp3350.entertainment_tracker.objects.EntertainmentItem;
import comp3350.entertainment_tracker.presentation.EntertainmentItemButton;
import comp3350.entertainment_tracker.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class InformationTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public InformationTest()
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

    public void testItemInformation() {
        EntertainmentItem tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Deadpool", null, null, 0);

        solo.waitForActivity("HomeActivity");
        EntertainmentItemButton button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.assertCurrentActivity("Expected activity InformationActivity", "InformationActivity");
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Captain Phillips", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);solo.clickOnView(button);
        solo.assertCurrentActivity("Expected activity InformationActivity", "InformationActivity");
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        tag = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Arrested Development", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);solo.clickOnView(button);
        solo.assertCurrentActivity("Expected activity InformationActivity", "InformationActivity");
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        tag = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "Animal Farm", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);solo.clickOnView(button);
        solo.assertCurrentActivity("Expected activity InformationActivity", "InformationActivity");
        solo.goBack();
    }

    public void testUserRating() {
        EntertainmentItem tag = new EntertainmentItem(EntertainmentItem.TYPE_MOVIE, "Deadpool", null, null, 0);

        solo.waitForActivity("HomeActivity");
        EntertainmentItemButton button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.setProgressBar(1, 40);
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        tag = new EntertainmentItem(EntertainmentItem.TYPE_TVSHOW, "Black Mirror", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.setProgressBar(1, 20);
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        tag = new EntertainmentItem(EntertainmentItem.TYPE_BOOK, "A Wrinkle In Time", null, null, 0);
        button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.setProgressBar(1, 30);
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        button = (EntertainmentItemButton) solo.getView(tag);
        solo.clickOnView(button);
        solo.goBack();
    }
}
