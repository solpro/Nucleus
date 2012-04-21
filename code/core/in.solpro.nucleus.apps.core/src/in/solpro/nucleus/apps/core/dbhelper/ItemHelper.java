package in.solpro.nucleus.apps.core.dbhelper;
import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Item;



public class ItemHelper extends GenericHelper{

	public ItemHelper()
	{
		super(Item.class);
	}
	
	public static void addItem(Item p)
	{
		GenericHelper g = new GenericHelper(Item.class);
		g.save(p);
		System.out.println("Item Saved");
	}
	
	public static Item getItemById(Integer id)
	{
		GenericHelper g = new GenericHelper(Item.class);
		Item Item=(Item)g.find(id);
	    return Item;
	}
	
	public Item getItem(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Item pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (Item) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<Item> getItems(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Item pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateItem(Item group)
	{
	   	GenericHelper g=new GenericHelper(Item.class);
	   	g.update(group);
	}
	
	
	
}
