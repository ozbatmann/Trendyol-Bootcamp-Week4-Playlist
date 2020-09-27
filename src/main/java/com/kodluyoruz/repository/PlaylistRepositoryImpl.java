package com.kodluyoruz.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.model.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository {

	private final Cluster couchbaseCluster;
	private final Collection playlistCollection;

	public PlaylistRepositoryImpl(Cluster couchbaseCluster, Collection playlistCollection) {
		this.couchbaseCluster = couchbaseCluster;
		this.playlistCollection = playlistCollection;
	}

	@Override
	public Playlist create(String playlistId, Playlist playlist) {
		playlistCollection.insert(playlistId, playlist);
		return playlist;
	}

	@Override
	public Optional<Playlist> findById(String playlistId) {
		GetResult getResult = playlistCollection.get(playlistId);
		return Optional.of(getResult.contentAs(Playlist.class));
	}

	@Override
	public List<Playlist> findAllByUserId(String userId) {
		QueryResult queryResult = couchbaseCluster.query("select `playlist`.* from playlist where userId = $1", queryOptions().parameters(JsonArray.from(userId)));
		return queryResult.rowsAs(Playlist.class);
	}

	@Override
	public void delete(String playlistId) {
		playlistCollection.remove(playlistId);
	}

	@Override
	public void update(String playlistId, Playlist playlist) {
		playlistCollection.replace(playlistId, playlist);
	}
}
