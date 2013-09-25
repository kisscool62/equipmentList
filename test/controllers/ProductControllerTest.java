package controllers;

import models.Product;
import org.junit.Test;
import play.Logger;

import java.io.File;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static play.test.Helpers.*;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 6:25 PM
 */
public class ProductControllerTest {

    /**
     * Integration Test
     */
    @Test
    public void shouldTreatAndSaveUploadedFile() {
        //given
        final String fileName = "inventaire.xlsx";
        final File testedFile = new File("resources/" + fileName);
        Logger.info("AVANT");
        //when
        running(fakeApplication(inMemoryDatabase()), new Runnable() {
            public void run() {
                ProductController.manageUploadedFile(testedFile, fileName);
                //then
                Logger.info("APRES");
                assertEquals(95, Product.find.all().size());
            }
        });
    }
}
