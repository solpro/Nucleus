package in.solpro.nucleus.apps.core.session;

import in.solpro.nucleus.apps.common.Company;

public class SSession
{
    private static SSession instance;

    private Company company;

    public static SSession getInstance()
    {
        if ( instance == null )
        {
            instance = new SSession();
        }
        return instance;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany( Company company )
    {
        this.company = company;
    }

}
