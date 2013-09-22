package controllers;

import helpers.excel.ProductUploader;
import models.Product;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import views.html.product;

import java.io.File;
import java.util.List;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:21 AM
 */
public class ProductController extends Controller {

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

    public static Result upload() {
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart inventory = body.getFile("inventory");
        if (inventory != null) {
            String fileName = inventory.getFilename();
            String contentType = inventory.getContentType();
            File file = inventory.getFile();
            if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
                ProductUploader productUploader = new ProductUploader();
                try{
                    Logger.info("Starting to upload inventory in Excel mode");
                    final List<Product> products = productUploader.treat(file);
                    Logger.info("Upload inventory in Excel mode done");
                    Logger.info("Saving uploaded products");
                    Product.create(products);
                    Logger.info("Uploaded products saved");
                }catch (Throwable e){
                    Logger.info("Could not upload the file: ", e);
                    flash("error", e.getMessage());
                    return redirect(routes.Application.index());
                }
                return redirect(routes.Application.index());
            } else {
                flash("error", "File should be [.xlsx|.xls] file");
                return redirect(routes.Application.index());
            }
        } else {
            flash("error", "Missing file");
            return redirect(routes.Application.index());
        }
    }

    public static Result newProduct() {
        return Results.TODO;
    }

    public static Result editProduct(Long id) {
        return Results.TODO;
    }


    public static Result deleteProduct(long id) {
        return Results.TODO;
    }

}
