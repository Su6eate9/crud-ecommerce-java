package dao;

import java.util.ArrayList;
import java.util.List;
import models.Product;

public class ProductDAO {
  private static final List<Product> products = new ArrayList<>();
  private static int nextId = 1;

  public void addProduct(Product product) {
    product.setId(nextId++);
    products.add(product);
  }

  public void updateProduct(Product product) {
    for (int i = 0; i < products.size(); i++) {
      if (products.get(i).getId() == product.getId()) {
        products.set(i, product);
        break;
      }
    }
  }

  public void deleteProduct(int id) {
    products.removeIf(product -> product.getId() == id);
  }

  public List<Product> getProducts() {
    return new ArrayList<>(products);
  }

  public Product getProductById(int id) {
    return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
  }
}