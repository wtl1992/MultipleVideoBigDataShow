package model;

import java.io.Serializable;

public class Idcard implements Serializable {
    private String uuid;

    private String name;

    private String idcard;

    private String todate;

    private String address;

    //签发机关
    private String signingandissuingorganization;

    private String frontidcardimagepath;

    private String backidcardimagepath;

    private static final long serialVersionUID = 1L;

    public Idcard() {
    }

    public Idcard(String uuid, String name, String idcard, String todate, String address, String signingandissuingorganization, String frontidcardimagepath, String backidcardimagepath) {
        this.uuid = uuid;
        this.name = name;
        this.idcard = idcard;
        this.todate = todate;
        this.address = address;
        this.signingandissuingorganization = signingandissuingorganization;
        this.frontidcardimagepath = frontidcardimagepath;
        this.backidcardimagepath = backidcardimagepath;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate == null ? null : todate.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getSigningandissuingorganization() {
        return signingandissuingorganization;
    }

    public void setSigningandissuingorganization(String signingandissuingorganization) {
        this.signingandissuingorganization = signingandissuingorganization == null ? null : signingandissuingorganization.trim();
    }

    public String getFrontidcardimagepath() {
        return frontidcardimagepath;
    }

    public void setFrontidcardimagepath(String frontidcardimagepath) {
        this.frontidcardimagepath = frontidcardimagepath == null ? null : frontidcardimagepath.trim();
    }

    public String getBackidcardimagepath() {
        return backidcardimagepath;
    }

    public void setBackidcardimagepath(String backidcardimagepath) {
        this.backidcardimagepath = backidcardimagepath == null ? null : backidcardimagepath.trim();
    }
}