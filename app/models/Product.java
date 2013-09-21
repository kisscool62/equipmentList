package models;

import com.avaje.ebean.Page;
import com.avaje.ebean.validation.NotNull;
import com.google.common.collect.Maps;
import play.Logger;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 12:15 PM
 */
@Entity
public class Product extends Model {
    @Id
    public Long id;

    @Constraints.Required
    @ManyToOne
    public EquipmentType equipmentType;

    @Constraints.Required
    @ManyToOne
    public Category category;

    @ManyToOne
    public Brand brand;

    @NotNull
    public String name;

    public String description;

    public Integer quantity;

    public Integer remaining_quantity;
    public String owner;
    public String room;
    public String location;
    public String state;
    public Date dateOfPurchase;
    public Double euroPrice;
    public Double usdPrice;

    public static class FieldProperties {
        private int index;
        private ProductFields productFields;

        private FieldProperties(int index, ProductFields productFields) {
            this.index = index;
            this.productFields = productFields;

        }

        public int getIndex() {
            return index;
        }

        public ProductFields getProductFields() {
            return productFields;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FieldProperties)) return false;

            FieldProperties that = (FieldProperties) o;

            if (index != that.index) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return index;
        }

    }

    public static FieldMap<String> fieldPropertiesMap = createProductPropertyMap();

    private static FieldMap<String> createProductPropertyMap() {
        FieldMap<String> result = new FieldMap<>();
        result.put("Type", new FieldProperties(ProductFields.PRODUCT_TYPE.getParsedLocation(), ProductFields.PRODUCT_TYPE));
        result.put("Catégorie", new FieldProperties(ProductFields.CATEGORY.getParsedLocation(), ProductFields.CATEGORY));
        result.put("Marque", new FieldProperties(ProductFields.BRAND.getParsedLocation(), ProductFields.BRAND));
        result.put("Produit", new FieldProperties(ProductFields.NAME.getParsedLocation(), ProductFields.NAME));
        result.put("Description", new FieldProperties(ProductFields.DESCRIPTION.getParsedLocation(), ProductFields.DESCRIPTION));
        result.put("Quant.", new FieldProperties(ProductFields.QUANTITY.getParsedLocation(), ProductFields.QUANTITY));
        result.put("Proprio", new FieldProperties(ProductFields.OWNER.getParsedLocation(), ProductFields.OWNER));
        result.put("Salle", new FieldProperties(ProductFields.ROOM.getParsedLocation(), ProductFields.ROOM));
        result.put("Emplacement", new FieldProperties(ProductFields.LOCATION.getParsedLocation(), ProductFields.LOCATION));
        result.put("Etat", new FieldProperties(ProductFields.STATE.getParsedLocation(), ProductFields.STATE));
        result.put("Date Achat", new FieldProperties(ProductFields.DATE_OF_PURCHASE.getParsedLocation(), ProductFields.DATE_OF_PURCHASE));
        result.put("Prix Achat Unit.", new FieldProperties(ProductFields.EURO_PRICE.getParsedLocation(), ProductFields.EURO_PRICE));
        result.put("USD", new FieldProperties(ProductFields.USD_PRICE.getParsedLocation(), ProductFields.USD_PRICE));
        return result;
    }

    public static Finder<String, Product> find = new Finder<String, Product>(String.class, Product.class);

    public Product() {
    }

    public Product(EquipmentType type, Category category, Brand brand, String name, String description, Integer quantity, String owner, String room, String location, String state, Date dateOfPurchase, Double euroPrice, Double usdPrice) {
        this.equipmentType = type;
        this.category = category;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.remaining_quantity = quantity;
        this.owner = owner;
        this.room = room;
        this.location = location;
        this.state = state;
        this.dateOfPurchase = dateOfPurchase;
        this.euroPrice = euroPrice;
        this.usdPrice = usdPrice;
    }

    public static void create(List<Product> products) {
        Logger.info("Begin saving " + products.size() + " products");
        for (Product product : products) {
            Logger.info("Try to create product : " + product);
            Product.create(
                    product.name,
                    product.equipmentType == null ? null : product.equipmentType.name,
                    product.category == null ? null : product.category.name,
                    product.brand == null ? null : product.brand.name,
                    product.description,
                    product.quantity,
                    product.owner,
                    product.room,
                    product.location,
                    product.state,
                    product.dateOfPurchase,
                    product.euroPrice,
                    product.usdPrice);
        }
        Logger.info("Product saved");
    }

    /**
     * Return a page of products
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Product property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Product> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
            find.where()
                .ilike("name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("category")
                .fetch("brand")
                .fetch("equipmentType")
                .findPagingList(pageSize)
                .getPage(page);
    }

    public static Product create(String name, String typeName, String categoryName, String brandName, String description, Integer quantity, String owner, String room, String location, String state, Date dateOfPurchase, Double euroPrice, Double usdPrice) {
        Category category = Category.findOrCreate(categoryName);
        EquipmentType type = EquipmentType.findOrCreate(typeName);
        Brand brand = Brand.findOrCreate(brandName);
        Product product = new Product(type, category, brand, name, description, quantity, owner, room, location, state, dateOfPurchase, euroPrice, usdPrice);
        Logger.info("Product: " + product);
        product.save();
        Logger.info("Product saved");
        return product;
    }

    public static class FieldMap<T> extends HashMap<T, FieldProperties> {
        private Map<Integer, FieldProperties> indexMap = Maps.newHashMap();

        @Override
        public FieldProperties put(T key, FieldProperties value) {
            indexMap.put(value.getIndex(), value);
            return super.put(key, value);
        }

        @Override
        public void putAll(Map<? extends T, ? extends FieldProperties> m) {
            for (FieldProperties fieldProperties : m.values()) {
                indexMap.put(fieldProperties.getIndex(), fieldProperties);
            }
            super.putAll(m);
        }

        @Override
        public FieldProperties remove(Object key) {
            indexMap.remove(get(key));
            return super.remove(key);
        }

        @Override
        public void clear() {
            indexMap.clear();
            super.clear();
        }

        public FieldProperties getAt(int i) {
            return indexMap.get(i);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + equipmentType +
                ", category=" + category +
                ", brand=" + brand +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", remaining_quantity=" + remaining_quantity +
                ", owner='" + owner + '\'' +
                ", room='" + room + '\'' +
                ", location='" + location + '\'' +
                ", state='" + state + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", euroPrice=" + euroPrice +
                ", usdPrice=" + usdPrice +
                '}';
    }
}
