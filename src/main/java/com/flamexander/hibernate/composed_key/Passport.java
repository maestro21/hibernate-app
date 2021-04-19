package com.flamexander.hibernate.composed_key;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    private static final long serialVersionUID = 3734710958953154546L;

    @EmbeddedId
    private SerialNumber id;

    @Column(name = "registration_address")
    private String registrationAddress;

    public SerialNumber getId() {
        return id;
    }

    public void setId(SerialNumber id) {
        this.id = id;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public Passport() {
    }

    @Override
    public String toString() {
        return String.format("Passport [serial = %d, number = %d, registration = %s]", id.getSerial(), id.getNumber(), registrationAddress);
    }
}
