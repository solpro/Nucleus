package test.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;

import java.util.List;

/**
 * @author asheesh
 */
public class CompanyHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        Company company=new Company();
        company.setName( "Test Company" );
        company.setPrintname( COMPANY_PRINT_NAME );        
        new CompanyHelper().addCompany( company );
    }
    
    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        new CompanyHelper().DeleteAll();
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddCompany()
    {
        Company p = new Company();
        p.setName( "Test Company1" );
        p.setPrintname( COMPANY_PRINT_NAME );
        try
        {
            new CompanyHelper().addCompany( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    

    public void testDuplicateCompany()
    {
        Company company = new Company();
        company.setName( "Duplicate Company" );
        company.setPrintname( COMPANY_PRINT_NAME );
         Company company2 = new Company();
        company2.setName( "Duplicate Company" );
        company2.setPrintname( COMPANY_PRINT_NAME );
         CompanyHelper helper = new CompanyHelper();
        try
        {
            helper.addCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addCompany( company2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

   
    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddCompany_NoName()
    {
        Company company = new Company();

        try
        {
            new CompanyHelper().addCompany( company );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     * @throws Exception
     */
    public void testgetCompanyById() throws Exception
    {
        CompanyHelper helper = new CompanyHelper();
        Company company = helper.getCompany( helper.getCompany( "Test Company" ).getId() );
        if ( company == null )
        {
            fail();
        }
         assertEquals( company.getId(), helper.getCompany( "Test Company" ).getId() );
        assertEquals( company.getName(), helper.getCompany( "Test Company" ).getName() );

    }

    public void testGetCompany() throws Exception
    {
        CompanyHelper helper = new CompanyHelper();
        Company company = helper.getCompany( "Test Company" );
        if ( company == null )
        {
            fail();
        }
         assertEquals( company.getId(), company.getId() );
        assertEquals( company.getName(), company.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testGetCompanies()
    {
        List<Company> companys;

        try
        {
            CompanyHelper helper = new CompanyHelper();
            Company company = new Company();
            company.setName( "LEDGERCOMPANY1" );
             helper.addCompany( company );
            Company company1 = new Company();
            company1.setName( "LEDGERCOMPANY2" );
            helper.addCompany( company1 );
            companys = helper.getCompanies();
            if ( companys == null )
            {
                fail();
            }
            for ( int i = 0; i < companys.size(); i++ )
            {
                System.out.println( "Company name:" + companys.get( i ).getName() + "Company ID:"
                        + companys.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCompany
     * @throws Exception
     */

    public void testUpdateCompany() throws Exception
    {

        CompanyHelper helper = new CompanyHelper();
        Company company = (Company) helper.getCompany( "Test Company" );
        company.setName( "XYZ_COMPANY" );
        helper.updateCompany( company );
        Company company2 = (Company) helper.getCompany( "XYZ_COMPANY" );
        if ( company2 == null )
        {
            fail();
        }
        assertEquals( company2.getName(), "XYZ_COMPANY" );
    }

}