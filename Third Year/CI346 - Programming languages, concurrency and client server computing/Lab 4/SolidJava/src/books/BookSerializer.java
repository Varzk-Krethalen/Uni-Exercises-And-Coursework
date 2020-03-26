package books;

public interface BookSerializer<T>
{
    public T serialize(Book book);
    public Book deserialize(T data);
}
