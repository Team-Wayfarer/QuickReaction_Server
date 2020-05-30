package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SpotAdmin {

    @Id
    @GeneratedValue
    @Column(name = "spotadmin_id")
    private Long id;

    private String name;

    private String businessNumber;

    private String contact;

    @OneToOne(mappedBy = "spotAdmin")
    private Spot spot;

    @Builder
    public SpotAdmin(String name, String businessNumber, String contact){
        this.name=name;
        this.businessNumber=businessNumber;
        this.contact = contact;
    }
}
