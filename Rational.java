/* Team FeelTheBernieSanders -- Zicheng Zhen and Dennis Duman
   APCS1 pd10 
   HW41 -- Implementing Interfaces
   2015-12-03 */

// Rational Class
public class Rational implements Comparable {
    // Instance Variables
    private int numer;
    private int denom;

    // Constructors
    public Rational() {
	numer = 0;
	denom = 1;
    }
    public Rational(int n, int d) {
	this();
	if (d == 0) {
	    System.out.println("Please enter a valid denominator!");
	}
	else {
	    numer = n;
	    denom = d;
	}
    }

    // Mutators and Accessors
    public int getNumer() {
	return numer;
    }
    public int getDenom() {
	return denom;
    }
    public void setNumer(int i) {
	numer = i;
    }
    public void setDenom(int i) {
	denom = i;
    }

    // Methods
    public String toString() {
	return (numer + "/" + denom);
    }
    public double floatValue() {
	return (double)numer / denom;
    }
    public void multiply(Rational r) {
	if (r.denom == 0) {
	    System.out.println("ERROR: Denominator value of 0");
	}
	else { // a/b * c/d = ac / bd
	    numer *= r.getNumer(); // n * n
	    denom *= r.getDenom(); // d * d
	    reduce();
	}
    }
    public void divide(Rational r) {
	if (r.denom == 0 || r.numer == 0) {
	    System.out.println("ERROR: Division by 0");
	}
	else { // a/b / c/d = ad / bc
	    numer *= r.getDenom(); // a * d
	    denom *= r.getNumer(); // b * c
	    reduce();
	}
    }
    public void add(Rational r) {
	if (r.denom == 0 || r.numer == 0) {
	    System.out.println("ERROR: Division by 0");
	}
	else { // a/b + c/d = [ad + bc] / bd
	    int sum1 = numer * r.getDenom(); // ad
	    int sum2 = r.getNumer() * denom; // bc
	    int newDenom = denom * r.getDenom(); // bd
	    numer = sum1 + sum2;
	    denom = newDenom;
	    reduce();
	}
    }
    public void subtract(Rational r) {
	int rNumer = r.getNumer() * -1;
	int rDenom = r.getDenom();
	add(new Rational(rNumer, rDenom));
	reduce();
    }

    // Greatest Common Denominator - Used for Reduce()
    public static int gcd(int a, int b) { // Euclidean Algorithm
	while (a % b != 0) {
	    int store = a;
	    a = b;
	    b = store % b;
	}
	return b;
    }
    private int gcd() { // Euclidean Algorithm, accesses self
	return gcd(numer,denom);
    }
    
    // Reduce Methods - Simplifies The Rational
    public void reduce() {
	int common = gcd(numer,denom);
	numer = numer / common;
	denom = denom / common;
    }
    public static void reduce(Rational r) {
	int common = gcd(r.getNumer(), 
			 r.getDenom());
	r.setNumer(r.getNumer() / common);
	r.setDenom(r.getDenom() / common);
    }

    // Compare To - -1 if r > s
    //               0 if r = s
    //               1 if r < s
    public int compareTo(Rational r) {
	reduce();
	r.reduce();
	// a/b [r] = c/d [s] | ad = bc
	if (numer * r.getDenom() == r.getNumer() * denom) {return 0;}
	// a/b [r] < c/d [s] | ad < bc
	if (numer * r.getDenom() < r.getNumer() * denom) {return -1;}
	// a/b [r] < c/d [s] | ad > bc
	if (numer * r.getDenom() > r.getNumer() * denom) {return 1;}
	return -999;
    }
    public int compareTo(Object o) {
	if (other.equals(null))
	    throw new NullPointerException("ERROR: Null object");
	if (!(other instanceof Rational))
	    throw new ClassCastException("ERROR: Incomparable classes");
	if (this == o) {return 0;}
	return compareTo((Rational)o);
    }

    // equals - returns if two rationals are equal
    public boolean equals(Object r) {
	boolean same = this == r;
	if (!same) {
	    return (r instanceof Rational && // is it a Rational?
		    this.compareTo((Rational)r) == 0);
	}
	return same;
    }
    
    // Testing
    public static void main(String[] args) {
	Rational oof = new Rational();
	System.out.println(oof);
	System.out.println(oof.floatValue());
	oof.setNumer(15); oof.setDenom(10);
	System.out.println(oof.floatValue()); // oof = 3/2
	
	Rational rab = new Rational(1,3);
        oof.multiply(rab); // oof = 1/2
	System.out.println(oof);
	oof.divide(rab); // oof = 3/2
	System.out.println(oof);
	oof.divide(rab); // oof = 9/2
	System.out.println(oof);

	Rational zab = new Rational(7,0);
	System.out.println(zab);
	System.out.println(gcd(100,1000) + "\n" +
			   gcd(61,5120934) + "\n" +
			   gcd(15, 505));
	reduce(oof);
	System.out.println(oof);
	oof.multiply(new Rational(2,2));
	oof.reduce();
	System.out.println(oof);

	Rational foo = new Rational(10,3); // foo = 10/3
	Rational bar = new Rational(-5,3);
	foo.add(bar); // foo > 5/3
	System.out.println(foo);
	foo.subtract(bar); // foo > 10/3
	System.out.println(foo);
	foo.add(new Rational(7,6)); // foo = 27 / 6 = 9 / 2
	System.out.println(foo);
	foo.multiply(new Rational(-12,-3)); // foo = 18 / 1;
	System.out.println(foo);
	foo.divide(new Rational(-5,3)); // foo = -54 / 5
	System.out.println(foo);
	System.out.println(foo.compareTo(new Rational(108,-10)));
	Rational plis = new Rational(7,5);
	Rational bruh = new Rational(98,70);
	Rational whai = new Rational(98,69);
	System.out.println(plis.equals(bruh)); // true
	System.out.println(plis.equals(whai)); // false
    }
}
