package org.quickresponse.qr.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Spot {

    @Id
    @GeneratedValue
    @Column(name = "spot_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String lat;

    private String lng;

    @OneToOne(fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "spotadmin_id")
    private SpotAdmin spotAdmin;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "code_id")
    private Code code;

    @OneToMany(mappedBy = "spot")
    private List<VisitInfo> visitInfoList;

    @Builder
    public Spot(String name, Address address, String lat, String lng, SpotAdmin spotAdmin, Code code) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.spotAdmin = spotAdmin;
        this.code = code;
    }

    //연관관계 메서드
    public void setSpotAdmin(SpotAdmin spotAdmin){
        this.spotAdmin = spotAdmin;
        spotAdmin.setSpot(this);
    }
}
