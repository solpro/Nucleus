package test.dbhelper;

import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class LedgerGroupHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        LedgerGroup ledgergroup = new LedgerGroup();
        ledgergroup.setName( "Test LedgerGroup" );
        ledgergroup.setType( LedgerGroupType.EXPENSES );
        new LedgerGroupHelper().addLedgerGroup( ledgergroup );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddLedgerGroup()
    {
        LedgerGroup p = new LedgerGroup();
        p.setName( "Test Group" );
        p.setType( LedgerGroupType.EXPENSES );

        try
        {
            new LedgerGroupHelper().addLedgerGroup( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddLedgerGroup_DiffrentCompany()
    {
        LedgerGroup ledgergroup = new LedgerGroup();
        ledgergroup.setName( "Test LedgerGroup" );
        ledgergroup.setTypeInt( 2 );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new LedgerGroupHelper().addLedgerGroup( ledgergroup );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateLedgerGroup()
    {
        LedgerGroup ledgergroup = new LedgerGroup();
        ledgergroup.setName( "Duplicate LedgerGroup" );
        ledgergroup.setTypeInt( 2 );
        LedgerGroup ledgergroup2 = new LedgerGroup();
        ledgergroup2.setName( "Duplicate LedgerGroup" );
        ledgergroup2.setTypeInt( 2 );
        LedgerGroupHelper helper = new LedgerGroupHelper();
        try
        {
            helper.addLedgerGroup( ledgergroup );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addLedgerGroup( ledgergroup2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetLedgerGroup_DifferentCompany()
    {
        LedgerGroup ledgergroup = new LedgerGroup();
        LedgerGroupHelper ledgergroupHelper = new LedgerGroupHelper();
        ledgergroup.setName( "LedgerGroup1" );
        ledgergroup.setTypeInt( 2 );
        try
        {
            ledgergroupHelper.addLedgerGroup( ledgergroup );
            assertNotNull( ledgergroupHelper.getLedgerGroup( "LedgerGroup1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( ledgergroupHelper.getLedgerGroup( "LedgerGroup1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper#addLedgerGroup(in.solpro.nucleus.apps.common.LedgerGroup)}.
     */

    public void testAddLedgerGroup_NoName()
    {
        LedgerGroup ledgergroup = new LedgerGroup();

        try
        {
            new LedgerGroupHelper().addLedgerGroup( ledgergroup );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper#addLedgerGroup(in.solpro.nucleus.apps.common.LedgerGroup)}.
     * @throws Exception
     */
    public void testgetLedgerGroupById() throws Exception
    {
        LedgerGroupHelper helper = new LedgerGroupHelper();
        LedgerGroup ledgergroup = helper.getLedgerGroup( helper.getLedgerGroup( "Test LedgerGroup" ).getId() );
        if ( ledgergroup == null )
        {
            fail();
        }
        ledgergroup.validateAndUpdate();
        assertEquals( ledgergroup.getId(), helper.getLedgerGroup( "Test LedgerGroup" ).getId() );
        assertEquals( ledgergroup.getName(), helper.getLedgerGroup( "Test LedgerGroup" ).getName() );

    }

    public void testGetLedgerGroup() throws Exception
    {
        LedgerGroupHelper helper = new LedgerGroupHelper();
        LedgerGroup ledgergroup = helper.getLedgerGroup( "Test LedgerGroup" );
        if ( ledgergroup == null )
        {
            fail();
        }
        ledgergroup.validateAndUpdate();
        assertEquals( ledgergroup.getId(), ledgergroup.getId() );
        assertEquals( ledgergroup.getName(), ledgergroup.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper#addLedgerGroup(in.solpro.nucleus.apps.common.LedgerGroup)}.
     */

    public void testGetLedgerGroups()
    {
        List<LedgerGroup> ledgergroups;

        try
        {
            LedgerGroupHelper helper = new LedgerGroupHelper();
            LedgerGroup ledgergroup = new LedgerGroup();
            ledgergroup.setName( "LEDGERGROUP1" );
            ledgergroup.setTypeInt( 2 );
            ledgergroup.setCompany( SessionUtil.getCompany() );
            helper.addLedgerGroup( ledgergroup );
            LedgerGroup ledgergroup1 = new LedgerGroup();
            ledgergroup1.setName( "LEDGERGROUP2" );
            ledgergroup1.setTypeInt( 2 );
            ledgergroup1.setCompany( SessionUtil.getCompany() );
            helper.addLedgerGroup( ledgergroup1 );
            ledgergroups = helper.getLedgerGroups();
            if ( ledgergroups == null )
            {
                fail();
            }
            for ( int i = 0; i < ledgergroups.size(); i++ )
            {
                ledgergroups.get( i ).validateAndUpdate();
                System.out.println( "LedgerGroup name:" + ledgergroups.get( i ).getName() + "LedgerGroup ID:"
                        + ledgergroups.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateLedgerGroup
     * @throws Exception
     */

    public void testUpdateLedgerGroup() throws Exception
    {

        LedgerGroupHelper helper = new LedgerGroupHelper();
        LedgerGroup ledgergroup = (LedgerGroup) helper.getLedgerGroup( "Test LedgerGroup" );
        ledgergroup.setName( "XYZ_GROUP" );
        helper.updateLedgerGroup( ledgergroup );
        LedgerGroup ledgergroup2 = (LedgerGroup) helper.getLedgerGroup( "XYZ_GROUP" );
        if ( ledgergroup2 == null )
        {
            fail();
        }
        assertEquals( ledgergroup2.getName(), "XYZ_GROUP" );
    }

}