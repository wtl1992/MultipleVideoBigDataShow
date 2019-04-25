package model.wechat;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Music {
    private String Title;
    private String Description;
    private String MusicUrl;
    private String HQMusicUrl;
    private String ThumbMediaId;

    public Music() {
    }

    public Music(String title, String description, String musicUrl, String HQMusicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicUrl = musicUrl;
        this.HQMusicUrl = HQMusicUrl;
        ThumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @Override
    public String toString() {
        return "Music{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", MusicUrl='" + MusicUrl + '\'' +
                ", HQMusicUrl='" + HQMusicUrl + '\'' +
                ", ThumbMediaId='" + ThumbMediaId + '\'' +
                '}';
    }
}
