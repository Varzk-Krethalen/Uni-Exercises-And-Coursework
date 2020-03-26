import java.awt.List;
import java.util.ArrayList;
import java.util.Random;



public class MyMapGraph extends MapGraph
{
	
	public void colorGraph()
	{
			borders();
			
					//assign random colors to the states
					for(int i = 0; i < states.length; i++)
					{
					Double r;
					Random generator = new Random();
					
					String color;
					r = generator.nextDouble();
					if (r < 0.25) {color = "red";} else if (r < 0.50 && r > 0.25) {color = "green";}
					else if (r < 0.75 && r > 0.50) {color = "blue";} else {color = "yellow";};
					
					color(states[i], color);
					}
						
					//while 2 adjacent  states are the same color 
					//pick a random color for one of them
					for(int i = 0; i < state1.size();i++) 
					{
						while(isSameColor(state1.get(i), state2.get(i)))
						{
							Double r;
							Random generator = new Random();
							
							String color;
							r = generator.nextDouble();
							if (r < 0.25) {color = "red";} else if (r < 0.50 && r > 0.25) {color = "green";}
							else if (r < 0.75 && r > 0.50) {color = "blue";} else {color = "yellow";};
							
							color(state1.get(i),color);

						}
					}				
	}	
}
