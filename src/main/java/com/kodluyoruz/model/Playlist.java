package com.kodluyoruz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Playlist {

	private String id;
	@NotNull
	private String name;
	private String description;
	private int followersCount;
	private List<Track> tracks;
	@NotNull
	private String userId;

	public Playlist(String name, String description, int followersCount, List<Track> tracks, String userId) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.description = description;
		this.followersCount = followersCount;
		this.tracks = tracks;
		this.userId = userId;
	}
}
