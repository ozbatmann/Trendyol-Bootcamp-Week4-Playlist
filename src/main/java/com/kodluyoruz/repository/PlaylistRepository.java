package com.kodluyoruz.repository;

import com.kodluyoruz.model.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaylistRepository {

	 Playlist create(String playlistId,Playlist playlist);

	 Optional<Playlist> findById(String playlistId);

	 List<Playlist> findAllByUserId(String userId);

	 void delete(String playlistId);

	 void update (String playlistId, Playlist playlist);

}
