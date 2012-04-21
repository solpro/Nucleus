package test.dbhelper;

import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class StateHelperTest extends TestCase
{

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */
    public void testAddState()
    {
        State p = new State();
        p.setName( "PUNJAB" );
        p.setObjCountry(CommonTestData.India);
        StateHelper.addState( p );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     */

    public void testAddState_NoName()
    {
        State p = new State();
      
        try
        {
            StateHelper.addState( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     */
    public void testgetStateById()
    {
    	StateHelper helper=new StateHelper();
    	State p = StateHelper.getStateById( helper.getState(CommonTestData.Maharashtra.getName()).getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.Maharashtra.getId() );
        assertEquals( p.getName(), CommonTestData.Maharashtra.getName());
        assertEquals( p.getObjCountry().getId(), CommonTestData.Maharashtra.getObjCountry().getId());
        
   	
    }
    
    public void testGetState()
    {
        StateHelper c = new StateHelper();
        State p = c.getState( CommonTestData.Maharashtra.getName() );
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getId(), CommonTestData.Maharashtra.getId());
        assertEquals( p.getName(), CommonTestData.Maharashtra.getName());
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     */

    public void testGetStates()
    {
        List<State> p;

        try
        {
            StateHelper c = new StateHelper();
            p = c.getStates();
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "State name:" + p.get( i ).getName() + "\nState ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateState
     */

    public void testUpdateState()
    {

        StateHelper c = new StateHelper();
        State p = (State) c.getState( CommonTestData.Maharashtra.getName() );
        p.setName("MAHARAJYA");
        c.save( p );
        State p2 = (State) c.getStateById(CommonTestData.Maharashtra.getId());
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getName(), "MAHARAJYA" );
    }

}