package model;

import java.io.Serializable;

public class HeadImage implements Serializable {
    private String uuid;

    private String headimagepath;

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getHeadimagepath() {
        return headimagepath;
    }

    public void setHeadimagepath(String headimagepath) {
        this.headimagepath = headimagepath == null ? null : headimagepath.trim();
    }
}