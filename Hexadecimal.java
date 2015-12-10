/* Team Tofoo - Zicheng Zhen and Gabriel Marks
   APCS1 pd10
   HW44 -- This or That or Fourteen Other Things / Hexadecimal Class
   2015-12-08 */

public class Hexadecimal implements Comparable {

    private final static String HEXDIGITS = "0123456789ABCDEF";
    private int _decNum;
    private String _hexNum;

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	this();
	_decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	this();
	_decNum = hexToDec(s);
	_hexNum = s;
    }

    /*=====================================
      Accessors and Mutators
      =====================================*/
    public int getValue() {
	return _decNum;
    }

    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;
    }

    /*=====================================
      String chooseHex(int) - returns hexadec representation from 0 - 15
      pre: 0 <= n < 16
      post: returns String from 0 - 9, a - f 
      =====================================*/
    public static String chooseHex(int n) {
	try {
	    return HEXDIGITS.substring(n,n+1);
	} catch (Exception ex) {
	    System.out.println("ERROR: Index out of range.");
	    return "";
	}
    }

    /*=====================================
      String chooseDec(String) - returns hexadec representation from 0 - 15
      pre: "0" <= n < "f"
      post: returns int from 0 to 15
      =====================================*/
    public static int chooseDec(String s) {
	try {
	    return HEXDIGITS.indexOf(s);
	} catch (Exception ex) {
	    System.out.println("ERROR: Index out of range.");
	    return -1;
	}
    }
	
    /*=====================================
      String decToHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "10"
      decToHex(3) -> "11"
      decToHex(14) -> "1110"
      =====================================*/
    public static String decToHex( int n ) {
	String hexVal = "";
	while (n != 0) {
	    int digit = n % 16;
	    hexVal = chooseHex(digit) + hexVal;
	    n /= 16;
	}
	if (hexVal.equals(""))
	    return "0";
	return hexVal;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToHexR(0) -> "0"
      decToHexR(1) -> "1"
      decToHexR(2) -> "10"
      decToHexR(3) -> "11"
      decToHexR(14) -> "1110"
      =====================================*/
    public static String decToHexR( int n ) {
	if (n < 16) {
	    return "" + chooseHex(n);
	}
	return decToHexR(n/16) + ("" + chooseHex(n%16));
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 16
      hexToDec("11") -> 17
      hexToDec("AD") -> 173
      =====================================*/
    public static int hexToDec( String s ) {
	int total = 0;
	int times = 1;
	for (int i = s.length()-1; i > -1; i--) {
	    total += chooseDec(s.substring(i,i+1)) * times;
	    times *= 16;
	}
	return total;
    }

    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns decimal equivalent as int
      eg  
      hexToDecR("0") -> 0
      hexToDecR("1") -> 1
      hexToDecR("10") -> 16
      hexToDecR("11") -> 17
      =====================================*/
    public static int hexToDecR( String s ) { 
	if (s.length() == 1) {
	    return chooseDec(s);
	}
	return 16 * hexToDecR(s.substring(0,s.length()-1)) +
	    chooseDec(s.substring(s.length()-1));
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
	boolean retVal = this == other;
	if (!(retVal)) {
	    retVal = other instanceof Hexadecimal;
	    if (retVal) {
		return (this.compareTo(other) == 0);
	    }
	    return false;
	}
	return true;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (other.equals(null))
	    throw new NullPointerException("ERROR: Null object");
	if (!(other instanceof Hexadecimal))
	    throw new ClassCastException("ERROR: Incomparable classes");
	if (_decNum > ((Hexadecimal)other).getValue()) {
	    return 1;
	} else if (_decNum < ((Hexadecimal)other).getValue()) {
	    return -1;
	} else {
	    return 0;
	}
    }

    //main method for testing
    public static void main( String[] args ) {
	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal("5");
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(7);
	Hexadecimal b5 = new Hexadecimal(266);
	Hexadecimal b6 = new Hexadecimal("10A");
	String s = "hallo";
	
	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );
	System.out.println( b5 );
	System.out.println( b6 );

	System.out.println( "\nhexToDec" );
	System.out.println(Hexadecimal.hexToDec("100")); //should be 256
	System.out.println(Hexadecimal.hexToDec("DEF")); //should be 3567
	System.out.println(Hexadecimal.hexToDec("ABE")); //should be 2750
	
	System.out.println( "\ndecToHex" );
	System.out.println(Hexadecimal.decToHex(256)); //should be "100"
	System.out.println(Hexadecimal.decToHex(3567)); //should be "3567"
	System.out.println(Hexadecimal.decToHex(2750)); //should be "2750"

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false
	System.out.println( b6.equals(b5) ); //should be true
	System.out.println( b6.equals(s)  ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	try {
	System.out.println( b5.compareTo(s)  ); //should throw ClassCastException
	} catch (Exception e) {
	    System.out.println(e);
	}
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
