package com.example.codetranslator.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exception")
@ToString
public class ExceptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "LONGTEXT")
    @Lob
    private String requestCode;

    @Column
    private String language;

    @Column
    private String targetLanguage;

    @Column
    private String errorMessage;

    @Column(columnDefinition = "LONGTEXT")
    @Lob
    private String stackTrace;

    @Column
    @CreationTimestamp
    private LocalDateTime timestamp;

}
