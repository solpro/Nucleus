package test.dbhelper;

import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.common.Tax;
import in.solpro.nucleus.apps.common.TaxType;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerHelper;
import in.solpro.nucleus.apps.core.dbhelper.TaxHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class TaxHelperTest extends AbstractMasterDataHelperTest
{
    LedgerGroup ledgergroup;

    LedgerGroup negledgergroup;

    Ledger ledger;

    Ledger negledger;

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

        ledger = new Ledger();
        ledger.setName( "Test Ledger" );
        ledger.setLedgergroup( ledgergroup );
        new LedgerHelper().addLedger( ledger );
        negledger = new Ledger();
        negledger.setName( "Test negLedger" );
        negledger.setLedgergroup( negledgergroup );
        SessionUtil.setCompany( companyNegTest );
        new LedgerHelper().addLedger( negledger );
        SessionUtil.setCompany( company );

        Tax tax = new Tax();
        tax.setName( "Test Tax" );
        tax.setType( TaxType.FIXED );
        tax.setLedger( ledger );
        new TaxHelper().addTax( tax );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddTax()
    {
        Tax tax = new Tax();
        tax.setName( "TESTTAX1" );
        tax.setType( TaxType.FIXED );
        tax.setLedger( ledger );

        try
        {
            new TaxHelper().addTax( tax );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddTax_DiffrentCompany()
    {
        Tax tax = new Tax();
        tax.setName( "Test Tax" );
        tax.setType( TaxType.FIXED );
        tax.setLedger( negledger );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new TaxHelper().addTax( tax );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateTax()
    {
        Tax tax = new Tax();
        tax.setName( "Duplicate Tax" );
        tax.setType( TaxType.FIXED );
        tax.setLedger( ledger );
        Tax tax2 = new Tax();
        tax2.setName( "Duplicate Tax" );
        tax2.setType( TaxType.FIXED );
        tax2.setLedger( ledger );
        TaxHelper helper = new TaxHelper();
        try
        {
            helper.addTax( tax );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addTax( tax2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetTax_DifferentCompany()
    {
        Tax tax = new Tax();
        TaxHelper taxHelper = new TaxHelper();
        tax.setName( "Canada AddTax_NoCompany" );
        tax.setType( TaxType.FIXED );
        tax.setLedger( ledger );

        try
        {
            taxHelper.addTax( tax );
            assertNotNull( taxHelper.getTax( "Canada AddTax_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( taxHelper.getTax( "Canada AddTax_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.TaxHelper#addTax(in.solpro.nucleus.apps.common.Tax)}.
     */

    public void testAddTax_NoName()
    {
        Tax tax = new Tax();

        try
        {
            new TaxHelper().addTax( tax );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.TaxHelper#addTax(in.solpro.nucleus.apps.common.Tax)}.
     * @throws Exception
     */
    public void testgetTaxById() throws Exception
    {
        TaxHelper helper = new TaxHelper();
        Tax tax = helper.getTax( helper.getTax( "Test Tax" ).getId() );
        if ( tax == null )
        {
            fail();
        }
        tax.validateAndUpdate();
        assertEquals( tax.getId(), helper.getTax( "Test Tax" ).getId() );
        assertEquals( tax.getName(), helper.getTax( "Test Tax" ).getName() );

    }

    public void testGetTax() throws Exception
    {
        TaxHelper helper = new TaxHelper();
        Tax tax = helper.getTax( "Test Tax" );
        if ( tax == null )
        {
            fail();
        }
        tax.validateAndUpdate();
        assertEquals( tax.getId(), tax.getId() );
        assertEquals( tax.getName(), tax.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.TaxHelper#addTax(in.solpro.nucleus.apps.common.Tax)}.
     */

    public void testGetTaxes()
    {
        List<Tax> taxes;

        try
        {
            TaxHelper helper = new TaxHelper();
            Tax tax = new Tax();
            tax.setName( "TEST TAX1" );
            tax.setType( TaxType.FIXED );
            tax.setLedger( ledger );
            helper.addTax( tax );
            Tax tax1 = new Tax();
            tax1.setName( "TEST TAX2" );
            tax1.setType( TaxType.FIXED );
            tax1.setLedger( ledger );
            helper.addTax( tax1 );
            taxes = helper.getTaxes();
            if ( taxes == null )
            {
                fail();
            }
            System.out.println( "TAXES SIZE:" + taxes.size() );
            for ( int i = 0; i < taxes.size(); i++ )
            {
                taxes.get( i ).validateAndUpdate();
                System.out.println( "Tax name:" + taxes.get( i ).getName() + "Tax ID:" + taxes.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateTax
     * @throws Exception
     */

    public void testUpdateTax() throws Exception
    {

        TaxHelper helper = new TaxHelper();
        Tax tax = (Tax) helper.getTax( "Test Tax" );
        tax.setName( "XYZ" );
        helper.updateTax( tax );
        Tax tax2 = (Tax) helper.getTax( "XYZ" );
        if ( tax2 == null )
        {
            fail();
        }
        assertEquals( tax2.getName(), "XYZ" );
    }

}