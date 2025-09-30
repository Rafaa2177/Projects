package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.domain.enums.Roles;
import pt.ipp.isep.dei.esoft.project.domain.enums.Status;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Objects;

import static pt.ipp.isep.dei.esoft.project.domain.CurrentSession.getEmail;

/**
 * The type Person.
 */
public class Person extends User implements Serializable {
    private String passportNumber;
    private String taxNumber;
    private String phoneNumber, pwd;
    private Roles roles;
    private static final int PASSPORT_NUMBER_LENGTH = 8;
    private static final int TAX_NUMBER_LENGTH = 9;

    /**
     * Instantiates a new Person.
     *
     * @param id             the id
     * @param pwd            the pwd
     * @param name           the name
     * @param passportNumber the passport number
     * @param taxNumber      the tax number
     * @param phoneNumber    the phone number
     * @param role           the role
     */
    public Person(Email id, String pwd, String name, String passportNumber, String taxNumber, String phoneNumber, Roles role) {
        super(id, new Password(pwd), name);
        if (!validatePassword(pwd))
            throw new IllegalArgumentException("Password must contain at least 3 capital letters and 2 numbers and be at least 7 characters long");
        //if (passportNumber == null || taxNumber == null || phoneNumber == null)
            //throw new IllegalArgumentException("Attributes cannot be null");
        //if (passportNumber.length() != PASSPORT_NUMBER_LENGTH || !StringUtils.isNumeric(passportNumber))
            //throw new IllegalArgumentException("Passport number must contain " + PASSPORT_NUMBER_LENGTH +
                    //" digits and be all numerics digits");
        //if (!phoneNumber.matches("\\(\\d{3}\\) \\d{3}-\\d{4}"))
            //throw new IllegalArgumentException("Phone number must be in the format (xxx) xxx-xxxx");
        //if (taxNumber.length() != TAX_NUMBER_LENGTH || !StringUtils.isNumeric(taxNumber))
            //throw new IllegalArgumentException("Tax number must contain " + TAX_NUMBER_LENGTH + " digits and be all numerics digits");

        this.passportNumber = passportNumber;
        this.taxNumber = taxNumber;
        this.phoneNumber = phoneNumber;
        this.pwd = pwd;
        this.roles = role;
    }

    /**
     * Instantiates a new Person.
     *
     * @param id   the id
     * @param name the name
     */
    public Person(Email id ,String name) {
        super(id ,null,name);
    }

    /**
     * Validate password boolean.
     *
     * @param pwd the pwd
     * @return the boolean
     */
    public boolean validatePassword(String pwd) {
        if (pwd.length() < 7)
            return false;
        int countCapitalLetters = 0;
        int countNumbers = 0;
        for (int i = 0; i < pwd.length(); i++) {
            if (Character.isUpperCase(pwd.charAt(i)))
                countCapitalLetters++;
            if (Character.isDigit(pwd.charAt(i)))
                countNumbers++;
        }
        if (countCapitalLetters < 3 || countNumbers < 2)
            return false;
        return true;
    }

    public Person clone() {
        return new Person(this.getId(),this.pwd, this.getName(), this.passportNumber,
                this.taxNumber, this.phoneNumber, this.roles);
    }


    /**
     * Gets pwd.
     *
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets pwd.
     *
     * @param pwd the pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Gets person role.
     *
     * @return the person role
     */
    public Roles getPersonRole() {
        return roles;
    }

    /**
     * Sets role.
     *
     * @param roles the roles
     */
    public void setRole(Roles roles) {
        this.roles = roles;
    }

    /**
     * Gets tax number.
     *
     * @return the tax number
     */
    public String getTaxNumber() {return taxNumber;}

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {return phoneNumber;}

    /**
     * Gets passport number.
     *
     * @return the passport number
     */
    public String getPassportNumber() {return passportNumber;}

    /**
     * Sets passport number.
     *
     * @param passportNumber the passport number
     */
    public void setPassportNumber(String passportNumber) {this.passportNumber = passportNumber;}

    /**
     * Sets tax number.
     *
     * @param taxNumber the tax number
     */
    public void setTaxNumber(String taxNumber) {this.taxNumber = taxNumber;}

    /**
     * Sets phone number.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    /**
     * Gets role.
     *
     * @return the role
     */
    public Roles getRole() {return roles;}


    /**
     * Send email.
     *
     * @throws FileNotFoundException the file not found exception
     */
    public void sendEmail() throws FileNotFoundException {
        String path ="Password_of_"+ getEmail() +".txt";
        File emailWithPassword= new File(path);
        PrintWriter printWriter = new PrintWriter(emailWithPassword);
        printWriter.write(this.getPwd());
        printWriter.close();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Has email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean hasEmail(String email) {
        return getId().equals(email);
    }

    /**
     * Sets order.
     *
     * @param announcement the announcement
     * @param order        the order
     * @param status       the status
     * @param email        the email
     * @return the order
     */
    public boolean setOrder(Announcement announcement, PurchaseOrder order, Status status, String email) {
        return true;
    }

    @Override
    public String toString() {
        return "{ " +
                "email = " + getId() +
                ", name = " + getName() +
                ", passportNumber = " + passportNumber +
                ", taxNumber = " + taxNumber +
                ", phoneNumber = " + phoneNumber +
                "}";
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public Email getEmail() {
        return getId();
    }
}
