package dao;

import java.util.ArrayList;
import java.util.List;
import models.Product;
import models.EletronicProduct;

public class ProductDAO {
  private List<Product> products = new ArrayList<Product>();
  private static int nextId = 1;

  public void add(Product product) {
    product.setId(nextId);
    products.add(product);
  }

  public void update(Product product) {
    for (int i = 0; i < products.size(); i++) {
      Product p = products.get(i);
      if (p.getId() == product.getId()) { 
        products.set(i, product);
        break;
      }
    }
  }

  public List<Product> getProducts() {
    return new ArrayList<Product>(products);
  }

  public Product getProductById(int id) {
    return products.stream()
    .filter(p -> p.getId() == id)
    .findFirst()
    .orElse(null);
  }

  public void removeProduct(Product product) {
    products.removeIf(p -> p.getId() == product.getId());
  }  
}
