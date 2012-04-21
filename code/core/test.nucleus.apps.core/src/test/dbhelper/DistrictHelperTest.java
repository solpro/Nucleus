package test.dbhelper;

import in.solpro.nucleus.apps.common.District;
import in.solpro.nucleus.apps.core.dbhelper.DistrictHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class DistrictHelperTest extends TestCase
{

    /**
     **/
    public void testAddDistrict()
    {
       District p = new District();
       p.setName( "NASHIK" );
       p.setObjState(CommonTestData.Maharashtra);
       DistrictHelper.addDistrict( p );
    }

    /**
     **/

    public void testAddDistrict_NoName()
    {
        District p = new District();
      
        try
        {
            DistrictHelper.addDistrict( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     **/
    public void testgetDistrictById()
    {
    	DistrictHelper helper=new DistrictHelper();
    	District p = DistrictHelper.getDistrictById( helper.getDistrict(CommonTestData.Pune.getName()).getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.Pune.getId() );
        assertEquals( p.getName(), CommonTestData.Pune.getName());
        assertEquals( p.getObjState().getId(), CommonTestData.Pune.getObjState().getId());
        assertEquals( p.getObjState().getObjCountry().getId(), CommonTestData.Pune.getObjState().getObjCountry().getId());
     }
    
    public void testGetDistrict()
    {
        DistrictHelper c = new DistrictHelper();
        District p = c.getDistrict( CommonTestData.Pune.getName() );
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.Pune.getId());
        assertEquals( p.getName(), CommonTestData.Pune.getName());
    }

    /**
     * */

    public void testGetDistricts()
    {
        List<District> p;

        try
        {
            DistrictHelper c = new DistrictHelper();
            p = c.getDistricts();
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "District name:" + p.get( i ).getName() + "\nDistrict ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateDistrict
     */

    public void testUpdateDistrict()
    {

        DistrictHelper c = new DistrictHelper();
        District p = (District) c.getDistrict( CommonTestData.Pune.getName() );
        p.setName("PUNYANAGARI");
        c.save( p );
        District p2 = (District) c.getDistrictById(CommonTestData.Pune.getId());
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getName(), "PUNYANAGARI" );
    }

}