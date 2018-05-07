package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="user_connection")
@Getter
@Setter
@NoArgsConstructor
public class UserConnection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=255)
    @NotNull
    private String providerId;

    @Size(max=255)
    private String providerUserId;

    @NotNull
    private int rank;

    @Size(max=255)
    private String dispalyName;

    @Size(max=512)
    private String profileUrl;

    @Size(max=512)
    private String imegeUrl;

    @NotNull
    @Size(max=512)
    private String accessToken;

    @Size(max=512)
    private String secret;

    @Size(max=512)
    private String refreshToken;

    private long expireTime;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    User user;

    public void addUser(User user){
        this.user = user;
        if(!user.getUserConnections().contains(this)){
            user.getUserConnections().add(this);
        }
    }
}
