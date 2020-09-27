package com.kodluyoruz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kodluyoruz.model.Playlist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlaylistService {

	 Playlist create(Playlist playlist);

	 Optional<Playlist> findById(String playlistId);

	 List<Playlist> findAllByUserId(String userId);

	 void delete(String playlistId);

	 Playlist update(String id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;
}
