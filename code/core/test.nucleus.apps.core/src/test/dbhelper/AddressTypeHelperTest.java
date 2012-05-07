package test.dbhelper;

import in.solpro.nucleus.apps.common.AddressType;
import in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper;
import in.solpro.nucleus.apps.core.session.SessionUtil;

import java.util.List;

/**
 * @author asheesh
 */
public class AddressTypeHelperTest extends AbstractMasterDataHelperTest
{
    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        AddressType addresstype = new AddressType();
        addresstype.setName( "Test AddressType" );
        new AddressTypeHelper().addAddressType( addresstype );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.CompanyHelper#addCompany(in.solpro.nucleus.apps.common.Company)}.
     */

   public void testAddAddressType()
    {
       AddressType addresstype = new AddressType();
       addresstype.setName( "Test AddressType1" );
        try
        {
            new AddressTypeHelper().addAddressType( addresstype );
        }
        catch ( Exception e )
        {
            fail();
        }
    }
   
   public void testAddAddressType_DiffrentCompany()
   {
       AddressType addresstype = new AddressType();
       addresstype.setName( "Test AddressType" );
       SessionUtil.setCompany( companyNegTest );
       try
       {
           new AddressTypeHelper().addAddressType(addresstype );
       }
       catch ( Exception e )
       {
           fail();
       }
   }
  

    public void testDuplicateAddressType() 
    {
        AddressType addresstype = new AddressType();
        addresstype.setName( "Duplicate AddressType" );
        AddressType addresstype2 = new AddressType();
        addresstype2.setName( "Duplicate AddressType" );
        AddressTypeHelper helper = new AddressTypeHelper();
        try
        {
            helper.addAddressType( addresstype );
        }
        catch ( Exception e )
        {
            fail();
        }
          try
        {
            helper.addAddressType( addresstype2 );
            fail();
        }
        catch ( Exception e )
        {

        }

    }

    public void testGetAddressType_DifferentCompany()
    {
        AddressType addresstype = new AddressType();
        AddressTypeHelper addresstypeHelper = new AddressTypeHelper();
        addresstype.setName( "AddAddressType1" );
        try
        {
            addresstypeHelper.addAddressType( addresstype );
            assertNotNull( addresstypeHelper.getAddressType( "AddAddressType1" ) );
            SessionUtil.setCompany( companyNegTest );
            assertNull( addresstypeHelper.getAddressType( "AddAddressType1" ) );
            SessionUtil.setCompany( company );
        }
        catch ( Exception e )
        {
            fail();
        }

    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     */

    public void testAddAddressType_NoName()
    {
        AddressType addresstype = new AddressType();

        try
        {
            new AddressTypeHelper().addAddressType( addresstype );
            fail();
        }
        catch ( Exception e )
        {

        }
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     * @throws Exception
     */
    public void testgetAddressTypeById() throws Exception
    {
        AddressTypeHelper helper = new AddressTypeHelper();
        AddressType addresstype = helper.getAddressType( helper.getAddressType( "Test AddressType" ).getId() );
        if ( addresstype == null )
        {
            fail();
        }
        addresstype.validateAndUpdate();
        assertEquals( addresstype.getId(), helper.getAddressType( "Test AddressType" ).getId() );
        assertEquals( addresstype.getName(), helper.getAddressType( "Test AddressType" ).getName() );

    }

    public void testGetAddressType() throws Exception
    {
        AddressTypeHelper helper = new AddressTypeHelper();
        AddressType addresstype = helper.getAddressType( "Test AddressType" );
        if ( addresstype == null )
        {
            fail();
        }
        addresstype.validateAndUpdate();
        assertEquals( addresstype.getId(), addresstype.getId() );
        assertEquals( addresstype.getName(), addresstype.getName() );
    }

    /**
     * Test method for {@link in.solpro.nucleus.apps.core.dbhelper.AddressTypeHelper#addAddressType(in.solpro.nucleus.apps.common.AddressType)}.
     */

    public void testGetAddressTypes()
    {
        List<AddressType> addresstypes;

        try
        {
            AddressTypeHelper helper = new AddressTypeHelper();
            AddressType addresstype=new AddressType();
            addresstype.setName( "INDIA" );
            addresstype.setCompany( SessionUtil.getCompany() );
            helper.addAddressType( addresstype );
            AddressType addresstype1=new AddressType();
            addresstype1.setName( "CHINA" );
            addresstype1.setCompany( SessionUtil.getCompany() );
            helper.addAddressType( addresstype1 );
            addresstypes = helper.getAddressTypes();
            if ( addresstypes == null )
            {
                fail();
            }
            System.out.println("addresstypes SIZE:"+addresstypes.size());
            for ( int i = 0; i < addresstypes.size(); i++ )
            {
                addresstypes.get( i ).validateAndUpdate();
                System.out.println( "AddressType name:" + addresstypes.get( i ).getName() + "AddressType ID:" + addresstypes.get( i ).getId() );
            }
        }
        catch ( Exception e )
        {
            System.out.print( "error:" );
        }
    }

    /**
     * This test verifies updateAddressType
     * @throws Exception
     */

    public void testUpdateAddressType() throws Exception
    {

        AddressTypeHelper helper = new AddressTypeHelper();
        AddressType addresstype = (AddressType) helper.getAddressType( "Test AddressType" );
        addresstype.setName( "XYZ" );
        helper.updateAddressType( addresstype );
        AddressType addresstype2 = (AddressType) helper.getAddressType( "XYZ" );
        if ( addresstype2 == null )
        {
            fail();
        }
        assertEquals( addresstype2.getName(), "XYZ" );
   }

}