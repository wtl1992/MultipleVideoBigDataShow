package model.baiduyunwangpan;

public class BaiDuYunWangPan {
    private String blink;
    private String des;
    private String host;
    private String link;
    private String more;
    private String title;
    private int pageCount;

    public BaiDuYunWangPan() {
    }

    public BaiDuYunWangPan(String blink, String des, String host, String link, String more, String title, int pageCount) {
        this.blink = blink;
        this.des = des;
        this.host = host;
        this.link = link;
        this.more = more;
        this.title = title;
        this.pageCount = pageCount;
    }

    public String getBlink() {
        return blink;
    }

    public void setBlink(String blink) {
        this.blink = blink;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
