package test.dbhelper;

import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class ItemCompanyHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ItemCompany itemcompany = new ItemCompany();
        itemcompany.setName( "Test ItemCompany" );
        new ItemCompanyHelper().addItemCompany( itemcompany );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddItemCompany()
    {
        ItemCompany p = new ItemCompany();
        p.setName( "Test Company" );

        try
        {
            new ItemCompanyHelper().addItemCompany( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItemCompany_DiffrentCompany()
    {
        ItemCompany itemcompany = new ItemCompany();
        itemcompany.setName( "Test ItemCompany" );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemCompanyHelper().addItemCompany( itemcompany );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItemCompany()
    {
        ItemCompany itemcompany = new ItemCompany();
        itemcompany.setName( "Duplicate ItemCompany" );
        ItemCompany itemcompany2 = new ItemCompany();
        itemcompany2.setName( "Duplicate ItemCompany" );
        ItemCompanyHelper helper = new ItemCompanyHelper();
        try
        {
            helper.addItemCompany( itemcompany );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItemCompany( itemcompany2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetItemCompany_DifferentCompany()
    {
        ItemCompany itemcompany = new ItemCompany();
        ItemCompanyHelper itemcompanyHelper = new ItemCompanyHelper();
        itemcompany.setName( "ItemCompany1" );
        try
        {
            itemcompanyHelper.addItemCompany( itemcompany );
            assertNotNull( itemcompanyHelper.getItemCompany( "ItemCompany1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( itemcompanyHelper.getItemCompany( "ItemCompany1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper#addItemCompany(in.solpro.nucleus.apps.common.ItemCompany)}.
     */

    public void testAddItemCompany_NoName()
    {
        ItemCompany itemcompany = new ItemCompany();

        try
        {
            new ItemCompanyHelper().addItemCompany( itemcompany );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper#addItemCompany(in.solpro.nucleus.apps.common.ItemCompany)}.
     * @throws Exception
     */
    public void testgetItemCompanyById() throws Exception
    {
        ItemCompanyHelper helper = new ItemCompanyHelper();
        ItemCompany itemcompany = helper.getItemCompany( helper.getItemCompany( "Test ItemCompany" ).getId() );
        if ( itemcompany == null )
        {
            fail();
        }
        itemcompany.validateAndUpdate();
        assertEquals( itemcompany.getId(), helper.getItemCompany( "Test ItemCompany" ).getId() );
        assertEquals( itemcompany.getName(), helper.getItemCompany( "Test ItemCompany" ).getName() );

    }

    public void testGetItemCompany() throws Exception
    {
        ItemCompanyHelper helper = new ItemCompanyHelper();
        ItemCompany itemcompany = helper.getItemCompany( "Test ItemCompany" );
        if ( itemcompany == null )
        {
            fail();
        }
        itemcompany.validateAndUpdate();
        assertEquals( itemcompany.getId(), itemcompany.getId() );
        assertEquals( itemcompany.getName(), itemcompany.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemCompanyHelper#addItemCompany(in.solpro.nucleus.apps.common.ItemCompany)}.
     */

    public void testGetItemCompanys()
    {
        List<ItemCompany> itemcompanys;

        try
        {
            ItemCompanyHelper helper = new ItemCompanyHelper();
            ItemCompany itemcompany = new ItemCompany();
            itemcompany.setName( "Company1" );
            itemcompany.setCompany( SessionUtil.getCompany() );
            helper.addItemCompany( itemcompany );
            ItemCompany itemcompany1 = new ItemCompany();
            itemcompany1.setName( "Company2" );
            itemcompany1.setCompany( SessionUtil.getCompany() );
            helper.addItemCompany( itemcompany1 );
            itemcompanys = helper.getItemCompanies();
            if ( itemcompanys == null )
            {
                fail();
            }
            for ( int i = 0; i < itemcompanys.size(); i++ )
            {
                itemcompanys.get( i ).validateAndUpdate();
                System.out.println( "ItemCompany name:" + itemcompanys.get( i ).getName() + "ItemCompany ID:"
                        + itemcompanys.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItemCompany
     * @throws Exception
     */

    public void testUpdateItemCompany() throws Exception
    {

        ItemCompanyHelper helper = new ItemCompanyHelper();
        ItemCompany itemcompany = (ItemCompany) helper.getItemCompany( "Test ItemCompany" );
        itemcompany.setName( "XYZ_Company" );
        helper.updateItemCompany( itemcompany );
        ItemCompany itemcompany2 = (ItemCompany) helper.getItemCompany( "XYZ_Company" );
        if ( itemcompany2 == null )
        {
            fail();
        }
        assertEquals( itemcompany2.getName(), "XYZ_Company" );
    }

}