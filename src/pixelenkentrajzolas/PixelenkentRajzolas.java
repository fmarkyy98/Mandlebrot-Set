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

    private void printMandlebrotSet(final PixelWriter pw, final int width, final int height, double xTranslate, double yTranslate, double zoomIn, int tolerance, Color color) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ComplexNumber c = ComplexNumber.Transzformal(j, i, width, height, xTranslate, yTranslate, zoomIn);
                if (ComplexNumber.HalmazbanVanE(c, tolerance)) {
                    pw.setColor(j, i, color);
                }
            }
        }
    }

    private void printRedDot(GraphicsContext gc, int canvasWidth, int canvasHeight) {
        PixelWriter pw1 = gc.getPixelWriter();
        for (int i = canvasWidth / 2 - 1; i < canvasWidth / 2 + 2; i++) {
            for (int j = canvasHeight / 2 - 1; j < canvasHeight / 2 + 2; j++) {
                pw1.setColor(i, j, Color.RED);
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

        double x = -0.61333709;
        double y = -0.461068;
        double zoom = 1;
        int tolerance = 10;
        int shape =255;
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, tolerance, Color.LIGHTGRAY);
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, -0.61333709, -0.461068, 1, 25, Color.DARKGRAY);
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, -0.61333709, -0.461068, 1, 35, Color.GRAY);
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, -0.61333709, -0.461068, 1, 60, Color.BLACK);
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, -0.61333709, -0.461068, 1, 100, Color.rgb(259, 255, 255));
        /*x tengely , y tengely, Közelítés mértéke, tolrancia*/
        printRedDot(gc, canvasWidth, canvasHeight);

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
