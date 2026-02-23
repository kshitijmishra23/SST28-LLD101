import java.util.*;

public class AddOnPricingFactory {

    private static final Map<AddOn, Money> ADDON_PRICES = Map.of(
            AddOn.MESS, new Money(1000.00),
            AddOn.LAUNDRY, new Money(500.00),
            AddOn.GYM, new Money(300.00)
    );

    public static List<PricingComponent> forAddOns(List<AddOn> addOns) {
        List<PricingComponent> list = new ArrayList<>();

        if (addOns == null || addOns.isEmpty()) return list;

        AddOn last = addOns.get(addOns.size() - 1);
        Money m = ADDON_PRICES.getOrDefault(last, new Money(0.0));
        list.add(new AddOnPricing(m));

        return list;
    }
}