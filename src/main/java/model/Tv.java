package model;

import java.io.Serializable;

public class Tv implements Serializable {
    private String uuid;

    private String title;

    private String href;

    private String imgsrc;

    private String jinumber;

    private static final long serialVersionUID = 1L;

    public Tv(){}

    public Tv(String uuid, String title, String href, String imgsrc, String jinumber) {
        this.uuid = uuid;
        this.title = title;
        this.href = href;
        this.imgsrc = imgsrc;
        this.jinumber = jinumber;
    }

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
}