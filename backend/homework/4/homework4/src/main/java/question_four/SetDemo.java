package question_four;

import org.student.Logging;

import java.util.*;

public class SetDemo {

    public static Set<Book> treeSetDemo(Comparator comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            Logging.logInfo(book.toString());
        }

        return books;
    }

    public static void main(String[] args) {
        Logging.logInfo("Without Comparator");
        treeSetDemo(null);

        Logging.logInfo("With PubDateAscComparator");
        treeSetDemo(new PubDateAscComparator());

        Logging.logInfo("With PubDateDescComparator");
        treeSetDemo(new PubDateDescComparator());
    }

}