package com.chainXpert.fin_manager.enitity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Data
@Entity
@Table(name = "spending_threshold_records")
public class SpendingThresholdRecord implements Serializable {

    private static final long serialVersionUID = 9034204572897473307L;

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

    @Column(name = "value_spent", nullable = false)
    private Double valueSpent;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "year", nullable = false)
    private String year;
}
