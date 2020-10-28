package Snake;

import java.util.ArrayList;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Field extends Pane {
	private int w, h;

	ArrayList<Block> blocks = new ArrayList<Block>();
	int score = 0;

	Food f;
	Snake snake;

	public Field(int width, int height) {
		w = width;
		h = height;

		setMinSize(w * Main_UI.block_size, h * Main_UI.block_size);
		setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		addFood();

	}

	public void addSnake(Snake s) {
		snake = s;
		for (Block b : s.blocks) {
			addBlock(b);
		}

	}

	// Update method that mooves the blocks
	public void update() {
		for (Block b : blocks) {
			b.update();
		}

		// checks if food is eaten
		if (isEaten(f)) {
			score += 10;
			addFood();
			addNewBlock();
		}
	}

	public boolean isDead() {
		for (Block b : blocks) {
			if (b != snake.head) {
				if (b.posX == snake.head.posX && b.posY == snake.head.posY) {
					return true;
				}
			}
		}
		return false;
	}

	public void addNewBlock() {
		Block b = new Block(snake.tail.oldPosX, snake.tail.oldPosY, snake.tail, this);
		snake.tail = b;
		addBlock(b);
	}

	private void addBlock(Block b) {
		getChildren().add(b);
		blocks.add(b);

	}

	public void addFood() {
		int randomX = (int) (Math.random() * w);
		int randomY = (int) (Math.random() * h);

		Food food = new Food(randomX, randomY);
		getChildren().add(food);
		getChildren().remove(f);
		f = food;

	}

	public boolean isEaten(Food f) {
		if (f == null) {
			return false;
		}
		return f.getPosX() == snake.head.posX && f.getPosY() == snake.head.posY;

	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
}
