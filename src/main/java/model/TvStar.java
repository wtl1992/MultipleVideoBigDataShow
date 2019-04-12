package model;

import java.io.Serializable;

public class TvStar implements Serializable {
    private String uuid;

    private String tvuuid;

    private String name;

    private String href;

    private static final long serialVersionUID = 1L;

    public TvStar(){}

    public TvStar(String uuid, String tvuuid, String name, String href) {
        this.uuid = uuid;
        this.tvuuid = tvuuid;
        this.name = name;
        this.href = href;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getTvuuid() {
        return tvuuid;
    }

    public void setTvuuid(String tvuuid) {
        this.tvuuid = tvuuid == null ? null : tvuuid.trim();
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

    @Override
    public String toString() {
        return "TvStar{" +
                "uuid='" + uuid + '\'' +
                ", tvuuid='" + tvuuid + '\'' +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}