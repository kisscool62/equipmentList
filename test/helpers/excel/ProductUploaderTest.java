package helpers.excel;

import models.Product;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 7:06 PM
 */
public class ProductUploaderTest {

    @Test
    public void shouldUploadXLSXFiles(){
        //given
        ProductUploader productUploaderloader = new ProductUploader();
        File testedFile = new File("resources/inventaire.xlsx");

        assertTrue(testedFile.exists());

        //when
        final List<Product> products = productUploaderloader.treat(testedFile);

        //then
        assertNotNull(products);
        assertTrue("Upload should not return empty list", 0 != products.size());
    }

    @Test
    public void shouldUpload10Products(){
        //given
        ProductUploader productUploaderloader = new ProductUploader();
        File testedFile = new File("resources/inventaire.xlsx");

        assertTrue(testedFile.exists());

        //when
        final List<Product> products = productUploaderloader.treat(testedFile);

        //then
        assertNotNull(products);
        for (Product product : products) {
            System.out.print(product);
        }
        assertTrue("Upload should not return empty list", 0 != products.size());
    }

    @Test
    public void shouldUploadXLSFiles(){
        //given
        ProductUploader productUploaderloader = new ProductUploader();
        File testedFile = new File("resources/inventaire.xls");

        assertTrue(testedFile.exists());

        //when
        final List<Product> products = productUploaderloader.treat(testedFile);

        //then
        assertNotNull(products);
        assertTrue("Upload should not return empty list", 0 != products.size());
    }



}
