package pl.sda.JobOfferApplication.user.model;

public enum Category {

    IT("IT"),
    FOOD("Food & Drinks"),
    OFFICE("Office"),
    COURIER("Courier"),
    SHOP_ASSISTANT("Shop Assistant");

    Category(String name) {
        this.name = name;
    }

    private final String name;

    public String getName() {
        return name;
    }
}
