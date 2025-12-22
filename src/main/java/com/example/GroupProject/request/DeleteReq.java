package com.example.GroupProject.request;

import java.util.List;

import com.example.GroupProject.constants.ConstantsMessage;

import jakarta.validation.constraints.NotEmpty;

public class DeleteReq {

    @NotEmpty(message = ConstantsMessage.PET_ID_LIST_IS_EMPTY)
    private List<Integer> petIdList;

    public List<Integer> getPetIdList() {
        return petIdList;
    }

    public void setPetIdLst(List<Integer> petIdList) {
        this.petIdList = petIdList;
    }

}