package com.example.GroupProject.response;

import java.util.List;

import com.example.GroupProject.vo.PetListVo;

public class PetListRes extends BasicRes {

    private List<PetListVo> petList;

    public PetListRes() {
        super();
    }

    public PetListRes(int code, String message) {
        super(code, message);
    }

    public PetListRes(int code, String message, List<PetListVo> petList) {
        super(code, message);
        this.petList = petList;
    }

    public List<PetListVo> getPetList() {
        return petList;
    }

    public void setPetList(List<PetListVo> petList) {
        this.petList = petList;
    }

}