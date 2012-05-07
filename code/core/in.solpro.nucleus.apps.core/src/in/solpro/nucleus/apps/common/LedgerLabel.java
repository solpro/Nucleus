package in.solpro.nucleus.apps.common;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LedgerLabel", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))

public class LedgerLabel extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "LEDGER_LABEL", joinColumns = {@JoinColumn(name = "label")}, inverseJoinColumns = {@JoinColumn(name = "ledger")})
    private Set<Ledger> ledgers = new HashSet<Ledger>();

    public void setName( String name )
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setId( int ID )
    {
        this.id = ID;
    }

    public int getId()
    {
        return id;
    }

    public String toString()
    {
        return this.name;
    }
    

    public Set<Ledger> getLedgers()
    {
        return ledgers;
    }
    
    public void setLedger( Ledger ledger )
    {
        ledgers.add( ledger );
    }

    public void setLedgers( Set<Ledger> ledgers )
    {
        this.ledgers = ledgers;
    }

}
