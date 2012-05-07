package in.solpro.nucleus.apps.common;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PartyAddress", uniqueConstraints = @UniqueConstraint(columnNames = {"address1","address2","address3","company_id"}))
public class PartyAddress extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // private Party party;

    private AddressType type = null;

    private String contactperson;

    private String address1;

    private String address2;

    private String address3;

    private String phone;

    private String fax;

    private String mobile;

    private String email;

    private String description;

    private City city = null;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "PARTY_ADDRESS", joinColumns = {@JoinColumn(name = "address")}, inverseJoinColumns = {@JoinColumn(name = "party")})
    @JoinColumn(nullable=false)
    private Party party;
    //private Set<Party> parties = new HashSet<Party>();

    private PartyAddressTag tag = PartyAddressTag.NONE;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Party getParty()
    {
        return party;
    }

    public void setParty( Party party )
    {
        this.party = party;
        party.getAddressList().add( this );
    }

   /* public void setParties( Set<Party> parties )
    {
        this.parties = parties;
    }
    
    public void addParty( Party party )
    {
        this.parties.add( party );
    }
    

    public Set<Party> getParties()
    {
        return this.parties;
    }*/

    /*
     * public int getType() { return type; } public void setType(int type) { this.type = type; }
     */

    public AddressType getType()
    {
        return type;
    }

    public void setType( AddressType addresstype )
    {
        this.type = addresstype;
        // this.type=addresstype.getId();
    }

    public String getContactperson()
    {
        return contactperson;
    }

    public void setContactperson( String contactperson )
    {
        this.contactperson = contactperson;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1( String address1 )
    {
        this.address1 = address1;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2( String address2 )
    {
        this.address2 = address2;
    }

    public String getAddress3()
    {
        return address3;
    }

    public void setAddress3( String address3 )
    {
        this.address3 = address3;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone( String phone )
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax( String fax )
    {
        this.fax = fax;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile( String mobile )
    {
        this.mobile = mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    /*
     * public int getCity() { return city; } public void setCity(int city) { this.city = city; }
     */

    public City getCity()
    {
        return city;
    }

    public void setCity( City city )
    {
        this.city = city;
        // this.city=city.getId();
    }

    public void setTag( PartyAddressTag tag )
    {
        this.tag = tag;
    }

    public PartyAddressTag getTag()
    {
        return tag;
    }
}
