package controllers;

import models.Category;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.category;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:34 AM
 */
public class CategoryController extends Controller {

    /**
     * Display the paginated list of categories.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on category names
     */
    public static Result categories(int page, String sortBy, String order, String filter) {
        return ok(
            category.render(
                    Category.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
    }

    public static Result newCategory() {
        return Results.TODO;
    }

    public static Result editCategory(String id) {
        return Results.TODO;
    }

    public static Result deleteCategory(String id) {
        return Results.TODO;
    }


}
