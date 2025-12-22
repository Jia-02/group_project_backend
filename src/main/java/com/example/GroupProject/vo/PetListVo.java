package com.example.GroupProject.vo;

public class PetListVo {
    private Integer catId;

    private String catName;

    private Integer age;

    private Boolean catStatus;

    private String catImg;

    private String catInfo;

    public PetListVo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(Boolean catStatus) {
        this.catStatus = catStatus;
    }

    public String getCatImg() {
        return catImg;
    }

    public void setCatImg(String catImg) {
        this.catImg = catImg;
    }

    public String getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(String catInfo) {
        this.catInfo = catInfo;
    }

}