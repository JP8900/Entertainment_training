package comp3350.entertainment_tracker.acceptance;

import com.robotium.solo.Solo;

import comp3350.entertainment_tracker.R;
import comp3350.entertainment_tracker.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Spinner;
import android.widget.TextView;

public class SortingTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    private Solo solo;

    public SortingTest()
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


    public void testZASort(){
        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Movies");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));
        solo.goBack();

        solo.clickOnButton("Books");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));
        solo.goBack();

        solo.clickOnButton("TV Shows");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));
        solo.goBack();
    }

    public void testAZSort(){
        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Movies");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();

        solo.clickOnButton("Books");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();

        solo.clickOnButton("TV Shows");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();
    }

    public void testRatingSort(){
        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Movies");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5));
        solo.goBack();

        solo.clickOnButton("Books");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5));
        solo.goBack();

        solo.clickOnButton("TV Shows");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5));
        solo.goBack();
    }

    public void testSearchSort() {
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "a");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5));
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "e");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));
        solo.goBack();

        solo.clearEditText(0);
        solo.waitForActivity("HomeActivity");
        solo.clickOnView(solo.getView(R.id.action_search));
        solo.enterText(0, "i");
        solo.waitForDialogToClose();
        solo.sendKey(Solo.ENTER);
        solo.assertCurrentActivity("Expected activity SearchActivity", "SearchActivity");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();

    }

    public void testAllSort() {
        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Movies");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5)); //HighestRated

        //Title: A-Z
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));

        //Title: Z-A
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("TV Shows");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4)); //Title: Z-A

        //HighestRated
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5));

        //Title: A-Z
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();

        solo.waitForActivity("HomeActivity");
        solo.clickOnButton("Books");
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 5)); //HighestRated

        //Title: Z-A
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 4));

        //Title: A-Z
        solo.clickOnView(solo.getView(Spinner.class, 0));
        solo.scrollToTop();
        solo.clickOnView(solo.getView(TextView.class, 3));
        solo.goBack();
    }
}
