package bdquest.controllers;

import bdquest.models.Drink;
import bdquest.models.Person;
import bdquest.models.services.DrinkService;
import bdquest.models.services.PartyManagementService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QuestController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(QuestController.class);

    @Autowired
    DrinkService drinkService;

    @Autowired
    PartyManagementService partyManagementService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("person", Person.of(1, "Ринат",
                null, null, null));
        return "greeting";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        if (partyManagementService.isInvited(person.getName())) {
            log.info("{} подключился!", person.getName());
            model.addAttribute("person", person);
            return "main";
        }
        log.info("{} не приглашен, но пытался подключиться!", person.getName());
        return "not_invited";
    }

    @RequestMapping(value = "/alcoTest", method = RequestMethod.GET)
    public String getAlcoTest(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "greeting";
    }

    @RequestMapping(value = "/alcoTest", method = RequestMethod.POST)
    public String alcoTest(@ModelAttribute Person person, Model model) {
        return partyManagementService.alcoTest(person, model);
    }
}