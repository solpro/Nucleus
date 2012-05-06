package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.StockLocationType;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class StockLocationTypeHelper extends GenericHelper
{

    public StockLocationTypeHelper()
    {
        super( StockLocationType.class );
    }

    public void addStockLocationType( StockLocationType stocklocationtype ) throws Exception
    {
        stocklocationtype.validateAndUpdate();
        save( stocklocationtype );
        System.out.println( "StockLocationType Saved" );
    }

    public StockLocationType getStockLocationType( Integer id ) throws Exception
    {
        StockLocationType stocklocationtype = (StockLocationType) find( id );
        stocklocationtype.validateAndUpdate();
        return stocklocationtype;
    }

    public StockLocationType getStockLocationType( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM StockLocationType pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );

        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            StockLocationType stocklocationtype = (StockLocationType) rs.get( 0 );
            stocklocationtype.validateAndUpdate();
            return stocklocationtype;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<StockLocationType> getStockLocationTypes()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM StockLocationType pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return query.getResultList();
    }

    public void updateStockLocationType( StockLocationType stocklocationtype ) throws Exception
    {
        stocklocationtype.validateAndUpdate();
        update( stocklocationtype );
    }

}