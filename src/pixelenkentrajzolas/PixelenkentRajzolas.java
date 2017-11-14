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

    private void printMandlebrotSet(final PixelWriter pw, final int width, final int height) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ComplexNumber c = ComplexNumber.Transzformal(j, i, width, height, -0.6127/*x tengely*/, -0.4612/*y tengely*/, 1500/*Közelítés mértéke*/);
                if (ComplexNumber.HalmazbanVanE(c)) {
                    pw.setColor(j, i, Color.BLACK);
                }
            }
        }
    }

    @Override
    public void start(final Stage primaryStage) {
        closeAlert.setTitle("Valóban?");
        closeAlert.setHeaderText("Biztosan kilépsz?");

        final int canvasWidth = 1750;
        final int canvasHeight = 1050;

        final Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight);
        PixelWriter pw1 = gc.getPixelWriter();
        for (int i = canvasWidth / 2 - 1; i < canvasWidth / 2 + 2; i++) {
            for (int j = canvasHeight / 2 - 1; j < canvasHeight / 2 + 2; j++) {
                pw1.setColor(i, j, Color.RED);
            }
        }

        final Group root = new Group();
        root.getChildren().add(canvas);

//        primaryStage.setOnCloseRequest(event -> {
//            final ButtonType buttonType = closeAlert.showAndWait().get();
//            if (buttonType == ButtonType.CANCEL) {
//                event.consume();
//            }
//        });
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
