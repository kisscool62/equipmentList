package models;


import scala.collection.immutable.List;
import scala.collection.JavaConverters;

import static com.google.common.collect.Lists.newArrayList;

/**
 * User: Pascal AUREGAN
 * Date: 2/10/14
 * Time: 7:42 PM
 */
public class SelectableProduct extends Product {
    public Boolean selected;

    public SelectableProduct(Product product) {
        super(product.typeOfEquipment, product.category, product.brand, product.name, product.description, product.quantity, product.owner, product.room, product.location, product.state, product.dateOfPurchase, product.euroPrice, product.usdPrice);
        this.id = product.id;
        selected = false;
    }

    public static List<SelectableProduct> toSelectableProducts(java.util.List<Product> products) {
        java.util.List<SelectableProduct> result = newArrayList();
        for (Product product : products) {
            result.add(new SelectableProduct(product));
        }
        return JavaConverters.asScalaIterableConverter(result).asScala().toList();
    }

    public static java.util.List<SelectableProduct> toSelectableProductsList(java.util.List<Product> products) {
        java.util.List<SelectableProduct> result = newArrayList();
        for (Product product : products) {
            result.add(new SelectableProduct(product));
        }
        return result;
    }
}
