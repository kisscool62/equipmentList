package models;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * User: Pascal AUREGAN
 * Date: 9/7/13
 * Time: 6:41 PM
 */
public enum ProductFields {
    PRODUCT_TYPE("Type", getWriteMethod("equipmentType"), String.class, 0),
    CATEGORY("Category", getWriteMethod("category"), String.class, 1),
    BRAND("Brand", getWriteMethod("brand"), String.class, 2),
    NAME("Name", getWriteMethod("name"), String.class, 3),
    DESCRIPTION("Description", getWriteMethod("description"), String.class, 4),
    QUANTITY("Quantity", getWriteMethod("quantity"), Integer.class, 5),
    OWNER("Owner", getWriteMethod("owner"), String.class, 6),
    ROOM("Room", getWriteMethod("room"), String.class, 7),
    LOCATION("Location", getWriteMethod("location"), String.class, 8),
    STATE("State", getWriteMethod("state"), String.class, 9),
    DATE_OF_PURCHASE("Date of purchase", getWriteMethod("dateOfPurchase"), Date.class, 10),
    EURO_PRICE("Price in EUR", getWriteMethod("euroPrice"), Double.class, 11),
    USD_PRICE("Price in USD", getWriteMethod("usdPrice"), Double.class, 12);


    private String displayName;
    private Method writeMethod;
    private Class parsedClass;
    private int parsedLocation;


    public String getDisplayName() {
        return displayName;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public int getParsedLocation() {
        return parsedLocation;
    }

    public Class getParsedClass() {
        return parsedClass;
    }

    private ProductFields(String name, Method method, Class parsedClass, int parsedLocation) {
        this.displayName = name;
        this.writeMethod = method;
        this.parsedClass = parsedClass;
        this.parsedLocation = parsedLocation;
    }

    private static Method getWriteMethod(String name) {
        try {
            return new PropertyDescriptor(name, Product.class).getWriteMethod();
        } catch (IntrospectionException e) {
            e.printStackTrace();  //should never happen
        }
        return null;
    }
}
