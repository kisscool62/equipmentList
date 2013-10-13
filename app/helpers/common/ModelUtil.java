package helpers.common;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: Pascal AUREGAN
 * Date: 10/13/13
 * Time: 3:11 PM
 */
public class ModelUtil {
    private static String SPACE_REGEX = "[\\s]+";
    private static Pattern PATTERN = Pattern.compile(SPACE_REGEX);

    private static String REJECTED_REGEX = "[^A-Z|0-9]+";
    private static Pattern REJECTED_PATTERN = Pattern.compile(REJECTED_REGEX);


    /**
     * replaces all characters != a-z A-Z 0-9 with '_'
     * @param candidate candidate to make Id Non null
     * @return an id without space, special character
     * @throws IllegalArgumentException when null candidate is given
     */
    public static String makeId(@Nonnull String candidate){
        Preconditions.checkArgument(candidate != null, "Candidate should not be null");

        candidate = candidate.toUpperCase();

        Matcher otherMatcher = REJECTED_PATTERN.matcher(candidate);
        candidate = otherMatcher.replaceAll(" ");

        candidate = candidate.trim();

        Matcher spaceMatcher =  PATTERN.matcher(candidate);
        candidate = spaceMatcher.replaceAll("_");

        return candidate;
    }
}
