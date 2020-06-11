package org.quickresponse.qr.domain.user;

import com.sun.istack.NotNull;
import lombok.*;
import org.quickresponse.qr.domain.visitInfo.VisitInfo;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime whenChange;

    @OneToMany(mappedBy = "user")
    private List<VisitInfo> visitInfoList = new ArrayList<>();

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void changeUserStatus(UserStatus userStatus){
        this.userStatus = userStatus;
    }

    public void setDuid(String duid){
        this.duid= duid;
    }

    public void changeWhenChange(LocalDateTime whenChange){
        this.whenChange = whenChange;
    }

    @Builder
    public User(String name, String contact, String email, String password, String duid) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
        this.duid=duid;
        this.userStatus = UserStatus.NORMAL;
        this.whenChange = LocalDateTime.now();
    }
}