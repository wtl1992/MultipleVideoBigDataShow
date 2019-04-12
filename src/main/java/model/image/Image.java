package model.image;

public class Image {
    private String bdImgnewsDate;
    private String fromPageTitle;
    private String fromPageTitleEnc;
    private String fromURL;
    private String fromURLHost;
    private String middleURL;
    private String thumbURL;
    private String type;
    private int width;

    public Image() {
    }

    public Image(String bdImgnewsDate, String fromPageTitle, String fromPageTitleEnc, String fromURL, String fromURLHost, String middleURL, String thumbURL, String type, int width) {
        this.bdImgnewsDate = bdImgnewsDate;
        this.fromPageTitle = fromPageTitle;
        this.fromPageTitleEnc = fromPageTitleEnc;
        this.fromURL = fromURL;
        this.fromURLHost = fromURLHost;
        this.middleURL = middleURL;
        this.thumbURL = thumbURL;
        this.type = type;
        this.width = width;
    }

    public String getBdImgnewsDate() {
        return bdImgnewsDate;
    }

    public void setBdImgnewsDate(String bdImgnewsDate) {
        this.bdImgnewsDate = bdImgnewsDate;
    }

    public String getFromPageTitle() {
        return fromPageTitle;
    }

    public void setFromPageTitle(String fromPageTitle) {
        this.fromPageTitle = fromPageTitle;
    }

    public String getFromPageTitleEnc() {
        return fromPageTitleEnc;
    }

    public void setFromPageTitleEnc(String fromPageTitleEnc) {
        this.fromPageTitleEnc = fromPageTitleEnc;
    }

    public String getFromURL() {
        return fromURL;
    }

    public void setFromURL(String fromURL) {
        this.fromURL = fromURL;
    }

    public String getFromURLHost() {
        return fromURLHost;
    }

    public void setFromURLHost(String fromURLHost) {
        this.fromURLHost = fromURLHost;
    }

    public String getMiddleURL() {
        return middleURL;
    }

    public void setMiddleURL(String middleURL) {
        this.middleURL = middleURL;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
