package Snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main_UI extends Application {

	// screen size
	static int block_size = 20;
	int width = 30, height = 20;
	int il = 5;
	int speed = 8;

	long then = System.nanoTime();

	boolean changed = false;
	int nextUpdate;
	boolean hasNext = false;

	Field f;

	@Override
	public void start(Stage ps) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));

		f = new Field(width, height);
		f.addObs(new Obstacle(f));
		f.addSnake(new Snake(il, f));

		Label score = new Label("Score: 0 ");
		score.setFont(Font.font("tahoma", 32));

		AnimationTimer timer = new AnimationTimer() {
			public void handle(long now) {

				// speed of the snake
				if (now - then > 1000000000 / speed) {
					f.update();
					then = now;
					score.setText("Score: " + f.score);
					changed = false;
					if (hasNext) {
						setDirection(f.snake, nextUpdate);
						hasNext = false;
					}

					if (f.isDead()) {
						stop();

						Alert al = new Alert(AlertType.INFORMATION);
						al.setHeaderText("You lost");
						al.setContentText("Your score is " + f.score);
						Platform.runLater(al::showAndWait);


						al.setOnHidden(e -> {
							root.getChildren().clear();
							f = new Field(width, height);
							f.addSnake(new Snake(il, f));
							score.setText("Score: 0 ");
							root.getChildren().addAll(f, score);
							f.addObs(new Obstacle(f));

							start();

						});

					}

				}
			}
		};
		timer.start();

		root.getChildren().addAll(f, score);

		Scene scene = new Scene(root);

		scene.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.UP) && f.snake.getDirection() != Block.DOWN) {
				setDirection(f.snake, Block.UP);
			}
			if (e.getCode().equals(KeyCode.DOWN) && f.snake.getDirection() != Block.UP) {
				setDirection(f.snake, Block.DOWN);
			}
			if (e.getCode().equals(KeyCode.RIGHT) && f.snake.getDirection() != Block.LEFT) {
				setDirection(f.snake, Block.RIGHT);
			}
			if (e.getCode().equals(KeyCode.LEFT) && f.snake.getDirection() != Block.RIGHT) {
				setDirection(f.snake, Block.LEFT);
			}

		});

		ps.setResizable(false);
		ps.setScene(scene);
		ps.setTitle("Snake Game");

		ps.show();
	}

	public void setDirection(Snake s, int d) {
		if (!changed) {
			s.setDirection(d);
			changed = true;
		} else {
			hasNext = true;
			nextUpdate = d;
		}
	}
	
	public void checkSpeed() {
		speed = Field.getScore () / 300 + 8;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
