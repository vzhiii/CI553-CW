package catalogue;

import java.io.Serializable;
import java.util.Collections;
import java.util.Currency;
import java.util.Formatter;
import java.util.Locale;

/**
 * Write a description of class BetterBasket here.
 *
 * @author  Your Name
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable {
  private static final long serialVersionUID = 1L;


  private int theOrderNum = 0; // Order number

  /**
   * Constructor for a basket which is
   *  used to represent a customer order/ wish list
   */
  public BetterBasket() {
    theOrderNum = 0;
  }

  /**
   * Set the customers unique order number
   * Valid order Numbers 1 .. N
   * @param anOrderNum A unique order number
   */
  public void setOrderNum(int anOrderNum) {
    theOrderNum = anOrderNum;
  }

  /**
   * Returns the customers unique order number
   * @return the customers order number
   */
  public int getOrderNum() {
    return theOrderNum;
  }

  /**
   * Add a product to the Basket.
   * If a product with the same product number exists,
   * increment the quantity. Otherwise, add the new product to the basket.
   *
   * @param p1 A product to be added to the basket
   * @return true if successfully adds the product
   */
  @Override
  public boolean add(Product p1) {
    for (Product p2 : this) {
      if (p1.getProductNum().equals(p2.getProductNum())) {
        p2.setQuantity(p2.getQuantity() + p1.getQuantity());
        return true;
      }
    }
    super.add(p1);
    return true;
  }

  /**
   * Returns a description of the products in the basket suitable for printing.
   * @return a string description of the basket products
   */
  public String getDetails() {
    Locale uk = Locale.UK;
    StringBuilder sb = new StringBuilder(256);
    Formatter fr = new Formatter(sb, uk);
    String csign = (Currency.getInstance(uk)).getSymbol();
    double total = 0.00;
    if (theOrderNum != 0)
      fr.format("Order number: %03d\n", theOrderNum);

    if (this.size() > 0) {
      for (Product pr : this) {
        int number = pr.getQuantity();
        fr.format("%-7s", pr.getProductNum());
        fr.format("%-14.14s ", pr.getDescription());
        fr.format("(%3d) ", number);
        fr.format("%s%7.2f", csign, pr.getPrice() * number);
        fr.format("\n");
        total += pr.getPrice() * number;
      }
      fr.format("----------------------------\n");
      fr.format("Total                       ");
      fr.format("%s%7.2f\n", csign, total);
      fr.close();
    }
    return sb.toString();
  }
}


  // You need to add code here