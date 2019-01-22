package bdquest;

import bdquest.models.AlcoholType;
import bdquest.models.Drink;
import bdquest.models.Person;
import bdquest.models.SoftDrinkType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DbRepository.class);

    public void setDrinkToPerson(String name, Integer drinkId) {
        jdbcTemplate.execute("UPDATE persons SET drink_id=" + drinkId +
                " WHERE name='" + name + "'");
    }

    public void setAvailability(Integer drinkId) {
        jdbcTemplate.execute("UPDATE drinks SET is_available=FALSE" +
                " WHERE id=" + drinkId);
    }

    public boolean isInvited(String name) {
        return !jdbcTemplate.query("SELECT * FROM persons WHERE name ='" + name + "'",
                (rs, rowNum) -> Person.of(rs.getInt("id"),
                        rs.getString("name"),
                        null,
                        null,
                        null)).isEmpty();
    }

    public List<Drink> findAvailableDrinksByParams(AlcoholType alcoholType) {
        return jdbcTemplate.query("SELECT * FROM drinks WHERE alco_type='" + alcoholType.name() + "'" +
                        " AND is_available=TRUE",
                (rs, rowNum) -> Drink.of(rs.getInt("id"),
                        rs.getString("name"),
                        AlcoholType.valueOf(rs.getString("alco_type")),
                        SoftDrinkType.valueOf(rs.getString("soft_type")),
                        rs.getString("location"),
                        rs.getBoolean("is_available")));
    }

    public void initDb() {
        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE IF EXISTS persons");
        jdbcTemplate.execute("CREATE TABLE persons(" +
                "id SERIAL, name VARCHAR(255), alco_type VARCHAR(255), " +
                "soft_type VARCHAR (255), drink_id integer )");

        jdbcTemplate.execute("DROP TABLE IF EXISTS drinks");
        jdbcTemplate.execute("CREATE TABLE drinks(" +
                "id SERIAL, name VARCHAR(255), alco_type VARCHAR(255), " +
                "soft_type VARCHAR (255), location VARCHAR (255), is_available BOOLEAN DEFAULT TRUE)");

        jdbcTemplate.batchUpdate("INSERT INTO persons(name) VALUES (?)", getGuests());
        jdbcTemplate.batchUpdate("INSERT INTO drinks(name, alco_type, soft_type, location) VALUES (?, ?, ?, ?)",
                getDrinks());


        jdbcTemplate.query("SELECT * FROM persons ",
                (rs, rowNum) -> Person.of(rs.getInt("id"),
                        rs.getString("name"),
                        null,
                        null,
                        null)
        ).forEach(person -> log.info(person.toString()));

        jdbcTemplate.query("SELECT * FROM drinks ",
                (rs, rowNum) -> Drink.of(rs.getInt("id"),
                        rs.getString("name"),
                        AlcoholType.valueOf(rs.getString("alco_type")),
                        SoftDrinkType.valueOf(rs.getString("soft_type")),
                        rs.getString("location"),
                        rs.getBoolean("is_available"))
        ).forEach(drink -> log.info(drink.toString()));
    }

    private ArrayList<Object[]> getGuests() {
        ArrayList<Object[]> guests = new ArrayList<>();
        guests.add(new String[]{"Ринат"});
        guests.add(new String[]{"Евгений"});
        guests.add(new String[]{"Аня"});
        guests.add(new String[]{"Вика"});
        guests.add(new String[]{"Антон"});
        guests.add(new String[]{"Роман"});
        guests.add(new String[]{"Мартын"});
        guests.add(new String[]{"Полина"});
        guests.add(new String[]{"Ира"});
        guests.add(new String[]{"Слава"});
        guests.add(new String[]{"Даша"});
        guests.add(new String[]{"Сергей"});
        guests.add(new String[]{"Ира"});
        guests.add(new String[]{"Юра"});
        return guests;
    }

    private ArrayList<Object[]> getDrinks() {
        ArrayList<Object[]> guests = new ArrayList<>();
        guests.add(new String[]{"Джин Тоник Обыкновенный", "GIN", "TONIC", "Он лежит и ждет тебя в подвале"});
        guests.add(new String[]{"Классическая водка с соком", "VODKA", "JUICE", "Он лежит и ждет тебя в подвале"});
        guests.add(new String[]{"Водка с колой", "VODKA", "COKE", "Он лежит на улице"});

        return guests;
    }
}
