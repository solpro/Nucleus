package in.solpro.nucleus.apps.core.dbhelper;


import in.solpro.nucleus.apps.common.State;

import java.util.List;

import javax.persistence.Query;

public class StateHelper extends GenericHelper{
	
	public StateHelper()
	{
		super(State.class);
	}

	public static void addState(State p)
	{
		GenericHelper g = new GenericHelper(State.class);
		g.save(p);
		System.out.println("State Saved");
	}
	
	 public static State getStateById(Integer id)
		{
			GenericHelper g = new GenericHelper(State.class);
			State state=(State)g.find(id);
		    return state;
		}
	public State getState(String name)
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM State pc WHERE pc.name = :name");
		query.setParameter("name", name);
		List<?> rs = query.getResultList();
		if (rs.size() > 0)
		{
			return (State) rs.get(0);
		}
		return null;
		//GenericHelper g = new GenericHelper(Company.class);
		//return (Company)g.find(name);
	}
	
	public List<State> getStates()
	{
		Query query = em.createQuery("SELECT OBJECT(pc) FROM State pc");
		return query.getResultList();
	}
	
	 public void updateState(State state)
	{
	   	GenericHelper g=new GenericHelper(State.class);
	   	g.update(state);
	}
	
}