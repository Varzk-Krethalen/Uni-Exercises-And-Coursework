package books;

public class BookInfoFormatter extends BookFormatter
{
    public  BookInfoFormatter(Book book)
    {
        super(book);
    }

    @Override
    public String format() {
        return formatInfo();
    }
}
