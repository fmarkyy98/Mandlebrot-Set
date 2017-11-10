package pixelenkentrajzolas;

public class ComplexNumber {

    private Double realPart;
    private Double imaginaryPart;

    public void add(final ComplexNumber c) {
        this.realPart += c.realPart;
        this.imaginaryPart += c.imaginaryPart;
    }

    public void mul(final ComplexNumber c) {
        this.realPart *= c.realPart;
        this.realPart *= c.imaginaryPart;
        this.imaginaryPart *= c.realPart;
        this.imaginaryPart *= c.imaginaryPart;
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
}
