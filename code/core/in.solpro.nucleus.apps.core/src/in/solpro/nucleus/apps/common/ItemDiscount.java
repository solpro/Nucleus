package in.solpro.nucleus.apps.common;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ItemDiscount", uniqueConstraints = @UniqueConstraint(columnNames = {"item_id","datefrom","dateto"}))
public class ItemDiscount extends Discount
{

    private Item item;

    private Date datefrom;

    private Date dateto;

    public Date getDatefrom()
    {
        return datefrom;
    }

    public void setDatefrom( Date datefrom )
    {
        this.datefrom = datefrom;
    }

    public Date getDateto()
    {
        return dateto;
    }

    public void setDateto( Date dateto )
    {
        this.dateto = dateto;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem( Item item )
    {
        this.item = item;
    }

}
