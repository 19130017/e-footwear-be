package vn.edu.hcmuaf.fit.efootwearspringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "verifies")
public class Verify implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    @Column(name = "is_expired")
    private Boolean isExpired;

    @Column(unique = true)
    private String token;

    public Date expireTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
