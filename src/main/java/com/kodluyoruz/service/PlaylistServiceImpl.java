package com.kodluyoruz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.kodluyoruz.exceptions.RestResourceNotFoundException;
import com.kodluyoruz.model.Playlist;
import com.kodluyoruz.repository.PlaylistRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaylistServiceImpl implements PlaylistService {

	private final PlaylistRepositoryImpl playlistRepository;
	private final ObjectMapper objectMapper;

	public PlaylistServiceImpl(PlaylistRepositoryImpl playlistRepository, ObjectMapper objectMapper) {
		this.playlistRepository = playlistRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public Playlist create(Playlist playlist) {
		playlist.setId(UUID.randomUUID().toString());
		return playlistRepository.create(playlist.getId(), playlist);
	}

	@Override
	public Optional<Playlist> findById(String playlistId) {
		Optional<Playlist> optPlaylist = playlistRepository.findById(playlistId);
		if(optPlaylist.isEmpty()) throw new RestResourceNotFoundException();

		return optPlaylist;
	}

	@Override
	public List<Playlist> findAllByUserId(String userId) {
		return playlistRepository.findAllByUserId(userId);
	}

	@Override
	public void delete(String playlistId) {
		playlistRepository.delete(playlistId);
	}

	@Override
	public Playlist update(String id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
		Optional<Playlist> playlist = playlistRepository.findById(id);
		if(playlist.isEmpty()) throw new RestResourceNotFoundException();

		Playlist playlistPatched = applyPatch(patch, playlist.get());

		playlistRepository.update(playlistPatched.getId(), playlistPatched);

		return playlistPatched;
	}

	private Playlist applyPatch(JsonPatch patch, Playlist target) throws JsonPatchException, JsonProcessingException {
		JsonNode patched = patch.apply(objectMapper.convertValue(target, JsonNode.class));
		return objectMapper.treeToValue(patched, Playlist.class);
	}
}
