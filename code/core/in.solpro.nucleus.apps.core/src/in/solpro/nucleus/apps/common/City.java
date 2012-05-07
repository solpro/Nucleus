package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "City", uniqueConstraints = @UniqueConstraint(columnNames = {"name","company_id","district_id"}))
public class City extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String pincode;

    @JoinColumn(nullable=false) 
    private District district = null;

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

    /*
     * public int getDistrict() { return district; } public void setDistrict(int district) { this.district = district; }
     */

    public District getDistrict()
    {
        return district;
    }

    public void setDistrict( District district )
    {
        this.district = district;
    }

    public String toString()
    {
        return this.name;
    }

    public void setPincode( String pincode )
    {
        this.pincode = pincode;
    }

    public String getPincode()
    {
        if ( pincode == null )
            return "";
        return pincode;
    }

}
