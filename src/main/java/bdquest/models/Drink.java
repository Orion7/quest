package bdquest.models;

public class Drink {
    private Integer id;
    private String name;
    private AlcoholType alcoholType;
    private SoftDrinkType softDrinkType;
    private String location;
    private boolean isAvailable;

    public static Drink of(Integer id,
                           String name,
                           AlcoholType alcoholType,
                           SoftDrinkType softDrinkType,
                           String location,
                           Boolean isAvailable) {
        Drink drink = new Drink();
        drink.id = id;
        drink.name = name;
        drink.softDrinkType = softDrinkType;
        drink.alcoholType = alcoholType;
        drink.location = location;
        drink.isAvailable = isAvailable;
        return drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    public void setAlcoholType(AlcoholType alcoholType) {
        this.alcoholType = alcoholType;
    }

    public SoftDrinkType getSoftDrinkType() {
        return softDrinkType;
    }

    public void setSoftDrinkType(SoftDrinkType softDrinkType) {
        this.softDrinkType = softDrinkType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Drink{" +
            "id=" + id +
            ", name=" + name +
            ", alcoholType=" + alcoholType +
            ", softDrinkType=" + softDrinkType +
            ", location=" + location +
            ", isAvailable=" + isAvailable +
            "}";
    }
}
