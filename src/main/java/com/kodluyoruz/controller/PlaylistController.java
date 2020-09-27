package com.kodluyoruz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kodluyoruz.model.Playlist;
import com.kodluyoruz.service.PlaylistServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/v1/playlists")
@Validated
public class PlaylistController {

	private final PlaylistServiceImpl playlistService;

	public PlaylistController(PlaylistServiceImpl playlistService) {
		this.playlistService = playlistService;
	}

	@PostMapping
	public ResponseEntity<Playlist> create(@Valid @RequestBody Playlist playlist) {
		Playlist createdPlaylist = playlistService.create(playlist);
		URI location = URI.create(String.format("/tracks/%s", createdPlaylist.getId()));
		return ResponseEntity.created(location).build();
	}
	@GetMapping ("/{id}")
	public ResponseEntity<Optional<Playlist>> findById(@PathVariable ("id") String playlistId) {
		Optional<Playlist> playlist = playlistService.findById(playlistId);
		return ResponseEntity.ok().body(playlist);
	}
	@GetMapping
	public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam String userId) {
		List<Playlist> playlists = playlistService.findAllByUserId(userId);
		return ResponseEntity.ok().body(playlists);
	}
	@DeleteMapping ("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable ("id") String playlistId) {
		playlistService.delete(playlistId);
		return ResponseEntity.noContent().build();
	}
	@PatchMapping (path = "/{id}", consumes = "application/json-patch+json")
	public ResponseEntity<Playlist> updateItem(@PathVariable ("id") String playlistId, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {

		Playlist playlist = playlistService.update(playlistId, patch);
		return ResponseEntity.ok(playlist);

	}

}
