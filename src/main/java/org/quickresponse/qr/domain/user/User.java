package org.quickresponse.qr.domain.user;

import com.sun.istack.NotNull;
import lombok.*;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

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

    @Builder
    public User(String name, String contact, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }
}