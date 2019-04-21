package practice3;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate() {
        BigDecimal grandTotal;
        grandTotal = calculateTotal();
        grandTotal = grandTotal.subtract(calculateDiscount());
        grandTotal = grandTotal.add(grandTotal.multiply(tax));
        return grandTotal;
    }

    private BigDecimal calculateDiscount() {
        BigDecimal reducedPrice = new BigDecimal(0);
        for (BigDecimal discount : discounts) {
            reducedPrice = reducedPrice.subtract(discount);
        }
        return reducedPrice;
    }

    private BigDecimal calculateTotal() {
        BigDecimal subTotal = new BigDecimal(0);
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }
}
