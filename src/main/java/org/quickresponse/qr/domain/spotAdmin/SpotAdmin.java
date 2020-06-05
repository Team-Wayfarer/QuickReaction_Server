package org.quickresponse.qr.domain.spotAdmin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
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

    public void setSpot(Spot spot){
        this.spot = spot;
    }

    @Builder
    public SpotAdmin(String name, String businessNumber, String contact){
        this.name=name;
        this.businessNumber=businessNumber;
        this.contact = contact;
    }
}
