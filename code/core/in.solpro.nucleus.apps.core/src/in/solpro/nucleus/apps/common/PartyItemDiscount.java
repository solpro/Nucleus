package in.solpro.nucleus.apps.common;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PartyItemDiscount", uniqueConstraints = @UniqueConstraint(columnNames = {"ledger_id", "item_id","company_id"}))
public class PartyItemDiscount extends Discount
{
    private Item item;

    private Party party;

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

    public Party getParty()
    {
        return party;
    }

    public void setParty( Party party )
    {
        this.party = party;
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
