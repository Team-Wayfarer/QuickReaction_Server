package org.quickresponse.qr.domain.spotAdmin;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SpotAdmin {

    @Id
    @GeneratedValue
    @Column(name = "spotadmin_id")
    private Long id;

    private String name;
    private String password;
    private String email;
    private String businessNumber;
    private String contact;

    @OneToOne(mappedBy = "spotAdmin")
    private Spot spot;

    public void setSpot(Spot spot){
        this.spot = spot;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Builder
    public SpotAdmin(String name, String businessNumber, String contact, String password, String email ){
        this.name=name;
        this.password = password;
        this.email = email;
        this.businessNumber=businessNumber;
        this.contact = contact;
    }
}
