package pixelenkentrajzolas;

import java.util.Random;
import java.util.function.Function;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelenkentRajzolas extends Application {

	private final Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);
	private final Random rand = new Random();

	private void rajzolniValamit(final PixelWriter pw, final int width, final int height) {

		final Function<Double, Integer> fun = (final Double num) -> {
			final int r = rand.nextInt(256);
			final int x = Math.abs(num.intValue()) % 256;
			return (r + x) / 2;
		};

		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				final double sn = Math.sin(i + j) * 1000.0;
				final double cn = Math.cos(i - j) * 1000.0;
				final double tn = Math.tan(i * j) * 1000.0;
				final int sm = fun.apply(sn);
				final int cm = fun.apply(cn);
				final int tm = fun.apply(tn);
				pw.setColor(j, i, Color.rgb(sm, cm, tm));
			}
		}

	}

	@Override
	public void start(final Stage primaryStage) {
		closeAlert.setTitle("Valóban?");
		closeAlert.setHeaderText("Biztosan kilépsz?");

		final int canvasWidth = 1000;
		final int canvasHeight = 500;

		final Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		final GraphicsContext gc = canvas.getGraphicsContext2D();

		rajzolniValamit(gc.getPixelWriter(), canvasWidth, canvasHeight);

		final Group root = new Group();
		root.getChildren().add(canvas);

		primaryStage.setOnCloseRequest(event -> {
			final ButtonType buttonType = closeAlert.showAndWait().get();
			if (buttonType == ButtonType.CANCEL) {
				event.consume();
			}
		});

		primaryStage.setMaxHeight(canvasHeight);
		primaryStage.setMaxWidth(canvasWidth);
		primaryStage.setTitle("Pixelenkent Rajzolas");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
