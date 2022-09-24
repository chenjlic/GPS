//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {
	private static int totalCost;
	
	public static Path findShortestPath(Graph graph, String startVertexName, String endVertexName) {
		PriorityQueueInterface<Path> paths = new HeapPriorityQueue<Path>();
		paths.add( new Path(startVertexName, 0, startVertexName));
		Set<String> visited = new HashSet<String>();
		while (!paths.isEmpty()) {
			Path p = paths.remove();
			visited.add(p.vertex);
			if(p.vertex.equals(endVertexName)) {
				return p;
			} else {
				for(Edge e: graph.getEdges()) {
					if(Graph.returnAddress == false) {
						// Skip edges that don't start from current vertex
						if (!e.fromVertex.symbol.equals(p.vertex)) continue;
					
						String toVertex = e.toVertex.symbol;

						// Skip visited destinations
						if (visited.contains(toVertex)) continue;
					
						// Queue new path
						String nextPath = p.pathStr.concat(" -> " + toVertex);
						if(Graph.useDistCost == true) {
							paths.add(new Path(e.toVertex.symbol, p.cost + e.distCost, nextPath)); 
						} else {
							paths.add(new Path(e.toVertex.symbol, p.cost + e.timeCost, nextPath)); 
						}
					
					} else {
						// Skip edges that don't start from current vertex
						if (!e.fromVertex.address.equals(p.vertex)) continue;
					
						String toVertex = e.toVertex.address;

						// Skip visited destinations
						if (visited.contains(toVertex)) continue;
				
						// Queue new path
						String nextPath = p.pathStr.concat(" -> " + toVertex);
						if(Graph.useDistCost == true) {
							paths.add(new Path(e.toVertex.address, p.cost + e.distCost, nextPath)); 
						} else {
							paths.add(new Path(e.toVertex.address, p.cost + e.timeCost, nextPath)); 
						}
					}
				}
			}
		}
		return null;
	}
	
	public int totalCost() {
		return totalCost;
	}
}
