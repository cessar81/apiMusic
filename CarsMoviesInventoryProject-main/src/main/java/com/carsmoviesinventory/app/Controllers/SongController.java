package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.DTOs.SongDTO;
import com.carsmoviesinventory.app.Entities.Song;
import com.carsmoviesinventory.app.Services.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        // Convertir a DTO (puedes usar un mapper, aquí lo hacemos de forma simple)
        List<SongDTO> dtos = songs.stream().map(song -> new SongDTO(
                song.getId(),
                song.getTitle(),
                song.getDuration(),
                song.getYear(),
                song.getDetails(),
                song.getAlbum() != null ? song.getAlbum().getId() : null
        )).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long id) {
        Song song = songService.getSongById(id);
        SongDTO dto = new SongDTO(
                song.getId(),
                song.getTitle(),
                song.getDuration(),
                song.getYear(),
                song.getDetails(),
                song.getAlbum() != null ? song.getAlbum().getId() : null
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> addSong(@RequestBody Song song) {
        songService.addSong(song);
        return ResponseEntity.status(201).body("Song added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSong(@PathVariable Long id, @RequestBody Song song) {
        songService.updateSong(id, song);
        return ResponseEntity.ok("Song updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok("Song deleted successfully");
    }
}
