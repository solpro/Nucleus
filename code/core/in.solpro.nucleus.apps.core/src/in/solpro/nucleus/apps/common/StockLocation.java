package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "StockLocation", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class StockLocation extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private StockLocationType type = null;

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

    public StockLocationType getType()
    {
        return type;
    }

    public void setType( StockLocationType stocklocationtype )
    {
        this.type = stocklocationtype;
    }

    public String toString()
    {
        return this.name;
    }
}
