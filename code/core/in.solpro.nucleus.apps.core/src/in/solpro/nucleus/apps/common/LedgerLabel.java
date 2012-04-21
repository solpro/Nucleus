package in.solpro.nucleus.apps.common;

import java.util.Set;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class LedgerLabel extends BaseObject
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int labelID;

    @Column(nullable = false)
    private String labelName;
    
    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(name = "LEDGER_LABEL",
    joinColumns = {
    @JoinColumn(name="label") 
    },
    inverseJoinColumns = {
    @JoinColumn(name="ledger")
    }
    )
    private Set<Ledger> ledgers;

    public void setLabelName( String labelName )
    {
        this.labelName = labelName;
    }

    public String getLabelName()
    {
        return labelName;
    }

    public void setLabelID( int labelID )
    {
        this.labelID = labelID;
    }

    public int getLabelID()
    {
        return labelID;
    }

    public String toString()
    {
        return this.labelName;
    }

    public Set<Ledger> getLedgers()
    {
        return ledgers;
    }

    public void setLedgers( Set<Ledger> ledgers )
    {
        this.ledgers = ledgers;
    }

}
