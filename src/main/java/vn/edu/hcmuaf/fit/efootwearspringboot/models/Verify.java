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
    @Column(name = "type")
    private String type;

    @Column(name = "is_used")
    private Boolean isUsed;

    @Column(unique = true, name = "token")
    private String token;

    @Column(name = "expire_time")
    public Date expireTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
