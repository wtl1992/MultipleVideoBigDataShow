package model.music;

public class Music {
    private String id;
    private String lrc;
    private String name;
    private String pic;
    private String singer;
    private String url;

    public Music() {
    }

    public Music(String id, String lrc, String name, String pic, String singer, String url) {
        this.id = id;
        this.lrc = lrc;
        this.name = name;
        this.pic = pic;
        this.singer = singer;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLrc() {
        return lrc;
    }

    public void setLrc(String lrc) {
        this.lrc = lrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
