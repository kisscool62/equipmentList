package controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import helpers.common.Messages;
import helpers.excel.ProductUploader;
import models.Product;
import models.SelectableProduct;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import util.pdf.PDF;
import views.html.product.createForm;
import views.html.product.product;
import views.html.product.productsDetailsForPDF;
import views.html.product.quickProductList;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static play.data.Form.form;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:21 AM
 */
public class ProductController extends Controller {
    private static final Pattern productPattern = Pattern.compile("product-([0-9]+)");

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.ProductController.products(0, "name", "asc", ""));

    /**
     * Display the paginated list of products.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on product names
     */
    public static Result products(int page, String sortBy, String order, String filter) {
        return ok(
            product.render(
                    Product.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
    }

    public static Result productsQuick() {
        final Form<Product> productForm = form(Product.class);
        final List<Product> products = Product.find.all();
        java.util.List<SelectableProduct> selectableProducts = SelectableProduct.toSelectableProductsList(products);
        return ok(quickProductList.render(productForm, selectableProducts));
    }

    public static Result printPdf(){
        Form<Product> productForm = form(Product.class).bindFromRequest();
        final Map<String,String> data = productForm.data();
        final Map<String, String> selectedProductsId = filterNotProductInput(data);
        final List<Long> productIdList = extractProductId(selectedProductsId);

        final List<Product> productList = Product.find.findByIds(productIdList);
        final List<List<Product>> partition = Lists.partition(productList, 30);
        return  PDF.ok(productsDetailsForPDF.render(partition));
    }

    protected static List<Long> extractProductId(Map<String, String> selectedProductsId) {
        List<Long> productIdList = Lists.newArrayList();
        for (String productId : selectedProductsId.keySet()) {

            final Matcher matcher = productPattern.matcher(productId);
            if(matcher.matches()){
                productIdList.add(Long.valueOf(matcher.group(1)));
            }
        }
        return productIdList;
    }

    private static Map<String, String> filterNotProductInput(Map<String, String> data) {
        return Maps.filterKeys(data, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String s) {
                final Matcher matcher = productPattern.matcher(s);
                return matcher.matches();
            }
        });
    }

    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart inventory = body.getFile("inventory");
        if (inventory != null) {
            String fileName = inventory.getFilename();
            String contentType = inventory.getContentType();
            File file = inventory.getFile();
            return manageUploadedFile(file, fileName);
        } else {
            flash("error", "Missing file");
            return GO_HOME;
        }
    }

    protected static Result manageUploadedFile(File file, String fileName) {
        if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            ProductUploader productUploader = new ProductUploader();
            try{
                Logger.info("Starting to upload inventory in Excel mode");
                final List<Product> products = productUploader.treat(file);
                Logger.info("Upload inventory in Excel mode done");
                Logger.info("Saving uploaded products");
                Product.create(products);
                Logger.info("Uploaded products saved");
                flash("success", "Uploaded succeeded with " + products.size() + " products saved");
                return GO_HOME;
            }catch (Throwable e){
                Logger.info("Could not upload the file: ", e);
                flash("error", e.getMessage());
                return GO_HOME;
            }
        } else {
            flash("error", "File should be [.xlsx|.xls] file");
            return GO_HOME;
        }
    }

    public static Result create() {
        Form<Product> productForm= form(Product.class);
        return ok(
            createForm.render(productForm)
        );
    }

    /**
     * Handle the 'new category form' submission
     */
    public static Result save() {
        Form<Product> productForm = form(Product.class).bindFromRequest();
        if(productForm.hasErrors()) {
            return badRequest(views.html.product.createForm.render(productForm));
        }
        final Product productCandidate = productForm.get();

        productCandidate.save();
        flash("success", Messages.get("product.productCreated", productCandidate.name));


        return GO_HOME;
    }

    public static Result edit(Long id) {
        return Results.TODO;
    }


    public static Result delete(long id) {
        final Product product = Product.find.byId(id);
        if(product != null){
            product.delete();
            flash("success", "The product " + product.name + " was correctly deleted");
        }else{
            flash("error", "The product couldn't be deleted");
        }
        return GO_HOME;
    }

    public static Result deleteAll() {
        int nbProductDeleted = Product.deleteAll();
        flash("success", "" + nbProductDeleted + (nbProductDeleted == 1 || nbProductDeleted == 0? "was":"were") + " correctly deleted");
        return GO_HOME;
    }
}
