package net.codejava.ws;

import java.util.ArrayList;
import java.util.List;

public class ProductDOA {
	private static ProductDOA instance;
    private static List<Product> data = new ArrayList<>();
     
    static {
        data.add(new Product(1, "iPhone X", 999.99f));
        data.add(new Product(2, "XBOX 360", 329.50f));     
    }
     
    private ProductDOA() {
         
    }
     
    public static ProductDOA getInstance() {
        if (instance == null) {
            instance = new ProductDOA();
        }
         
        return instance;               
    }
     
    public List<Product> listAll() {
        return new ArrayList<Product>(data);
    }
     
    public int add(Product product) {
        int newId = data.size() + 1;
        product.setId(newId);
        data.add(product);
         
        return newId;
    }
     
    public Product get(int id) {
        Product productToFind = new Product(id);
        int index = data.indexOf(productToFind);
        if (index >= 0) {
            return data.get(index);
        }
        return null;
    }
     
    public boolean delete(int id) {
        Product productToFind = new Product(id);
        int index = data.indexOf(productToFind);
        if (index >= 0) {
            data.remove(index);
            return true;
        }
         
        return false;
    }
     
    public boolean update(Product product) {
        int index = data.indexOf(product);
        if (index >= 0) {
            data.set(index, product);
            return true;
        }
        return false;
    }
}
