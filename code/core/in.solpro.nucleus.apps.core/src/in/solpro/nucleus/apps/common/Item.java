package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Item", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id", "itemcompany_id"}))
public class Item extends BaseObject
{
    public static final int LOCKED_NO = 0;

    public static final int LOCKED_YES = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private ItemCompany itemcompany;

    private ItemGroup itemgroup;

    private Unit unit;

    private String sku;

    private double mrp;

    private ItemType type = ItemType.TAXABLE;

    private double openingstock;

    private int tax;

    private int locked = LOCKED_NO;

    private StockLocation stocklocation;

    private Ledger tradingaccount;

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

    public ItemCompany getItemCompany()
    {
        return itemcompany;
    }

    public void setItemCompany( ItemCompany itemcompany )
    {
        this.itemcompany = itemcompany;
    }

    public ItemGroup getItemgroup()
    {
        return itemgroup;
    }

    public void setItemgroup( ItemGroup itemgroup )
    {
        this.itemgroup = itemgroup;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit( Unit unit )
    {
        this.unit = unit;
    }

    public String getSku()
    {
        return sku;
    }

    public void setSku( String sku )
    {
        this.sku = sku;
    }

    public double getMrp()
    {
        return mrp;
    }

    public void setMrp( double mrp )
    {
        this.mrp = mrp;
    }

    public String toString()
    {
        return this.sku;
    }

    public boolean getLocked()
    {
        return locked == LOCKED_YES ? true : false;
    }

    public void setLocked( boolean locked )
    {
        this.locked = locked == true ? LOCKED_YES : LOCKED_NO;
    }

    public void setLocked( int locked )
    {
        this.locked = locked == LOCKED_YES ? LOCKED_YES : LOCKED_NO;
    }

    public void setOpeningstock( double openingstock )
    {
        this.openingstock = openingstock;
    }

    public double getOpeningstock()
    {
        return openingstock;
    }

    public void setTax( int tax )
    {
        this.tax = tax;
    }

    public int getTax()
    {
        return tax;
    }

    public void setType( ItemType type )
    {
        this.type = type;
    }

    public void setType( int type )
    {
        this.type = ItemType.getItemType( type );
    }

    public ItemType getType()
    {
        return type;
    }

    public void setStocklocation( StockLocation stocklocation )
    {
        this.stocklocation = stocklocation;
    }

    public StockLocation getStocklocation()
    {
        return stocklocation;
    }

    /**
     * @param tradingaccount the tradingaccount to set
     */
    public void setTradingaccount( Ledger tradingaccount )
    {
        this.tradingaccount = tradingaccount;
    }

    /**
     * @return the tradingaccount
     */
    public Ledger getTradingaccount()
    {
        return tradingaccount;
    }
}
