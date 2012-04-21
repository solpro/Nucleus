package test.dbhelper;


import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.LedgerLabel;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerLabelHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class UserDefinedLabelHelperTest extends TestCase
{

    public void testAddUserDefinedLabel()
    {
        Company c = new Company();
        c.setName( "A" );
        c.setPrintname( "B" );
        CompanyHelper.addCompany( c );
        
        LedgerGroup lg = new LedgerGroup();
        lg.setName( "A" );
        lg.setType( LedgerGroupType.ASSETS );
        lg.setCompany( c );
        LedgerGroupHelper.addLedgerGroup( lg );
        
        
        LedgerLabel p = new LedgerLabel();
        p.setLabelName("SPECIAL CUSSTOMER");
        p.setCompany( c );
        LedgerLabelHelper.addLedgerLabel( p );
        System.out.println("LEDGERLABLE ID" + p.getLabelID());
        
        Ledger l = new Ledger();
        l.setName( "A ");
        l.setObjLedgergroup( lg );
        l.setCompany( c );
        l.getLabels().add( p );
        LedgerHelper.addLedger( l );
        
        
    }
    
    public void testUserDefinedLabel_NoName()
    {
        LedgerLabel p = new LedgerLabel();
           
        try
        {
            LedgerLabelHelper.addLedgerLabel(p);
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     **/
    public void testLedgerLabelById()
    {
    	LedgerLabelHelper helper=new LedgerLabelHelper();
    	Company c=new CompanyHelper().getCompany(CommonTestData.abcCorpComp.getName()); 
    	Integer id=helper.getLedgerLabel(CommonTestData.Label.getLabelName(), c.getId()).getLabelID();
    	LedgerLabel p = LedgerLabelHelper.getLedgerLabelById(id);
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getLabelName(),CommonTestData.Label.getLabelName());
   	
    }

    public void testLedgerLabel()
    {
        LedgerLabelHelper p = new LedgerLabelHelper();
        Company c=new CompanyHelper().getCompany(CommonTestData.abcCorpComp.getName()); 
        LedgerLabel g = p.getLedgerLabel( "SPECIAL CUSSTOMER",c.getId());
        assertNotNull( g );
        assertEquals( g.getLabelName(), "SPECIAL CUSSTOMER" );
    }

     public void testLedgerLabels()
    {
        LedgerLabelHelper p = new LedgerLabelHelper();
        Company c=new CompanyHelper().getCompany(CommonTestData.Label.getCompany().getName());
        List<LedgerLabel> g = p.getLedgerLabels( c.getId() );
        for ( int i = 0; i < g.size(); i++ )
            System.out.println( "GROUP_NAME:" + g.get( i ).getLabelName() + " of Company:" + g.get( i ).getCompany().getName() );
        // System.out.println("ID:"+g.getCompany().getId());
    }
     
     public void testUpdatetUserDefinedLabel()
     {

    	 LedgerLabelHelper c = new LedgerLabelHelper();
         Company cmpny=new CompanyHelper().getCompany(CommonTestData.abcCorpComp.getName());
    	 LedgerLabel p = (LedgerLabel) c.getLedgerLabel(CommonTestData.Label.getLabelName(),cmpny.getId());
         p.setLabelName("REGULAR CUSTOMER 2");
         c.save( p );
            LedgerLabel p2 = (LedgerLabel) c.getLedgerLabelById(p.getLabelID());
         if ( p2 == null )
         {
             fail();
         }
         assertEquals( p2.getLabelName(), "REGULAR CUSTOMER 2" );
     }

}
