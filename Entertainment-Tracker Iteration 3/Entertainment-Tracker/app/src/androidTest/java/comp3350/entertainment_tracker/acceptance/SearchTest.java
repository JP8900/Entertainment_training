package comp3350.entertainment_tracker.acceptance;

import com.robotium.solo.Solo;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class SearchTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public SearchTest()
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


    public void testSearchFull(){
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "avengers");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();


        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "life of pi");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "series of unfortunate events");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "breaking bad");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();
    }


    public void testSearchNoResults(){
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "This Movie Does Not Exist");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();
    }

    public void testManyResults(){
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "a");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "e");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "i");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "o");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "u");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();
    }

    public void testSearchInvalid(){
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "@vengers");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "A series of unfortunat3 events");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "!&@#$^");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "1337");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "u");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.goBack();
    }
}
