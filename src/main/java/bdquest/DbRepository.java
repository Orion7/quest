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
}
