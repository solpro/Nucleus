package in.solpro.nucleus.apps.common;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ItemBatch", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id", "item_id"}))
public class ItemBatch extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(nullable=false)
    private Item item = null;

    @Column(nullable = false)
    private String name;

    private Date expirydate;

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    /*
     * public int getItem() { return item; } public void setItem(int item) { this.item = item; }
     */

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

    public Date getExpirydate()
    {
        return expirydate;
    }

    public void setExpirydate( Date expirydate )
    {
        this.expirydate = expirydate;
    }
}
