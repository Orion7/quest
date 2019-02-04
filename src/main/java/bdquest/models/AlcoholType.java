package bdquest.models;

import java.util.stream.Stream;

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

    public static boolean contains(String value) {
        return Stream.of(AlcoholType.values())
                .anyMatch(alcoholType -> alcoholType.getName().equals(value));
    }
}

