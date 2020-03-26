package books;

public class BookContentsFormatter extends BookFormatter
{
    public  BookContentsFormatter(Book book)
    {
        super(book);
    }

    @Override
    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<book.getLength();i++) {
            stringBuilder.append("------------------------------------------------- ");
            stringBuilder.append(formatHeader());
            stringBuilder.append("------------------------------------------------- \n");
            stringBuilder.append(formatPage());
            book.turnPage();
        }
        return stringBuilder.toString();
    }
}
