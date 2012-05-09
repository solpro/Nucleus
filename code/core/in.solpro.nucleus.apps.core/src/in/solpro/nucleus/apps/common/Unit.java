package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Unit", uniqueConstraints = @UniqueConstraint(columnNames = {"name","company_id"}))
public class Unit extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private double factor;

    private Unit parentObj;

    public Unit getParentObj()
    {
        return parentObj;
    }

    public void setParentObj( Unit parentObj )
    {
        this.parentObj = parentObj;
    }

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

    public double getFactor()
    {
        return factor;
    }

    public void setFactor( double factor )
    {
        this.factor = factor;
    }

    public String toString()
    {
        return this.name;
    }
}
