package comp3350.entertainment_tracker.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.entertainment_tracker.tests.business.BusinessTests;
import comp3350.entertainment_tracker.tests.objects.EntertainmentItemTest;
import comp3350.entertainment_tracker.tests.persistence.PersistenceTests;

public class RunUnitTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("Unit tests");
		suite.addTest(BusinessTests.suite());
		suite.addTestSuite(EntertainmentItemTest.class);
		suite.addTest(PersistenceTests.suite());
		return suite;
	}
}
