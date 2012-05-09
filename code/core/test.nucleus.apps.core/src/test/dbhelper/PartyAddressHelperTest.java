package test.dbhelper;

import in.solpro.nucleus.apps.common.AddressType;
import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.common.PartyAddress;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper;
import in.solpro.nucleus.apps.core.dbhelper.CityHelper;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.PartyAddressHelper;
import in.solpro.nucleus.apps.core.dbhelper.PartyHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class PartyAddressHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

    Party party;

    Party negparty;

    AddressType addresstype;

    AddressType negaddresstype;

    Country country;

    Country negcountry;

    State state;

    State negstate;

    District district;

    District negdistrict;

    City city;

    City negcity;

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

        party = new Party();
        party.setName( "Test Party" );
        party.setLedgergroup( ledgergroup );
        new PartyHelper().addParty( party );
        negparty = new Party();
        negparty.setName( "Test negParty" );
        negparty.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        new PartyHelper().addParty( negparty );
        SessionUtil.setCompany( company );

        addresstype = new AddressType();
        addresstype.setName( "Test AddressType" );
        new AddressTypeHelper().addAddressType( addresstype );
        negaddresstype = new AddressType();
        negaddresstype.setName( "Test negAddressType" );
        SessionUtil.setCompany( companyNegTest );
        new AddressTypeHelper().addAddressType( negaddresstype );
        SessionUtil.setCompany( company );

        country = new Country();
        country.setName( "TEST COUNTRY" );
        new CountryHelper().addCountry( country );
        negcountry = new Country();
        negcountry.setName( "NEG TEST COUNTRY" );
        SessionUtil.setCompany( companyNegTest );
        new CountryHelper().addCountry( negcountry );
        SessionUtil.setCompany( company );

        state = new State();
        state.setName( "Test State" );
        state.setCountry( country );
        new StateHelper().addState( state );
        negstate = new State();
        negstate.setName( "NEG Test State" );
        negstate.setCountry( negcountry );
        SessionUtil.setCompany( companyNegTest );
        new StateHelper().addState( negstate );
        SessionUtil.setCompany( company );

        district = new District();
        district.setName( "Test District" );
        district.setState( state );
        new DistrictHelper().addDistrict( district );
        negdistrict = new District();
        negdistrict.setName( "NEG Test District" );
        negdistrict.setState( negstate );
        SessionUtil.setCompany( companyNegTest );
        new DistrictHelper().addDistrict( negdistrict );
        SessionUtil.setCompany( company );

        city = new City();
        city.setName( "TEST CITY" );
        city.setDistrict( district );
        new CityHelper().addCity( city );
        negcity = new City();
        negcity.setName( "TEST negCITY" );
        negcity.setDistrict( negdistrict );
        new CityHelper().addCity( negcity );

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddPartyAddress()
    {
        PartyAddress partyaddress = new PartyAddress();
        partyaddress.setContactperson( "TEST PERSON" );
        partyaddress.setCity( city );
        partyaddress.setParty( party );
        // partyaddress.addParty( party );
        partyaddress.setType( addresstype );
        try
        {
            new PartyAddressHelper().addPartyAddress( partyaddress );
        }
        catch ( Exception e )
        {
            fail();
        }

        List<PartyAddress> list = (List<PartyAddress>) party.getAddressList();
        for ( int i = 0; i < list.size(); i++ )
            System.out.println( "" + list.get( i ).getCity().getName() );
    }

    public void testDuplicatePartyAddress()
    {
        PartyAddress partyaddress = new PartyAddress();
        partyaddress.setContactperson( "TEST PERSON" );
        partyaddress.setAddress1( "1" );
        partyaddress.setAddress2( "1" );
        partyaddress.setAddress3( "1" );
        partyaddress.setCity( city );
        partyaddress.setParty( party );
        partyaddress.setType( addresstype );
        PartyAddress partyaddress2 = new PartyAddress();
        partyaddress2.setContactperson( "TEST PERSON" );
        partyaddress2.setCity( city );
        partyaddress2.setAddress1( "1" );
        partyaddress2.setAddress2( "1" );
        partyaddress2.setAddress3( "1" );
        partyaddress2.setParty( party );
        partyaddress2.setType( addresstype );
        PartyAddressHelper helper = new PartyAddressHelper();
        try
        {
            helper.addPartyAddress( partyaddress );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addPartyAddress( partyaddress2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetPartyAddress_DifferentCompany()
    {
        PartyAddress partyaddress = new PartyAddress();
        PartyAddressHelper partyaddressHelper = new PartyAddressHelper();
        partyaddress.setContactperson( "TEST PERSON" );
        partyaddress.setCity( city );
        partyaddress.setParty( party );
        partyaddress.setType( addresstype );
        try
        {
            partyaddressHelper.addPartyAddress( partyaddress );
            assertNotNull( partyaddressHelper.getPartyAddress( partyaddress.getId() ) );
            SessionUtil.setCompany( companyNegTest );
            try
            {
               assertNull( partyaddressHelper.getPartyAddress( partyaddress.getId() ) );
            }
            catch(Exception e)
            {
                
            }
               SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    public void testgetPartyAddressById() throws Exception
    {
        PartyAddress partyaddress = new PartyAddress();
        partyaddress.setContactperson( "TEST PERSON" );
        partyaddress.setCity( city );
        partyaddress.setParty( party );
        partyaddress.setType( addresstype );
        PartyAddressHelper helper = new PartyAddressHelper();
        helper.addPartyAddress( partyaddress );
        PartyAddress partyaddress1 = helper.getPartyAddress( partyaddress.getId() );
        if ( partyaddress1 == null )
        {
            fail();
        }
        partyaddress1.validateAndUpdate();
        assertEquals( partyaddress1.getId(), partyaddress.getId() );

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.PartyAddressHelper#addPartyAddress(in.solpro.nucleus.apps.common.PartyAddress)}.
     */

    public void testGetPartyAddresses()
    {
        List<PartyAddress> partyaddresses;

        try
        {
            PartyAddressHelper helper = new PartyAddressHelper();

            PartyAddress partyaddress = new PartyAddress();
            partyaddress.setContactperson( "TEST PERSON" );
            partyaddress.setCity( city );
            partyaddress.setParty( party );
            partyaddress.setType( addresstype );
            helper.addPartyAddress( partyaddress );
            PartyAddress partyaddress1 = new PartyAddress();
            partyaddress.setContactperson( "TEST PERSON2" );
            partyaddress.setCity( city );
            partyaddress.setParty( party );
            partyaddress.setType( addresstype );
            helper.addPartyAddress( partyaddress1 );
            partyaddresses = helper.getPartyAddresses();
            if ( partyaddresses == null )
            {
                fail();
            }
            System.out.println( "TAXES SIZE:" + partyaddresses.size() );
            for ( int i = 0; i < partyaddresses.size(); i++ )
            {
                partyaddresses.get( i ).validateAndUpdate();
                System.out.println( "PartyAddress name:" + partyaddresses.get( i ).getContactperson() + "PartyAddress ID:"
                        + partyaddresses.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updatePartyAddress
     * @throws Exception
     */

    public void testUpdatePartyAddress() throws Exception
    {

        PartyAddressHelper helper = new PartyAddressHelper();
        PartyAddress partyaddress = new PartyAddress();
        partyaddress.setContactperson( "TEST PERSON" );
        partyaddress.setCity( city );
        partyaddress.setParty( party );
        partyaddress.setType( addresstype );
        new PartyAddressHelper().addPartyAddress( partyaddress );
        helper.updatePartyAddress( partyaddress );
        PartyAddress partyaddress2 = (PartyAddress) helper.getPartyAddress( partyaddress.getId() );
        if ( partyaddress2 == null )
        {
            fail();
        }
    }

}