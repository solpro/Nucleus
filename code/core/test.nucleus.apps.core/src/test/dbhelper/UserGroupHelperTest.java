package test.dbhelper;

import in.solpro.nucleus.apps.common.UserGroup;
import in.solpro.nucleus.apps.core.dbhelper.UserGroupHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class UserGroupHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        UserGroup usergroup = new UserGroup();
        usergroup.setName( "Test UserGroup" );
        new UserGroupHelper().addUserGroup( usergroup );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

    public void testAddUserGroup()
    {
        UserGroup p = new UserGroup();
        p.setName( "Test Group" );

        try
        {
            new UserGroupHelper().addUserGroup( p );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testAddUserGroup_DiffrentCompany()
    {
        UserGroup usergroup = new UserGroup();
        usergroup.setName( "Test UserGroup" );
        SessionUtil.setCompany( companyNegTest );
        try
        {
            new UserGroupHelper().addUserGroup( usergroup );
        }
        catch ( Exception e )
        {
            fail();
        }
    }

    public void testDuplicateUserGroup()
    {
        UserGroup usergroup = new UserGroup();
        usergroup.setName( "Duplicate UserGroup" );
        UserGroup usergroup2 = new UserGroup();
        usergroup2.setName( "Duplicate UserGroup" );
        UserGroupHelper helper = new UserGroupHelper();
        try
        {
            helper.addUserGroup( usergroup );
        }
        catch ( Exception e )
        {
            fail();
        }
        try
        {
            helper.addUserGroup( usergroup2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetUserGroup_DifferentCompany()
    {
        UserGroup usergroup = new UserGroup();
        UserGroupHelper usergroupHelper = new UserGroupHelper();
        usergroup.setName( "UserGroup1" );
        try
        {
            usergroupHelper.addUserGroup( usergroup );
            assertNotNull( usergroupHelper.getUserGroup( "UserGroup1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( usergroupHelper.getUserGroup( "UserGroup1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.UserGroupHelper#addUserGroup(in.solpro.nucleus.apps.common.UserGroup)}.
     */

    public void testAddUserGroup_NoName()
    {
        UserGroup usergroup = new UserGroup();

        try
        {
            new UserGroupHelper().addUserGroup( usergroup );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.UserGroupHelper#addUserGroup(in.solpro.nucleus.apps.common.UserGroup)}.
     * @throws Exception
     */
    public void testgetUserGroupById() throws Exception
    {
        UserGroupHelper helper = new UserGroupHelper();
        UserGroup usergroup = helper.getUserGroup( helper.getUserGroup( "Test UserGroup" ).getId() );
        if ( usergroup == null )
        {
            fail();
        }
        usergroup.validateAndUpdate();
        assertEquals( usergroup.getId(), helper.getUserGroup( "Test UserGroup" ).getId() );
        assertEquals( usergroup.getName(), helper.getUserGroup( "Test UserGroup" ).getName() );

    }

    public void testGetUserGroup() throws Exception
    {
        UserGroupHelper helper = new UserGroupHelper();
        UserGroup usergroup = helper.getUserGroup( "Test UserGroup" );
        if ( usergroup == null )
        {
            fail();
        }
        usergroup.validateAndUpdate();
        assertEquals( usergroup.getId(), usergroup.getId() );
        assertEquals( usergroup.getName(), usergroup.getName() );
    }

    /**
     * Test method for
     * {@link in.solpro.nucleus.apps.core.dbhelper.UserGroupHelper#addUserGroup(in.solpro.nucleus.apps.common.UserGroup)}.
     */

    public void testGetUserGroups()
    {
        List<UserGroup> usergroups;

        try
        {
            UserGroupHelper helper = new UserGroupHelper();
            UserGroup usergroup = new UserGroup();
            usergroup.setName( "LEDGERGROUP1" );
            usergroup.setCompany( SessionUtil.getCompany() );
            helper.addUserGroup( usergroup );
            UserGroup usergroup1 = new UserGroup();
            usergroup1.setName( "LEDGERGROUP2" );
            usergroup1.setCompany( SessionUtil.getCompany() );
            helper.addUserGroup( usergroup1 );
            usergroups = helper.getUserGroups();
            if ( usergroups == null )
            {
                fail();
            }
            for ( int i = 0; i < usergroups.size(); i++ )
            {
                usergroups.get( i ).validateAndUpdate();
                System.out.println( "UserGroup name:" + usergroups.get( i ).getName() + "UserGroup ID:"
                        + usergroups.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateUserGroup
     * @throws Exception
     */

    public void testUpdateUserGroup() throws Exception
    {

        UserGroupHelper helper = new UserGroupHelper();
        UserGroup usergroup = (UserGroup) helper.getUserGroup( "Test UserGroup" );
        usergroup.setName( "XYZ_GROUP" );
        helper.updateUserGroup( usergroup );
        UserGroup usergroup2 = (UserGroup) helper.getUserGroup( "XYZ_GROUP" );
        if ( usergroup2 == null )
        {
            fail();
        }
        assertEquals( usergroup2.getName(), "XYZ_GROUP" );
    }

}