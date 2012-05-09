package test.dbhelper;

import java.util.*;
import in.solpro.nucleus.apps.common.*;

import in.solpro.nucleus.apps.core.dbhelper.*;

public class CommonTestData
{
    static Company abcCorpComp;
    
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
    	    
    	createAbcCorpCompTestData();
        createIndiaCountryTestData();
        
    }
    
    /************************COMPANY
     * @throws Exception ****************************/
    
    public static void createAbcCorpCompTestData() throws Exception
    {
        abcCorpComp = new Company();
        abcCorpComp.setName( "ABC Corporation" );
        abcCorpComp.setPrintname( "ABC Corporation" );
        new CompanyHelper().addCompany( abcCorpComp ); 
        
        createLedgerGroupAbcCorp();
        createLedgerLabelAbcCorp();
        createLedgerAbcCorp();
        
        createAddressTypeAbcCorp();
        
        createItemGroupAbcCorp();
        createItemCompanyAbcCorp();
        createItemAbcCorp();
        createItemTaxAbcCorp();
        createItemBatchAbcCorp();
        createItemDiscountAbcCorp();
        
        createPartyAbcCorp() ;
        createPartyAddressAbcCorp(); 
        createPartyItemDiscountAbcCorp(); 
        
         createUnitAbcCorp();
         createTaxAbcCorp();
         createStockLocationTypeAbcCorp(); 
         createStockLocationAbcCorp();
        
          
    
    }
    /************************ AREA 
     * @throws Exception ***************************/
    
    public static void createIndiaCountryTestData() throws Exception
    {
        India = new Country();
        India.setName("INDIA");
        India.setCompany(abcCorpComp);
        new CountryHelper().addCountry(India);
        createStateMaharshtraIndia();
    }
    
    public static void createStateMaharshtraIndia() throws Exception
    {
     Maharashtra =new State();
     Maharashtra.setName("MAHARASHRTRA");
     Maharashtra.setCountry(India);
     Maharashtra.setCompany(abcCorpComp);
     new StateHelper().addState(Maharashtra);
     createDistrictPune();
    }
    
    public static void createDistrictPune() throws Exception
    {
     Pune =new District();
     Pune.setName("PUNE");
     Pune.setState(Maharashtra);
     Pune.setCompany(abcCorpComp);
     new DistrictHelper().addDistrict(Pune);
     createCityPune();
    }
    
    public static void createCityPune() throws Exception
    {
     PuneCity =new City();
     PuneCity.setName("PUNE");
     PuneCity.setDistrict(Pune);
     PuneCity.setCompany(abcCorpComp);
     new CityHelper().addCity(PuneCity);
    }
 
/***************************** LEDGER 
 * @throws Exception ******************************/
    

    public static void createLedgerGroupAbcCorp() throws Exception
    {
     ledgergroup=new LedgerGroup();
     ledgergroup.setName("TRAVELING");
     ledgergroup.setType(LedgerGroupType.EXPENSES);
     ledgergroup.setCompany(abcCorpComp);
     new LedgerGroupHelper().addLedgerGroup(ledgergroup);
    }
    
    public static void createLedgerLabelAbcCorp() throws Exception
    {
    	 Label=new LedgerLabel();
    	 Label.setName( "PRIME CUSTOMER");
    	 Label.setCompany(abcCorpComp);
    	 new LedgerLabelHelper().addLedgerLabel(Label);
   }
    
    public static void createLedgerAbcCorp() throws Exception
    {
    	ledger=new Ledger();
        ledger.setName("PETROL EXPENSE");
        ledger.setLedgergroup(ledgergroup);
        ledger.setPrintname("PETROL EXPENCES");
        ledger.getLabels().add(Label);
        ledger.setCompany(abcCorpComp);
        new LedgerHelper().addLedger(ledger);
    }
 /**
 * @throws Exception *******************************************************/   
     
      public static void createAddressTypeAbcCorp() throws Exception
     {
    	 addresstype=new AddressType();
    	 addresstype.setName("OFFICE");
    	 addresstype.setCompany(abcCorpComp);
    	 addresstype.setDescription("OFFICE ADDRESS");
    	 new AddressTypeHelper().addAddressType(addresstype);
    	 
     }
      
  /************************ ITEM 
 * @throws Exception ******************************/
      public static void createItemGroupAbcCorp() throws Exception
      { 
      harddisk=new ItemGroup();
      harddisk.setCompany(abcCorpComp);
      harddisk.setName("HARDDISKS");
      harddisk.setDescription("SECONDARY_STORAGE");
      new ItemGroupHelper().addItemGroup(harddisk);
      }
      
      public static void createItemCompanyAbcCorp() throws Exception
      { 
      segate=new ItemCompany();
      segate.setCompany(abcCorpComp);
      segate.setName("SEGATE");
      new ItemCompanyHelper().addItemCompany(segate);
      }
      
      public static void createItemAbcCorp() throws Exception
      { 
      segate5=new Item();
      segate5.setCompany(abcCorpComp);
      segate5.setName("SEGATE 500");
      segate5.setMrp(3500);
      segate5.setType(ItemType.TAX_FREE);
      segate5.setItemgroup(harddisk);
      segate5.setItemCompany(segate);
      new ItemHelper().addItem(segate5);
      }
      
       public static void createItemTaxAbcCorp() throws Exception
      { 
        segate5tax=new ItemTax();
        segate5tax.setName("segate5tax");
        segate5tax.setCompany(abcCorpComp);
        new ItemTaxHelper().addItemTax(segate5tax);
      }
       
       public static void createItemBatchAbcCorp() throws Exception
      { 
      jan2012=new ItemBatch();
      jan2012.setName("JAN2012");
      jan2012.setItem(segate5);
      jan2012.setCompany(abcCorpComp);
      new ItemBatchHelper().addItemBatch(jan2012);
      } 
     
       public static void createItemDiscountAbcCorp() throws Exception
      { 
       segatedisc=new ItemDiscount();
       segatedisc.setItem(segate5);
       segatedisc.setCompany(abcCorpComp);
       new ItemDiscountHelper().addItemDiscount(segatedisc);     
      }  
     
/******************************* PARTY 
 * @throws Exception ***************************************/

       public static void createPartyAbcCorp() throws Exception 
       {
        party=new Party();
        party.setCompany(abcCorpComp);
        party.setName("PQR CORP.");
        party.getLabels().add(Label);
        new PartyHelper().addParty(party);
       }
       
       public static void createPartyAddressAbcCorp() throws Exception 
       {
    	   partyaddress=new PartyAddress();
    	   partyaddress.setCompany(abcCorpComp);
    	   partyaddress.setParty( party );
    	   new PartyAddressHelper().addPartyAddress(partyaddress);
       }
       public static void createPartyItemDiscountAbcCorp() throws Exception 
       {
    	   partydisc=new PartyItemDiscount();
    	   partydisc.setCompany(abcCorpComp);
    	   new PartyItemDiscountHelper().addPartyItemDiscount(partydisc);
       }
       
       /**
     * @throws Exception ******************************************************************/
       public static void createUnitAbcCorp() throws Exception
       {
    	   pcs=new Unit();
    	   pcs.setName("Ltr");
    	   pcs.setCompany(abcCorpComp);
    	   new UnitHelper().addUnit(pcs);
       }
       public static void createTaxAbcCorp() throws Exception
       {
    	   tax=new Tax();
    	   tax.setName("SERVICETAX");
    	   tax.setType(TaxType.PERCENT);
    	   tax.setCompany(abcCorpComp);
    	   tax.setValue(5);
    	   new TaxHelper().addTax(tax);
       }
       public static void createStockLocationTypeAbcCorp() throws Exception 
       {
    	office=new StockLocationType();   
   	   office.setName("OFFICE");
   	   office.setCompany(abcCorpComp);
   	new StockLocationTypeHelper().addStockLocationType(office);
       }
       
       public static void createStockLocationAbcCorp() throws Exception 
        {
    	   downtown=new StockLocation();
    	   downtown.setCompany(abcCorpComp);
    	   downtown.setName("DOWNTOWN");
    	   downtown.setType(office);
    	   new StockLocationHelper().addStockLocation(downtown);
        }
       
       public static void createDiscountAbcCorp() throws Exception 
       {
   	   discount.setCompany(abcCorpComp);
   	   discount.setType(DiscountType.PERCENT);
   	   discount.setValue(5);
   	   new DiscountHelper().addDiscount( discount );
       }
       
       
       
}
