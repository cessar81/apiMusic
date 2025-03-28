package com.carsmoviesinventory.app.Services;

import com.songsinventory.app.Entities.Album;
import com.songsinventory.app.Repositories.AlbumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public ResponseEntity<?> getAllAlbums(Pageable pageable) {
        Page<Album> albums = albumRepository.findAll(pageable);
        return ResponseEntity.ok(albums);
    }

    public ResponseEntity<?> getAlbumById(UUID id) {
        Optional<Album> album = albumRepository.findById(id);
        return album.map(value -> ResponseEntity.ok(Collections.singletonMap("Album", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Status", "Album not found")));
    }

    public ResponseEntity<?> addAlbum(Album albumToAdd) {
        if (albumRepository.existsByName(albumToAdd.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("Status", "Album already exists"));
        }
        Album savedAlbum = albumRepository.save(albumToAdd);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("Status", "Added Album with ID " + savedAlbum.getId()));
    }

    public ResponseEntity<?> updateAlbum(UUID id, Album albumToUpdate) {
        Optional<Album> album = albumRepository.findById(id);
        if (album.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Status", "Album not found"));
        }
        Album existingAlbum = album.get();
        existingAlbum.setName(albumToUpdate.getName());
        existingAlbum.setReleaseYear(albumToUpdate.getReleaseYear());
        existingAlbum.setSongs(albumToUpdate.getSongs());
        albumRepository.save(existingAlbum);
        return ResponseEntity.ok(Collections.singletonMap("Status", "Updated Album with ID " + existingAlbum.getId()));
    }

    public ResponseEntity<?> deleteAlbum(UUID id) {
        if (!albumRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Status", "Album not found"));
        }
        albumRepository.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("Status", "Deleted Album with ID " + id));
    }
}
