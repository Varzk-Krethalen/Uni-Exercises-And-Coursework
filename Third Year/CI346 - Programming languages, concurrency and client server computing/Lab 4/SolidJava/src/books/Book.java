package books;

import java.io.*;
import java.util.Optional;

/**
 * This class represents a Book. It contains the code to print various details
 * of the book and to save and retrieve books using serialization.
 * Created by jb259 on 21/10/16.
 *
 */
public class Book {

    private String title;
    private Author author;
    private int currentPage = 0;
    private String[] contents;

    public Book(String title, Author author, String[] contents) {
        this.title = title;
        this.author = author;
        this.contents = contents;
    }

    public int getLength() {
        return contents.length;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void turnPage() {
        currentPage++;
    }

    public String getCurrentPage() {
        return contents[currentPage%contents.length];
    }

}
