package in.solpro.nucleus.apps.core.dbhelper;

import java.util.List;

import javax.persistence.Query;

import in.solpro.nucleus.apps.common.Company;



public class CompanyHelper extends GenericHelper{
	
	public CompanyHelper()
	{
		super(Company.class);
	}

	public static void addCompany(Company p)
	{
		GenericHelper g = new GenericHelper(Company.class);
		g.save(p);
		System.out.println("Company Saved");
	}
	
	 public static Company getCompanyById(Integer id)
	{
		GenericHelper g = new GenericHelper(Company.class);
		Company company=(Company)g.find(id);
	    return company;
	}
	
	 public Company getCompany(String name)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Company pc WHERE pc.name = :name");
		query.setParameter("name", name);
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
			return (Company) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<Company> getCompanies()
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM Company pc");
		return query.getResultList();
	}
	
	 public void updateCompany(Company company)
	{
	   	GenericHelper g=new GenericHelper(Company.class);
	   	g.update(company);
	}
	
}

