package test.dbhelper;

import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class ItemGroupHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ItemGroup itemgroup = new ItemGroup();
        itemgroup.setName( "Test ItemGroup" );
        new ItemGroupHelper().addItemGroup( itemgroup );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddItemGroup()
    {
        ItemGroup p = new ItemGroup();
        p.setName( "Test Group" );

        try
        {
            new ItemGroupHelper().addItemGroup( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddItemGroup_DiffrentCompany()
    {
        ItemGroup itemgroup = new ItemGroup();
        itemgroup.setName( "Test ItemGroup" );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new ItemGroupHelper().addItemGroup( itemgroup );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateItemGroup()
    {
        ItemGroup itemgroup = new ItemGroup();
        itemgroup.setName( "Duplicate ItemGroup" );
        ItemGroup itemgroup2 = new ItemGroup();
        itemgroup2.setName( "Duplicate ItemGroup" );
        ItemGroupHelper helper = new ItemGroupHelper();
        try
        {
            helper.addItemGroup( itemgroup );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addItemGroup( itemgroup2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetItemGroup_DifferentCompany()
    {
        ItemGroup itemgroup = new ItemGroup();
        ItemGroupHelper itemgroupHelper = new ItemGroupHelper();
        itemgroup.setName( "ItemGroup1" );
        try
        {
            itemgroupHelper.addItemGroup( itemgroup );
            assertNotNull( itemgroupHelper.getItemGroup( "ItemGroup1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( itemgroupHelper.getItemGroup( "ItemGroup1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper#addItemGroup(in.solpro.nucleus.apps.common.ItemGroup)}.
     */

    public void testAddItemGroup_NoName()
    {
        ItemGroup itemgroup = new ItemGroup();

        try
        {
            new ItemGroupHelper().addItemGroup( itemgroup );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper#addItemGroup(in.solpro.nucleus.apps.common.ItemGroup)}.
     * @throws Exception
     */
    public void testgetItemGroupById() throws Exception
    {
        ItemGroupHelper helper = new ItemGroupHelper();
        ItemGroup itemgroup = helper.getItemGroup( helper.getItemGroup( "Test ItemGroup" ).getId() );
        if ( itemgroup == null )
        {
            fail();
        }
        itemgroup.validateAndUpdate();
        assertEquals( itemgroup.getId(), helper.getItemGroup( "Test ItemGroup" ).getId() );
        assertEquals( itemgroup.getName(), helper.getItemGroup( "Test ItemGroup" ).getName() );

    }

    public void testGetItemGroup() throws Exception
    {
        ItemGroupHelper helper = new ItemGroupHelper();
        ItemGroup itemgroup = helper.getItemGroup( "Test ItemGroup" );
        if ( itemgroup == null )
        {
            fail();
        }
        itemgroup.validateAndUpdate();
        assertEquals( itemgroup.getId(), itemgroup.getId() );
        assertEquals( itemgroup.getName(), itemgroup.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.ItemGroupHelper#addItemGroup(in.solpro.nucleus.apps.common.ItemGroup)}.
     */

    public void testGetItemGroups()
    {
        List<ItemGroup> itemgroups;

        try
        {
            ItemGroupHelper helper = new ItemGroupHelper();
            ItemGroup itemgroup = new ItemGroup();
            itemgroup.setName( "LEDGERGROUP1" );
            itemgroup.setCompany( SessionUtil.getCompany() );
            helper.addItemGroup( itemgroup );
            ItemGroup itemgroup1 = new ItemGroup();
            itemgroup1.setName( "LEDGERGROUP2" );
            itemgroup1.setCompany( SessionUtil.getCompany() );
            helper.addItemGroup( itemgroup1 );
            itemgroups = helper.getItemGroups();
            if ( itemgroups == null )
            {
                fail();
            }
            for ( int i = 0; i < itemgroups.size(); i++ )
            {
                itemgroups.get( i ).validateAndUpdate();
                System.out.println( "ItemGroup name:" + itemgroups.get( i ).getName() + "ItemGroup ID:"
                        + itemgroups.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateItemGroup
     * @throws Exception
     */

    public void testUpdateItemGroup() throws Exception
    {

        ItemGroupHelper helper = new ItemGroupHelper();
        ItemGroup itemgroup = (ItemGroup) helper.getItemGroup( "Test ItemGroup" );
        itemgroup.setName( "XYZ_GROUP" );
        helper.updateItemGroup( itemgroup );
        ItemGroup itemgroup2 = (ItemGroup) helper.getItemGroup( "XYZ_GROUP" );
        if ( itemgroup2 == null )
        {
            fail();
        }
        assertEquals( itemgroup2.getName(), "XYZ_GROUP" );
    }

}