package bdquest.controllers;

import bdquest.models.Person;
import bdquest.services.DrinkService;
import bdquest.services.PartyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class QuestController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    PartyManagementService partyManagementService;


    @RequestMapping(value = "/image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File("./src/main/resources/images/" + imageName + ".jpg");
        return Files.readAllBytes(serverFile.toPath());
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        return partyManagementService.greetingSubmit(person, model);
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public String menu(@ModelAttribute Person person,
                           @RequestParam(value = "action", required = false) String action, Model model) {
        if (action != null && action.equals("find")) {
            return "f";
        }

        return partyManagementService.alcoTest(person, model);
    }

    @RequestMapping(value = "/alcoTest", method = RequestMethod.POST)
    public String alcoTest(@ModelAttribute Person person,
                           @RequestParam(value = "action", required = false) String action, Model model) {
        if (action != null && action.equals("random")) {
            return partyManagementService.getRandomDrink(person, model);
        }

        return partyManagementService.alcoTest(person, model);
    }

    @RequestMapping(value = "/findDrink", method = RequestMethod.POST)
    public String findDrink(@ModelAttribute Person person, Model model) {
        return partyManagementService.getDrinkLocations(person, model);
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("person", Person.of(1, "Ринат",
                null, null, null));
        return "greeting";
    }

    @RequestMapping(value = "/findDrink", method = RequestMethod.GET)
    public String getFindDrink(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "greeting";
    }

    @RequestMapping(value = "/alcoTest", method = RequestMethod.GET)
    public String getAlcoTest(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "greeting";
    }
}