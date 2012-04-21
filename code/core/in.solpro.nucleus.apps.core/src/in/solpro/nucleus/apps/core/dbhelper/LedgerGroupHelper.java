package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.LedgerGroup;



public class LedgerGroupHelper extends GenericHelper{

	public LedgerGroupHelper()
	{
		super(LedgerGroup.class);
	}
	
	public static void addLedgerGroup(LedgerGroup p)
	{
		GenericHelper g = new GenericHelper(LedgerGroup.class);
		g.save(p);
		System.out.println("LedgerGroup Saved");
	}
	
	public static LedgerGroup getLedgerGroupById(Integer id)
	{
		GenericHelper g = new GenericHelper(LedgerGroup.class);
		LedgerGroup ledgergroup=(LedgerGroup)g.find(id);
	    return ledgergroup;
	}
	
	public LedgerGroup getLedgerGroup(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM LedgerGroup pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (LedgerGroup) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<LedgerGroup> getLedgerGroups(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM LedgerGroup pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateLedgerGroup(LedgerGroup group)
	{
	   	GenericHelper g=new GenericHelper(LedgerGroup.class);
	   	g.update(group);
	}
	
	
	
}
