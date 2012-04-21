package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.ItemCompany;



public class ItemCompanyHelper extends GenericHelper{

	public ItemCompanyHelper()
	{
		super(ItemCompany.class);
	}
	
	public static void addItemCompany(ItemCompany p)
	{
		GenericHelper g = new GenericHelper(ItemCompany.class);
		g.save(p);
		System.out.println("ItemCompany Saved");
	}
	
	public static ItemCompany getItemCompanyById(Integer id)
	{
		GenericHelper g = new GenericHelper(ItemCompany.class);
		ItemCompany ItemCompany=(ItemCompany)g.find(id);
	    return ItemCompany;
	}
	
	public ItemCompany getItemCompany(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemCompany pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (ItemCompany) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<ItemCompany> getItemCompanys(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemCompany pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateItemCompany(ItemCompany group)
	{
	   	GenericHelper g=new GenericHelper(ItemCompany.class);
	   	g.update(group);
	}
	
	
	
}
