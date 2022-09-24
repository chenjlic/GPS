//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
public class Path implements Comparable<Path>{
	public String pathStr = "";
	public int cost;
	public String vertex;
	
	public Path(String vertex, int cost, String pathStr) {
		this.vertex = vertex;
		this.cost = cost;
		this.pathStr = pathStr;
	}
	
	public int compareTo(Path other) {
		return cost - other.cost;
	}
	
	public String getLast() {
		return (pathStr.length() == 0) ? "" : pathStr.charAt(pathStr.length()-1) + "";
	}
	
	@Override
	public String toString() {
		return "Path: " + pathStr + " Cost: " + cost;
	}
}
