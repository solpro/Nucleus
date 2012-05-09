package test.dbhelper;

import in.solpro.nucleus.apps.common.Unit;
import in.solpro.nucleus.apps.core.dbhelper.UnitHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class DiscountHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        Unit unit = new Unit();
        unit.setName( "Test Unit" );

        new UnitHelper().addUnit( unit );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddUnit()
    {
        Unit unit = new Unit();
        unit.setName( "TESTUNTI1" );

        try
        {
            new UnitHelper().addUnit( unit );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddUnit_DiffrentCompany()
    {
        Unit unit = new Unit();
        unit.setName( "Test Unit" );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new UnitHelper().addUnit( unit );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateUnit()
    {
        Unit unit = new Unit();
        unit.setName( "Duplicate Unit" );
        Unit unit2 = new Unit();
        unit2.setName( "Duplicate Unit" );
        UnitHelper helper = new UnitHelper();
        try
        {
            helper.addUnit( unit );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addUnit( unit2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetUnit_DifferentCompany()
    {
        Unit unit = new Unit();
        UnitHelper unitHelper = new UnitHelper();
        unit.setName( "AddUnit1" );

        try
        {
            unitHelper.addUnit( unit );
            assertNotNull( unitHelper.getUnit( "AddUnit1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( unitHelper.getUnit( "AddUnit1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.UnitHelper#addUnit(in.solpro.nucleus.apps.common.Unit)}.
     */

    public void testAddUnit_NoName()
    {
        Unit unit = new Unit();

        try
        {
            new UnitHelper().addUnit( unit );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.UnitHelper#addUnit(in.solpro.nucleus.apps.common.Unit)}.
     * @throws Exception
     */
    public void testgetUnitById() throws Exception
    {
        UnitHelper helper = new UnitHelper();
        Unit unit = helper.getUnit( helper.getUnit( "Test Unit" ).getId() );
        if ( unit == null )
        {
            fail();
        }
        unit.validateAndUpdate();
        assertEquals( unit.getId(), helper.getUnit( "Test Unit" ).getId() );
        assertEquals( unit.getName(), helper.getUnit( "Test Unit" ).getName() );

    }

    public void testGetUnit() throws Exception
    {
        UnitHelper helper = new UnitHelper();
        Unit unit = helper.getUnit( "Test Unit" );
        if ( unit == null )
        {
            fail();
        }
        unit.validateAndUpdate();
        assertEquals( unit.getId(), unit.getId() );
        assertEquals( unit.getName(), unit.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.UnitHelper#addUnit(in.solpro.nucleus.apps.common.Unit)}.
     */

    public void testGetUnites()
    {
        List<Unit> units;

        try
        {
            UnitHelper helper = new UnitHelper();
            Unit unit = new Unit();
            unit.setName( "TEST UNIT1" );
            helper.addUnit( unit );
            Unit unit1 = new Unit();
            unit1.setName( "TEST UNIT2" );
            helper.addUnit( unit1 );
            units = helper.getUnits();
            if ( units == null )
            {
                fail();
            }
            System.out.println( "TAXES SIZE:" + units.size() );
            for ( int i = 0; i < units.size(); i++ )
            {
                units.get( i ).validateAndUpdate();
                System.out.println( "Unit name:" + units.get( i ).getName() + "Unit ID:" + units.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateUnit
     * @throws Exception
     */

    public void testUpdateUnit() throws Exception
    {

        UnitHelper helper = new UnitHelper();
        Unit unit = (Unit) helper.getUnit( "Test Unit" );
        unit.setName( "XYZ" );
        helper.updateUnit( unit );
        Unit unit2 = (Unit) helper.getUnit( "XYZ" );
        if ( unit2 == null )
        {
            fail();
        }
        assertEquals( unit2.getName(), "XYZ" );
    }

}