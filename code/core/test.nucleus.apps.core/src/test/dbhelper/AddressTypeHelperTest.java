package test.dbhelper;


import in.solpro.nucleus.apps.common.AddressType;
import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper;
import in.solpro.nucleus.apps.core.dbhelper.CompanyHelper;

import java.util.List;

import junit.framework.TestCase;

/**
 * @author asheesh
 */
public class AddressTypeHelperTest extends TestCase
{
	 Company cmpny; 
    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     */
	
    public void testAddAddressType()
    {
        AddressType p = new AddressType();
       p.setName("RESIDENCIAL");
       p.setCompany(CommonTestData.abcCorpComp);
        AddressTypeHelper.addAddressType( p );
    }

    /**
     **/

    public void testAddAddressType_NoName()
    {
        AddressType p = new AddressType();
        p.setDescription("RESIDENTIAL ADDRESS");
        p.setCompany(CommonTestData.abcCorpComp);
        try
        {
            AddressTypeHelper.addAddressType( p );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     */
    public void testgetAddressTypeById()
    {
    	AddressTypeHelper c = new AddressTypeHelper();
        cmpny=new CompanyHelper().getCompany(CommonTestData.addresstype.getCompany().getName());
        
    	Integer id=c.getAddressType(CommonTestData.addresstype.getName(),cmpny.getId()).getId();
        AddressType p = c.getAddressTypeById(id);
        if ( p == null )
        {
            fail();
        }
        //assertEquals( p.getId(), CommonTestData.abcCorpComp.getId() );
        assertEquals( p.getName(), CommonTestData.addresstype.getName() );
   	
    }
    public void testGetAddressType()
    {
        AddressTypeHelper c = new AddressTypeHelper();
        cmpny=new CompanyHelper().getCompany(CommonTestData.addresstype.getCompany().getName());
        AddressType p = c.getAddressType(CommonTestData.addresstype.getName(),cmpny.getId());
        if ( p == null )
        {
            fail();
        }
        assertEquals( p.getCompany().getId(), CommonTestData.abcCorpComp.getId() );
        assertEquals( p.getName(),CommonTestData.addresstype.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     */

    public void testGetCompanies()
    {
        List<AddressType> p;
        cmpny=new CompanyHelper().getCompany(CommonTestData.addresstype.getCompany().getName());
        try
        {
            AddressTypeHelper c = new AddressTypeHelper();
            
            p = c.getAddressTypes(cmpny.getId());
            if ( p == null )
            {
                fail();
            }
            for ( int i = 0; i < p.size(); i++ )
                System.out.println( "AddressType name:" + p.get( i ).getName() + "\nAddressType ID:" + p.get( i ).getId() );
          
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateAddressType
     */

    public void testUpdateAddressType()
    {

        AddressTypeHelper c = new AddressTypeHelper();
        cmpny=new CompanyHelper().getCompany(CommonTestData.addresstype.getCompany().getName());
        AddressType p = (AddressType) c.getAddressType(CommonTestData.addresstype.getName(),cmpny.getId() );
        p.setDescription("UPDATED");
        c.save( p );
        AddressType p2 = (AddressType) c.getAddressType(CommonTestData.addresstype.getName(),cmpny.getId() );
        if ( p2 == null )
        {
            fail();
        }
        assertEquals( p2.getDescription(), "UPDATED" );
    }

}
