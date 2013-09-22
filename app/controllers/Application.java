package controllers;

import models.Category;
import models.TypeOfEquipment;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.category;
import views.html.typeOfEquipment;

public class Application extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.ProductController.products(0, "name", "asc", ""));

    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }

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
     * Display the paginated list of type of equipments.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on type names
     */
    public static Result typesOfEquipment(int page, String sortBy, String order, String filter) {
        return ok(
            typeOfEquipment.render(
                    TypeOfEquipment.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
    }

    public static Result equipments() {
        return Results.TODO;
    }

    public static Result newEquipment() {
        return Results.TODO;
    }

    public static Result deleteEquipment(long id) {
        return Results.TODO;
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

    public static Result newTypeOfEquipment() {
        return Results.TODO;
    }

    public static Result editTypeOfEquipment(String id) {
        return Results.TODO;
    }

    public static Result deleteTypeOfEquipment(String id) {
        return Results.TODO;
    }
}
