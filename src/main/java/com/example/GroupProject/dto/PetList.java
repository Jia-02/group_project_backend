package com.example.GroupProject.dto;

public class PetList {

    private int catId;

    private String catName;

    private int age;

    private boolean catStatus;

    private String catImg;

    private String catInfo;

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isCatStatus() {
        return catStatus;
    }

    public void setCatStatus(boolean catStatus) {
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