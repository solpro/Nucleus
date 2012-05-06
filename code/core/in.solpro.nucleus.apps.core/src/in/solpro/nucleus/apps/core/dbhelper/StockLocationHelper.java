package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.StockLocation;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class StockLocationHelper extends GenericHelper
{

    public StockLocationHelper()
    {
        super( StockLocation.class );
    }

    public void addStockLocation( StockLocation stocklocation ) throws Exception
    {
        stocklocation.validateAndUpdate();
        save( stocklocation );
        System.out.println( "StockLocation Saved" );
    }

    public StockLocation getStockLocation( Integer id ) throws Exception
    {
        StockLocation stocklocation = (StockLocation) find( id );
        stocklocation.validateAndUpdate();
        return stocklocation;
    }

    public StockLocation getStockLocation( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM StockLocation pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            StockLocation stocklocation = (StockLocation) rs.get( 0 );
            stocklocation.validateAndUpdate();
            return stocklocation;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<StockLocation> getStockLocations()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM StockLocation pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateStockLocation( StockLocation stocklocation ) throws Exception
    {
        stocklocation.validateAndUpdate();
        update(stocklocation);
    }

}