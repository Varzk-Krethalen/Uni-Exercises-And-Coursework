public class MapNode {

	public String name;
	public String color;
	
	public boolean assigned;
	
	public MapEdge[] borders;
	
	public MapNode(String parName, int i, String parBorder1) {
		
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
	}	
	public MapNode(String parName, int i, String parBorder1, String parBorder2) {
		
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
	}
	public MapNode(String parName, int i, String parBorder1, String parBorder2, String parBorder3) 
	{
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
			borders[2] = new MapEdge(this.name, parBorder3);
	}
	public MapNode(String parName, int i, String parBorder1, String parBorder2,
			String parBorder3, String parBorder4) 
	{
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
			borders[2] = new MapEdge(this.name, parBorder3);
			borders[3] = new MapEdge(this.name, parBorder4);
	}
	public MapNode(String parName, int i, String parBorder1, String parBorder2,
			String parBorder3, String parBorder4, String parBorder5) 
	{
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
			borders[2] = new MapEdge(this.name, parBorder3);
			borders[3] = new MapEdge(this.name, parBorder4);
			borders[4] = new MapEdge(this.name, parBorder5);
	}
	public MapNode(String parName, int i, String parBorder1, String parBorder2,
			String parBorder3, String parBorder4, String parBorder5, String parBorder6) 
	{
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
			borders[2] = new MapEdge(this.name, parBorder3);
			borders[3] = new MapEdge(this.name, parBorder4);
			borders[4] = new MapEdge(this.name, parBorder5);
			borders[5] = new MapEdge(this.name, parBorder6);
	}
	public MapNode(String parName, int i, String parBorder1, String parBorder2,
			String parBorder3, String parBorder4, String parBorder5, 
			String parBorder6, String parBorder7) 
	{
		this.name = parName;
		this.color = "red";
		assigned = false;
		
			this.borders = new MapEdge[i];
			
			borders[0] = new MapEdge(this.name, parBorder1);
			borders[1] = new MapEdge(this.name, parBorder2);
			borders[2] = new MapEdge(this.name, parBorder3);
			borders[3] = new MapEdge(this.name, parBorder4);
			borders[4] = new MapEdge(this.name, parBorder5);
			borders[5] = new MapEdge(this.name, parBorder6);
			borders[6] = new MapEdge(this.name, parBorder7);
	}
	
	public String GetName(){
		return this.name;
	}
	
	public String GetColor(){
		return this.color;
	}
	
	public void SetColor(String parColor){
		this.color = parColor;
	}
}
