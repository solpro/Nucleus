package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.District;


import java.util.List;

import javax.persistence.Query;

public class DistrictHelper extends GenericHelper{
	
	public DistrictHelper()
	{
		super(District.class);
	}

	public static void addDistrict(District p)
	{
		GenericHelper g = new GenericHelper(District.class);
		g.save(p);
		System.out.println("District Saved");
	}
	
	 public static District getDistrictById(Integer id)
		{
			GenericHelper g = new GenericHelper(District.class);
			District district=(District)g.find(id);
		    return district;
		}
	public District getDistrict(String name)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM District pc WHERE pc.name = :name");
		query.setParameter("name", name);
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
			return (District) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<District> getDistricts()
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM District pc");
		return query.getResultList();
	}
	
	 public void updateDistrict(District district)
	{
	   	GenericHelper g=new GenericHelper(District.class);
	   	g.update(district);
	}
	
}
