package comp3350.entertainment_tracker.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(SearchTest.class);
        suite.addTestSuite(SortingTest.class);
        suite.addTestSuite(WatchedListTest.class);
        suite.addTestSuite(InformationTest.class);
        return suite;
    }
}
