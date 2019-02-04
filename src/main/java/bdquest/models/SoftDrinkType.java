package bdquest.models;

import java.util.stream.Stream;

public enum SoftDrinkType {
    JUICE("Сок"),
    COKE("Кола"),
    SPRITE("Спрайт"),
    TONIC("Тоник"),
    SECRET("Секретный");

    private String name;

    SoftDrinkType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static boolean contains(String value) {
        return Stream.of(SoftDrinkType.values())
                .anyMatch(softDrinkType -> softDrinkType.getName().equals(value));
    }
}
