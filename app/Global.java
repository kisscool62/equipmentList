import com.avaje.ebean.Ebean;
import models.Event;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 3:14 PM
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(play.Application app){
        //check if the database is empty
        if(Event.find.findRowCount() == 0){
            Ebean.save((List) Yaml.load("test-data.yml"));
        }
    }

}
