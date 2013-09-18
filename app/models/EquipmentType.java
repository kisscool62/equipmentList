package models;

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
public class EquipmentType extends Model {

    @Id
    public String name;

    public static Finder<String,EquipmentType> find = new Finder<String,EquipmentType>(String.class, EquipmentType.class);

    public EquipmentType() {
    }

    public EquipmentType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EquipmentType{" +
                "name='" + name + '\'' +
                '}';
    }

    public static EquipmentType findOrCreate(String typeName) {
        //we can't find or create a EquipmentType with id null
        if(typeName == null){
            return null;
        }
        EquipmentType type = find.byId(typeName);
        Logger.debug("Found EquipmentType for name " + typeName + ": " + type);
        if(type == null){
            type = create(typeName);
        }
        Logger.debug("Found EquipmentType for name " + typeName + ": " + type);
        return type;
    }

    public static EquipmentType create(String typeName) {
        EquipmentType type = new EquipmentType(typeName);
        type.save();
        return type;
    }
}
