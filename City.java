/**
 * @author Shanika Perera
 *
 */

/**
 * The City class represents a vertex in the Romania Map represented by a graph.
 */
public class City implements Comparable<City>{
	
	//unique ID of the city
	private int id;
	//name of the city
	private String name;
	//first character of the name as an ID
	private Character nameId;
	//keeps track of the parent while traversing - right when this city is being explored
	private City parent;
	//depth of the node in graph
	private int depth;
	
	City(int id, String name)
	{
		this.id = id;
		this.name = name;
		this.nameId = Character.toUpperCase(name.charAt(0));
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Character getNameId()
	{
		return nameId;
	}
	
	public City getParent()
	{
		return parent;
	}
	
	public int getDepth()
	{
		return depth;
	}
	
	public void setParent(City parent)
	{
		this.parent = parent;
	}
	
	public void setDepth(int depth)
	{
		this.depth = depth;
	}
    
	@Override
	public int compareTo(City city) {
		if (id >= city.getId())
			return 1;
		else
			return -1;
	}

}
