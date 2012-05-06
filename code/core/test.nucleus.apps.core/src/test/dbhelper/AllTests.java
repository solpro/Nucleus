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
        
        suite.addTestSuite( CountryHelperTest.class );
        suite.addTestSuite( StateHelperTest.class );
        suite.addTestSuite( DistrictHelperTest.class );
        suite.addTestSuite( CityHelperTest.class );
        
        suite.addTestSuite( LedgerGroupHelperTest.class );
        suite.addTestSuite(LedgerLabelHelperTest.class);
        suite.addTestSuite( LedgerHelperTest.class );
        
        suite.addTestSuite(AddressTypeHelperTest.class);
        
        suite.addTestSuite(DiscountHelperTest.class);
        suite.addTestSuite(ItemCompanyHelperTest.class);
        suite.addTestSuite(ItemGroupHelperTest.class);
        suite.addTestSuite(ItemHelperTest.class);
        suite.addTestSuite(ItemDiscountHelperTest.class);
        suite.addTestSuite(TaxHelperTest.class);
        suite.addTestSuite(ItemTaxHelperTest.class);
        suite.addTestSuite(PartyHelperTest.class);
        suite.addTestSuite(PartyItemDiscountHelperTest.class);
        suite.addTestSuite(UnitHelperTest.class);
        suite.addTestSuite(StockLocationHelperTest.class);        
        suite.addTestSuite(StockLocationTypeHelperTest.class);
       /*suite.addTestSuite(HelperTest.class);
        suite.addTestSuite(HelperTest.class);
       */ 
        
        TestSetup wrapper = new TestSetup( suite )
        {
            protected void setUp()
            {
                try
                {
                    oneTimeSetUp();
                }
                catch ( Exception e )
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            protected void tearDown()
            {
                oneTimeTearDown();
            }
        };
        return wrapper;
    }

    private static void oneTimeSetUp() throws Exception
    {
        System.out.println( "Initialising Test Data" );
        CommonTestData.createTestData();
        CommonTestData2.createTestData();
        System.out.println( "Test Data Initialisation Complete." );
    }

    private static void oneTimeTearDown()
    {
        System.out.println( "I M HERE2");
    }

}
