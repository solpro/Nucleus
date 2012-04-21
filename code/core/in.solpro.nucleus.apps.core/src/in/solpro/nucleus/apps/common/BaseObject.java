/**
 * 
 */
package in.solpro.nucleus.apps.common;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * @author asheesh
 *
 */
@MappedSuperclass
public class BaseObject
{

    /**
     * Company
     */
 
    @JoinColumn( nullable=false)
    protected Company company;

    public Company getCompany()
    {
        return company;
    }

    public void setCompany( Company company )
    {
        this.company = company;
    }
}
