package comp3350.entertainment_tracker.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.entertainment_tracker.tests.business.ItemSearchTest;
import comp3350.entertainment_tracker.tests.business.SorterTest;
import comp3350.entertainment_tracker.tests.objects.EntertainmentItemTest;
import comp3350.entertainment_tracker.tests.persistence.DataAccessTest;

public class AllTests {
	public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        testPersistence();
        return suite;
    }

    private static void testObjects() {
        suite.addTestSuite(EntertainmentItemTest.class);
    }

    private static void testBusiness() {
        suite.addTestSuite(SorterTest.class);
		suite.addTestSuite(ItemSearchTest.class);
    }

    private static void testPersistence() {
		suite.addTestSuite(DataAccessTest.class);
    }
}
