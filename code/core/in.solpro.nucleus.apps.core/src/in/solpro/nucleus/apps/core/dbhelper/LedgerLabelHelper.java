package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;


import in.solpro.nucleus.apps.common.LedgerLabel;



public class LedgerLabelHelper extends GenericHelper{

	public LedgerLabelHelper()
	{
		super(LedgerLabel.class);
	}
	
	public static void addLedgerLabel(LedgerLabel p)
	{
		GenericHelper g = new GenericHelper(LedgerLabel.class);
		g.save(p);
		System.out.println("UserDefinedLabel Saved");
	}
	
	public static LedgerLabel getLedgerLabelById(Integer id)
	{
		GenericHelper g = new GenericHelper(LedgerLabel.class);
		LedgerLabel userdefinedlabel=(LedgerLabel)g.find(id);
	    return userdefinedlabel;
	}
	
	public LedgerLabel getLedgerLabel(String name,Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM UserDefinedLabel pc WHERE pc.labelName = :name AND pc.company.id= :compid");
		query.setParameter("name", name);
		//query.setParameter("compid",compid);
		query.setParameter("compid",compid);
		
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
		 return (LedgerLabel) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<LedgerLabel> getLedgerLabels(Integer compid)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM UserDefinedLabel pc WHERE pc.company.id= :compid");
		query.setParameter("compid", compid);
		return query.getResultList();
	}
	
	 public void updateLedgerLabel(LedgerLabel group)
	{
	   	GenericHelper g=new GenericHelper(LedgerLabel.class);
	   	g.update(group);
	}
	
	
	
}

