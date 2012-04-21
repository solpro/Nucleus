package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.ItemGroup;



public class ItemGroupHelper extends GenericHelper{

	public ItemGroupHelper()
	{
		super(ItemGroup.class);
	}
	
	public static void addItemGroup(ItemGroup p)
	{
		GenericHelper g = new GenericHelper(ItemGroup.class);
		g.save(p);
		System.out.println("ItemGroup Saved");
	}
	
	public static ItemGroup getItemGroupById(Integer id)
	{
		GenericHelper g = new GenericHelper(ItemGroup.class);
		ItemGroup ItemGroup=(ItemGroup)g.find(id);
	    return ItemGroup;
	}
	
	public ItemGroup getItemGroup(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemGroup pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (ItemGroup) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<ItemGroup> getItemGroups(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemGroup pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateItemGroup(ItemGroup group)
	{
	   	GenericHelper g=new GenericHelper(ItemGroup.class);
	   	g.update(group);
	}
	
	
	
}
