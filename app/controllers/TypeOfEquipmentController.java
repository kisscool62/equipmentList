package controllers;

import helpers.common.Messages;
import models.TypeOfEquipment;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.typeofequipment.createForm;
import views.html.typeofequipment.typeOfEquipment;

import static play.data.Form.form;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:39 AM
 */
public class TypeOfEquipmentController extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.TypeOfEquipmentController.typesOfEquipment(0, "nameId", "asc", ""));

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

    /**
     * Display the 'new computer form'.
     */
    public static Result create() {
        Form<TypeOfEquipment> typeOfEquipmentForm= form(TypeOfEquipment.class);
        return ok(
            createForm.render(typeOfEquipmentForm)
        );
    }

    /**
     * Handle the 'new typeOfEquipment form' submission
     */
    public static Result save() {
        Form<TypeOfEquipment> typeOfEquipmentForm = form(TypeOfEquipment.class).bindFromRequest();
        if(typeOfEquipmentForm.hasErrors()) {
            return badRequest(createForm.render(typeOfEquipmentForm));
        }
        final TypeOfEquipment typeOfEquipmentCandidate = typeOfEquipmentForm.get();
        final boolean wasSaved = typeOfEquipmentCandidate.saveOrReturnFalseIfExists();
        if(wasSaved){
            flash("success", Messages.get("typeOfEquipment.typeOfEquipmentCreated", typeOfEquipmentCandidate.name));
        }else{
            flash("error", Messages.get("typeOfEquipment.typeOfEquipmentNotCreated", typeOfEquipmentCandidate.name));
        }
        return GO_HOME;
    }



    public static Result editTypeOfEquipment(String id) {
        return Results.TODO;
    }

    public static Result deleteTypeOfEquipment(String id) {
        return Results.TODO;
    }
}
