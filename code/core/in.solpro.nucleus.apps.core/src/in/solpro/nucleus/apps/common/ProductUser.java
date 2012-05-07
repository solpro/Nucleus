package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ProductUser", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "password", "company_id"}))
public class ProductUser extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    private String firstname;

    private String middlename;

    private String lastname;

    @Column(nullable = false)
    private String password;

    private UserGroup usergroup = null;

    private String description;

    private String address1;

    private String address2;

    private String address3;

    private String phone;

    private String fax;

    private String mobile;

    private String email;

    private City city = null;

    private int readOnlyFalgs = 0;

    private boolean enabled = true;

    private static final int PERMISSIONS_READ_ONLY = 1;

    private static final int USER_GROUP_READ_ONLY = 2;

    private static final int USER_CANNOT_BE_DELETED = 4;

    private static final int USER_CANNOT_BE_DISABLED = 8;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        if ( null == username )
            this.username = new String();
        else
            this.username = username;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname( String firstname )
    {
        if ( null == firstname )
            this.firstname = new String();
        else
            this.firstname = firstname;
    }

    public String getMiddlename()
    {
        return middlename;
    }

    public void setMiddlename( String middlename )
    {
        if ( null == middlename )
            this.middlename = new String();
        else
            this.middlename = middlename;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname( String lastname )
    {
        if ( null == lastname )
            this.lastname = new String();
        else
            this.lastname = lastname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        if ( null == password )
            this.password = new String();
        else
            this.password = password;
    }

    public UserGroup getUserGroup()
    {
        return usergroup;
    }

    public void setUserGroup( UserGroup usergroup )
    {
        this.usergroup = usergroup;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        if ( null == description )
            this.description = new String();
        else
            this.description = description;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1( String address1 )
    {
        if ( null == address1 )
            this.address1 = new String();
        else
            this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2( String address2 )
    {
        if ( null == address2 )
            this.address2 = new String();
        else
            this.address2 = address2;
    }

    public String getAddress3()
    {
        return address3;
    }

    public void setAddress3( String address3 )
    {
        if ( null == address3 )
            this.address3 = new String();
        else
            this.address3 = address3;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        if ( null == phone )
            this.phone = new String();
        else
            this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax( String fax )
    {
        if ( null == fax )
            this.fax = new String();
        else
            this.fax = fax;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile( String mobile )
    {
        if ( null == mobile )
            this.mobile = new String();
        else
            this.mobile = mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        if ( null == email )
            this.email = new String();
        else
            this.email = email;
    }

    public City getCity()
    {
        return city;
    }

    public void setCity( City city )
    {
        this.city = city;
    }

    public int getReadOnlyFlags()
    {
        return readOnlyFalgs;
    }

    public void setReadOnlyFlags( int readOnlyFlags )
    {
        this.readOnlyFalgs = readOnlyFlags;
    }

    public boolean isPermissionsEditable()
    {
        return ( ( readOnlyFalgs & ProductUser.PERMISSIONS_READ_ONLY ) == 0 );
    }

    public boolean isUserGroupEditable()
    {
        return ( ( readOnlyFalgs & ProductUser.USER_GROUP_READ_ONLY ) == 0 );
    }

    // return true if user cannot be deleted
    public boolean isUserReadOnly()
    {
        return ( ( readOnlyFalgs & ProductUser.USER_CANNOT_BE_DELETED ) != 0 );
    }

    // return true if user can be disabled
    public boolean canUserDisabled()
    {
        return ( ( readOnlyFalgs & ProductUser.USER_CANNOT_BE_DISABLED ) == 0 );
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean enabled )
    {
        if ( !canUserDisabled() && false == enabled )
            return;

        this.enabled = enabled;
    }

    public String toString()
    {
        return this.username;
    }
}
