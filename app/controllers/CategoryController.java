package controllers;

import models.Category;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.category.category;
import views.html.category.createForm;

import static play.data.Form.form;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:34 AM
 */
public class CategoryController extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.CategoryController.categories(0, "name", "asc", ""));

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

    /**
     * Display the 'new computer form'.
     */
    public static Result create() {
        Form<Category> categoryForm= form(Category.class);
        return ok(
            createForm.render(categoryForm)
        );
    }

    /**
     * Handle the 'new category form' submission
     */
    public static Result save() {
        Form<Category> categoryForm = form(Category.class).bindFromRequest();
        if(categoryForm.hasErrors()) {
            return badRequest(createForm.render(categoryForm));
        }
        categoryForm.get().save();
        flash("success", "Category " + categoryForm.get().name + " has been created");
        return GO_HOME;
    }

    public static Result editCategory(String id) {
        return Results.TODO;
    }

    public static Result deleteCategory(String id) {
        return Results.TODO;
    }


}
