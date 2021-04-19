package com.flamexander.hibernate.composed_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SerialNumber implements Serializable {
    private static final long serialVersionUID = -3163035995355860580L;

    @Column(name = "pserial")
    private Integer serial;

    @Column(name = "pnumber")
    private Integer number;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SerialNumber() {
    }

    public SerialNumber(Integer serial, Integer number) {
        this.serial = serial;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerialNumber that = (SerialNumber) o;
        return Objects.equals(serial, that.serial) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, number);
    }
}
