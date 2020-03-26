package ci284.ass2.htable;

/**
 * A HashTable with no deletions allowed. Duplicates overwrite the existing value. Values are of
 * type V and keys are strings -- one extension is to adapt this class to use other types as keys.
 * 
 * The underlying data is stored in the array `arr', and the actual values stored are pairs of 
 * (key, value). This is so that we can detect collisions in the hash function and look for the next 
 * location when necessary.
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;



//FIX 2 FUCKUPS, CHANGE 2 RECURSIVE



public class Hashtable<V> {

	private Object[] arr; //an array of Pair objects, where each pair contains the key and value stored in the hashtable
	private int max; //the size of arr. This should be a prime number
	private int itemCount; //the number of items stored in arr
	private final double maxLoad = 0.6; //the maximum load factor

	public static enum PROBE_TYPE {
		LINEAR_PROBE, QUADRATIC_PROBE, DOUBLE_HASH;
	}
	

	PROBE_TYPE probeType; //the type of probe to use when dealing with collisions
	private final BigInteger DBL_HASH_K = BigInteger.valueOf(8);

	/**
	 * Create a new Hashtable with a given initial capacity and using a given probe type
	 * @param initialCapacity
	 * @param pt
	 */
	public Hashtable(int initialCapacity, PROBE_TYPE pt) {
		max = nextPrime(initialCapacity);
		arr = new Object[max];
		probeType = pt;
	}
	
	/**
	 * Create a new Hashtable with a given initial capacity and using the default probe type
	 * @param initialCapacity
	 */
	public Hashtable(int initialCapacity) {
		max = nextPrime(initialCapacity);
		arr = new Object[max];
		probeType = PROBE_TYPE.LINEAR_PROBE;
	}

	/**
	 * Store the value against the given key. If the loadFactor exceeds maxLoad, call the resize 
	 * method to resize the array. the If key already exists then its value should be overwritten.
	 * Create a new Pair item containing the key and value, then use the findEmpty method to find an unoccupied 
	 * position in the array to store the pair. Call findEmpty with the hashed value of the key as the starting
	 * position for the search, stepNum of zero and the original key.
	 * containing   
	 * @param key
	 * @param value
	 */
	public void put(String key, V value) {
		int index = hash(key);
		Pair newItem = new Pair(key, value);
		
		if (arr[index] == null) {
			arr[index] = newItem;
			itemCount++;
		}
		else {
			arr[findEmpty(index, 0, key)] = newItem;
		}
		
		if (getLoadFactor() > maxLoad) {
			resize();
		}
	}

	/**
	 * Get the value associated with key, or return null if key does not exists. Use the find method to search the
	 * array, starting at the hashed value of the key, stepNum of zero and the original key.
	 * @param key
	 * @return
	 */
	public V get(String key) {
		if (hasKey(key)) {
			return find(hash(key), key, 0);
		}
		return null;
	}

	/**
	 * Return true if the Hashtable contains this key, false otherwise 
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		if (find(hash(key), key, 0) != null) {
			return true;
		}
		return false;
	}

	/**
	 * Return all the keys in this Hashtable as a collection
	 * @return
	 */
	public Collection<String> getKeys() {
		Collection<String> keyCollection = new ArrayList<String>();
		for (int i = 0; i < max; i++) {
			if  (arr[i] != null) {
				keyCollection.add(((Hashtable<V>.Pair) arr[i]).key);
			}
		}
		return keyCollection;
	}

	/**
	 * Return the load factor, which is the ratio of itemCount to max
	 * @return
	 */
	public double getLoadFactor() {
		double load = (double) itemCount / (double) max;
		return load;
	}

	/**
	 * return the maximum capacity of the Hashtable
	 * @return
	 */
	public int getCapacity() {
		return max;
	}
	
	/**
	 * Find the value stored for this key, starting the search at position startPos in the array. If
	 * the item at position startPos is null, the Hashtable does not contain the value, so return null. 
	 * If the key stored in the pair at position startPos matches the key we're looking for, return the associated 
	 * value. If the key stored in the pair at position startPos does not match the key we're looking for, this
	 * is a hash collision so use the getNextLocation method with an incremented value of stepNum to find 
	 * the next location to search (the way that this is calculated will differ depending on the probe type 
	 * being used). Then use the value of the next location in a recursive call to find.
	 * @param startPos
	 * @param key
	 * @param stepNum
	 * @return
	 */
	private V find(int startPos, String key, int stepNum) {
		if (arr[startPos] == null) {
			return null;
		}
		else {
			while (arr[startPos] != null) {
				if (((Hashtable<V>.Pair) arr[startPos]).key.equals(key)) {
					return ((Hashtable<V>.Pair) arr[startPos]).value;
				}
				stepNum++;
				startPos = getNextLocation(startPos, stepNum, key);
			}
			return null;
		}
	}

	/**
	 * Find the first unoccupied location where a value associated with key can be stored, starting the
	 * search at position startPos. If startPos is unoccupied, return startPos. Otherwise use the getNextLocation
	 * method with an incremented value of stepNum to find the appropriate next position to check 
	 * (which will differ depending on the probe type being used) and use this in a recursive call to findEmpty.
	 * @param startPos
	 * @param stepNum
	 * @param key
	 * @return
	 */
	private int findEmpty(int startPos, int stepNum, String key) {
		while (true) {
			if (arr[startPos] == null) {
				itemCount++;
				return startPos;
			}
			else if (((Hashtable<V>.Pair) arr[startPos]).key.equals(key)) {
				return startPos;
			}
			stepNum++;
			startPos = getNextLocation(startPos, stepNum, key);
		}
		//return findEmpty(startPos, stepNum, key);
	}

	/**
	 * Finds the next position in the Hashtable array starting at position startPos. If the linear
	 * probe is being used, we just increment startPos. If the double hash probe type is being used, 
	 * add the double hashed value of the key to startPos. If the quadratic probe is being used, add
	 * the square of the step number to startPos.
	 * @param i
	 * @param stepNum
	 * @param key
	 * @return
	 */
	private int getNextLocation(int startPos, int stepNum, String key) {
		int step = startPos;
		switch (probeType) {
		case LINEAR_PROBE:
			step++;
			break;
		case DOUBLE_HASH:
			step += doubleHash(key);
			break;
		case QUADRATIC_PROBE:
			step += stepNum * stepNum;
			break;
		default:
			break;
		}
		return step % max;
	}

	/**
	 * A secondary hash function which returns a small value (less than or equal to DBL_HASH_K)
	 * to probe the next location if the double hash probe type is being used
	 * @param key
	 * @return
	 */
	private int doubleHash(String key) {
		BigInteger hashVal = BigInteger.valueOf(key.charAt(0) - 96);
		for (int i = 0; i < key.length(); i++) {
			BigInteger c = BigInteger.valueOf(key.charAt(i) - 96);
			hashVal = hashVal.multiply(BigInteger.valueOf(27)).add(c);
		}
		return DBL_HASH_K.subtract(hashVal.mod(DBL_HASH_K)).intValue();
	}

	/**
	 * Return an int value calculated by hashing the key. See the lecture slides for information
	 * on creating hash functions. The return value should be less than max, the maximum capacity 
	 * of the array
	 * @param key
	 * @return
	 */
	private int hash(String key) {
		BigInteger hashVal = BigInteger.valueOf(key.charAt(0) - 96);
		for (int i = 0; i < key.length(); i++) {
			BigInteger c = BigInteger.valueOf(key.charAt(i) - 96);
			hashVal = hashVal.multiply(BigInteger.valueOf(27)).add(c);
		}
		return hashVal.mod(BigInteger.valueOf(max)).intValue();
	}
	
	private int hash2(String key) {
		int hash = 19;
		for (int i = 0; i < key.length(); i++) {
			if (i % 2 == 0) {
				hash = hash*13 + key.charAt(i)*7;
			}
			else {
				hash = hash*5 + key.charAt(i)*17;
			}
		}
		hash = hash % max;
		return hash;
	}

	/**
	 * Return true if n is prime
	 * @param n
	 * @return
	 */
	private boolean isPrime(int n) {
		if (n % 2 == 0 || n == 1) {
			return false;
		}
		for (int i=3; i*i <= n; i+=2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the smallest prime number which is larger than n
	 * @param n
	 * @return
	 */
	private int nextPrime(int n) {
		while (!isPrime(n)) {
			n++;
		}
		return n;
	}

	/**
	 * Resize the hashtable, to be used when the load factor exceeds maxLoad. The new size of
	 * the underlying array should be the smallest prime number which is at least twice the size
	 * of the old array.
	 */
	private void resize() {
		Object[] oldArr = arr;
		max = nextPrime(max * 2);
		arr = new Object[max];
		itemCount = 0;
		for (Object old : oldArr) {
			if (old != null) {
				Pair temp = (Hashtable<V>.Pair) old;
				put(temp.key, temp.value);
			}
		}
	}

	
	/**
	 * Instances of Pair are stored in the underlying array. We can't just store
	 * the value because we need to check the original key in the case of collisions.
	 * @author jb259
	 *
	 */
	private class Pair {
		private String key;
		private V value;

		public Pair(String key, V value) {
			this.key = key;
			this.value = value;
		}
	}

}
