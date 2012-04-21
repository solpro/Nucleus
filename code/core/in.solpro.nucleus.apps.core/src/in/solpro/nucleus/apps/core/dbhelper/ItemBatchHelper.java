package in.solpro.nucleus.apps.core.dbhelper;
import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.ItemBatch;



public class ItemBatchHelper extends GenericHelper{

	public ItemBatchHelper()
	{
		super(ItemBatch.class);
	}
	
	public static void addItemBatch(ItemBatch p)
	{
		GenericHelper g = new GenericHelper(ItemBatch.class);
		g.save(p);
		System.out.println("ItemBatch Saved");
	}
	
	public static ItemBatch getItemBatchById(Integer id)
	{
		GenericHelper g = new GenericHelper(ItemBatch.class);
		ItemBatch ItemBatch=(ItemBatch)g.find(id);
	    return ItemBatch;
	}
	
	public ItemBatch getItemBatch(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemBatch pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (ItemBatch) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<ItemBatch> getItemBatchs(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM ItemBatch pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateItemBatch(ItemBatch group)
	{
	   	GenericHelper g=new GenericHelper(ItemBatch.class);
	   	g.update(group);
	}
	
	
	
}
