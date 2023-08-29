/**
 * @author Shanika Perera
 *
 */

import java.io.InputStreamReader;
import java.util.Scanner;

public class ShotestPathFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		int inputSearchAlgo;
		Character inputStartCity;
		char inputContinue = 'Y';
		
		//initializing cities
		City A = new City(0,"Arad");
		City B = new City(1,"Bucharest");
		City C = new City(2,"Craiova");
		City D = new City(3,"Drobeta");
		City E = new City(4,"Eforie");
		City F = new City(5,"Fagaras");
		City G = new City(6,"Giurgiu");
		City H = new City(7,"Hirsova");
		City I = new City(8,"Iasi");
		City L = new City(9,"Lugoj");
		City M = new City(10,"Mehadia");
		City N = new City(11,"Neamt");
		City O = new City(12,"Oradea");
		City P = new City(13,"Pitesti");
		City R = new City(14,"Rimnicu");
		City S = new City(15,"Sibiu");
		City T = new City(16,"Timisoara");
		City U = new City(17,"Urziceni");
		City V = new City(18,"Vaslui");
		City Z = new City(19,"Zerind");
		
		//adding vertices to the graph
		RoadMapGraph.addCity(A);
		RoadMapGraph.addCity(B);
		RoadMapGraph.addCity(C);
		RoadMapGraph.addCity(D);
		RoadMapGraph.addCity(E);
		RoadMapGraph.addCity(F);
		RoadMapGraph.addCity(G);
		RoadMapGraph.addCity(H);
		RoadMapGraph.addCity(I);
		RoadMapGraph.addCity(L);
		RoadMapGraph.addCity(M);
		RoadMapGraph.addCity(N);
		RoadMapGraph.addCity(O);
		RoadMapGraph.addCity(P);
		RoadMapGraph.addCity(R);
		RoadMapGraph.addCity(S);
		RoadMapGraph.addCity(T);
		RoadMapGraph.addCity(U);
		RoadMapGraph.addCity(V);
		RoadMapGraph.addCity(Z);
		
        // adding edges to the graph
        RoadMapGraph.addRoad(A, S, true);
        RoadMapGraph.addRoad(A, T, true);
        RoadMapGraph.addRoad(A, Z, true);
        RoadMapGraph.addRoad(S, F, true);
        RoadMapGraph.addRoad(S, O, true);
        RoadMapGraph.addRoad(S, R, true);
        RoadMapGraph.addRoad(T, L, true);
        RoadMapGraph.addRoad(Z, O, true);
        RoadMapGraph.addRoad(F, B, true);
        RoadMapGraph.addRoad(R, C, true);
        RoadMapGraph.addRoad(R, P, true);
        RoadMapGraph.addRoad(L, M, true);
        RoadMapGraph.addRoad(B, G, true);
        RoadMapGraph.addRoad(B, P, true);
        RoadMapGraph.addRoad(B, U, true);
        RoadMapGraph.addRoad(C, D, true);
        RoadMapGraph.addRoad(C, P, true);
        RoadMapGraph.addRoad(M, D, true);
        RoadMapGraph.addRoad(U, H, true);
        RoadMapGraph.addRoad(U, V, true);
        RoadMapGraph.addRoad(H, E, true);
        RoadMapGraph.addRoad(V, I, true);
        RoadMapGraph.addRoad(I, N, true);
        
        System.out.println("Welcome to Romania Problem Application!\n\nBelow are the graph representations of the Romania Map:\n");
        
        System.out.println("Adjacency List of the graph \n----------------------------");
        String adjListStr = RoadMapGraph.adjacencyList();
        System.out.println(adjListStr);
        
        System.out.println("Adjacency Matrix of the graph \n------------------------------");
        String adjMatrixStr = RoadMapGraph.adjacencyMatrix();
        System.out.println(adjMatrixStr);
        
        while(inputContinue == 'Y' || inputContinue == 'y') {
	        System.out.println("Please select the Search Algorithm and press enter:\n|1| BFS |2| DFS |3| IDS");
	        inputSearchAlgo = scanner.nextInt();
	        
	        System.out.println("\nPlease select the Start City(first character only) and press enter: \n"
	        		+ "|A| Arad    |Z| Zerind  |O| Oradea  |S| Sibiu    |T| Timisoara |L| Lugoj   |M| Mehadia |D| Drobeta |C| Craiova  |R| Rimnicu\n"
	        		+ "|F| Fagaras |P| Pitesti |G| Giurgiu |U| Urziceni |H| Hirsova   |E| Eforie  |V| Vaslui  |I| Iasi    |N| Neamt");
	        inputStartCity = Character.toUpperCase(scanner.next().charAt(0));
	        
	        
	        if (inputSearchAlgo == 1)
	        	RoadMapGraph.bfs(RoadMapGraph.getCityByNameId(inputStartCity), B);
	        else if (inputSearchAlgo == 2)
	        	RoadMapGraph.dfs(RoadMapGraph.getCityByNameId(inputStartCity), B);
	        else if (inputSearchAlgo == 3)
	        	RoadMapGraph.ids(RoadMapGraph.getCityByNameId(inputStartCity), B);
	        else
	        	System.out.println("Incorrect option!");
        
	        System.out.print("\nList of Explored/Visited cities: ");
	        for (int i = 0; i < RoadMapGraph.exploredCityList.size(); i++) {
	            System.out.print(RoadMapGraph.exploredCityList.get(i).getNameId() + " ");
	        }
	        
	        System.out.print("\nPath from the Start to Destination: ");
	        for (int i = 0; i < RoadMapGraph.pathCityList.size(); i++) {
	            System.out.print(RoadMapGraph.pathCityList.get(i).getNameId() + " ");
	        }
	        
	        System.out.println("\nTotal Path Cost: " + RoadMapGraph.pathCost);
		        
		    System.out.println("\nDo you want to continue?(Y/N)");
	        inputContinue = scanner.next().charAt(0);
	        
	        //restart cleanup
	        RoadMapGraph.exploredCityList.removeAll(RoadMapGraph.exploredCityList);
	        RoadMapGraph.pathCityList.removeAll(RoadMapGraph.pathCityList);
	        RoadMapGraph.pathCost = -1;
        }
        System.out.println("\nThank you!");
	}

}
