package Snake;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Snake {

	// The snake head
	ArrayList<Block> blocks = new ArrayList<Block>();

	Block head;
	Block tail;
	
	public Snake(int il, Field f) {
		// Starting position
		int ipx = f.getW() / 2;
		int ipy = f.getH() / 2;

		// Head
		head = new Block(ipx, ipy, null, f);
		blocks.add(head);
		
		head.setFill(Color.RED.desaturate());
		
		// Body
		tail = head;
		for (int i = 1; i < il; i++) {
			Block b = new Block(ipx + i, ipy, tail, f);
			blocks.add(b);
			tail = b;
		}

	}
	
	public int getDirection () {
		return head.direction;
	}
	
	public void setDirection (int d) {
		head.direction = d;
		
	}
}
