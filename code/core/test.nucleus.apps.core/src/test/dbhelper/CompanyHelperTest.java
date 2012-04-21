/**
 * 
 */
package test.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class CompanyHelperTest extends TestCase
{

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */
    public void testAddCompany()
    {
        Company p = new Company();
        p.setName( "IJK Coorporation" );
        p.setPrintname( "IJK Coorporation" );
        CompanyHelper.addCompany( p );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddCompany_NoName()
    {
        Company p = new Company();
        p.setPrintname( "XYZ Coorporation" );
        try
        {
            CompanyHelper.addCompany( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */
    public void testgetCompanyById()
    {
    	CompanyHelper c = new CompanyHelper();
        Company p = c.getCompanyById( CommonTestData.abcCorpComp.getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.abcCorpComp.getId() );
        assertEquals( p.getName(), CommonTestData.abcCorpComp.getName() );
   	
    }
    public void testGetCompany()
    {
        CompanyHelper c = new CompanyHelper();
        Company p = c.getCompany( CommonTestData.abcCorpComp.getName() );
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.abcCorpComp.getId() );
        assertEquals( p.getName(), CommonTestData.abcCorpComp.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testGetCompanies()
    {
        List<Company> p;

        try
        {
            CompanyHelper c = new CompanyHelper();
            p = c.getCompanies();
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "Company name:" + p.get( i ).getName() + "\nCompany ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateCompany
     */

    public void testUpdateCompany()
    {

        CompanyHelper c = new CompanyHelper();
        Company p = (Company) c.getCompany( CommonTestData.abcCorpComp.getName() );
        p.setAddress1( "PUNE" );
        p.setPan( "15gh" );
        c.save( p );
        Company p2 = (Company) c.getCompany( CommonTestData.abcCorpComp.getName() );
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getAddress1(), "PUNE" );
    }

}