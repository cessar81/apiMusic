package com.carsmoviesinventory.app.Services;

import com.songsalbumsinventory.app.Entities.Song;
import com.songsalbumsinventory.app.Repositories.SongRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public ResponseEntity<?> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return ResponseEntity.ok(Collections.singletonMap("Songs", songs));
    }

    public ResponseEntity<?> getSongById(UUID id) {
        Optional<Song> song = songRepository.findById(id);
        return song.map(value -> ResponseEntity.ok(Collections.singletonMap("Song", value)))
                .orElseGet(() -> new ResponseEntity<>(Collections.singletonMap("Status", "Song not found"), HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> addSong(Song song) {
        Song savedSong = songRepository.save(song);
        return new ResponseEntity<>(Collections.singletonMap("Status", "Added Song with ID " + savedSong.getId()), HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateSong(UUID id, Song songToUpdate) {
        return songRepository.findById(id).map(existingSong -> {
            existingSong.setTitle(songToUpdate.getTitle());
            existingSong.setDuration(songToUpdate.getDuration());
            existingSong.setYear(songToUpdate.getYear());
            existingSong.setAlbum(songToUpdate.getAlbum());
            songRepository.save(existingSong);
            return ResponseEntity.ok(Collections.singletonMap("Status", "Updated Song with ID " + existingSong.getId()));
        }).orElseGet(() -> new ResponseEntity<>(Collections.singletonMap("Status", "Song not found"), HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<?> deleteSong(UUID id) {
        if (!songRepository.existsById(id)) {
            return new ResponseEntity<>(Collections.singletonMap("Status", "Song not found"), HttpStatus.NOT_FOUND);
        }
        songRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", "Deleted Song with ID " + id));
    }

}
