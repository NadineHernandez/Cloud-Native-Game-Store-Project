package com.company.adminapi.model;

import java.util.Objects;

public class CustomerViewModel {


    private Integer customerId;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private String email;
    private String phone;

    public CustomerViewModel() {
    }

    public CustomerViewModel(Integer customerId, String firstName, String lastName, String street, String city, String zip, String email, String phone) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public CustomerViewModel(String firstName, String lastName, String street, String city, String zip, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewModel that = (CustomerViewModel) o;
        return getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getStreet().equals(that.getStreet()) &&
                getCity().equals(that.getCity()) &&
                getZip().equals(that.getZip()) &&
                getEmail().equals(that.getEmail()) &&
                getPhone().equals(that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getStreet(), getCity(), getZip(), getEmail(), getPhone());
    }
}
