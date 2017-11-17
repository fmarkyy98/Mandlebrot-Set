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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PixelenkentRajzolas extends Application {

    private final Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION);

    private void printMandlebrotSet(final PixelWriter pw, final int width, final int height, double xTranslate, double yTranslate, double zoomIn, int tolerance, Color color) {
        ComplexNumber c = new ComplexNumber();
        ComplexNumber x = new ComplexNumber();
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                ComplexNumber.Transzformal(c, j, i, width, height, xTranslate, yTranslate, zoomIn);
                int a = c.HalmazbanVanE(x, tolerance);
                Color q;
                if (a < 256) {
                    q = Color.rgb(a, a, a);
                } else {

                    q = Color.rgb(255, a - 255, a - 255);
                }

                pw.setColor(j, i, q);
                //https://stackoverflow.com/questions/27389249/what-type-of-array-required-in-writableraster-method-setpixels
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
double zoom = 10;
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
        
        
        
        
        
//        int tolerance = 10;
//        int shape =255;
        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 200, Color.LIGHTGRAY);
//        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 215, Color.DARKGRAY);
//        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 230, Color.GRAY);
//        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 255, Color.rgb(40, 40, 40));
//        printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 280, Color.rgb(0, 0, 0));
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
Scene scene = new Scene(root);
scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.A) {
                zoom *= 10;
                event.consume();
                printMandlebrotSet(gc.getPixelWriter(), canvasWidth, canvasHeight, x, y, zoom, 200, Color.LIGHTGRAY);
            }
        });

        primaryStage.setMaxHeight(canvasHeight);
        primaryStage.setMaxWidth(canvasWidth);
        primaryStage.setTitle("Pixelenkent Rajzolas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
