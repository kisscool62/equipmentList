package helpers.common;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * User: Pascal AUREGAN
 * Date: 10/13/13
 * Time: 3:14 PM
 */
public class ModelUtilTest {

    @Test
    public void shouldTrimAdditionalSpaces(){
        //given
        String candidate = "   TOTO     ";

        //when
        String actual = ModelUtil.makeId(candidate);
        String expected = "TOTO";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldUpperCase(){
        //given
        String candidate = "toTo";

        //when
        String actual = ModelUtil.makeId(candidate);
        String expected = "TOTO";

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReplaceOnce(){
        //given
        String candidate = "TOTO   TITI";

        //when
        String actual = ModelUtil.makeId(candidate);
        String expected = "TOTO_TITI";

        //then
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptNullCandidate(){
        //given
        String candidate = null;

        //when
        String actual = ModelUtil.makeId(candidate);

        //throw NullPointerException
        fail("Should have thrown an IllegalArgumentException");
    }

    @Test
    public void shouldReplaceNotAllowedCharacters(){
        //given
        String candidate = "$$$   ùùù¨ ^TOTO TITI   TUTU  123 _  %%°+)+=}{";

        //when
        String actual = ModelUtil.makeId(candidate);
        String expected = "TOTO_TITI_TUTU_123";

        //then
        assertEquals(expected, actual);
    }
}
