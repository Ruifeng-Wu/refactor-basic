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
        BigDecimal total;
        total = calculateTotal();
        total = total.subtract(calculateDiscount());
        total = total.add(total.multiply(tax));
        return total;
    }

    private BigDecimal calculateDiscount() {
        BigDecimal reducedPrice = new BigDecimal(0);
        for (BigDecimal discount : discounts) {
            reducedPrice = reducedPrice.add(discount);
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
