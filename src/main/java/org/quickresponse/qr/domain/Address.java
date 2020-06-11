package org.quickresponse.qr.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @NotEmpty @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty @Column(name = "gun_gu", nullable = false)
    private String gunGu;

    @NotEmpty @Column(name = "zip_code", nullable = false)
    private String zipcode;

    @NotEmpty @Column(name = "detail", nullable = false)
    private String detail;

    @Builder
    public Address(String city, String gunGu, String zipcode, String detail) {
        Assert.hasText(city, "city must not be emtpy");
        Assert.hasText(gunGu, "gunGu must not be emtpy");
        Assert.hasText(zipcode, "zipcode must not be emtpy");
        Assert.hasText(detail, "detail must not be emtpy");

        this.city = city;
        this.gunGu = gunGu;
        this.zipcode = zipcode;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "주소{" +
                "시='" + city + '\'' +
                ", 군구='" + gunGu + '\'' +
                ", 주소'" + zipcode + '\'' +
                ", 상세주소='" + detail + '\'' +
                '}';
    }
}


