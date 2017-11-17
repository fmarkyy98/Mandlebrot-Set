package pixelenkentrajzolas;

public class ComplexNumber {

    private Double realPart;
    private Double imaginaryPart;

    public ComplexNumber() {
        this.realPart = 0.0;
        this.imaginaryPart = 0.0;
    }

    public ComplexNumber(double a, double i) {
        this.realPart = a;
        this.imaginaryPart = i;
    }

    public ComplexNumber(ComplexNumber c) {
        this.realPart = c.realPart;
        this.imaginaryPart = c.imaginaryPart;
    }

    public void add(final ComplexNumber c) {
        this.realPart += c.realPart;
        this.imaginaryPart += c.imaginaryPart;
    }

    public void mul(final ComplexNumber c) {
        double a = this.realPart;
        double ia = this.imaginaryPart;
        double b = c.realPart;
        double ib = c.imaginaryPart;
        this.realPart = a * b - ia * ib;
        this.imaginaryPart = a * ib + ia * b;
    }

    public double lenSquared() {
        return this.realPart * this.realPart + this.imaginaryPart * this.imaginaryPart;

    }

    public static void Transzformal(ComplexNumber z, double m, double n, int width, int height, double xTranslate, double yTranslate, double zoomIn) {
        double x = 5 * m / zoomIn / (width - 1) - (2.5 / zoomIn - xTranslate);
        double y = 3 * n / zoomIn / (height - 1) - (1.5 / zoomIn - yTranslate);
        z.realPart = x;
        z.imaginaryPart = y;
    }

    public int HalmazbanVanE(ComplexNumber x, int tolerance) {
        int j = 255*2/*Tolerancia*/;
        x.realPart = this.realPart;
        x.imaginaryPart = this.imaginaryPart;
        while ( x.lenSquared() < 4 && j > 0) {
            x.mul(x);
            x.add(this);
            j--;
        }
        return j;
    }

}
