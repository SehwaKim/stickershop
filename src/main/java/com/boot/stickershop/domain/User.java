package com.boot.stickershop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    public User(){
        regtime = LocalDateTime.now();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String phone1;
    private String phone2;
    private String phone3;
    private LocalDateTime regtime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserRole> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_connection_id")
    private UserConnection userConnection;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserConnection> userConnections = new ArrayList<>();

    // 헬퍼
    public void addUserRole(UserRole role){
        this.roles.add(role);
        if(role.getUser()!=this){
            role.setUser(this);
        }
    }

    public void addUserConnection(UserConnection userConnection){
        this.userConnections.add(userConnection);
        if(userConnection.getUser()!=this){
            userConnection.setUser(this);
        }
    }
}
