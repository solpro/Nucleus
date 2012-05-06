package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "State", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "country_id", "company_id"}))
public class State extends BaseObject
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @JoinColumn(nullable=false)
    private Country country = null;

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

    public Country getCountry()
    {
        return country;
    }

    public void setCountry( Country country )
    {
        this.country = country;
    }

    public String toString()
    {
        return this.name;
    }

}
