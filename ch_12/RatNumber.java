public class RatNumber {
  private int num = 0;
  private int denom = 1;

  public RatNumber() {}

  public RatNumber(int n, int d) 
      throws InvalidDenominatorException {
    num = d > 0 ? n : -n; 
    denom = Math.abs(d);
    normalize();
  }

  public int numerator() { return num; }
  public int denominator() { return denom; }

  public boolean isZero() { 
    return num == 0; 
  }

  public boolean equals(Object o) {
    if (o instanceof RatNumber) {
      RatNumber r = (RatNumber) o;
      return numerator() * r.denominator() ==
      	denominator() * r.numerator();
    }
    else 
      return false;
  }

  public String toString() {
    return "[" + num + "/" + denom + "]";
  }

  public RatNumber add(RatNumber r) 
      throws InvalidDenominatorException {
    int n, d;
    n = numerator() * r.denominator() + 
      r.numerator() * denominator();
    d = denominator () * r.denominator ();
    return new RatNumber(n, d);
  }

  private void normalize() 
    throws InvalidDenominatorException {
    if (num == 0)
      return;
    if (denom > 0) {
      int g = ggt(Math.abs(num), denom);
      num = num / g;
      denom = denom / g;
    }
    else
      throw new InvalidDenominatorException();
  }

  private int ggt(int x, int y) {
    while (x != y) {
      if (x > y) 
      	x -= y;
      else
      	y -= x;
    }
    return x;
  }
}
