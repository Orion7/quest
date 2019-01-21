package bdquest.models;

public class Person {

    private Integer id;
    private Integer drinkId;
    private String name;
    private AlcoholType alcoholType;
    private SoftDrinkType softDrinkType;

    public static Person of(Integer id,
                            String name,
                            Integer drinkId,
                            AlcoholType alcoholType,
                            SoftDrinkType softDrinkType) {
        Person person = new Person();
        person.id = id;
        person.name = name;
        person.drinkId = drinkId;
        person.alcoholType = alcoholType;
        person.softDrinkType = softDrinkType;
        return person;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", drinkId=" + drinkId +
                ", name=" + name +
                ", alcoholType=" + alcoholType +
                ", softDrinkType=" + softDrinkType +
                "}";
    }
}
