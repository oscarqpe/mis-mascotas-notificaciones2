package com.mismascotas.pe.restapi.model;

/**
 * Created by oscarqpe on 07/08/17.
 */

public class MediaResponse {
    private String id;
    private int likes;
    private String type;

    public MediaResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
