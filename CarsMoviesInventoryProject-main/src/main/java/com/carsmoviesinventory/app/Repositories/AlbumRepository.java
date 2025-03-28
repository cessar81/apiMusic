package com.carsmoviesinventory.app.Repositories;

import com.carsmoviesinventory.app.Entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
}
