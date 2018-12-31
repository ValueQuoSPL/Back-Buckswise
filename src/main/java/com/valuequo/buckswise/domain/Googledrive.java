package com.valuequo.buckswise.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;

@Entity
@Table(name = "googledrivefile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Googledrive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tid")
    private Long tid;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "type")
    private String type;

    @Column(name = "fileweblink")
    private String fileweblink;

    @Column(name = "deleteweblink")
    private String deleteweblink;
    
    @Column(name = "filename")
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
     * @return the uid
     */
    public Long getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(Long uid) {
        this.uid = uid;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Googledrive googledrive = (Googledrive) o;
        if (googledrive.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), googledrive.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
}