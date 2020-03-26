package ci284.lab1.arrays;


public class UnorderedPeopleArray extends PeopleArray {

	public UnorderedPeopleArray(int max) {
		super(max);
	}
	
	/**
	 * Return the Person with lastName, or null. Use SequentialSearch to find the target.
	 * @param lastName
	 * @return
	 */
	public Person find(String lastName) {
		for(int i = 0;i <= arr.length - 1;i++)
		{
			if (arr[i] != null && arr[i].getLastName().equals(lastName))
			{
				return arr[i];
			}
		}
		return null;
	}
	
	/**
	 * Insert a new person to the end of the array.
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

