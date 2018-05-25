package comp3350.entertainment_tracker.tests.integration;

import junit.framework.TestCase;

import comp3350.entertainment_tracker.persistence.DataAccess;
import comp3350.entertainment_tracker.application.*;
import comp3350.entertainment_tracker.tests.persistence.DataAccessTest;

public class DataAccessHSQLDBTest extends TestCase{

    private static String dbName = Main.dbName;

    public DataAccessHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        Services.closeDataAccess();

        System.out.println("\nStarting Integration test DataAccess (using default DB)");

        // Use the following statement to run with the real database
        Services.createDataAccess(dbName);

        DataAccessTest.dataAccessTest();

        System.out.println("Finished Integration test DataAccess (using default DB)");
    }
}
