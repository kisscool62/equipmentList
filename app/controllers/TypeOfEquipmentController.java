package controllers;

import models.TypeOfEquipment;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.typeOfEquipment;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:39 AM
 */
public class TypeOfEquipmentController extends Controller {
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
