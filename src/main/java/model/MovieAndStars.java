package model;

import java.io.Serializable;
import java.util.List;

public class MovieAndStars implements Serializable {
    private String uuid;

    private String title;

    private String href;

    private String imgsrc;

    private String jinumber;

    private List<MovieStar> movieStars;

    private static final long serialVersionUID = 1L;

    public MovieAndStars(){}


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc == null ? null : imgsrc.trim();
    }

    public String getJinumber() {
        return jinumber;
    }

    public void setJinumber(String jinumber) {
        this.jinumber = jinumber == null ? null : jinumber.trim();
    }

    public List<MovieStar> getMovieStars() {
        return movieStars;
    }

    public void setMovieStars(List<MovieStar> movieStars) {
        this.movieStars = movieStars;
    }
}