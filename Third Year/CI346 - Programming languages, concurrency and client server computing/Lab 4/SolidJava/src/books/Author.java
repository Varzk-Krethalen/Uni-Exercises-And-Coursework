package books;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents an Author.
 * Created by jb259 on 21/10/16.
 */
public class Author implements Serializable {
    private String firstName;
    private String middleName;
    private String surname;
    private Date dateOfBirth;

    public Author(String firstName, String middleName, String surname, Date dob) {
        this.setFirstName(firstName);
        this.setMiddleName(middleName);
        this.setSurname(surname);
        this.setDateOfBirth(dob);
    }

    public String getName() {
        StringBuilder n = new StringBuilder();
        if(!firstName.isEmpty()) n.append(firstName.charAt(0) + ". ");
        if(!middleName.isEmpty()) n.append(middleName.charAt(0) + ". ");
        n.append(surname);
        return n.toString();
    }

    public String getFirstName() { return firstName;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMiddleName() { return middleName;}

    public void setMiddleName(String middleName) {this.middleName = middleName;}
}
