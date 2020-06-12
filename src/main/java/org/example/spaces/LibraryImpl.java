package main.java.org.example.spaces;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryImpl implements Library {

    private static List<Book> storage = new ArrayList<>();

    @Override
    public boolean addNewBook(Book book) {
        if (book == null) {
            return false;
        }

        if (!isBookExists(book.id)) {
            storage.add(book);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteBook(String id) {
        if (!isBookExists(id)) {
            return false;
        }

        storage.removeIf(bookLocal -> bookLocal.id.equals(id));
        return true;
    }

    @Override
    public Collection<String> listBooksByName(String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return Collections.emptyList();
        }

        List<Book> filtered = storage
                .stream()
                .filter(Objects::nonNull)
                .filter(bookLocal -> bookLocal.name.toLowerCase()
                        .contains(searchString.toLowerCase()))
                .limit(10)
                .collect(Collectors.toList());

        List<String> names = filtered
                .stream()
                .map(book -> book.name)
                .collect(Collectors.toList());

        return filtered
                .stream()
                .map(book -> {
                    if (Collections.frequency(names, book.name) > 1) {
                        return String.format("%s - %s", book.author, book.name);
                    }

                    return book.name;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listBooksByAuthor(String searchString) {
        if (searchString == null || searchString.isEmpty()) {
            return Collections.emptyList();
        }

        return storage
                .stream()
                .filter(Objects::nonNull)
                .filter(bookLocal -> bookLocal.author.toLowerCase()
                        .contains(searchString.toLowerCase()))
                .limit(10)
                .sorted(bookComparator)
                .map(book -> book.name)
                .collect(Collectors.toList());
    }

    private Comparator<Book> bookComparator = (book1, book2) ->
            book1.author.compareToIgnoreCase(book2.author);

    private static boolean isBookExists(String id) {
        return storage.stream().anyMatch(bookLocal -> bookLocal.id.equals(id));
    }
}
