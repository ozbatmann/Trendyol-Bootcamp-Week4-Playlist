# Trendyol-Bootcamp-Week4-Playlist
Trendyol Bootcamp Week 4 Playlist Homework

# Endpoints
Endpoints are designed by implementing best practices.
- `GET` **/v1/playlists**
- `POST` **/v1/playlists**
- `GET` **/v1/playlists/{id}**
- `DELETE` **/v1/playlists/{id}**
- `PATCH` **/v1/playlists/{id}**

In order to add/remove track `PATCH` endpoint should be used. As a best practise, I tried to implement `JsonPatch`.

Using following request bodies you can add or remove track.

**Track can be added to the end of tracks array `-`**

 `{ 
    "op": "add",
    "path":"/tracks/-",
    "value":{"name":"Track", "lengnth":"180", "artist":"Artist"}
  }`
  
**Track can be removed with the given index `0`**

 `{ 
    "op": "remove",
    "path":"/tracks/0"
  }`
  
# CouchBase
`Java SDK` is used as a CouchBase client. All the related configurations can be seen by reaching out *configuration* package.
