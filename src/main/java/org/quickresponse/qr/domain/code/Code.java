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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Builder
    public Code(Spot spot, String url) {
        setSpot(spot);
        this.url = url;
    }

    private void setSpot(Spot spot) {
        this.spot = spot;
        spot.setCode(this);
    }
}
