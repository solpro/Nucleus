/**
 * 
 */
package in.solpro.nucleus.apps.common;

import in.solpro.nucleus.apps.core.session.SessionUtil;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;

/**
 * @author asheesh
 */
@MappedSuperclass
public class BaseObject
{

    /**
     * Company
     */

    @JoinColumn(nullable = false)
    protected Company company;

    public Company getCompany()
    {
        return company;
    }

    public void setCompany( Company company )
    {
        this.company = company;
    }

    public void validateAndUpdate() throws Exception
    {
        if ( getCompany() == null )
        {
            setCompany( SessionUtil.getCompany() );
        }
        else
        {
            if ( getCompany().getId() != SessionUtil.getCompany().getId() )
            {
                System.out.println("Invlaid Company info");
                throw new Exception( "Invlaid Company info" );
            }
        }

    }
}
