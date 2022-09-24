//Author: Jonny Chen
//Worked with: Jerome Washika, Cole Winkhart
//Date: 12/3/2021
//Instructor: Stahr
public class Vertex implements Comparable<Vertex>{
	private static int LongestAddress = 1;
	public String symbol;
	public String address;
	
	public Vertex(String symbol, String address) {
		this.symbol = symbol;
		this.address = address;
		LongestAddress = Math.max(LongestAddress,  address.length());
	}
	
	public String equalsStr() {
		return symbol + "~" + address;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertex)) return false;
		return ((Vertex)obj).equalsStr().equals(equalsStr());
	}
	
	@Override 
	public int compareTo(Vertex o) {
			return symbol.compareTo(o.symbol);
	}
	
	@Override
	public String toString() {
		return String.format("%-" + (Graph.returnAddress ? LongestAddress : 1) + "s",
				Graph.returnAddress ? address : symbol);
	}
}
