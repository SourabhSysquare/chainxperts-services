package com.chainXpert.fin_manager.enitity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author : HARSHIT RASTOGI
 * @email : harshit.rastogi@sysquare.com
 * @date : 22/06/24
 */
@Data
@Entity
@Table(name = "ticket_tag_mappings", uniqueConstraints = { @UniqueConstraint(columnNames = { "ticket_id", "tag_id" }) })
public class TicketTagMapping implements Serializable {

    private static final long serialVersionUID = 7485034351930195266L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "tag_id", nullable = true)
    private Integer tagId;

    
    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = true, insertable = false, updatable = false)
    private Tag tag;

    @Column(name = "ticket_type", nullable = false)
    private String ticketType;

    @Column(name = "ticket_id", nullable = false)
    private Long ticketId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
