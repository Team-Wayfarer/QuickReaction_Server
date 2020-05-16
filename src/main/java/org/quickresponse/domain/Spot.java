package org.quickresponse.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Spot {

    @Id
    @GeneratedValue
    @Column(name = "spot_id")
    private Long id;

    private String name;

    private String address;

    private String lat;

    private String lng;

    @OneToOne
    private StoreAdmin storeAdmin;

    @OneToOne
    private Code code;

    @OneToMany(mappedBy = "spot")
    private List<VisitInfo> visitInfoList;

    @Builder
    public Spot(String name, String address, String lat, String lng) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
