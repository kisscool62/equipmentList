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
 * Time: 12:14 PM
 */
@Entity
public class TypeOfEquipment extends Model {

    @Id
    public Long id;

    @Column(unique = true)
    public String nameId;

    @Constraints.Required
    @Column(unique = true)
    public String name;

    public static Finder<String, TypeOfEquipment> find = new Finder<String, TypeOfEquipment>(String.class, TypeOfEquipment.class);

    public TypeOfEquipment(String name) {
        this.nameId = makeId(name);
        this.name = name.trim();
    }


    @Override
    public String toString() {
        return "TypeOfEquipment{" +
                "nameId='" + nameId + '\'' + ", "+
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
         //we can't find or create a typeOfEquipment with id null
        if(typeName == null && "".equals(typeName)){
            return null;
        }
        TypeOfEquipment typeOfEquipment = findByNameId(makeId(typeName));
        Logger.debug("Found TypeOfEquipment for name " + typeName + ": " + typeOfEquipment);
        if(typeOfEquipment == null){
            typeOfEquipment = create(typeName);
        }
        Logger.debug("Found TypeOfEquipment for name " + typeName + ": " + typeOfEquipment);
        return typeOfEquipment;
    }

    public static TypeOfEquipment create(String typeName) {
        TypeOfEquipment type = new TypeOfEquipment(typeName);
        type.save();
        return type;
    }

    private static String makeId(String name){
        Preconditions.checkArgument(name != null && !"".equals(name.trim()), "Name of Category mustn't be null");
        return name.trim().toUpperCase();
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for(TypeOfEquipment c: TypeOfEquipment.find.orderBy("nameId").findList()) {
            options.put(c.name, c.name);
        }
        return options;
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

       private static TypeOfEquipment findByNameId(String nameId){
           return createExpressionFindByNameId(nameId).findUnique();
       }

       private static ExpressionList<TypeOfEquipment> createExpressionFindByNameId(String nameId) {
           return find.where().ilike("nameId", "%" + nameId + "%");
       }

}
