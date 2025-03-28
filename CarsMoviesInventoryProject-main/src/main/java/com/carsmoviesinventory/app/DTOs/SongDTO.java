package com.carsmoviesinventory.app.DTOs;

public class SongDTO {
    private Long id;
    private String title;
    private int duration; // en segundos
    private int year;
    private String details;
    private Long albumId; // Si la canción está asociada a un álbum

    // Constructores
    public SongDTO() {}

    public SongDTO(Long id, String title, int duration, int year, String details, Long albumId) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.year = year;
        this.details = details;
        this.albumId = albumId;
    }

    // Getters y Setters
    // ...
}
