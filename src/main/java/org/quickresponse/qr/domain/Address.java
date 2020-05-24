package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String gunGu;
    private String zipcode;
    private String deatail;

    protected Address(){

    }

    @Builder
    public Address(String city, String gunGu, String zipcode, String deatail) {
        this.city = city;
        this.gunGu = gunGu;
        this.zipcode = zipcode;
        this.deatail = deatail;
    }
}


