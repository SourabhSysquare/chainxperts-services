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
@Table(name = "master_tags")
public class Tag implements Serializable {

    private static final long serialVersionUID = 7614500210597678021L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private Set<NoteTagMapping> noteTagMappings;

    
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "tag", fetch = FetchType.LAZY)
    private Set<TicketTagMapping> ticketTagMappings;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.name = this.name.toUpperCase();
    }

}
