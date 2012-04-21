package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Ledger;
import in.solpro.nucleus.apps.common.LedgerGroup;




public class LedgerHelper extends GenericHelper{

	public LedgerHelper()
	{
		super(Ledger.class);
	}
	
	public static void addLedger(Ledger p)
	{
		GenericHelper g = new GenericHelper(Ledger.class);
		g.save(p);
		System.out.println("Ledger Saved");
	}
	
	public static Ledger getLedgerById(Integer id)
	{
		GenericHelper g = new GenericHelper(Ledger.class);
		Ledger Ledger=(Ledger)g.find(id);
	    return Ledger;
	}
	
	public Ledger getLedger(String name,Integer compid)
	{
		Query query =em.createQuery("SELECT OBJECT(pc) FROM Ledger pc WHERE pc.name = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (Ledger) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<Ledger> getLedgers(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Ledger pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateLedger(Ledger group)
	{
	   	GenericHelper g=new GenericHelper(Ledger.class);
	   	g.update(group);
	}
	
	
	
}
