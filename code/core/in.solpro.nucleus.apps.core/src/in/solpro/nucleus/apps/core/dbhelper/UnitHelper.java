package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Unit;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class UnitHelper extends GenericHelper
{

    public UnitHelper()
    {
        super( Unit.class );
    }

    public void addUnit( Unit unit ) throws Exception
    {
        unit.validateAndUpdate();
        save( unit );
        System.out.println( "Unit Saved" );
    }

    public Unit getUnit( Integer id ) throws Exception
    {
        Unit unit = (Unit) find( id );
        unit.validateAndUpdate();
        return unit;
    }

    public Unit getUnit( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Unit pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            Unit unit = (Unit) rs.get( 0 );
            unit.validateAndUpdate();
            return unit;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Unit> getUnits()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM Unit pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateUnit( Unit unit ) throws Exception
    {
        unit.validateAndUpdate();
        update( unit );
    }

}