
package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.ProductUser;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class ProductUserHelper extends GenericHelper
{
    public ProductUserHelper()
    {
        super( ProductUser.class );
    }

    public void addProductUser( ProductUser productuser ) throws Exception
    {
        productuser.validateAndUpdate();
        save( productuser );
        System.out.println("USERGROUP SAVED");
    }

    public ProductUser getProductUser( Integer id ) throws Exception
    {
        ProductUser productuser = (ProductUser) find( id );
        productuser.validateAndUpdate();
        return productuser;
    }

    public ProductUser getProductUser( String username ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ProductUser pc WHERE pc.username = :username AND pc.company.id= :compid" );
        query.setParameter( "username", username );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            ProductUser productuser = (ProductUser) rs.get( 0 );
            productuser.validateAndUpdate();
            return productuser;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ProductUser> getProductUsers()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM ProductUser pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return (List<ProductUser>) query.getResultList();
    }

    public void updateProductUser( ProductUser productuser ) throws Exception
    {
        productuser.validateAndUpdate();
        update( productuser );
    }

}
