package org.quickresponse.qr.domain.center;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long centerIdx;
    private String id;
    private String password;
    private String contact;

    @Builder
    public Center(String id, String password, String contact) {
        this.id = id;
        this.password = password;
        this.contact = contact;
    }
}
