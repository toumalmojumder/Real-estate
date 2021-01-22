package com.jg.real_estate.Model;

import android.graphics.Bitmap;

public class House {
    int ID;
    String estate_name;

    String street;
    String city;
    String province;
    String postal_code;
    String country;
    String phone;
    String email;
    String google_location;

    String estate_type_name;
    String floor_space;
    String number_of_balconies;
    String balconies_space;
    String number_of_bedrooms;
    String number_of_bathrooms;
    String number_of_garages;
    String number_of_parking_spaces;
    boolean pets_allowed;
    String estate_description;
    String estate_status;
    String estate_price;

    Bitmap estate_image1;
    Bitmap estate_image2;
    Bitmap estate_image3;
    Bitmap estate_image4;

    public House() {
    }

    public House(int ID, String estate_name, String street, String city, String province, String postal_code,
                 String country, String phone, String email,
                 String google_location, String estate_type_name,
                 String floor_space, String number_of_balconies,
                 String balconies_space, String number_of_bedrooms,
                 String number_of_bathrooms, String number_of_garages,
                 String number_of_parking_spaces,
                 boolean pets_allowed, String estate_description,
                 String estate_status, String estate_price,
                 Bitmap estate_image1, Bitmap estate_image2,
                 Bitmap estate_image3, Bitmap estate_image4) {
        this.ID = ID;
        this.estate_name = estate_name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postal_code = postal_code;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.google_location = google_location;
        this.estate_type_name = estate_type_name;
        this.floor_space = floor_space;
        this.number_of_balconies = number_of_balconies;
        this.balconies_space = balconies_space;
        this.number_of_bedrooms = number_of_bedrooms;
        this.number_of_bathrooms = number_of_bathrooms;
        this.number_of_garages = number_of_garages;
        this.number_of_parking_spaces = number_of_parking_spaces;
        this.pets_allowed = pets_allowed;
        this.estate_description = estate_description;
        this.estate_status = estate_status;
        this.estate_price = estate_price;
        this.estate_image1 = estate_image1;
        this.estate_image2 = estate_image2;
        this.estate_image3 = estate_image3;
        this.estate_image4 = estate_image4;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEstate_name() {
        return estate_name;
    }

    public void setEstate_name(String estate_name) {
        this.estate_name = estate_name;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogle_location() {
        return google_location;
    }

    public void setGoogle_location(String google_location) {
        this.google_location = google_location;
    }

    public String getEstate_type_name() {
        return estate_type_name;
    }

    public void setEstate_type_name(String estate_type_name) {
        this.estate_type_name = estate_type_name;
    }

    public String getFloor_space() {
        return floor_space;
    }

    public void setFloor_space(String floor_space) {
        this.floor_space = floor_space;
    }

    public String getNumber_of_balconies() {
        return number_of_balconies;
    }

    public void setNumber_of_balconies(String number_of_balconies) {
        this.number_of_balconies = number_of_balconies;
    }

    public String getBalconies_space() {
        return balconies_space;
    }

    public void setBalconies_space(String balconies_space) {
        this.balconies_space = balconies_space;
    }

    public String getNumber_of_bedrooms() {
        return number_of_bedrooms;
    }

    public void setNumber_of_bedrooms(String number_of_bedrooms) {
        this.number_of_bedrooms = number_of_bedrooms;
    }

    public String getNumber_of_bathrooms() {
        return number_of_bathrooms;
    }

    public void setNumber_of_bathrooms(String number_of_bathrooms) {
        this.number_of_bathrooms = number_of_bathrooms;
    }

    public String getNumber_of_garages() {
        return number_of_garages;
    }

    public void setNumber_of_garages(String number_of_garages) {
        this.number_of_garages = number_of_garages;
    }

    public String getNumber_of_parking_spaces() {
        return number_of_parking_spaces;
    }

    public void setNumber_of_parking_spaces(String number_of_parking_spaces) {
        this.number_of_parking_spaces = number_of_parking_spaces;
    }

    public boolean isPets_allowed() {
        return pets_allowed;
    }

    public void setPets_allowed(boolean pets_allowed) {
        this.pets_allowed = pets_allowed;
    }

    public String getEstate_description() {
        return estate_description;
    }

    public void setEstate_description(String estate_description) {
        this.estate_description = estate_description;
    }

    public String getEstate_status() {
        return estate_status;
    }

    public void setEstate_status(String estate_status) {
        this.estate_status = estate_status;
    }

    public String getEstate_price() {
        return estate_price;
    }

    public void setEstate_price(String estate_price) {
        this.estate_price = estate_price;
    }

    public Bitmap getEstate_image1() {
        return estate_image1;
    }

    public void setEstate_image1(Bitmap estate_image1) {
        this.estate_image1 = estate_image1;
    }

    public Bitmap getEstate_image2() {
        return estate_image2;
    }

    public void setEstate_image2(Bitmap estate_image2) {
        this.estate_image2 = estate_image2;
    }

    public Bitmap getEstate_image3() {
        return estate_image3;
    }

    public void setEstate_image3(Bitmap estate_image3) {
        this.estate_image3 = estate_image3;
    }

    public Bitmap getEstate_image4() {
        return estate_image4;
    }

    public void setEstate_image4(Bitmap estate_image4) {
        this.estate_image4 = estate_image4;
    }
}
