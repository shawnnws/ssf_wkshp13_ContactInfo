package sg.nus.iss.visa.ssf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import sg.nus.iss.visa.ssf.model.Contact;
import sg.nus.iss.visa.ssf.service.ContactService;
import sg.nus.iss.visa.ssf.utility.Utility;

@Controller
// Indicating / as landing page
@RequestMapping(path="/")
public class AddressBookController {

    @Autowired
    Utility utility;

    @Autowired
    ContactService contactService;

    @Value("${data.dir}")
    private String dataDir;
    
    // Load AddressBook.html as the landing page, no GET or POST method here as we are just loading a page
    // Model parameter allows us to pass the retrieved information to our html to display to user
    @GetMapping
    public String showAddressBook(Model model) {

        model.addAttribute("contact", new Contact());

        return "AddressBook";
    }

    // 
    @PostMapping(consumes = "application/x-www-form-urlencoded", path="/contact")
    // There are multiple ways(parameters) to capture the contact object to save inside our AddressBook html
    // Valid validation will check information against Contact.java constraints to make sure the data input is valid
    // BindingResult allows us to bind any invalid input errors to the result object
    public String saveAddressBook(@Valid Contact contact, BindingResult bindingResult, Model model) throws Exception {

        // This is manual printing out of the contact info on our terminal, just to check that we are capturing 
        // the info keyed in on the webpage successfully
        // System.out.println("Name: " + contact.getName());
        // System.out.println("Email: " + contact.getEmail());
        // System.out.println("Phone Number: " + contact.getPhoneNumber());

        // Do something if there are errors encountered.
        if (bindingResult.hasErrors()) {  
            return "AddressBook";
        }

        // Custom data validation for email duplicates
        // if (!utility.isUniqueEmail(contact.getEmail())) {
        //     ObjectError err = new ObjectError("globalError", "%s is not available".formatted(contact.getEmail()));
        //     bindingResult.addError(err);
        // }

        //
        

        // We can pass in the path manually inside the parameter or pass it from application.properties via dataDir
        contactService.save(contact, model, dataDir);
        model.addAttribute("successMessage", "Contact saved successfully, with status code: " + HttpStatus.CREATED + ".");
        return "showContact";
    }


}
