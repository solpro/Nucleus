package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.City;


import java.util.List;

import javax.persistence.Query;

public class CityHelper extends GenericHelper{
	
	public CityHelper()
	{
		super(City.class);
	}

	public static void addCity(City p)
	{
		GenericHelper g = new GenericHelper(City.class);
		g.save(p);
		System.out.println("City Saved");
	}
	
	 public static City getCityById(Integer id)
		{
			GenericHelper g = new GenericHelper(City.class);
			City city=(City)g.find(id);
		    return city;
		}
	public City getCity(String name)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM City pc WHERE pc.name = :name");
		query.setParameter("name", name);
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
			return (City) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<City> getCities()
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM City pc");
		return query.getResultList();
	}
	
	 public void updateCity(City city)
	{
	   	GenericHelper g=new GenericHelper(City.class);
	   	g.update(city);
	}
	
}
