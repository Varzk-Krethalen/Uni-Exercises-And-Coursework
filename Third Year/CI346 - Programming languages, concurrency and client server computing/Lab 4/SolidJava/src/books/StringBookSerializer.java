package books;

public class StringBookSerializer implements  BookSerializer<String>
{
    public StringBookSerializer()
    {
    }

    @Override
    public String serialize(Book book) {
        return null;
    }

    @Override
    public Book deserialize(String data) {
        return null;
    }
}
