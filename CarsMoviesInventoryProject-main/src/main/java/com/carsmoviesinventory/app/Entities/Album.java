package com.carsmoviesinventory.app.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int releaseYear;
    private double ranking; // Ranking del álbum

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private List<Song> songs;
}
