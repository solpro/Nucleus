package in.solpro.nucleus.apps.common;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "LedgerGroup", uniqueConstraints = @UniqueConstraint(columnNames =
{ "id", "company" }))
public class LedgerGroup extends BaseObject
{
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    /**
     * Name.
     */
    @Column(nullable=false)
    private String name;

    /**
     * LedgerGroupType.
     */
    @Column(nullable=false)
    private LedgerGroupType type;

    /**
     * Description.
     */
    private String description;

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description != null ? description : "";
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the type
     */
    public LedgerGroupType getType()
    {
        return type;
    }

    public int getTypeInt()
    {
        return type.toInt();
    }

    /**
     * @param description the description to set
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * @param id the id to set
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @param type the type to set
     */
    public void setType( LedgerGroupType type )
    {
        this.type = type;
    }

    /**
     * @param type
     */
    public void setTypeInt( int type )
    {
        this.type = LedgerGroupType.getTypeFromInt( type );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return this.name;
    }
}
