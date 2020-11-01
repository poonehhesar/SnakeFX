package Snake;

import java.util.ArrayList;

public class Obstacle {
		
	ArrayList<Block> blocks = new ArrayList<>();
	
	public Obstacle(Field field) {
			ArrayList<Integer> xcords = new ArrayList<>();
			ArrayList<Integer> ycords = new ArrayList<>();
			
			
			xcords.add(5);
			ycords.add(2);
			
			xcords.add(5);
			ycords.add(3);
			
			xcords.add(5);
			ycords.add(4);
			
			xcords.add(5);
			ycords.add(5);
			
			xcords.add(5);
			ycords.add(6);
			
			
			
			
			xcords.add(24);
			ycords.add(5);
			
			xcords.add(24);
			ycords.add(6);
			
			xcords.add(24);
			ycords.add(7);
			
			xcords.add(24);
			ycords.add(8);
			
			xcords.add(24);
			ycords.add(9);
			
			
			
			
			for (int x = 0; x < xcords.size(); x++) {
				Block block = new Block(xcords.get(x), ycords.get(x), null , field);
				blocks.add(block);
			}
			
	}
}
