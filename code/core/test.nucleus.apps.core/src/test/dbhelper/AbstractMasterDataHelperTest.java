package test.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;
import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class AbstractMasterDataHelperTest extends TestCase
{

    public static final String COMPANY_NAME = "NAME1";

    public static final String COMPANY_PRINT_NAME = "NAME PRINT1";

    protected Company company;

    protected Company companyNegTest;

    @Override
    protected void setUp() throws Exception
    {
        CompanyHelper helper = new CompanyHelper();
        company = new Company();
        company.setName( COMPANY_NAME );
        company.setPrintname( COMPANY_PRINT_NAME );
        helper.addCompany( company );
        SessionUtil.setCompany( company );

        companyNegTest = new Company();
        companyNegTest.setName( COMPANY_NAME + "NEGATIVE " );
        companyNegTest.setPrintname( COMPANY_PRINT_NAME );
        helper.addCompany( companyNegTest );

    }

    @Override
    protected void tearDown() throws Exception
    {
        CompanyHelper helper = new CompanyHelper();
        helper.deleteCompany( company );
        helper.deleteCompany( companyNegTest );
        SessionUtil.setCompany( null );
    }
}