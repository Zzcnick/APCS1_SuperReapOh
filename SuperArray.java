/* Zicheng Zhen
   APCS1 pd10
   HW45 -- Come Together
   2015-12-09 */

public class SuperArray {

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;
		
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() {
	_data = new Comparable[10];
	_lastPos = -1;
	_size = 0;
    }
		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() {
	String storage = "["; // Keeps track of non-zeros.
	for (int i = 0; i < _size; i++) {
	    storage += _data[i] + ",";
	}
	if (storage.equals("[")) {return "[]";}
	return storage.substring(0,storage.length()-1) + "]";
    }
    
    //add - adds an item to the end of the array and gives the index meaning
    //precond: int value - value to be added at the end
    public void add(Comparable value) {
	if (_size+1 >= _data.length) {expand();} // Increases size if no space
	_data[_lastPos+1] = value;
	_lastPos += 1; // Afterwards, in case there is an error.
	_size += 1;
    }
    //add - adds an item to an index of the array and shifts array to the right
    //precond: int value - value to be added
    //         int index - index to add to
    public void add(int index, Comparable value) {
	if (_size+1 >= _data.length) {expand();}
	for (int i = _size; i > index; i--) {
	    _data[i] = _data[i-1];
	}
	_data[index] = value;
	_lastPos += 1;
	_size +=1;
    }

    //remove - removes an item at a specified index and shifts everything left
    //precond: int index - index to be removed;
    public void remove(int index) {
	for (int i = index; i < _lastPos; i++) {
	    _data[i] = _data[i+1];
	}
	_lastPos -= 1;
	_size -= 1;
    }
    //remove - removes last meaningful element
    public void remove() {
	remove(_lastPos);
    }
    
    //double capacity of this SuperArray
    private void expand() {
	int len = _data.length * 2;
	Comparable[] copy = new Comparable[len];
	for (int i = 0; i < len / 2; i++) {
	    copy[i] = _data[i];
	}
	_data = copy;
    }
		
    //accessor -- return value at specified index
    public Comparable get( int index ) {
	return _data[index];
    }
    public int size() {
	return _size;
    }
		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) {
	Comparable storage = _data[index];
	_data[index] = newVal;
	return storage;
    }

    //isSorted -- returns true if array is sorted from smallest to largest
    //            false otherwise
    public boolean isSorted() {
	for (int i = 0; i < _lastPos; i++) {
	    if (_data[i].compareTo(_data[i+1]) > 0)
		return false;
	}
	return true;
    }

    //linSearch -- returns index of first occurrence of a value
    //             if value not in superarray, return -1
    public int linSearch(Comparable value) {
	for (int i = 0; i < _size; i++) {
	    if (_data[i].equals(value)) {
		return i;
	    }
	}
	return -1;
    }

    //main method for testing
    public static void main( String[] args ) {
	SuperArray rationals = new SuperArray();
	SuperArray binaries = new SuperArray();
	SuperArray hexadecs = new SuperArray();

	// add()
	for (int i = 0; i < 10; i++) {
	    binaries.add(new Binary(i));
	    hexadecs.add(new Hexadecimal(i));
	    for (int j = 1; j < 3; j++) {
		rationals.add(new Rational(i,j));
	    }
	}
	
	// isSorted)()
	System.out.println("Sorted? Rationals:\t" + rationals.isSorted());
	System.out.println("Sorted? Binaries:\t" + binaries.isSorted());
	System.out.println("Sorted? Hexadecimals:\t" + hexadecs.isSorted() +
			   "\n");

	// add() - at index
	for (int i = 5; i < 8; i++) {
	    rationals.add(i, new Rational(i*i, i+2));
	    binaries.add(i, new Binary(i*i));
	    hexadecs.add(i, new Hexadecimal(i*i));
	}

	// toString()
	System.out.println("Rationals:\t" + rationals);
	System.out.println("Binaries:\t" + binaries);
	System.out.println("Hexadecimals:\t" + hexadecs +
			   "\n");

	// remove()
	for (int i = 0; i < 9; i++) {
	    binaries.remove();
	    hexadecs.remove();
	    rationals.remove();
	}
	System.out.println("Rationals:\t" + rationals);
	System.out.println("Binaries:\t" + binaries);
	System.out.println("Hexadecimals:\t" + hexadecs +
			   "\n");

	// linSearch();
	System.out.println("Linear Search for the value 3:");
	System.out.println("Rationals:\t" +
			   rationals.linSearch(new Rational(3,1)));
	System.out.println("Binaries:\t" +
			   binaries.linSearch(new Binary(3)));
	System.out.println("Hexadecimals:\t" +
			   hexadecs.linSearch(new Hexadecimal(3)) + "\n");
	
    }//end main
		
}//end class
