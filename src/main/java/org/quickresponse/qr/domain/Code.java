package org.quickresponse.qr.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
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
}
