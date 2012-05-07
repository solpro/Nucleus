package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.UserGroup;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

import javax.persistence.Query;

public class UserGroupHelper extends GenericHelper
{

    public UserGroupHelper()
    {
        super( UserGroup.class );
    }

    public void addUserGroup( UserGroup usergroup ) throws Exception
    {
        usergroup.validateAndUpdate();
        save( usergroup );
        System.out.println("USERGROUP SAVED");
    }

    public UserGroup getUserGroup( Integer id ) throws Exception
    {
        UserGroup usergroup = (UserGroup) find( id );
        usergroup.validateAndUpdate();
        return usergroup;
    }

    public UserGroup getUserGroup( String name ) throws Exception
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM UserGroup pc WHERE pc.name = :name AND pc.company.id= :compid" );
        query.setParameter( "name", name );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        List<?> rs = query.getResultList();
        if ( rs.size() > 0 )
        {
            UserGroup usergroup = (UserGroup) rs.get( 0 );
            usergroup.validateAndUpdate();
            return usergroup;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<UserGroup> getUserGroups()
    {
        Query query = em.createQuery( "SELECT OBJECT(pc) FROM UserGroup pc WHERE pc.company.id= :compid" );
        query.setParameter( "compid", SessionUtil.getCompany().getId() );
        return (List<UserGroup>) query.getResultList();
    }

    public void updateUserGroup( UserGroup usergroup ) throws Exception
    {
        usergroup.validateAndUpdate();
        update( usergroup );
    }

}