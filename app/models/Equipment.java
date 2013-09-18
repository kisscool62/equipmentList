package models;

import play.db.ebean.Model;

import javax.persistence.Entity;

/**
 * User: Pascal AUREGAN
 * Date: ${DATE}
 * Time: ${TIME}
 */
@Entity
public class Equipment extends Model{
    public Product product;
    public boolean used;

    public static Finder<String,Equipment> find = new Finder<String,Equipment>(
        String.class, Equipment.class
    );
}
