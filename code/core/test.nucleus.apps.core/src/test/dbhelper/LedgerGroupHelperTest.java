/**
 * 
 */
package test.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.LedgerGroup;
import in.solpro.nucleus.apps.common.LedgerGroupType;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;
import in.solpro.nucleus.apps.core.dbhelper.LedgerGroupHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class LedgerGroupHelperTest extends TestCase
{
    
    public void testAddLedgerGroup()
    {
        LedgerGroup p = new LedgerGroup();
        p.setName( "Sam" );
        p.setCompany( CommonTestData.abcCorpComp );
        p.setType( LedgerGroupType.ASSETS );
        LedgerGroupHelper.addLedgerGroup( p );
    }

    public void testGetLedgerGroup()
    {
        LedgerGroupHelper p = new LedgerGroupHelper();
        LedgerGroup g = p.getLedgerGroup( "Sam",CommonTestData.abcCorpComp.getId() );
        assertNotNull( g );
        assertEquals( g.getName(), "Sam" );
    }

    public void testGetLedgerGroups()
    {
        LedgerGroupHelper p = new LedgerGroupHelper();
        List<LedgerGroup> g = p.getLedgerGroups( CommonTestData.abcCorpComp.getId() );
        for ( int i = 0; i < g.size(); i++ )
            System.out.println( "GROUP_NAME:" + g.get( i ).getName() + " of Company:" + g.get( i ).getCompany().getName() );
        // System.out.println("ID:"+g.getCompany().getId());
    }

}