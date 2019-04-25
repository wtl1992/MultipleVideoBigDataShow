package model.wechat;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class OutMsgEntity {
    private String ToUserName;
    private String FromUserName;
    private Long CreateTime;
    private String MsgType;
    private String Content;

    @XmlElementWrapper(name = "Image")
    private String [] MediaId;//媒体id数组

    @XmlElementWrapper(name = "Voice")
    @XmlElement(name = "MediaId")
    private String [] media_id_voice;


    private Integer ArticleCount;
    @XmlElementWrapper(name = "Articles")
    private ArticleItem [] item;


    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String[] getMediaId() {
        return MediaId;
    }

    public void setMediaId(String[] mediaId) {
        MediaId = mediaId;
    }

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public ArticleItem[] getItem() {
        return item;
    }

    public void setItem(ArticleItem[] item) {
        this.item = item;
    }

    public String[] getMedia_id_voice() {
        return media_id_voice;
    }

    public void setMedia_id_voice(String[] media_id_voice) {
        this.media_id_voice = media_id_voice;
    }

}
