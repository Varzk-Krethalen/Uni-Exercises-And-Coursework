package CI346.lambdaExercises;

/**
 * A POJO that carries the information of a person's name.
 * Created by jb259 on 20/11/16.
 */
public class Name implements Comparable {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        StringBuilder sb = new StringBuilder(this.firstName);
        sb.append(" ");
        sb.append(this.lastName);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(this.getName());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Name)) {
            return -1;
        }
        Name that = (Name) o;
        return this.lastName.compareTo(that.lastName);
    }
}
