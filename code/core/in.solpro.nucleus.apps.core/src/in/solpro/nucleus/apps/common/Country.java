package in.solpro.nucleus.apps.common;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Country", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "company_id"}))
public class Country extends BaseObject
{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

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

    public String toString()
    {
        return this.name;
    }
}
