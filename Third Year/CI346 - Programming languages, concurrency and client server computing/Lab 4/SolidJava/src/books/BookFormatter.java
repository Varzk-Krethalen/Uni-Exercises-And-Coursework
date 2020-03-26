package books;

/**
 * Created by jb259 on 21/10/16.
 */
public abstract class BookFormatter {

    protected final Book book;

    public BookFormatter(Book book) {
        this.book = book;
    }

    public abstract String format();

    public String formatInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle()+" ["+book.getLength()+" pages]");
        sb.append("\n");
        sb.append(book.getAuthor().getName());
        sb.append("\n");
        return sb.toString();
    }

    public String formatHeader() {
        return book.getAuthor().getName()+", "+book.getTitle();
    }

    public String formatPage() {
        return book.getCurrentPage();
    }
}
