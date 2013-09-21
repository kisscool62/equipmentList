package models;

import com.avaje.ebean.Page;
import play.Logger;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 12:14 PM
 */
@Entity
public class TypeOfEquipment extends Model {

    @Id
    public String name;

    public static Finder<String, TypeOfEquipment> find = new Finder<String, TypeOfEquipment>(String.class, TypeOfEquipment.class);

    public TypeOfEquipment() {
    }

    public TypeOfEquipment(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeOfEquipment{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Return a page of typesOfEquipment
     *
     * @param page Page to display
     * @param pageSize Number of equipment types per page
     * @param sortBy Equipment property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<TypeOfEquipment> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static TypeOfEquipment findOrCreate(String typeName) {
        //we can't find or create a TypeOfEquipment with id null
        if(typeName == null){
            return null;
        }
        TypeOfEquipment type = find.byId(typeName);
        Logger.debug("Found TypeOfEquipment for name " + typeName + ": " + type);
        if(type == null){
            type = create(typeName);
        }
        Logger.debug("Found TypeOfEquipment for name " + typeName + ": " + type);
        return type;
    }

    public static TypeOfEquipment create(String typeName) {
        TypeOfEquipment type = new TypeOfEquipment(typeName);
        type.save();
        return type;
    }
}
