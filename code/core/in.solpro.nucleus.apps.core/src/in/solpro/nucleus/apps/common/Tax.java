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
@Table(name = "Tax", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class Tax extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private double value;

    @JoinColumn(nullable=false)
    private TaxType type;

    @JoinColumn(nullable=false)
    private Ledger ledger;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue( double value )
    {
        this.value = value;
    }

    public String toString()
    {
        return this.name;
    }

    public void setType( TaxType type )
    {
        this.type = type;
    }

    public TaxType getType()
    {
        return type;
    }

    public void setType( int type )
    {
        TaxType typ = TaxType.getTaxType( type );
        if ( typ != null )
        {
            this.type = typ;
        }
        else
        {
            this.type = TaxType.PERCENT;
        }
    }

    public void setLedger( Ledger ledger )
    {
        this.ledger = ledger;
    }

    public Ledger getLedger()
    {
        return ledger;
    }
}
