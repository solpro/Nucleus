package test.dbhelper;

import in.solpro.nucleus.apps.common.Country;
import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.dbhelper.CountryHelper;
import in.solpro.nucleus.apps.core.dbhelper.StateHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class StateHelperTest extends AbstractMasterDataHelperTest
{
    private Country negcountry;
    private Country country;
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        country = new Country();
        country.setName( "TEST COUNTRY" );
        new CountryHelper().addCountry( country );
        negcountry = new Country();
        negcountry.setName( "NEG TEST COUNTRY" );
        SessionUtil.setCompany( companyNegTest );
        new CountryHelper().addCountry( negcountry );
        SessionUtil.setCompany( company );
        
        State state = new State();
        state.setName( "Test State" );
        state.setCountry( country );
        new StateHelper().addState(state );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

   public void testAddState()
    {
        State state = new State();
        state.setName( "Maharshtra" );
        state.setCountry(country);
        try
        {
            new StateHelper().addState( state );
        }
        catch ( Exception e )
        {
            fail();
        }
    }
   
   public void testAddState_DiffrentCompany()
   {
       State state = new State();
       state.setCountry( negcountry );
       state.setName( "Test State" );
       SessionUtil.setCompany( companyNegTest );
       try
       {
           new StateHelper().addState(state );
       }
       catch ( Exception e )
       {
           fail();
       }
   }
  

    public void testDuplicateState() 
    {
        State state = new State();
        state.setName( "Duplicate State" );
        state.setCountry( country );
        State state2 = new State();
        state2.setName( "Duplicate State" );
       state2.setCountry( country );
        StateHelper helper = new StateHelper();
        try
        {
            helper.addState( state );
        }
        catch ( Exception e )
        {
            fail();
        }
          try
        {
            helper.addState( state2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetState_DifferentCompany()
    {
        State state = new State();
        StateHelper stateHelper = new StateHelper();
        state.setName( "Canada AddState_NoCompany" );
        state.setCountry( country );
        try
        {
            stateHelper.addState( state );
            assertNotNull( stateHelper.getState( "Canada AddState_NoCompany" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( stateHelper.getState( "Canada AddState_NoCompany" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     */

    public void testAddState_NoName()
    {
        State state = new State();
        
        try
        {
            new StateHelper().addState( state );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     * @throws Exception
     */
    public void testgetStateById() throws Exception
    {
        StateHelper helper = new StateHelper();
        State state = helper.getState( helper.getState( "Test State" ).getId() );
        if ( state == null )
        {
            fail();
        }
        state.validateAndUpdate();
        assertEquals( state.getId(), helper.getState( "Test State" ).getId() );
        assertEquals( state.getName(), helper.getState( "Test State" ).getName() );

    }

    public void testGetState() throws Exception
    {
        StateHelper helper = new StateHelper();
        State state = helper.getState( "Test State" );
        if ( state == null )
        {
            fail();
        }
        state.validateAndUpdate();
        assertEquals( state.getId(), state.getId() );
        assertEquals( state.getName(), state.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.StateHelper#addState(in.solpro.nucleus.apps.common.State)}.
     */

    public void testGetStates()
    {
        List<State> states;

        try
        {
            StateHelper helper = new StateHelper();
            State state=new State();
            state.setName( "STATE1" );
            state.setCountry( country );
            state.setCompany( SessionUtil.getCompany() );
            helper.addState( state );
            State state1=new State();
            state1.setName( "STATE2" );
            state1.setCountry( negcountry );
            state1.setCompany( SessionUtil.getCompany() );
            helper.addState( state1 );
            states = helper.getStates();
            if ( states == null )
            {
                fail();
            }
            System.out.println("COUNTRIES SIZE:"+states.size());
            for ( int i = 0; i < states.size(); i++ )
            {
                states.get( i ).validateAndUpdate();
                System.out.println( "State name:" + states.get( i ).getName() + "State ID:" + states.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateState
     * @throws Exception
     */

    public void testUpdateState() throws Exception
    {

        StateHelper helper = new StateHelper();
        State state = (State) helper.getState( "Test State" );
        state.setName( "HINDUSTAN" );
        helper.updateState( state );
        State state2 = (State) helper.getState( "HINDUSTAN" );
        if ( state2 == null )
        {
            fail();
        }
        assertEquals( state2.getName(), "HINDUSTAN" );
   }

}