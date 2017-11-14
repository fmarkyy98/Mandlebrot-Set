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

    public ComplexNumber getConjugated() {
        ComplexNumber c = new ComplexNumber();
        c.realPart = this.realPart;
        c.imaginaryPart = -this.imaginaryPart;
        return c;
    }

    public double lenSquared() {
        return Math.pow(this.realPart, 2) + Math.pow(this.imaginaryPart, 2);
    }

    public static ComplexNumber Transzformal(int m, int n, int width, int height, double xTranslate, double yTranslate, double zoomIn) {
        double x = (double) 5 * m / zoomIn / (width - 1) - (2.5 / zoomIn - xTranslate);
        double y = (double) 3 * n / zoomIn / (height - 1) - (1.5 / zoomIn - yTranslate);

        ComplexNumber z = new ComplexNumber(x, y);
        return z;
    }

    public static boolean HalmazbanVanE(ComplexNumber c) {
        int j = 100/*Tolerancia*/;
        ComplexNumber x = new ComplexNumber(c);
        while (j > 0 && x.lenSquared() < 4) {
            x.mul(x);
            x.add(c);
            j--;
        }
        return j == 0;
    }

}
