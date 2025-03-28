package com.carsmoviesinventory.app.DTOs;

import java.util.List;

public class AlbumDTO {
    private Long id;
    private String name;
    private int releaseYear;
    private double ranking;
    private List<SongDTO> songs; // Opcional, para incluir las canciones del álbum

    // Constructores
    public AlbumDTO() {}

    public AlbumDTO(Long id, String name, int releaseYear, double ranking, List<SongDTO> songs) {
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.ranking = ranking;
        this.songs = songs;
    }

    // Getters y Setters
    // ...
}
