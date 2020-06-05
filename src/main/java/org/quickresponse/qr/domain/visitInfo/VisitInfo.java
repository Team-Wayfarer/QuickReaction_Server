package org.quickresponse.qr.domain.visitInfo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.quickresponse.qr.domain.spot.Spot;
import org.quickresponse.qr.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class VisitInfo {

    @Id
    @GeneratedValue
    @Column(name = "visitinfo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    private LocalDateTime localDateTime;

    @Builder
    public VisitInfo(User user, Spot spot, LocalDateTime localDateTime) {
        setUser(user);
        setSpot(spot);
        this.localDateTime = localDateTime;
    }

    //연관관계 메서드

    private void setUser(User user){
        this.user=user;
        user.getVisitInfoList().add(this);
    }

    private void setSpot(Spot spot){
        this.spot=spot;
        spot.getVisitInfoList().add(this);
    }
}
