package me.tamasrigoczki.bookCatalogApi.model.entity;

import lombok.Data;
import me.tamasrigoczki.bookCatalogApi.model.enums.ProgressType;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class BookEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date created_at;

    private int progress;

    @Enumerated(EnumType.ORDINAL)
    private ProgressType progressType;


}
