package com.carsmoviesinventory.app.Entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int duration; // en segundos
    private int year;

    @Column(columnDefinition = "TEXT")
    private String details; // interpretación de la canción

    @ManyToOne
    @JoinColumn(name = "album_id") // Clave foránea en la tabla song
    private Album album;
}
