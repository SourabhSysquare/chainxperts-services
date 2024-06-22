package com.chainXpert.fin_manager.enitity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
@Table(name = "note_tag_mappings", uniqueConstraints = { @UniqueConstraint(columnNames = { "note_id", "tag_id" }) })
public class NoteTagMapping implements Serializable {

    private static final long serialVersionUID = -1177635991384609405L;

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

    @Column(name = "note_id", nullable = true)
    private Long noteId;

    @EqualsAndHashCode.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id", nullable = true, insertable = false, updatable = false)
    private Note note;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
