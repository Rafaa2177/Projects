package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Address.
 * Author Marco Ferreira and Diana Neves
 */
public class Address implements Serializable {
    private String street;
    private City city;
    private District district;
    private String zipCode;
    private State state;
    private static final int ZIP_CODE_LENGHT = 5;

    /**
     * Instantiates a new Address.
     *
     * @param street   the street
     * @param city     the city
     * @param district the district
     * @param zipCode  the zip code
     * @param state    the state
     */
    public Address(String street, City city, District district, String zipCode, State state) {
        if (StringUtils.isBlank(street) || city == null || StringUtils.isBlank(zipCode) || state == null) {
            throw new IllegalArgumentException("Invalid arguments.");
        }
        if (zipCode.length() != ZIP_CODE_LENGHT){
            throw new IllegalArgumentException("Invalid zip code");
        }

        this.street = street;
        this.city = city;
        this.district = district;
        this.zipCode = zipCode;
        this.state = state;
    }

    public Address (String nm){
        this.state = new State("not Specified");
        this.city = new City(this.state, "");
        this.district = new District("", this.city);
        this.street = nm;
        this.zipCode = "00000";
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {return street;}

    /**
     * Gets district.
     *
     * @return the district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Sets district.
     *
     * @param district the district
     */
    public void setDistrict(District district) {
        this.district = district;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{ " +
                "street = '" + street +
                ", city = " + city +
                ", district = " + district +
                ", zipCode = " + zipCode +
                ", state = " + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(district, address.district) && Objects.equals(zipCode, address.zipCode) && Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, district, zipCode, state);
    }
       public Address clone(){
        return new Address(this.street,this.city,this.district,this.zipCode,this.state);
    }
}


