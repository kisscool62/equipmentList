package models;

import com.avaje.ebean.Page;
import com.google.common.base.Preconditions;
import helpers.common.ModelUtil;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 2:33 PM
 */
@Entity
public class Event extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public Date startDate;

    @Constraints.Required
    public Date endDate;

    @Constraints.Required
    public String name;

    public String nameId;

    public static Model.Finder<String,Event> find = new Model.Finder<String,Event>(String.class, Event.class);


    public Event(Date startDate, Date endDate, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.nameId = makeId(name);
    }

    private static String makeId(String name){
        Preconditions.checkArgument(name != null && !"".equals(name.trim()), "Name of the Event mustn't be null");
        return ModelUtil.makeId(name);
    }

    /**
     * Return a page of events
     *
     * @param page Page to display
     * @param pageSize Number of events per page
     * @param sortBy Event property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Event> page(int page, int pageSize, String sortBy, String order, String filter) {

        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Event findByNameId(String nameId){
        return find.where().eq("nameId", nameId).findUnique();
    }
}
