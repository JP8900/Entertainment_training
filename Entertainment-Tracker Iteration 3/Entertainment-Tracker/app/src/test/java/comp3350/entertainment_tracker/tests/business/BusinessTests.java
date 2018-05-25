package comp3350.entertainment_tracker.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests {
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("Business tests");
		suite.addTestSuite(ItemSearchTest.class);
		suite.addTestSuite(SorterTest.class);
		return suite;
	}
}
