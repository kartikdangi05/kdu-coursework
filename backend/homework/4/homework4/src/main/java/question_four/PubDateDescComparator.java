package question_four;

import java.util.Comparator;

public class PubDateDescComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        if(b1.getYear() < b2.getYear()){
            return 1;
        }
        else if(b1.getYear() == b2.getYear()){
            if(b1.getTitle().compareTo(b2.getTitle()) > 0){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }
}
