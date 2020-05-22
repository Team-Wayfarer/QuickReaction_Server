package org.quickresponse.qr.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contact;

    @OneToMany(mappedBy = "user")
    private List<VisitInfo> visitInfoList;

    @Builder
    public User(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public List<VisitInfo> getVisitInfoList() {
        return visitInfoList;
    }
}