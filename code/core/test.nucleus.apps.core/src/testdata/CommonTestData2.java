package testdata;


import java.util.*;
import in.solpro.nucleus.apps.common.*;

import in.solpro.nucleus.apps.core.dbhelper.*;

public class CommonTestData2
{
    static Company xyzCorpComp;
    
    static Country India;
    static State Maharashtra;
    static District Pune;
    static City PuneCity;
    
    static LedgerGroup ledgergroup; 
    static LedgerLabel Label; 
    static Ledger ledger;
    
    
    static AddressType addresstype;
    
    static Item segate5;
    static ItemGroup harddisk;
    static ItemCompany segate;
    static ItemTax segate5tax;
    static ItemBatch jan2012;
    static ItemDiscount segatedisc;
    
    static Party party;
    static PartyAddress partyaddress;
    static PartyItemDiscount partydisc;
    
    static Unit pcs;
    static Tax tax;
    
    static StockLocation downtown;
    static StockLocationType office;
    static Discount discount;
    
    static Set<LedgerLabel> ll = new HashSet<LedgerLabel>();
    static Set<PartyAddress> pa = new HashSet<PartyAddress>();
	
    /**
     * @throws Exception ***************************************************/
    
    public static void createTestData() throws Exception
    {
    	    
    	createXyzCorpCompTestData();
        createIndiaCountryTestDataXyzCorp();
        
    }
    
    /************************COMPANY
     * @throws Exception ****************************/
    
    public static void createXyzCorpCompTestData() throws Exception
    {
        xyzCorpComp = new Company();
        xyzCorpComp.setName( "XYZ Corporation" );
        xyzCorpComp.setPrintname( "XYZ Corporation" );
        new CompanyHelper().addCompany( xyzCorpComp ); 
        
        createLedgerGroupXyzCorp();
        createLedgerLabelXyzCorp();
        createLedgerXyzCorp();
        
        createAddressTypeXyzCorp();
        
        createItemGroupXyzCorp();
        createItemCompanyXyzCorp();
        createItemXyzCorp();
        createItemTaxXyzCorp();
        createItemBatchXyzCorp();
        createItemDiscountXyzCorp();
        
        createPartyXyzCorp() ;
        createPartyAddressXyzCorp(); 
        createPartyItemDiscountXyzCorp(); 
        
         createUnitXyzCorp();
         createTaxXyzCorp();
         createStockLocationTypeXyzCorp(); 
         createStockLocationXyzCorp();
        
          
    
    }
    /************************ AREA 
     * @throws Exception ***************************/
    
    public static void createIndiaCountryTestDataXyzCorp() throws Exception
    {
        CountryHelper helper=new CountryHelper();
        India = new Country();
        India.setName("xINDIA");
        India.setCompany(xyzCorpComp);
        helper.addCountry(India);
        createStateMaharshtraIndiaXyzCorp();
    }
    
    public static void createStateMaharshtraIndiaXyzCorp() throws Exception
    {
     Maharashtra =new State();
     Maharashtra.setName("xMAHARASHRTRA");
     Maharashtra.setCountry(India);
     Maharashtra.setCompany(xyzCorpComp);
     new StateHelper().addState(Maharashtra);
     createDistrictPuneXyzCorp();
    }
    
    public static void createDistrictPuneXyzCorp() throws Exception
    {
     Pune =new District();
     Pune.setName("xPUNE");
     Pune.setState(Maharashtra);
     Pune.setCompany(xyzCorpComp);
     new DistrictHelper().addDistrict(Pune);
     createCityPuneXyzCorp();
    }
    
    public static void createCityPuneXyzCorp() throws Exception
    {
     PuneCity =new City();
     PuneCity.setName("xPUNE");
     PuneCity.setDistrict(Pune);
     PuneCity.setCompany(xyzCorpComp);
     new CityHelper().addCity(PuneCity);
    }
 
/***************************** LEDGER 
 * @throws Exception ******************************/
    

    public static void createLedgerGroupXyzCorp() throws Exception
    {
     ledgergroup=new LedgerGroup();
     ledgergroup.setName("xTRAVELING");
     ledgergroup.setType(LedgerGroupType.EXPENSES);
     ledgergroup.setCompany(xyzCorpComp);
     new LedgerGroupHelper().addLedgerGroup(ledgergroup);
    }
    
    public static void createLedgerLabelXyzCorp() throws Exception
    {
    	 Label=new LedgerLabel();
    	 Label.setName("xSPECIAL CUSTOMER");
    	 Label.setCompany(xyzCorpComp);
    	 new LedgerLabelHelper().addLedgerLabel(Label);
    	 ll.add(Label);
    	 
    }
    
    public static void createLedgerXyzCorp() throws Exception
    {
    	ledger=new Ledger();
        ledger.setName("xPETROL EXPENSE");
        ledger.setLedgergroup(ledgergroup);
        ledger.setPrintname("PETROL EXPENCES");
        ledger.setLabels(ll);
        ledger.setCompany(xyzCorpComp);
        new LedgerHelper().addLedger(ledger);
    }
 /**
 * @throws Exception *******************************************************/   
     
      public static void createAddressTypeXyzCorp() throws Exception
     {
    	 addresstype=new AddressType();
    	 addresstype.setName("xOFFICE");
    	 addresstype.setCompany(xyzCorpComp);
    	 addresstype.setDescription("OFFICE ADDRESS");
    	 new AddressTypeHelper().addAddressType(addresstype);
    	 
     }
      
  /************************ ITEM 
 * @throws Exception ******************************/
      public static void createItemGroupXyzCorp() throws Exception
      { 
          ItemGroupHelper helper=new ItemGroupHelper();
      harddisk=new ItemGroup();
      harddisk.setCompany(xyzCorpComp);
      harddisk.setName("xHARDDISKS");
      harddisk.setDescription("SECONDARY_STORAGE");
      helper.addItemGroup(harddisk);
      }
      
      public static void createItemCompanyXyzCorp() throws Exception
      { 
      segate=new ItemCompany();
      segate.setCompany(xyzCorpComp);
      segate.setName("xSEGATE");
      new ItemCompanyHelper().addItemCompany(segate);
      }
      
      public static void createItemXyzCorp() throws Exception
      { 
      segate5=new Item();
      segate5.setCompany(xyzCorpComp);
      segate5.setName("xSEGATE 500");
      segate5.setMrp(3500);
      segate5.setType(ItemType.TAX_FREE);
      segate5.setItemgroup(harddisk);
      segate5.setItemCompany(segate);
      new ItemHelper().addItem(segate5);
      }
      
       public static void createItemTaxXyzCorp() throws Exception
      { 
        segate5tax=new ItemTax();
        segate5tax.setName("xsegate5tax");
        segate5tax.setCompany(xyzCorpComp);
        new ItemTaxHelper().addItemTax(segate5tax);
      }
       
       public static void createItemBatchXyzCorp() throws Exception
      { 
      jan2012=new ItemBatch();
      jan2012.setName("xJAN2012");
      jan2012.setItem(segate5);
      jan2012.setCompany(xyzCorpComp);
      new ItemBatchHelper().addItemBatch(jan2012);
      } 
     
       public static void createItemDiscountXyzCorp() throws Exception
      { 
       segatedisc=new ItemDiscount();
       segatedisc.setItem(segate5);
       segatedisc.setCompany(xyzCorpComp);
       new ItemDiscountHelper().addItemDiscount(segatedisc);     
      }  
     
/******************************* PARTY 
 * @throws Exception ***************************************/

       public static void createPartyXyzCorp() throws Exception 
       {
        party=new Party();
        party.setCompany(xyzCorpComp);
        party.setName("xPQR CORP.");
        //party.setLabels(ll);
        new PartyHelper().addParty(party);
       }
       
       public static void createPartyAddressXyzCorp() throws Exception 
       {
    	   partyaddress=new PartyAddress();
    	   partyaddress.setCompany(xyzCorpComp);
    	   //partyaddress.setParties(parties)
    	   new PartyAddressHelper().addPartyAddress(partyaddress);
       }
       public static void createPartyItemDiscountXyzCorp() throws Exception 
       {
    	   partydisc=new PartyItemDiscount();
    	   partydisc.setCompany(xyzCorpComp);
    	   new PartyItemDiscountHelper().addPartyItemDiscount(partydisc);
       }
       
       /**
     * @throws Exception ******************************************************************/
       public static void createUnitXyzCorp() throws Exception
       {
    	   pcs=new Unit();
    	   pcs.setName("xLtr");
    	   pcs.setCompany(xyzCorpComp);
    	   new UnitHelper().addUnit(pcs);
       }
       public static void createTaxXyzCorp() throws Exception
       {
    	   tax=new Tax();
    	   tax.setName("xSERVICETAX");
    	   tax.setType(TaxType.PERCENT);
    	   tax.setCompany(xyzCorpComp);
    	   tax.setValue(5);
    	   new TaxHelper().addTax(tax);
       }
       public static void createStockLocationTypeXyzCorp() throws Exception 
       {
    	office=new StockLocationType();   
   	   office.setName("xOFFICE");
   	   office.setCompany(xyzCorpComp);
   	new StockLocationTypeHelper().addStockLocationType(office);
       }
       
       public static void createStockLocationXyzCorp() throws Exception 
        {
    	   downtown=new StockLocation();
    	   downtown.setCompany(xyzCorpComp);
    	   downtown.setName("xDOWNTOWN");
    	   downtown.setType(office);
    	   new StockLocationHelper().addStockLocation(downtown);
        }
       
       public static void createDiscountXyzCorp() throws Exception 
       {
   	   discount.setCompany(xyzCorpComp);
   	   discount.setType(DiscountType.PERCENT);
   	   discount.setValue(5);
   	   new DiscountHelper().addDiscount( discount );
       }
       
       
       
}
