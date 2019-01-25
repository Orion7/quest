package bdquest.controllers;

import bdquest.models.Person;
import bdquest.services.DrinkService;
import bdquest.services.PartyManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class QuestController {

    @Autowired
    DrinkService drinkService;

    @Autowired
    PartyManagementService partyManagementService;


    @RequestMapping(value = "/image{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {
        File serverFile = new File("./src/main/resources/images/" + imageName + ".jpg");
        return Files.readAllBytes(serverFile.toPath());
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greetingForm(Model model) {
        model.addAttribute("person", Person.of(1, "Ринат",
            null, null, null));
        return "greeting";
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        return partyManagementService.greetingSubmit(person, model);
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

    @RequestMapping(value = "/findDrink", method = RequestMethod.POST)
    public String findDrink(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        return "drink_locations";
    }
}