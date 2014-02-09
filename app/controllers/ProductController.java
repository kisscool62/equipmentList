package controllers;

import helpers.excel.ProductUploader;
import models.Product;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import views.html.product.createForm;
import views.html.product.product;
import views.html.product.quickProductList;

import java.io.File;
import java.util.List;

import static play.data.Form.form;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:21 AM
 */
public class ProductController extends Controller {

    /**
     * This result directly redirect to application home.
     */
//    public static Result GO_HOME = redirect(routes.ProductController.products(0, "nameId", "asc", ""));
    public static Result GO_HOME = redirect(routes.Application.index());

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

    public static Result productsQuick(int page, String sortBy, String order, String filter) {
        return ok(
            quickProductList.render(Product.find.all(),
                    Product.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
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

        flash("success", "Product " + productCandidate.name + " has been created");

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
