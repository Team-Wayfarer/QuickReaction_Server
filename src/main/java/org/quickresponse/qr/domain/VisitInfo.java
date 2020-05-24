package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
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
        this.user = user;
        this.spot = spot;
        this.localDateTime = localDateTime;
    }
}
