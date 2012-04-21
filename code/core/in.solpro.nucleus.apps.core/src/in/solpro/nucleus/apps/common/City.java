package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String pincode;

    private District objDistrict = null;

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

    public District getObjDistrict()
    {
        return objDistrict;
    }

    public void setObjDistrict( District district )
    {
        this.objDistrict = district;
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
