package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    private final BigDecimal TAX;

    public Receipt() {
        TAX = new BigDecimal(0.1).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public double calculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);
        subTotal = subTotal.subtract(calculateReducedPrice(products, items));
        BigDecimal taxTotal = subTotal.multiply(TAX);
        BigDecimal grandTotal = subTotal.add(taxTotal);
        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private BigDecimal calculateReducedPrice(List<Product> products, List<OrderItem> items) {
        BigDecimal reducedPriceTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem curItem = findOrderItemByProduct(items, product);
            BigDecimal reducedPrice = product.getPrice()
                    .multiply(product.getDiscountRate())
                    .multiply(new BigDecimal(curItem.getCount()));
            reducedPriceTotal = reducedPriceTotal.add(reducedPrice);
        }
        return reducedPriceTotal;
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        return items.stream().filter(item -> item.getCode() == product.getCode()).findFirst().orElse(null);
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = new BigDecimal(0);
        for (Product product : products) {
            OrderItem item = findOrderItemByProduct(items, product);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(item.getCount()));
            subTotal = subTotal.add(itemTotal);
        }
        return subTotal;
    }
}
