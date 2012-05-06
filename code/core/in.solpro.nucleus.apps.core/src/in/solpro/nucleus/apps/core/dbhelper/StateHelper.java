package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.State;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class StateHelper extends GenericHelper
{

    public StateHelper()
    {
        super( State.class );
    }

    public void addState( State state ) throws Exception
    {
        state.validateAndUpdate();
        save( state );
        System.out.println( "State Saved" );
    }

    public State getState( Integer id ) throws Exception
    {
        State state = (State) find( id );
        state.validateAndUpdate();
        return state;
    }

    public State getState( String name) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM State pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId());
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            State state=(State) rs.get( 0 );
            state.validateAndUpdate();
            return state;
        }
        return null;
        }

    @SuppressWarnings("unchecked")
    public List<State> getStates( )
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM State pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateState( State state ) throws Exception
    {
        state.validateAndUpdate();
        update( state );
    }

}