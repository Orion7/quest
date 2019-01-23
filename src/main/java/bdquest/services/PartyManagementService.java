package bdquest.services;

import bdquest.DbRepository;
import bdquest.models.Drink;
import bdquest.models.Person;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Component
public class PartyManagementService {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PartyManagementService.class);

    @Autowired
    private DbRepository dbRepository;

    @Autowired
    private DrinkService drinkService;

    private boolean isInvited(String name) {
        return dbRepository.isInvited(name);
    }

    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        if (isInvited(person.getName())) {
            log.info("{} подключился!", person.getName());
            model.addAttribute("person", person);
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
                model.addAttribute("try_again",
                    "Все напитки c основой \"" + person.getAlcoholType() + "\"" +
                        " разобрали, попробуй выбрать что-то другое(((");
                return "alco_test";
            }

            log.info("{} достался напиток {}.", person.getName(), drinkByParams.getName());
            person.setDrinkId(drinkByParams.getId());
            model.addAttribute("person", person);
            model.addAttribute("drink", drinkByParams);
            return "find_drink";
        }
        if (person.getAlcoholType() != null) {
            log.info("{} выбрал {}.", person.getName(), person.getAlcoholType());
            model.addAttribute("person", person);
            return "zapivon";
        }
        model.addAttribute("person", person);
        return "alco_test";
    }
}
