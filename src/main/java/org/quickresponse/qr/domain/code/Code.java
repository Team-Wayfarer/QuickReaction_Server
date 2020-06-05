package org.quickresponse.qr.domain.code;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Code {

    @Id
    @GeneratedValue
    @Column(name = "code_id")
    private Long id;

    private String url;

    @OneToOne(mappedBy = "code")
    private Spot spot;

    @Builder
    public Code(String url) {
        this.url = url;
    }

    public void setSpot(Spot spot){
        this.spot = spot;
    }
}
