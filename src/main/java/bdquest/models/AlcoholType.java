package bdquest.models;

public enum AlcoholType {
    VODKA("Водка"),
    GIN("Джин"),
    WHISKEY("Виски"),
    RUM("Ром"),
    SECRET("Секрет");

    private String name;

    AlcoholType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
