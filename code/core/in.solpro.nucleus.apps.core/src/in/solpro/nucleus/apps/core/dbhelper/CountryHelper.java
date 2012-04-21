package in.solpro.nucleus.apps.core.dbhelper;

import in.solpro.nucleus.apps.common.Company;
import in.solpro.nucleus.apps.common.Country;

import java.util.List;

import javax.persistence.Query;

public class CountryHelper extends GenericHelper{
	
	public CountryHelper()
	{
		super(Country.class);
	}

	public static void addCountry(Country p)
	{
		GenericHelper g = new GenericHelper(Country.class);
		g.save(p);
		System.out.println("Country Saved");
	}
	
	 public static Country getCountryById(Integer id)
		{
			GenericHelper g = new GenericHelper(Country.class);
			Country country=(Country)g.find(id);
		    return country;
		}
	public  Country getCountry(String name)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Country pc WHERE pc.name = :name");
		query.setParameter("name", name);
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
			return (Country) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<Country> getCountries()
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Country pc");
		return query.getResultList();
	}
	
	 public static void updateCountry(Country country)
	{
	   	GenericHelper g=new GenericHelper(Country.class);
	   	g.update(country);
	}
	
}