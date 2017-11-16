package com.becheer.donation.model.extension.member;

/*
* MemberInfoExtension
* Creator : xiaokepu
* Date : 2017-09-27
*/

import com.becheer.donation.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class MemberInfoExtension implements Serializable {
    private long id;

    private String name;

    private int role;

    private String mobile;

    private String idCard;

    private int sex;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
//    private Date birthday;
    private String birthday;

    private String project;

    private String summary;

    private int validation;

    private String avator;

    private String organizationType;

    private String organizationCode;

    private String license;

    private String idCardFront;

    private String idCardBack;

    //身份证
    private String id_card_before;

    private String id_card_after;

    private String id_card_birthday;

    public String getid_card_birthday() {
        return id_card_birthday;
    }

    public void setid_card_birthday(String id_card_birthday) {
        this.id_card_birthday = id_card_birthday;
    }

    public String getId_card_before() {
        return id_card_before;
    }

    public void setId_card_before(String id_card_before) {
        this.id_card_before = id_card_before;
    }

    public String getId_card_after() {
        return id_card_after;
    }

    public void setId_card_after(String id_card_after) {
        this.id_card_after = id_card_after;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

//    public Date getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(Date birthday) {
//        this.birthday = birthday;
//    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getIdCardFront() {
        return idCardFront;
    }

    public void setIdCardFront(String idCardFront) {
        this.idCardFront = idCardFront;
    }

    public String getIdCardBack() {
        return idCardBack;
    }

    public void setIdCardBack(String idCardBack) {
        this.idCardBack = idCardBack;
    }
}
