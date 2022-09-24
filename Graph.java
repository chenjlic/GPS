//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Graph {
	public static boolean useDistCost = true;
	public static boolean returnAddress = false;
	 
	private Map<String, Vertex> verticies;
	private ArrayList<Edge> edges;
	
	public Graph(String fileName) {
		verticies = new HashMap<>();
		edges = new ArrayList<>();
		String[] parts;
		try(Scanner fin = new Scanner(new File(fileName))) {
			while(fin.hasNextLine()) {
				parts = fin.nextLine().split("\t");
				if(parts[0].equals("<Nodes>")) {
					fin.nextLine();
					while(true) {
						parts = fin.nextLine().split("\t");
						if(parts[0].equals("</Nodes>")) break;
						verticies.put(parts[0], new Vertex(parts[0], parts[1]));	
					}
				} else if(parts[0].equals("<Edges>")) {
					fin.nextLine();
					while(true) {
						parts = fin.nextLine().split("\t");
						if(parts[0].equals("</Edges>")) break;
						edges.add(new Edge( verticies.get(parts[0]),
										verticies.get(parts[1]),
										Integer.parseInt(parts[2]),
										Integer.parseInt(parts[3])));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Edge> getEdges() {
		return this.edges;
	}
	
	//Takes addresses from text file and place into an array of strings
	public String[] vertexArrAdd(String fileName) {
		String[] ret = new String[20];
		int i = 0;
		//Create scanner
		try(Scanner fin = new Scanner(new File(fileName))) {
			while(fin.hasNextLine()) {
				if(fin.nextLine().equals("<Nodes>")) {
					if(fin.nextLine().equals("</Nodes>")) break;
					while(true) {
						String[] parts = fin.nextLine().split("\t");
						ret[i] = parts[1];
						i++;
						parts = null;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return ret;
	}
	
	//Takes symbols from text file and place into an array of strings
	public String[] vertexArrSym(String fileName) {
		String[] ret = new String[20];
		int i = 0;
		//Create scanner
		try(Scanner fin = new Scanner(new File(fileName))) {
			while(fin.hasNextLine()) {
				if(fin.nextLine().equals("<Nodes>")) {
					if(fin.nextLine().equals("</Nodes>")) break;
					while(true) {
						String[] parts = fin.nextLine().split("\t");
						ret[i] = parts[0];
						i++;
						parts = null;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for(Edge e : edges)
			s.append(e.toString()).append("\n");
		return s.toString();
	}
	
}
