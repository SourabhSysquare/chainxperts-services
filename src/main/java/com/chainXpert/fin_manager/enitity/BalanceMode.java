package com.chainXpert.fin_manager.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "balance_modes")
public class BalanceMode implements Serializable {

    private static final long serialVersionUID = -6355462386694894438L;

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "total_balance_id", nullable = true)
    private Long totalBalanceId;
    
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "total_balance_id", nullable = true, insertable = false, updatable = false)
    private TotalBalance totalBalance;

    @Column(name = "mode_type", nullable = false)
    private String modeType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "balanceMode", fetch = FetchType.LAZY)
    private Set<CompletedTicket> completedTickets;

    
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "balanceMode", fetch = FetchType.LAZY)
    private Set<FutureTicket> futureTickets;

    @PrePersist
    void onCreate() {
        
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
