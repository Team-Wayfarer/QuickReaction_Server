package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class StoreAdmin {

    @Id
    @GeneratedValue
    @Column(name = "storeadmin_id")
    private Long id;

    private String name;

    private String businessNumber;

    @OneToOne(mappedBy = "storeAdmin")
    private Spot spot;

    @Builder
    public StoreAdmin(String name, String businessNumber){
        this.name=name;
        this.businessNumber=businessNumber;
    }

}
