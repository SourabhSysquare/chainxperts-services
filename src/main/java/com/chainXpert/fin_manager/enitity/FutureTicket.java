package com.chainXpert.fin_manager.enitity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
@Table(name = "future_tickets")
public class FutureTicket implements Serializable {

    private static final long serialVersionUID = 764699031042581465L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    private User user;

    @Column(name = "balance_mode_id", nullable = true)
    private Long balanceModeId;

    
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "balance_mode_id", nullable = true, insertable = false, updatable = false)
    private BalanceMode balanceMode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true, length = 500)
    private String description;

    @Column(name = "ticket_type", nullable = false)
    private String ticketType;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "ticket_completion_date", nullable = true)
    private LocalDate ticketCompletionDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

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
