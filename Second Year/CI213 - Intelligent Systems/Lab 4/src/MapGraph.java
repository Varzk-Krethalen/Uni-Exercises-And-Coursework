import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public abstract class MapGraph {

	public MapNode[] states;
	
	public List<MapNode> state1 = new ArrayList<MapNode>();
	public List<MapNode> state2 = new ArrayList<MapNode>();
	
	
	public MapGraph() {
		
		this.states = new MapNode[48];
		
		this.states[0] = new MapNode("OR", 4, "WA", "ID", "NV", "CA");
		
		this.states[1] = new MapNode("WA", 2, "OR", "ID");
		
		this.states[2] = new MapNode("CA", 3, "OR", "NV", "AZ");
		this.states[3] = new MapNode("ID", 6, "WA", "OR", "NV", "UT", "WY", "MT");
		this.states[4] = new MapNode("NV", 5, "ID", "OR", "CA", "AZ", "UT");
		this.states[5] = new MapNode("UT", 5, "NV", "ID", "WY", "CO", "AZ");
		this.states[6] = new MapNode("AZ", 4, "UT", "NV", "CA", "NM");
		
		this.states[7] = new MapNode("MT", 4, "ID", "WY", "SD", "ND");
		this.states[8] = new MapNode("WY", 6, "MT", "ID", "UT", "CO", "NE", "SD");
		this.states[9] = new MapNode("CO", 6, "WY", "UT", "NM", "OK", "KS", "NE");
		this.states[10] = new MapNode("NM", 4, "CO", "AZ", "TX", "OK");
		this.states[11] = new MapNode("TX", 4, "NM", "OK", "AR", "LA");
		this.states[12] = new MapNode("ND", 3, "MT", "SD", "MN");
		this.states[13] = new MapNode("SD", 6, "ND", "MT", "WY", "NE", "IA", "MN");
		
		this.states[14] = new MapNode("NE", 6, "SD", "WY", "CO", "KS", "MO", "IA");
		this.states[15] = new MapNode("KS", 4, "NE", "CO", "OK", "MO");
		this.states[16] = new MapNode("OK", 6, "KS", "CO", "NM", "TX", "AR", "MO");
		this.states[17] = new MapNode("MN", 4, "WI", "IA", "SD", "ND");
		this.states[18] = new MapNode("IA", 6, "MN", "SD", "NE", "MO", "IL", "WI");
		this.states[19] = new MapNode("MO", 7, "IA", "NE", "KS", "OK", "AR", "KY", "IL");
		this.states[20] = new MapNode("AR", 6, "MO", "OK", "TX", "LA", "MS", "TN");
		
		this.states[21] = new MapNode("LA", 3, "AR", "TX", "MS");
		this.states[22] = new MapNode("WI", 3, "MN", "IA", "IL");
		this.states[23] = new MapNode("IL", 5, "WI", "IA", "MO", "KY", "IN");
		this.states[24] = new MapNode("KY", 7, "VA", "WV", "OH", "IN", "IL", "MO", "TN");
		this.states[25] = new MapNode("TN", 7, "KY", "AR", "MS", "AL", "GA", "NC", "VA");
		this.states[26] = new MapNode("MS", 4, "TN", "AR", "LA", "AL");
		this.states[27] = new MapNode("MI", 2, "IN", "OH");
		
		this.states[28] = new MapNode("IN", 4, "MI", "IL", "KY", "OH");
		this.states[29] = new MapNode("OH", 5, "MI", "IN", "KY", "WV", "PA");
		this.states[30] = new MapNode("WV", 5, "MD", "PA", "OH", "KY", "VA" );
		this.states[31] = new MapNode("VA", 5, "MD", "WV", "KY", "TN", "NC" );
		this.states[32] = new MapNode("PA", 5, "NY", "NJ", "MD", "WV", "OH");
		this.states[33] = new MapNode("NY", 6, "PA", "NJ", "RI", "MA", "VT", "CT");
		this.states[34] = new MapNode("ME", 1, "NH");
		
		this.states[35] = new MapNode("VT", 3, "NY", "MA", "NH");
		this.states[36] = new MapNode("NH", 3, "VT", "ME", "MA");
		this.states[37] = new MapNode("MA", 4, "NH", "VT", "NY", "RI");
		this.states[38] = new MapNode("RI", 2, "MA", "NY");
		this.states[39] = new MapNode("CT", 1, "NY" );
		this.states[40] = new MapNode("NJ", 3, "NY", "PA", "DE");
		this.states[41] = new MapNode("DE", 2, "NJ", "MD");
		
		this.states[42] = new MapNode("AL", 4, "TN", "MS", "FL", "GA");
		this.states[43] = new MapNode("GA", 5, "TN", "AL", "FL", "SC", "NC");
		this.states[44] = new MapNode("FL", 2, "AL", "GA");
		this.states[45] = new MapNode("SC", 2, "NC", "GA");
		this.states[46] = new MapNode("NC", 4, "VA", "TN", "GA", "SC");
        this.states[47] = new MapNode("MD", 4, "VA", "WV", "PA", "DE");
		
	}
	
	abstract void colorGraph();
	
	public String displayGraph(){
		String ret = "\n LIST OF STATES AND COLORS:\n";
		for (int i = 0; i < states.length; i++) {
			ret = ret+"  State "+states[i].GetName()+" => "+states[i].GetColor()+"\n";
		}
		ret = ret+"\n";
		return ret;
	}
	
	public void color(MapNode m, String parColor)
	{
			m.SetColor(parColor);
	}
	
	public boolean isSameColor(MapNode m1, MapNode m2)
	{
		if(m1.color == m2.color)
			return true;
		else return false;
	}
	public void borders()
	{
		for (MapNode map : states)
		{
			for (MapEdge border : map.borders)
			{
				for (MapNode m : states)
				{
					if(border.node2 == m.name)
					{
						state1.add(map);
						state2.add(m); 
					}
				}
				
			}
		}
	}		
}
