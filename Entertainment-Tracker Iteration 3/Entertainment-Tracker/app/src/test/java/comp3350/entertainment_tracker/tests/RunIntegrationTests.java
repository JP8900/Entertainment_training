package comp3350.entertainment_tracker.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.entertainment_tracker.tests.integration.IntegrationTests;

public class RunIntegrationTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("Integration tests");
		suite.addTest(IntegrationTests.suite());
		return suite;
	}
}