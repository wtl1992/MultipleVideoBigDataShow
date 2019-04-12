package model;

import java.io.Serializable;

public class MovieStar implements Serializable {
    private String uuid;

    private String movieuuid;

    private String name;

    private String href;

    private static final long serialVersionUID = 1L;

    public MovieStar(){}

    public MovieStar(String uuid, String movieuuid, String name, String href) {
        this.uuid = uuid;
        this.movieuuid = movieuuid;
        this.name = name;
        this.href = href;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getMovieuuid() {
        return movieuuid;
    }

    public void setMovieuuid(String movieuuid) {
        this.movieuuid = movieuuid == null ? null : movieuuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }
}