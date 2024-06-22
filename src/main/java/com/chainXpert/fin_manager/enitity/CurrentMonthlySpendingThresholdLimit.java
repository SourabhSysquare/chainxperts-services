package com.chainXpert.fin_manager.enitity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


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
@Table(name = "current_monthly_spending_threshold_limits")
public class CurrentMonthlySpendingThresholdLimit implements Serializable {

    private static final long serialVersionUID = -1092512212071687815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, insertable = false, updatable = false)
    private Integer id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true, insertable = false, updatable = false)
    private User user;

    @Column(name = "limit_value", nullable = false)
    private Double limitValue;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

}
