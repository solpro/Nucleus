package in.solpro.nucleus.apps.core.session;

import in.solpro.nucleus.apps.common.Company;

public class SessionUtil
{
    public static Company getCompany()
    {
        SSession session = SSession.getInstance();
        return session.getCompany();
    }

    public static void setCompany(Company company)
    {
        SSession session = SSession.getInstance();
        session.setCompany(company);
    }
}
