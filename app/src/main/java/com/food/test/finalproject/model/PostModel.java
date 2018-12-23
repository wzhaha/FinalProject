package com.food.test.finalproject.model;

public class PostModel {
    public String handle;
    public String description;
    public String createdAt;
    public String image;
    public String haedImg;
    public int like;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHaedImg() {
        return haedImg;
    }

    public void setHaedImg(String haedImg) {
        this.haedImg = haedImg;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
