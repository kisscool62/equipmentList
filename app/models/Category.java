package models;

import com.avaje.ebean.Page;
import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 12:13 PM
 */
@Entity
public class Category extends Model {

    @Id
    public String name;

    public static Finder<String,Category> find = new Finder<String,Category>(String.class, Category.class);

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Return a page of categories
     *
     * @param page Page to display
     * @param pageSize Number of categories per page
     * @param sortBy Category property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Category> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Category findOrCreate(String categoryName){
        //we can't find or create a category with id null
        if(categoryName == null){
            return null;
        }
        Category category = find.byId(categoryName);
        Logger.debug("Found 4 Category for name " + categoryName + ": " + category);
        if(category == null){
            category = create(categoryName);
        }
        Logger.debug("Found 4 Category for name " + categoryName + ": " + category);
        return category;
    }


    public static Category create(String categoryName) {
        Category category = new Category(categoryName);
        category.save();
        return category;
    }
}
