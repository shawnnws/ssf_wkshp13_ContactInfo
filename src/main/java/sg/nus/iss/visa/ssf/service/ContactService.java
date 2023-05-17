package sg.nus.iss.visa.ssf.service;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sg.nus.iss.visa.ssf.model.Contact;


@Service
public class ContactService {
    
    public void save(Contact contact, Model model, String dataDir) throws Exception{

        String fileName = contact.getId();
        PrintWriter pw = null;

        FileWriter fw = new FileWriter(dataDir + "/" + fileName + ".txt");
        pw = new PrintWriter(fw);

        pw.println(contact.getName());
        pw.println(contact.getEmail());
        pw.println(contact.getPhoneNumber());
        pw.println(contact.getDateOfBirth());

        model.addAttribute("contact", new Contact(contact.getId(), contact.getName(), contact.getEmail(), contact.getDateOfBirth(), contact.getPhoneNumber()));

        pw.close();
    }
}
