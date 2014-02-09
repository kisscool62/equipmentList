package controllers;

import models.Event;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import util.pdf.PDF;
import views.html.event.createForm;
import views.html.event.event;
import views.html.event.eventDetails;
import views.html.event.eventDetailsForPDF;

import java.util.List;

import static play.data.Form.form;

/**
 * User: Pascal AUREGAN
 * Date: 9/22/13
 * Time: 10:34 AM
 */
public class EventController extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(routes.EventController.events(0, "nameId", "asc", ""));

    /**
     * Display the paginated list of events.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on event names
     */
    public static Result events(int page, String sortBy, String order, String filter) {
        return ok(
            event.render(
                    Event.page(page, 20, sortBy, order, filter),
                    sortBy, order, filter
            )
        );
    }

    /**
     * Display the 'new event form'.
     */
    public static Result create() {
        Form<Event> eventForm= form(Event.class);
        return ok(createForm.render(eventForm));
    }

    /**
     * Handle the 'new event form' submission
     */
    public static Result save() {
        Form<Event> eventForm = form(Event.class).bindFromRequest();
        if(eventForm.hasErrors()) {
            return badRequest(createForm.render(eventForm));
        }
        final Event eventCandidate = eventForm.get();
        final boolean wasSaved = true;
        eventCandidate.save();
        if(wasSaved){
            flash("success", "Event " + eventCandidate.name + " has been created");
        }else{
            flash("error", "Event " + eventCandidate.name + " was not created because exists");
        }
        return GO_HOME;
    }

    /**
     * Handle the 'new event form' submission
     */
    public static Result saveOrSaveAndLinkProducts() {
        Form<Event> eventForm = form(Event.class).bindFromRequest();
        if(eventForm.hasErrors()) {
            return badRequest(createForm.render(eventForm));
        }
        final Event eventCandidate = eventForm.get();
        final boolean wasSaved = true;
        eventCandidate.save();
        if(wasSaved){
            flash("success", "Event " + eventCandidate.name + " has been created");
        }else{
            flash("error", "Event " + eventCandidate.name + " was not created because exists");
        }
        if("save".equals(eventForm.data().get("saveOrSaveAndLink"))){
            List<Product> products = findFreeProducts(eventCandidate);
            return ok(views.html.event.editProductList.render(eventCandidate, products));
        }
        return GO_HOME;
    }

    private static List<Product> findFreeProducts(Event eventCandidate) {
        return Product.find.all();
    }

    public static Result edit(Long id) {
        return Results.TODO;
    }

    public static Result delete(Long id) {
        return Results.TODO;
    }

    public static Result view(String nameId){
        Event event = Event.findByNameId(nameId);
        return ok(eventDetails.render(event));
    }

    public static Result viewPdfHtml(String nameId){
        Event event = Event.findByNameId(nameId);
        return ok(views.html.event.eventDetailsForPDFHTML.render(event));
    }


    public static Result viewPdf(String nameId) {
        Event event = Event.findByNameId(nameId);
        return PDF.ok(eventDetailsForPDF.render(event));
    }
}
