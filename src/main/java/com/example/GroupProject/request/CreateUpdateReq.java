package com.example.GroupProject.request;

public class CreateUpdateReq {

    private int catId;

    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
