package test.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.common.City;
import in.solpro.nucleus.apps.common.*;

import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.dbhelper.CityHelper;
import in.solpro.nucleus.apps.core.dbhelper.*;
public class CommonTestData
{
    static Company abcCorpComp;
    static Country India;
    static State Maharashtra;
    static District Pune;
    static City PuneCity;
    static LedgerLabel Label; 
    static AddressType addresstype;
    
    
    public static void createTestData()
    {
        createAbcCorpCompTestData();
        createIndiaCountryTestData();
    }
    
    public static void createAbcCorpCompTestData()
    {
        abcCorpComp = new Company();
        abcCorpComp.setName( "ABC Corporation" );
        abcCorpComp.setPrintname( "ABC Corporation" );
        CompanyHelper.addCompany( abcCorpComp ); 
        createLedgerGroupsAbcCorp();
        createUserDefinedLabelAbcCorp();
        createAddressTypeAbcCorp();
    }
    
    public static void createIndiaCountryTestData()
    {
        India = new Country();
        India.setName("INDIA");
        CountryHelper.addCountry(India);
        createStateMaharshtraIndia();
    }
    

    public static void createLedgerGroupsAbcCorp()
    {
        
    }
    
     public static void createUserDefinedLabelAbcCorp()
    {
    	 Label=new LedgerLabel();
    	 Label.setLabelName("REGULAR CUSTOMER");
    	 Label.setCompany(abcCorpComp);
    	 LedgerLabelHelper.addLedgerLabel(Label);
    }
     public static void createAddressTypeAbcCorp()
     {
    	 addresstype=new AddressType();
    	 addresstype.setName("OFFICE");
    	 addresstype.setCompany(abcCorpComp);
    	 addresstype.setDescription("OFFICE ADDRESS");
    	AddressTypeHelper.addAddressType(addresstype);
    	 
     }
          
    public static void createStateMaharshtraIndia()
    {
     Maharashtra =new State();
     Maharashtra.setName("MAHARASHRTRA");
     Maharashtra.setObjCountry(India);
     StateHelper.addState(Maharashtra);
     createDistrictPune();
    }
    
    public static void createDistrictPune()
    {
     Pune =new District();
     Pune.setName("PUNE");
     Pune.setObjState(Maharashtra);
     DistrictHelper.addDistrict(Pune);
     createCityPune();
    }
    public static void createCityPune()
    {
     PuneCity =new City();
     PuneCity.setName("PUNE");
     PuneCity.setObjDistrict(Pune);
     CityHelper.addCity(PuneCity);
    }
    
    
 
}
