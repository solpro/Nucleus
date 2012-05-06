package in.solpro.nucleus.apps.common;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Company", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class Company
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false)
    private String printname;

    private String pan;

    private String tan;

    private String vat;

    private String tot;

    private String servicetax;

    private String licensenumber;

    private String licensedate;

    private String contactperson;

    private String address1;

    private String address2;

    private String address3;

    private String phone;

    private String fax;

    private String mobile;

    private String email;

    private String description;

    private String ruleConfiguration;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getPrintname()
    {
        return printname;
    }

    public void setPrintname( String printname )
    {
        this.printname = printname;
    }

    public String getPan()
    {
        return pan;
    }

    public void setPan( String pan )
    {
        this.pan = pan;
    }

    public String getTan()
    {
        return tan;
    }

    public void setTan( String tan )
    {
        this.tan = tan;
    }

    public String getVat()
    {
        return vat;
    }

    public void setVat( String vat )
    {
        this.vat = vat;
    }

    public String getTot()
    {
        return tot;
    }

    public void setTot( String tot )
    {
        this.tot = tot;
    }

    public String getServicetax()
    {
        return servicetax;
    }

    public void setServicetax( String servicetax )
    {
        this.servicetax = servicetax;
    }

    public String getLicensenumber()
    {
        return licensenumber;
    }

    public void setLicensenumber( String licensenumber )
    {
        this.licensenumber = licensenumber;
    }

    public String getLicensedate()
    {
        return licensedate;
    }

    public void setLicensedate( String licensedate )
    {
        this.licensedate = licensedate;
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

    public String toString()
    {
        return this.name;
    }

    public void setRuleConfiguration( String ruleConfiguration )
    {
        this.ruleConfiguration = ruleConfiguration;
    }

    public String getRuleConfiguration()
    {
        return ruleConfiguration;
    }
}
