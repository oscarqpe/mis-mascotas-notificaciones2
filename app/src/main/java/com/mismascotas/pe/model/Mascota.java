package com.mismascotas.pe.model;

/**
 * Created by oscarqpe on 22/07/17.
 */

public class Mascota {
    private String id;
    private String nombreCompleto;
    private String urlFoto;
    private int likes;
    private String mediaId;

    public Mascota(String id, String nombreCompleto, String urlFoto, int likes) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.urlFoto = urlFoto;
        this.likes = likes;
    }

    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
