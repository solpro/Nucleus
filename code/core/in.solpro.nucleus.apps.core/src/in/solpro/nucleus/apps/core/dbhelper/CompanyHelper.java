package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.AddressType;
import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.Discount;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.Item;
import in.solpro.nucleus.apps.common.ItemBatch;
import in.solpro.nucleus.apps.common.ItemCompany;
import in.solpro.nucleus.apps.common.ItemDiscount;
import in.solpro.nucleus.apps.common.ItemGroup;
import in.solpro.nucleus.apps.common.ItemTax;
import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.common.Party;
import in.solpro.nucleus.apps.common.PartyAddress;
import in.solpro.nucleus.apps.common.PartyItemDiscount;
import in.solpro.nucleus.apps.common.ProductUser;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.common.StockLocation;
import in.solpro.nucleus.apps.common.StockLocationType;
import in.solpro.nucleus.apps.common.Tax;
import in.solpro.nucleus.apps.common.Unit;
import in.solpro.nucleus.apps.common.UserGroup;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class CompanyHelper extends GenericHelper
{

    public CompanyHelper()
    {
        super( Company.class );
    }

    public void addCompany( Company company )
    {
        save( company );
        System.out.println( "Company Saved" );
    }

    public Company getCompany( Integer id )
    {
        Company company = (Company) find( id );
        return company;
    }

    public Company getCompany( String name )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Company pc WHERE pc.name = :name" );
        query.setParameter( "name", name );
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            return (Company) rs.get( 0 );
        }
        return null;

    }

    @SuppressWarnings("unchecked")
    public List<Company> getCompanies()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Company pc" );
        return query.getResultList();
    }

    public void updateCompany( Company company ) throws Exception
    {
        update( company );
    }
    
    public void DeleteAll()
    {
        List<Company> companylist = getCompanies();
        for (int i = 0; i < companylist.size(); i++ )
           delete( companylist.get( i ) );

    }

    public void deleteCompany( Company company )
    {
        int i;
        GenericHelper helper = new GenericHelper( Company.class );
        SessionUtil.setCompany( company );
        /**********************************************************/
        
        /***********************************************************************/
        ItemTaxHelper itemtaxhelper = new ItemTaxHelper();
        List<ItemTax> itemtaxlist = itemtaxhelper.getItemTaxes();
        for ( i = 0; i < itemtaxlist.size(); i++ )
            helper.delete( itemtaxlist.get( i ) );
        
        ProductUserHelper productuserhelper = new ProductUserHelper();
        List<ProductUser> productuserlist = productuserhelper.getProductUsers();
        for ( i = 0; i < productuserlist.size(); i++ )
            helper.delete( productuserlist.get( i ) );
        
        UserGroupHelper usergrouphelper = new UserGroupHelper();
        List<UserGroup> usergrouplist = usergrouphelper.getUserGroups();
        for ( i = 0; i < usergrouplist.size(); i++ )
            helper.delete( usergrouplist.get( i ) );
        
        TaxHelper taxhelper = new TaxHelper();
        List<Tax> taxlist = taxhelper.getTaxes();
        for ( i = 0; i < taxlist.size(); i++ )
            helper.delete( taxlist.get( i ) );
        
        DiscountHelper discounthelper = new DiscountHelper();
        List<Discount> discountlist = discounthelper.getDiscounts();
        for ( i = 0; i < discountlist.size(); i++ )
            helper.delete( discountlist.get( i ) );

        ItemBatchHelper itembatchhelper = new ItemBatchHelper();
        List<ItemBatch> itembatchlist = itembatchhelper.getItemBatches();
        for ( i = 0; i < itembatchlist.size(); i++ )
            helper.delete( itembatchlist.get( i ) );

        ItemHelper itemhelper = new ItemHelper();
        List<Item> itemlist = itemhelper.getItems();
        for ( i = 0; i < itemlist.size(); i++ )
            helper.delete( itemlist.get( i ) );

        ItemCompanyHelper itemcompanyhelper = new ItemCompanyHelper();
        List<ItemCompany> itemcompanylist = itemcompanyhelper.getItemCompanies();
        for ( i = 0; i < itemcompanylist.size(); i++ )
            helper.delete( itemcompanylist.get( i ) );

        ItemDiscountHelper itemdiscounthelper = new ItemDiscountHelper();
        List<ItemDiscount> itemdiscountlist = itemdiscounthelper.getAllItemDiscounts();
        for ( i = 0; i < itemdiscountlist.size(); i++ )
            helper.delete( itemdiscountlist.get( i ) );

        ItemGroupHelper itemgrouphelper = new ItemGroupHelper();
        List<ItemGroup> itemgrouplist = itemgrouphelper.getItemGroups();
        for ( i = 0; i < itemgrouplist.size(); i++ )
            helper.delete( itemgrouplist.get( i ) );

      
        

        /***********************************************************************/

        PartyItemDiscountHelper partyitemdiscounthelper = new PartyItemDiscountHelper();
        List<PartyItemDiscount> partyitemdiscountlist = partyitemdiscounthelper.getAllPartyItemDiscounts();
        for ( i = 0; i < partyitemdiscountlist.size(); i++ )
            helper.delete( partyitemdiscountlist.get( i ) );

        PartyAddressHelper partyaddresshelper = new PartyAddressHelper();
        List<PartyAddress> partyaddresslist = partyaddresshelper.getPartyAddresses();
        for ( i = 0; i < partyaddresslist.size(); i++ )
            helper.delete( partyaddresslist.get( i ) );
        
        AddressTypeHelper addresstypehelper = new AddressTypeHelper();
        List<AddressType> addresstypelist = addresstypehelper.getAddressTypes();
        for ( i = 0; i < addresstypelist.size(); i++ )
            helper.delete( addresstypelist.get( i ) );

        PartyHelper partyhelper = new PartyHelper();
        List<Party> partylist = partyhelper.getParties();
        for ( i = 0; i < partylist.size(); i++ )
            helper.delete( partylist.get( i ) );

        /*********************************************************************/

        StockLocationHelper stocklocationhelper = new StockLocationHelper();
        List<StockLocation> stocklocationlist = stocklocationhelper.getStockLocations();
        for ( i = 0; i < stocklocationlist.size(); i++ )
            helper.delete( stocklocationlist.get( i ) );

        StockLocationTypeHelper stocklocationtypehelper = new StockLocationTypeHelper();
        List<StockLocationType> stocklocationtypelist = stocklocationtypehelper.getStockLocationTypes();
        for ( i = 0; i < stocklocationtypelist.size(); i++ )
            helper.delete( stocklocationtypelist.get( i ) );

        UnitHelper unithelper = new UnitHelper();
        List<Unit> unitlist = unithelper.getUnits();
        for ( i = 0; i < unitlist.size(); i++ )
            helper.delete( unitlist.get( i ) );

        CityHelper cityhelper = new CityHelper();
        List<City> citylist = cityhelper.getCities();
        for ( i = 0; i < citylist.size(); i++ )
            helper.delete( citylist.get( i ) );

        DistrictHelper districthelper = new DistrictHelper();
        List<District> districtlist = districthelper.getDistricts();
        for ( i = 0; i < districtlist.size(); i++ )
            helper.delete( districtlist.get( i ) );

        StateHelper statehelper = new StateHelper();
        List<State> statelist = statehelper.getStates();
        for ( i = 0; i < statelist.size(); i++ )
            statehelper.delete( statelist.get( i ) );

        CountryHelper countryhelper = new CountryHelper();
        List<Country> countrylist = countryhelper.getCountries();
        for ( i = 0; i < countrylist.size(); i++ )
            countryhelper.delete( countrylist.get( i ) );
        /*****************************************************************/
        LedgerLabelHelper ledgerlabelhelper = new LedgerLabelHelper();
        List<LedgerLabel> ledgerlabellist = ledgerlabelhelper.getLedgerLabels();
        for ( i = 0; i < ledgerlabellist.size(); i++ )
            helper.delete( ledgerlabellist.get( i ) );

        LedgerHelper ledgerhelper = new LedgerHelper();
        List<Ledger> ledgerlist = ledgerhelper.getLedgers();
        for ( i = 0; i < ledgerlist.size(); i++ )
            helper.delete( ledgerlist.get( i ) );

        LedgerGroupHelper ledgergrouphelper = new LedgerGroupHelper();
        List<LedgerGroup> ledgergrouplist = ledgergrouphelper.getLedgerGroups();
        for ( i = 0; i < ledgergrouplist.size(); i++ )
            helper.delete( ledgergrouplist.get( i ) );

        /***********************************************************************/
        helper.delete( company );
    }
}
