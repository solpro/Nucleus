package in.solpro.nucleus.apps.core.dbhelper;
import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.ItemTax;



public class ItemTaxHelper extends GenericHelper{

	public ItemTaxHelper()
	{
		super(ItemTax.class);
	}
	
	public static void addItemTax(ItemTax p)
	{
		GenericHelper g = new GenericHelper(ItemTax.class);
		g.save(p);
		System.out.println("ItemTax Saved");
	}
	
	public static ItemTax getItemTaxById(Integer id)
	{
		GenericHelper g = new GenericHelper(ItemTax.class);
		ItemTax ItemTax=(ItemTax)g.find(id);
	    return ItemTax;
	}
	
	public ItemTax getItemTax(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemTax pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (ItemTax) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<ItemTax> getItemTaxs(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemTax pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateItemTax(ItemTax group)
	{
	   	GenericHelper g=new GenericHelper(ItemTax.class);
	   	g.update(group);
	}
	
	
	
}
