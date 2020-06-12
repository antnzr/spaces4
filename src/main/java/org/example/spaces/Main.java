package main.java.org.example.spaces;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        test(new LibraryImpl());
    }

    private static void test(Library lib) {
        assertTrue(!lib.deleteBook("1"));
        assertTrue(lib.addNewBook(new Book("1", "1", "Lex")));
        assertTrue(!lib.addNewBook(new Book("1", "1", "Lex")));
        assertTrue(lib.deleteBook("1"));
        assertTrue(lib.addNewBook(new Book("4", "Name1", "Lex3")));
        assertTrue(lib.addNewBook(new Book("3", "Name3", "Lex2")));
        assertTrue(lib.addNewBook(new Book("2", "Name2", "Lex2")));
        assertTrue(lib.addNewBook(new Book("1", "Name1", "Lex1")));
        final Collection<String> byNames = lib.listBooksByName("Name");
        assertTrue(byNames.contains("Lex3 - Name1"));
        assertTrue(byNames.contains("Name2"));
        assertTrue(byNames.contains("Name3"));
        assertTrue(byNames.contains("Lex1 - Name1"));
        final List<String> byAuthor = lib.listBooksByAuthor("Lex");
        assertTrue(byAuthor.get(0).equals("Name1"));
        assertTrue(byAuthor.get(1).equals("Name2") || byAuthor.get(1).equals("Name3"));
        assertTrue(byAuthor.get(2).equals("Name2") || byAuthor.get(2).equals("Name3"));
        assertTrue(byAuthor.get(3).equals("Name1"));
    }

    private static void assertTrue(boolean condition) {
        if (!condition) {
            throw new Error("assert failed");
        }
    }
}
