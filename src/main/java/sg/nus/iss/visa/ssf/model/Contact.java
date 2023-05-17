package sg.nus.iss.visa.ssf.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;

public class Contact {

    /*
     * Defining the contraints for our attributes
     */

    @NotNull(message = "Name cannot be empty")
    @Size(min= 3, max= 64, message = "Name should be between 3 to 15 characters")
    private String name;

    @NotEmpty(message = "Email field cannot be empty") // Because @Email will not take empty field as an error
    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 8, message = "Invalid phone number")
    private String phoneNumber;

    @Past(message = "Invalid date of birth")
    @NotNull(message = "Date of birth field is empty")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate dateOfBirth;

    @Min(value = 10, message = "Must be above 10 years old")
    @Max(value = 100, message = "Must be below 100 years old")
    private int age;

    private String id;

    private String generateId() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < 8) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 8);
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        //calculate age
        int calculatedAge = 0;
        if (dateOfBirth != null) {
            calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
        }
        this.age = calculatedAge;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Contact() {
        this.id = generateId();
    }

    public Contact(String name, String email, String phoneNumber, LocalDate dateOfBirth, String id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    

    @Override
    public String toString() {
        return "Contact [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
