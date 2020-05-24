package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address {

    private String city;
    private String gunGu;
    private String zipcode;
    private String deatail;

    @Builder
    public Address(String city, String gunGu, String zipcode, String deatail) {
        this.city = city;
        this.gunGu = gunGu;
        this.zipcode = zipcode;
        this.deatail = deatail;
    }
}


