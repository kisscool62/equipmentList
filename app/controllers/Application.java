package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

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

    public static Result equipments() {
        return Results.TODO;
    }

    public static Result newEquipment() {
        return Results.TODO;
    }

    public static Result deleteEquipment(long id) {
        return Results.TODO;
    }

}
