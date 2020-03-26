package books;

import java.io.*;
import java.util.Optional;

public class FileBookSerializer implements BookSerializer<String>
{
    @Override
    public String serialize(Book book) {
        try {
            FileOutputStream fout = new FileOutputStream(book.getTitle()+".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book deserialize(String path) {
        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fin);
            return (Book) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
