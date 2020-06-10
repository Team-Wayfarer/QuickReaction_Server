package org.quickresponse.qr.domain.user;

import com.sun.istack.NotNull;
import lombok.*;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String contact;
  
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private String duid;

    @OneToMany(mappedBy = "user")
    private List<VisitInfo> visitInfoList = new ArrayList<>();

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
    }

    public void setDuid(String duid){
        this.duid= duid;
    }

    @Builder
    public User(String name, String contact, String email, String password) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.userStatus = UserStatus.NORMAL;
    }
}