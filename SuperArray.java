/* Team FeelTheBernieSanders - Zicheng Zhen and Matthew So
   APCS1 pd10
   HW42 -- Array of Titanium / More Implementation of Interfaces
   2015-12-04 */

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
	for (int i = _lastPos; i > index; i--) {
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

    //main method for testing
    public static void main( String[] args ) {
	SuperArray rationals = new SuperArray();
	SuperArray binaries = new SuperArray();
	SuperArray hexadecs = new SuperArray();
	
    }//end main
		
}//end class
