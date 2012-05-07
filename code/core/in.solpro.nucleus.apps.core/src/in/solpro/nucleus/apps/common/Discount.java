package in.solpro.nucleus.apps.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Discount extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    protected DiscountType type;

    protected double value;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public DiscountType getType()
    {
        return type;
    }

    public void setType( DiscountType type )
    {
        this.type = type;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue( double value )
    {
        this.value = value;
    }

}
