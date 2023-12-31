package com.example.myproject.model;

public class Site {
    String id;
    String label;
    String description;
    String image;
    String video;

    public Site() {
    }

    public Site(String id, String label, String description, String image, String video) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.image = image;
        this.video = video;
    }

    public Site(String label, String description, String image, String video) {
        this.label = label;
        this.description = description;
        this.image = image;
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
