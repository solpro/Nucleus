package in.solpro.nucleus.apps.core.dbhelper;
import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.AddressType;



public class AddressTypeHelper extends GenericHelper{

	public AddressTypeHelper()
	{
		super(AddressType.class);
	}
	
	public static void addAddressType(AddressType p)
	{
		GenericHelper g = new GenericHelper(AddressType.class);
		g.save(p);
		System.out.println("AddressType Saved");
	}
	
	public static AddressType getAddressTypeById(Integer id)
	{
		GenericHelper g = new GenericHelper(AddressType.class);
		AddressType AddressType=(AddressType)g.find(id);
	    return AddressType;
	}
	
	public AddressType getAddressType(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM AddressType pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (AddressType) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<AddressType> getAddressTypes(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM AddressType pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateAddressType(AddressType group)
	{
	   	GenericHelper g=new GenericHelper(AddressType.class);
	   	g.update(group);
	}
	
	
	
}
