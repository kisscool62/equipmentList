package models;

import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 12:16 PM
 */
@Entity
public class Brand extends Model {

    @Id
    public String name;

    public static Finder<String,Brand> find = new Finder<String,Brand>(String.class, Brand.class);

    public Brand() {
    }

    public Brand(String name) {
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
        Brand brand = find.byId(brandName);
        Logger.debug("Found 4 Brand for name " + brandName + ": " + brand);
        if(brand == null){
            brand = create(brandName);
        }
        Logger.debug("Found 4 Brand for name " + brandName + ": " + brand);
        return brand;
    }

    private static Brand create(String brandName) {
        Brand brand = new Brand(brandName);
        brand.save();
        return brand;
    }
}
