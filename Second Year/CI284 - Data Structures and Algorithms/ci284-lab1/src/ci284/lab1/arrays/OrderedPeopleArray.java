package ci284.lab1.arrays;

public class OrderedPeopleArray extends PeopleArray {
	
	public OrderedPeopleArray(int max) {
		super(max);
	}
	
	/**
	 * Return the Person with lastName, or null. Use BinarySearch to to do the finding.
	 * @param lastName
	 * @return
	 */
	public Person find(String lastName) {
		int low = 0;
		int mid;
		int high = arr.length - 1;
		while (low <= high)
		{
			mid = (low + high)/2;
			if (arr[mid] != null && arr[mid].getLastName().compareTo(lastName) < 0)
			{
				low = mid + 1;
			}
			else if (arr[mid] != null && arr[mid].getLastName().compareTo(lastName) > 0)
			{
				high = mid - 1;
			}
			else if (arr[mid] == null)
			{
				high = mid - 1;
			}
			else
				return arr[mid];
		}
		return null;
	}
	
	/**
	 * Insert a new person maintaining the order of the data.
	 * Throw ArrayIndexOutOfBoundsException if arr is full
	 * @param first
	 * @param last
	 * @param age
	 */
	public void insert(String first, String last, int age) {
		if (arr == null)
		{
			arr = new Person[10];
		}
		for(int i = 0;i <= 10;i++)
		{			
			if (arr[i] == null)
			{
				arr[i] = new Person(first, last, age);
				return;
			}
		}
		throw new ArrayIndexOutOfBoundsException("Array Full");
	}

}

