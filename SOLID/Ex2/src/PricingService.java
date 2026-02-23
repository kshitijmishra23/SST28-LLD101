import java.util.*;

public class PricingService {

    public double calculateSubtotal(List<OrderLine> lines, Map<String, MenuItem> menu) {
        double subtotal = 0.0;

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        return subtotal;
    }
}