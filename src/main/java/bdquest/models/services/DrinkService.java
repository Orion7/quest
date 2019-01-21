package bdquest.models.services;

import bdquest.DbRepository;
import bdquest.models.Drink;
import bdquest.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DrinkService {

    @Autowired
    private DbRepository dbRepository;

    public Drink findDrinkByParams(Person person) {
        List<Drink> allDrinksByAlcoType = dbRepository.findAvailableDrinksByParams(person.getAlcoholType());

        if(allDrinksByAlcoType.isEmpty()) {
            return null;
        }

        Optional<Drink> optionalDrink = allDrinksByAlcoType.stream()
                .filter(drink -> drink.getSoftDrinkType() == person.getSoftDrinkType())
                .findFirst();

        Drink calculatedDrink = optionalDrink.orElse(getRandomDrinkFromList(allDrinksByAlcoType));
        setDrinkToPerson(calculatedDrink.getId(), person.getName());
        return calculatedDrink;
    }

    private Drink getRandomDrinkFromList(List<Drink> allDrinksByAlcoType) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, allDrinksByAlcoType.size());
        return allDrinksByAlcoType.get(randomNum);
    }

    @Transactional
    void setDrinkToPerson(Integer drinkId, String name) {
        dbRepository.setDrinkToPerson(name, drinkId);
        dbRepository.setAvailability(drinkId);
    }
}
