package com.example.pruebarappi.Models;

/**
 * @Autor: Luis Jimenez
 * @Creado el: 13/01/2017
 * @Descripcion Modelo para cargar los post en el list view
 * @Version 1.0
 */
public class PostToList {

    private String postId;
    private String postName;
    private String postDateTime;

    public PostToList(String postId, String postName, String postDateTime) {
        this.postId = postId;
        this.postName = postName;
        this.postDateTime = postDateTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(String postDateTime) {
        this.postDateTime = postDateTime;
    }
}
