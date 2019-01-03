package com.valuequo.buckswise.service.dto;

import org.springframework.stereotype.Component;

@Component
public class GoogledriveDTO {

    private Long id;
    private Long tid;
    private String type;
    private Long uid;
    private String fileweblink;
    private String deleteweblink;
    private String filename;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the tid
     */
    public Long getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the userid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return the fileweblink
     */
    public String getFileweblink() {
        return fileweblink;
    }

    /**
     * @param fileweblink the fileweblink to set
     */
    public void setFileweblink(String fileweblink) {
        this.fileweblink = fileweblink;
    }

    /**
     * @return the deleteweblink
     */
    public String getDeleteweblink() {
        return deleteweblink;
    }

    /**
     * @param deleteweblink the deleteweblink to set
     */
    public void setDeleteweblink(String deleteweblink) {
        this.deleteweblink = deleteweblink;
    }

    /**
     * @return the fileName
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }


}