package org.quickresponse.domain;

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

    @OneToOne
    private Spot spot;
}
