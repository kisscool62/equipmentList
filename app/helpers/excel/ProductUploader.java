package helpers.excel;

import com.google.common.collect.Lists;
import models.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * User: Pascal AUREGAN
 * Date: 8/31/13
 * Time: 6:29 PM
 */
public class ProductUploader {

    public class UploadException extends RuntimeException{
        public UploadException(String message, Throwable cause) {
            super(message, cause);
        }

        public UploadException(String message) {
            super(message);
        }
    }


    public List<Product> treat(File file){
        try {
            Workbook wb = WorkbookFactory.create(file);
            if(wb.getNumberOfSheets()!=0){
                return extractProducts(wb.getSheetAt(0));
            }else{
                throw new UploadException("Workbook should have at least 1 sheet");
            }
        } catch (IOException e) {
            throw new UploadException("IO Exception during xls parsing", e);
        } catch (InvalidFormatException e) {
            throw new UploadException("Invalid format exception", e);
        }
    }

    protected List<Product> extractProducts(Sheet sheet) {
        if(sheet== null){
            throw new UploadException("Sheet should not be null!");
        }
        List<Product> products = Lists.newArrayList();
        if(sheet.getLastRowNum() == 0 ){
            throw new UploadException("Excel File shouldn't have empty first sheet");
        }
        checkHeader(sheet.getRow(0));
        for(int i=1; i<sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            Product product = extractProduct(row);
            if(product != null && product.name != null){
                products.add(product);
            }
        }
        return products;
    }

    private void checkHeader(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            String headerName = extractCellAString(cell);
            if(headerName != null){
                headerName = headerName.trim();
                if (!"".equals(headerName)){//if headerName is not an empty cell
                    final Product.FieldProperties fieldProperties = Product.fieldPropertiesMap.get(headerName);
                    if (fieldProperties == null){
                        throw new UploadException(headerName + " is not part of " + Product.fieldPropertiesMap.keySet());
                    }
                    final int index = fieldProperties.getIndex();
                    if (i != index){
                        throw new UploadException(headerName + " has not right index; should be " + index + "; now it's " + i);
                    }
                }//else noop
            }
        }
    }

    private String extractCellAString(Cell cell) {
        return cell.getStringCellValue();
    }

    private Double extractCellAsDouble(Cell cell){
        return cell.getNumericCellValue();
    }

    private Integer extractCellAsInteger(Cell cell){
        return ((Double)cell.getNumericCellValue()).intValue();
    }

    private Date extractCellAsDate(Cell cell){
        return cell.getDateCellValue();
    }

    private <T> T extractCell(Cell cell, Class t){
        if(cell == null){
            return null;
        }
        if(Double.class.equals(t)){
            return (T) extractCellAsDouble(cell);
        }
        if(Integer.class.equals(t)){
            return (T) extractCellAsInteger(cell);
        }
        if(Date.class.equals(t)){
            return (T) extractCellAsDate(cell);
        }
        if(String.class.equals(t)){
            return (T) extractCellAString(cell);
        }
        throw new IllegalArgumentException("Unexpected class: " + t);
    }

    private <T> T extractProductField(Cell cell, Class t){
        if(cell == null) {
            return null;
        }
        if(!isSimpleClassField(t)){
            if(Brand.class.equals(t)){
                return (T) new Brand((String)extractCell(cell, String.class));
            }
            if(Category.class.equals(t)){
                return (T) new Category((String)extractCell(cell, String.class));
            }
            if(EquipmentType.class.equals(t)){
                return (T) new EquipmentType((String)extractCell(cell, String.class));
            }
        }
        return extractCell(cell, t);
    }

    private boolean isSimpleClassField(Class candidate) {
        List<Class> simpleClasses = Lists.newArrayList();
        simpleClasses.add(Double.class);
        simpleClasses.add(String.class);
        simpleClasses.add(Date.class);
        simpleClasses.add(Integer.class);

        boolean result = false;
        for (Class simpleClass : simpleClasses) {
            result = result || simpleClass.equals(candidate);
        }
        return result;
    }

    protected Product extractProduct(Row row) {
        Product product = null;
        if (row != null){
            product = new Product();
            List<Object> cellExtraction = Lists.newArrayList();
            for (int i = 0; i < row.getLastCellNum(); i++) {
                final Product.FieldProperties fieldProperties = Product.fieldPropertiesMap.getAt(i);
                cellExtraction.add(extractProductField(row.getCell(i), fieldProperties.getProductFields().getParsedClass()));
                final Method writeMethod = fieldProperties.getProductFields().getWriteMethod();
                final Class<?> paramClass = writeMethod.getParameterTypes()[0];
                try {
                    final Object readValue = extractProductField(row.getCell(i), paramClass);
                    if(readValue != null){
                        writeMethod.invoke(product, paramClass.cast(readValue));
                    }
                } catch (IllegalAccessException e) {
                    throw new UploadException("Couldn't write value in new product", e);
                } catch (InvocationTargetException e) {
                    throw new UploadException("Couldn't write value in new product", e);
                }
            }
        }
        return product;
    }

}
