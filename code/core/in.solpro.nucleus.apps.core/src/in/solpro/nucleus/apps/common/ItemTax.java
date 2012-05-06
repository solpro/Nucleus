package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ItemTax", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class ItemTax extends BaseObject
{
    @Id
    @GeneratedValue
    private int id;

    Item item;

    @Column(nullable = false)
    private String name;

    private String description;

    private double value;

    private Ledger ledger;
    
    private TaxType taxtype;

    public TaxType getType()
    {
        return taxtype;
    }

    public void setType( TaxType taxtype )
    {
        this.taxtype = taxtype;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem( Item item )
    {
        this.item = item;
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

    public void setLedger( Ledger ledger )
    {
        this.ledger = ledger;
    }

    public Ledger getLedger()
    {
        return ledger;
    }
}
