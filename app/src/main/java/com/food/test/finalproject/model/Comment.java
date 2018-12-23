package com.food.test.finalproject.model;

public class Comment {
    public String username;
    public String commentcontent;

    public Comment(String username,String commentcontent){
        this.username=username;
        this.commentcontent=commentcontent;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentcontent() {
        return commentcontent;
    }

    public void setCommentcontent(String commentcontent) {
        this.commentcontent = commentcontent;
    }
}
