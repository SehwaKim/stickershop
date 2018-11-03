package com.boot.stickershop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private LocalDateTime regtime;

    @ManyToMany
    @JoinTable(name = "user_user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") ,
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
    private Set<UserRole> roles = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "user_connection_id")
    private UserConnection userConnection;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserConnection> userConnections = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BasketProduct> basketProducts = new ArrayList<>();

    // 헬퍼
    public void addUserRole(UserRole role){
        this.roles.add(role);
    }

    public void addUserConnection(UserConnection userConnection){
        this.userConnections.add(userConnection);
        if(userConnection.getUser()!=this){
            userConnection.setUser(this);
        }
    }

    public void addBasketProduct(BasketProduct basketProduct){
        basketProducts.add(basketProduct);
        if(basketProduct.getUser() != this){
            basketProduct.setUser(this);
        }
    }

    public Long getUserId(){
        return getUserId();
    }
}
