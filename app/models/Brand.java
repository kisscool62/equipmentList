package models;

import com.avaje.ebean.ExpressionList;
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
 * Time: 12:16 PM
 */
@Entity
public class Brand extends Model {

    @Id
    public Long id;

    @Column(unique = true)
    public String nameId;

    @Constraints.Required
    @Column(unique = true)
    public String name;

    public static Finder<String,Brand> find = new Finder<String,Brand>(String.class, Brand.class);

    public Brand() {
    }

    private static String makeId(String name){
        Preconditions.checkArgument(name != null && !"".equals(name.trim()), "Name of Category mustn't be null");
        return name.trim().toUpperCase();
    }

    public Brand(String name) {
        this.nameId = makeId(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                '}';
    }

    public static Brand findOrCreate(String brandName) {
        //we can't find or create a brand with id null
        if(brandName == null){
            return null;
        }
        Brand brand = findByNameId(makeId(brandName));
        Logger.debug("Found 4 Brand for name " + brandName + ": " + brand);
        if(brand == null){
            brand = create(brandName);
        }
        Logger.debug("Found 4 Brand for name " + brandName + ": " + brand);
        return brand;
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(Brand c: Brand.find.orderBy("nameId").findList()) {
            options.put(c.name, c.name);
        }
        return options;
    }

    private static Brand findByNameId(String nameId){
        return createExpressionFindByNameId(nameId).findUnique();
    }

    private static ExpressionList<Brand> createExpressionFindByNameId(String nameId) {
        return find.where().ilike("nameId", "%" + nameId + "%");
    }

    private static Brand create(String brandName) {
        Brand brand = new Brand(brandName);
        brand.save();
        return brand;
    }
}
