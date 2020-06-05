package org.quickresponse.qr.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contact;
  
    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<VisitInfo> visitInfoList = new ArrayList<>();

    public List<VisitInfo> getVisitInfoList() {
        return visitInfoList;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}