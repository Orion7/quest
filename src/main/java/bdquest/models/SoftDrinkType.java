package bdquest.models;

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
}
