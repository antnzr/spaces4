package main.java.org.example.spaces;

import java.util.Collection;
import java.util.List;

public interface Library {
    /**
     Adds a new book object to the Library.
     @param book: book to add to the Library
     @return false if the book with same id already exists in the Library, true – otherwise.
     */
    boolean addNewBook(Book book);

    /**
     Deletes the book with the specified id from the Library.
     @return true if the book with same id existed in the Library, false – otherwise.
     */
    boolean deleteBook(String id);

    /**
     @return 10 book names containing the specified string. If there are several books with the same name, author's name is appended to book's name.
     */
    Collection<String> listBooksByName(String searchString);

    /**
     @return 10 book names whose author contains the specified string, ordered by authors.
     */
    List<String> listBooksByAuthor(String searchString);
}
