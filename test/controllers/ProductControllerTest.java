package controllers;

import com.google.common.collect.Maps;
import models.Product;
import org.junit.Test;
import play.Logger;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.thoughtworks.selenium.SeleneseTestBase.assertEquals;
import static com.thoughtworks.selenium.SeleneseTestBase.assertFalse;
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

    /**
     * Unit test
     */
    @Test
    public void shouldExtractProductId(){
        //given
        final Map<String, String> productIds = Maps.newHashMap();
        productIds.put("product-01", "");
        productIds.put("product-2", "");
        productIds.put("product-123", "");

        //when
        final List<Long> ids = ProductController.extractProductId(productIds);
        //then
        assertFalse(ids.isEmpty());
        assertEquals(3, ids.size());
        ids.contains(1L);
        ids.contains(2L);
        ids.contains(123L);

    }
}
