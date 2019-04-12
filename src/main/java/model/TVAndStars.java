package model;

import java.util.List;

public class TVAndStars {
    private String uuid;

    private String title;

    private String href;

    private String imgsrc;

    private String jinumber;

    private List<TvStar> tvStars;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getJinumber() {
        return jinumber;
    }

    public void setJinumber(String jinumber) {
        this.jinumber = jinumber;
    }

    public List<TvStar> getTvStars() {
        return tvStars;
    }

    public void setTvStars(List<TvStar> tvStars) {
        this.tvStars = tvStars;
    }
}
