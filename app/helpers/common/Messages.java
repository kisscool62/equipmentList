package helpers.common;

/**
 * User: Pascal AUREGAN
 * Date: 3/15/14
 * Time: 10:45 PM
 */
public class Messages {
    public static String get(String key, Object... objects){
        return String.format(play.i18n.Messages.get(key), objects);
    }

}
