package models;
import models.Product;

public class EletronicProduct extends Product {
  private String brand;
  private String model;
  private int guaranteedMonths;

  public EletronicProduct(int id, String name, double price, int quantity, String brand, String model, int guaranteedMonths) {
    super(id, name, price, quantity);
    this.brand = brand;
    this.model = model;
    this.guaranteedMonths = guaranteedMonths;
  }

  // Getters e Setters
  public String getBrand() { return brand; }
  public void setBrand(String brand) { this.brand = brand; }
  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
  public int getGuaranteedMonths() { return guaranteedMonths; }
  public void setGuaranteedMonths(int guaranteedMonths) { this.guaranteedMonths = guaranteedMonths; }
}