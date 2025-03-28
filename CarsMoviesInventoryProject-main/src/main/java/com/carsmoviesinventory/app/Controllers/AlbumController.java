package com.carsmoviesinventory.app.Controllers;

import com.carsmoviesinventory.app.DTOs.AlbumDTO;
import com.carsmoviesinventory.app.Entities.Album;
import com.carsmoviesinventory.app.Services.AlbumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<Page<Album>> getAllAlbums(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Album> albums = albumService.getAllAlbums(PageRequest.of(page, size));
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        Album album = albumService.getAlbumById(id);
        AlbumDTO dto = new AlbumDTO(
                album.getId(),
                album.getName(),
                album.getReleaseYear(),
                album.getRanking(),
                null // Para simplificar, no incluimos la lista de canciones en este ejemplo
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<String> addAlbum(@RequestBody Album album) {
        albumService.addAlbum(album);
        return ResponseEntity.status(201).body("Album added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        albumService.updateAlbum(id, album);
        return ResponseEntity.ok("Album updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.ok("Album deleted successfully");
    }
}
