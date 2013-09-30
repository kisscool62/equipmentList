package models;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Page;
import com.google.common.base.Preconditions;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 12:13 PM
 */
@Entity
public class Category extends Model {

    @Id
    public Long id;

    @Column(unique = true)
    public String nameId;

    @Constraints.Required
    @Column(unique = true)
    public String name;

    public static Finder<String,Category> find = new Finder<String,Category>(String.class, Category.class);

    public Category(String name) {
        this.nameId = makeId(name);
        this.name = name.trim();
    }

    private static String makeId(String name){
        Preconditions.checkArgument(name != null && !"".equals(name.trim()), "Name of Category mustn't be null");
        return name.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return "Category{" +
                "nameId='" + nameId + '\'' + ", "+
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
        if(categoryName == null && "".equals(categoryName)){
            return null;
        }
        Category category = findByNameId(makeId(categoryName));
        Logger.debug("Found Category for name " + categoryName + ": " + category);
        if(category == null){
            category = create(categoryName);
        }
        Logger.debug("Found Category for name " + categoryName + ": " + category);
        return category;
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Category c: Category.find.orderBy("nameId").findList()) {
            options.put(c.id.toString(), c.name);
        }
        return options;
    }

    private static Category create(String categoryName) {
        Logger.info("create category" + categoryName);
        Category category = new Category(categoryName);
        Logger.info("create category" + category);
        category.save();
        return category;
    }

    @Override
    public void save(){
        nameId = makeId(name);
        super.save();
    }

    public boolean saveOrReturnFalseIfExists(){
        nameId = makeId(name);
        if(nameIdExists()){
            Logger.info(toString() + " was found, so was not saved");
            return false;
        }else{
            Logger.info(toString() + " was not found, so was saved");
            save();
            return true;
        }
    }

    /**
     *
     * @return true if nameId is found in database
     */
    public boolean nameIdExists() {
        return createExpressionFindByNameId(nameId).findRowCount() != 0;
    }

    private static Category findByNameId(String nameId){
        return createExpressionFindByNameId(nameId).findUnique();
    }

    private static ExpressionList<Category> createExpressionFindByNameId(String nameId) {
        return find.where().ilike("nameId", "%" + nameId + "%");
    }


}
