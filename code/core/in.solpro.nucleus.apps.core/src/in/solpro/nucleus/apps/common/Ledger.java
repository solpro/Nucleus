package in.solpro.nucleus.apps.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Ledger", uniqueConstraints = @UniqueConstraint(columnNames = {"name","company_id"}))
public class Ledger extends BaseObject
{
    public static final int LOCKED_NO = 0;

    public static final int LOCKED_YES = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(nullable = false)
    protected String name;

    protected String printname;

    @JoinColumn(nullable=false)
    protected LedgerGroup ledgergroup = null;

    protected String description;

    protected String location;

    protected double openingBalance;

    protected int locked = LOCKED_NO;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "LEDGER_LABEL", joinColumns = {@JoinColumn(name = "ledger")}, inverseJoinColumns = {@JoinColumn(name = "label")})
    protected Set<LedgerLabel> labels = new HashSet<LedgerLabel>();

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

    public String getPrintname()
    {
        return printname;
    }

    public void setPrintname( String printname )
    {
        this.printname = printname;
    }

    public LedgerGroup getLedgergroup()
    {
        return ledgergroup;
    }

    public void setLedgergroup( LedgerGroup ledgerGroup )
    {
        this.ledgergroup = ledgerGroup;
    }

    public String toString()
    {
        return this.name;
    }

    public String getDescription()
    {
        return description != null ? description : "";
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation( String location )
    {
        this.location = location;
    }

    public double getOpeningBalance()
    {
        return openingBalance;
    }

    public void setOpeningBalance( double openingBalance )
    {
        this.openingBalance = openingBalance;
    }

    public boolean getLocked()
    {
        return locked == LOCKED_YES ? true : false;
    }

    public void setLocked( boolean locked )
    {
        this.locked = locked == true ? LOCKED_YES : LOCKED_NO;
    }

    public void setLock( int locked )
    {
        this.locked = locked == LOCKED_YES ? LOCKED_YES : LOCKED_NO;
    }

    public void setLabels( Set<LedgerLabel> labels )
    {
        this.labels = labels;
    }

    public Set<LedgerLabel> getLabels()
    {
        return labels;
    }

}
