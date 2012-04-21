package test.dbhelper;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite( "nucleus" );
        suite.addTestSuite( CompanyHelperTest.class );
        suite.addTestSuite( LedgerGroupHelperTest.class );
        suite.addTestSuite( CountryHelperTest.class );
        suite.addTestSuite( StateHelperTest.class );
        suite.addTestSuite( DistrictHelperTest.class );
        suite.addTestSuite( CityHelperTest.class );
        suite.addTestSuite(UserDefinedLabelHelperTest.class);
        suite.addTestSuite(AddressTypeHelperTest.class);

        TestSetup wrapper = new TestSetup( suite )
        {
            protected void setUp()
            {
                oneTimeSetUp();
            }

            protected void tearDown()
            {
                oneTimeTearDown();
            }

        };
        return wrapper;
    }

    private static void oneTimeSetUp()
    {
        System.out.println( "Initialising Test Data" );
        CommonTestData.createTestData();
        System.out.println( "Test Data Initialisation Complete." );
    }

    private static void oneTimeTearDown()
    {
        System.out.println( "I M HERE2");

    }

}
