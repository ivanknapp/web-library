package beans.authors;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class AuthorList {

    public AuthorList() {
    }
    
    private ArrayList<Author> authorList = new ArrayList<>();

    public ArrayList<Author> getAuthorList() {
        if (authorList.isEmpty()) {
            initList();
        }
        return authorList;
    }

    private void initList() {
        authorList.add(new Author("Garry Goldman"));
        authorList.add(new Author("Akunin Borya"));
        authorList.add(new Author("Horstman Shon"));
    }
    
    
}
