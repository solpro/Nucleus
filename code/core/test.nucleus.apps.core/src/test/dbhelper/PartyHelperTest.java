package test.dbhelper;

import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper;
import in.solpro.nucleus.apps.core.dbhelper.PartyHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author asheesh
 */
public class PartyHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;
    LedgerGroup negledgergroup;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        ledgergroup = new LedgerGroup();
        ledgergroup.setName( "Test LedgerGroup" );
        ledgergroup.setType( LedgerGroupType.EXPENSES );
        new LedgerGroupHelper().addLedgerGroup( ledgergroup );
        negledgergroup = new LedgerGroup();
        negledgergroup.setName( "Test NegLedgerGroup" );
        negledgergroup.setType( LedgerGroupType.EXPENSES );
        SessionUtil.setCompany( companyNegTest );
        new LedgerGroupHelper().addLedgerGroup( negledgergroup );
        SessionUtil.setCompany( company );

        Party party = new Party();
        party.setName( "Test Party" );
        party.setLedgergroup( ledgergroup );
        new PartyHelper().addParty( party );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddParty()
    {
        Party p = new Party();
        p.setName( "Test Party1" );
        p.setLedgergroup( ledgergroup );
        try
        {
            new PartyHelper().addParty( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddParty_DiffrentCompany()
    {
        Party party = new Party();
        party.setName( "Test Party" );
        party.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new PartyHelper().addParty( party );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddMultipleLabels() throws Exception
    {
        LedgerLabelHelper helper = new LedgerLabelHelper();
        LedgerLabel testlabel = new LedgerLabel();
        testlabel.setName( "test label 2" );
        helper.addLedgerLabel( testlabel );
        LedgerLabel partylabel = new LedgerLabel();
        partylabel.setName( "LABEL 1" );
        helper.addLedgerLabel( partylabel );
        Party party1 = new Party();
        party1.setName( "Test Party1" );
        party1.setLedgergroup( ledgergroup );
        Set<LedgerLabel> labels=new HashSet<LedgerLabel>();
        labels.add( partylabel );
        labels.add( testlabel );
        party1.setLabels( labels );
        new PartyHelper().addParty( party1 );

    }

    public void testDuplicateParty()
    {
        Party party = new Party();
        party.setName( "Duplicate Party" );
        party.setLedgergroup( ledgergroup );
        Party party2 = new Party();
        party2.setName( "Duplicate Party" );
        party.setLedgergroup( ledgergroup );
        PartyHelper helper = new PartyHelper();
        try
        {
            helper.addParty( party );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addParty( party2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetParty_DifferentCompany()
    {
        Party party = new Party();
        PartyHelper partyHelper = new PartyHelper();
        party.setName( "Party1" );
        party.setLedgergroup( ledgergroup );
        try
        {
            partyHelper.addParty( party );
            assertNotNull( partyHelper.getParty( "Party1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( partyHelper.getParty( "Party1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.PartyHelper#addParty(in.solpro.nucleus.apps.common.Party)}.
     */

    public void testAddParty_NoName()
    {
        Party party = new Party();

        try
        {
            new PartyHelper().addParty( party );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.PartyHelper#addParty(in.solpro.nucleus.apps.common.Party)}.
     * @throws Exception
     */
    public void testgetPartyById() throws Exception
    {
        PartyHelper helper = new PartyHelper();
        Party party = helper.getParty( helper.getParty( "Test Party" ).getId() );
        if ( party == null )
        {
            fail();
        }
        party.validateAndUpdate();
        assertEquals( party.getId(), helper.getParty( "Test Party" ).getId() );
        assertEquals( party.getName(), helper.getParty( "Test Party" ).getName() );

    }

    public void testGetParty() throws Exception
    {
        PartyHelper helper = new PartyHelper();
        Party party = helper.getParty( "Test Party" );
        if ( party == null )
        {
            fail();
        }
        party.validateAndUpdate();
        assertEquals( party.getId(), party.getId() );
        assertEquals( party.getName(), party.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.PartyHelper#addParty(in.solpro.nucleus.apps.common.Party)}.
     */

    public void testGetPartys()
    {
        List<Party> partys;

        try
        {
            PartyHelper helper = new PartyHelper();
            Party party = new Party();
            party.setName( "LEDGER1" );
            party.setLedgergroup( ledgergroup );
            helper.addParty( party );
            Party party1 = new Party();
            party1.setName( "LEDGER2" );
            helper.addParty( party1 );
            partys = helper.getParties();
            if ( partys == null )
            {
                fail();
            }
            for ( int i = 0; i < partys.size(); i++ )
            {
                partys.get( i ).validateAndUpdate();
                System.out.println( "Party name:" + partys.get( i ).getName() + "Party ID:" + partys.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateParty
     * @throws Exception
     */

    public void testUpdateParty() throws Exception
    {

        PartyHelper helper = new PartyHelper();
        Party party = (Party) helper.getParty( "Test Party" );
        party.setName( "XYZ_GROUP" );
        helper.updateParty( party );
        Party party2 = (Party) helper.getParty( "XYZ_GROUP" );
        if ( party2 == null )
        {
            fail();
        }
        assertEquals( party2.getName(), "XYZ_GROUP" );
    }

}