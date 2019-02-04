package bdquest.services;

import bdquest.DbRepository;
import bdquest.models.AlcoholType;
import bdquest.models.Drink;
import bdquest.models.Person;
import bdquest.models.SoftDrinkType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PartyManagementService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PartyManagementService.class);

    @Autowired
    private DbRepository dbRepository;

    @Autowired
    private DrinkService drinkService;

    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        List<Person> personList = dbRepository.getPerson(person.getName());
        if (!personList.isEmpty()) {
            Person concretePerson = personList.get(0);
            log.info("{} подключился!", concretePerson.getName());
            model.addAttribute("person", concretePerson);

            if (concretePerson.getDrinkId() != 0) {
                List<Drink> drinkById = dbRepository.findDrinkById(concretePerson.getDrinkId());
                if (!drinkById.isEmpty()) {
                    model.addAttribute("drink", drinkById.get(0));
                    return "find_drink";
                }
            }

            return "main";
        }
        log.info("{} не приглашен, но пытался подключиться!", person.getName());
        return "not_invited";
    }

    public String alcoTest(@ModelAttribute Person person, Model model) {

        if (person.getSoftDrinkType() != null) {
            log.info("{} выбрал {} в качестве запивона.", person.getName(), person.getSoftDrinkType());
            Drink drinkByParams = drinkService.findDrinkByParams(person);

            if (drinkByParams == null) {
                model.addAttribute("try_again", getTryAgainMessage(person));
                return "alco_test";
            }
            dbRepository.savePerson(person);
            return goToFindDrink(person, model, drinkByParams);
        }

        if (person.getAlcoholType() != null) {
            log.info("{} выбрал {}.", person.getName(), person.getAlcoholType());
            dbRepository.savePerson(person);
            model.addAttribute("softs", SoftDrinkType.values());
            model.addAttribute("person", person);
            return "zapivon";
        }

        model.addAttribute("alcos", AlcoholType.values());
        model.addAttribute("person", person);
        return "alco_test";
    }

    private String goToFindDrink(@ModelAttribute Person person, Model model, Drink drinkByParams) {
        log.info("{} достался напиток {}.", person.getName(), drinkByParams.getName());
        person.setDrinkId(drinkByParams.getId());
        model.addAttribute("person", person);
        model.addAttribute("drink", drinkByParams);
        return "find_drink";
    }

    public String getDrinkLocations(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        List<String> drinkLocations = dbRepository.findDrinkLocations(person.getDrinkId()).stream()
                .filter(img -> img != null && !img.isEmpty())
                .collect(Collectors.toList());
        model.addAttribute("locations",
                drinkLocations.isEmpty() ? Collections.singletonList("dummy_map") : drinkLocations);
        return "drink_locations";
    }

    public String getRandomDrink(@ModelAttribute Person person, Model model) {
        log.info("{} выбрал рандомный напиток!.", person.getName());
        Drink randomDrink = drinkService.getRandomAvailableDrink(person);
        return goToFindDrink(person, model, randomDrink);
    }

    private String getTryAgainMessage(@ModelAttribute Person person) {
        return "Все напитки c основой \"" + person.getAlcoholType().getName() + "\"" +
                " разобрали, попробуй выбрать что-то другое(((";
    }
}
