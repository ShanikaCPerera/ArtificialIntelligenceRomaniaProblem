/**
 * @author Shanika Perera
 *
 */

/**
 * The RoadMapGraph class represents the Romania map as a graph and its functionalities.
 */

import java.util.*;

public class RoadMapGraph {

	//hash map with the city as key and its adjacency list as value
	private static HashMap<City, List<City>> roadMap = new HashMap<>();
	public static  ArrayList<City> pathCityList = new ArrayList<City>();
	public static ArrayList<City> exploredCityList = new ArrayList<City>();
	public static int pathCost;
	
	/**
     *  Adds a new city(vertex) to the graph
     *   
     * @param   new_city    a City object needs to be added
     * @return  none
     */
	public static void addCity(City new_city) 
	{
		roadMap.put(new_city, new LinkedList<City> ());
	}
	
	/**
     *  Adds an edge between source to destination vertices in the graph
     *   
     * @param   source         the starting vertex of the edge
     * @param   destination    the ending vertex of the edge
     * @param   bidirectional  whether the edge is bidirectional or not
     * @return  none
     */
    public static void addRoad(City source, City destination, boolean bidirectional)
    {
        if (!roadMap.containsKey(source))
        	addCity(source);
 
        if (!roadMap.containsKey(destination))
        	addCity(destination);
 
        roadMap.get(source).add(destination);
        if (bidirectional == true) {
        	roadMap.get(destination).add(source);
        }
    }
    
	/**
	 *  Return the number of vertices in the graph
	 *   
	 */
    public static int getVertexCount()
    {
        return roadMap.keySet().size();
    }
    
	/**
     *  Generate the adjacency list of the graph
     *  
     */
    public static String adjacencyList()
    {
        StringBuilder builder = new StringBuilder();
    	ArrayList<City> keyList = new ArrayList<City>(roadMap.keySet());
    	
    	Collections.sort(keyList);
    	
        for (City v : keyList) {
            builder.append(v.getNameId() + "-> ");
            for (City w : roadMap.get(v)) {
                builder.append(w.getNameId() + " -> ");
            }
            builder.delete(builder.length()-3, builder.length()-1);
            builder.append("\n");
        }
 
        return builder.toString();
    }
    
	/**
     *  Generate the adjacency matrix of the graph
     *  
     */
    public static String adjacencyMatrix() 
    {
    	int numVertices = getVertexCount();
    	int[][] adjMatrix = new int[numVertices][numVertices];
    	StringBuilder builder = new StringBuilder();
    	int count = 0;
    	ArrayList<City> keyList = new ArrayList<City>(roadMap.keySet());
    	
    	Collections.sort(keyList);

    	//printing horizontal keys
    	builder.append("  ");
    	for (City c : keyList) {
    		builder.append(c.getNameId() + " ");
        }
    	
    	//initializing the matrix with connections
    	for (City i : roadMap.keySet()) {
            for (City j : roadMap.get(i)) {
            	adjMatrix[i.getId()][j.getId()] = 1;
            }
        }
    	
    	//printing the matrix values
		builder.append("\n");
	    for (int i = 0; i < numVertices; i++) {
	    	//to print vertical keys 
	    	builder.append(keyList.get(count).getNameId() + " ");
	    	for (int j = 0; j < numVertices; j++) {
	    		builder.append(adjMatrix[i][j] + " ");
	        }
	    	builder.append("\n");
	    	count++;
	    }
		return builder.toString();
    }
    
	/**
     *  Get a City(vertex) by its name ID
     *   
     * @param   name_id        the name ID of the City(vertex)
     * @return  the city
     */
	public static City getCityByNameId(Character name_id)
	{
		name_id = Character.toUpperCase(name_id);
        for (City c : roadMap.keySet()) {
            if(c.getNameId().equals(name_id))
            	return c;   
        }
        return null;
	}
	
	/**
     *  Get the final path from source to destination
     *   
     * @param   destination        the name ID of the destination City(vertex)
     * @return  the path in a list 
     */
	private static ArrayList<City> findPath(City destination) 
	{
		ArrayList<City> path = new ArrayList<City>();
		City currentParent = destination;
		
		while(currentParent != null)
		{
			path.add(currentParent);
			currentParent = currentParent.getParent();
		}
		Collections.reverse(path);
		return path;
	}
    
	/**
     *  Perform the Breadth First Search(BFS)
     *   
     * @param   source         the starting city(vertex) of the search
     * @param   destination    the ending city(vertex) of the search
     * @return  none
     */
	public static void bfs(City start, City destination)
	{
		int noOfVectors = getVertexCount();
		City current, next;
		//mark all the vertices as not explored(By default set as false)
        boolean explored[] = new boolean[noOfVectors];
        //FIFO queue
        Queue<City> fringe = new LinkedList<City>();
        
        // enqueue the start node
        start.setParent(null);
        fringe.add(start);
 
        while (fringe.size() != 0) {
        	
            // Dequeue a vertex from queue, mark it as explored(as its about to be explored) and record to print it
        	current = fringe.poll();
        	
        	//ignoring nodes that are already explored but in the queue
        	if (explored[current.getId()])
        		continue;
        	else
        		explored[current.getId()] = true;
        	
        	exploredCityList.add(current);
            
            //when destination is found
            if (current.getNameId().equals(destination.getNameId()))
            	break;
 
            // Get all adjacent vertices of the dequeued vertex.
            // If an adjacent vertex has not been explored, enqueue it to the queue
            // If already explored do nothing
            List<City> childList = roadMap.get(current);
            Collections.sort(childList);
            Iterator<City> i = childList.listIterator();
            while (i.hasNext()) {
                next = i.next();
                if (!explored[next.getId()]) {
                	next.setParent(current);
                	fringe.add(next);
                }
            }
        }
        pathCityList = findPath(destination);
        pathCost = pathCityList.size() - 1 ;
	}
	
	/**
     *  Perform the Depth First Search(DFS)
     *   
     * @param   source         the starting city(vertex) of the search
     * @param   destination    the ending city(vertex) of the search
     * @return  none
     */
	public static void dfs(City start, City destination)
	{
		int noOfVectors = getVertexCount();
		City current, next;
		//mark all the vertices as not explored(By default set as false)
        boolean explored[] = new boolean[noOfVectors];
        //FIFO queue
        Stack<City> fringe = new Stack<>();
        
        // push the start node
        start.setParent(null);
        fringe.push(start);
 
        while (fringe.size() != 0) {
        	
            // pop a vertex from stack, mark it as explored(as its about to be explored) and record to print it
        	current = fringe.pop();
        	
        	//ignoring nodes that are already explored but in the queue
        	if (explored[current.getId()])
        		continue;
        	else
        		explored[current.getId()] = true;
        	
        	exploredCityList.add(current);
            
            //when destination is found
            if (current.getNameId().equals(destination.getNameId()))
            	break;
 
            // Get all adjacent vertices of the popped vertex.
            // If an adjacent vertex has not been explored, push it to the queue
            // If already explored do nothing
            List<City> childList = roadMap.get(current);
            Collections.sort(childList);
            Collections.reverse(childList);
            Iterator<City> i = childList.listIterator();
            while (i.hasNext()) {
                next = i.next();
                if (!explored[next.getId()]) {
                	next.setParent(current);
                	fringe.push(next);
                }
            }
        }
        pathCityList = findPath(destination);
        pathCost = pathCityList.size() - 1 ;
	}
	
	/**
     *  Perform the Breadth First Search(BFS) within a depth limit
     *   
     * @param   source         the starting city(vertex) of the search
     * @param   destination    the ending city(vertex) of the search
     * @param   depth_limit    the depth limit DFS should consider
     * @return  none
     */
	public static boolean dfs(City start, City destination, int depth_limit)
	{
		int noOfVectors = getVertexCount();
		City current, next;
		//mark all the vertices as not explored(By default set as false)
        boolean explored[] = new boolean[noOfVectors];
        //FIFO queue
        Stack<City> fringe = new Stack<>();
        boolean success = false;
        
        // push the start node
        start.setParent(null);
        start.setDepth(0);
        fringe.push(start);
 
        while (fringe.size() != 0) {
        	
            // pop a vertex from stack, mark it as explored(as its about to be explored) and record to print it
        	current = fringe.pop();
        	
        	//ignoring nodes that are already explored but in the queue
        	if (explored[current.getId()])
        		continue;
        	else
        		explored[current.getId()] = true;
        	
        	exploredCityList.add(current);
            
            //when destination is found
            if (current.getNameId().equals(destination.getNameId()))
            {
            	success = true;
            	break;
            }
            if(current.getDepth() < depth_limit)
            {
	            // Get all adjacent vertices of the dequeued vertex s.
	            // If an adjacent vertex has not been explored, push it to the queue
	            // If already explored do nothing
	            List<City> childList = roadMap.get(current);
	            Collections.sort(childList);
	            Collections.reverse(childList);
	            Iterator<City> i = childList.listIterator();
		        while (i.hasNext()) {
		            next = i.next();
		            if (!explored[next.getId()]) {
		            	next.setParent(current);
		            	next.setDepth(next.getParent().getDepth()+1);
		            	fringe.push(next);
		            }
		        }
            }
        }
        pathCityList = findPath(destination);
        pathCost = pathCityList.size() - 1 ;
        return success;
	}
	
	/**
     *  Perform the Iterative Deepening Search(IDS)
     *   
     * @param   source         the starting city(vertex) of the search
     * @param   destination    the ending city(vertex) of the search
     * @return  none
     */
	public static void ids(City start, City destination)
	{
		boolean isSuccess = false;
		for(int depth_limit = 0; depth_limit < 100; depth_limit++)
		{
			System.out.print("\nDepth_limit:" + depth_limit);
			isSuccess = dfs(start, destination, depth_limit);
			if(isSuccess)
			{
				System.out.print(" - Found!");
				break;
			}
			else
			{
				System.out.print(" - Not Found!\n");
				System.out.print("Explored List: ");
				for (int i = 0; i < RoadMapGraph.exploredCityList.size(); i++) {
		            System.out.print(RoadMapGraph.exploredCityList.get(i).getNameId() + " ");
		        }
				//cleanup
		        RoadMapGraph.exploredCityList.removeAll(RoadMapGraph.exploredCityList);
		        RoadMapGraph.pathCityList.removeAll(RoadMapGraph.pathCityList);
		        RoadMapGraph.pathCost = -1;
			}
		}
		System.out.print("\n");
	}

}
