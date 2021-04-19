package com.flamexander.hibernate.composed_key;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "citizens")
public class Citizen implements Serializable {
    private static final long serialVersionUID = -503212700335355521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumns({
            @JoinColumn(
                    name = "passport_serial",
                    referencedColumnName = "pserial"),
            @JoinColumn(
                    name = "passport_number",
                    referencedColumnName = "pnumber")
    })
    private Passport passport;

    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Citizen() {
    }

    @Override
    public String toString() {
        return String.format("Citizen [id = %d, name = %s]", id, name);
    }
}
