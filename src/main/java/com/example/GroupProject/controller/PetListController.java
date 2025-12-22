package com.example.GroupProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GroupProject.request.CreateUpdateReq;
import com.example.GroupProject.request.DeleteReq;
import com.example.GroupProject.response.BasicRes;
import com.example.GroupProject.response.PetListRes;
import com.example.GroupProject.service.PetListService;

@CrossOrigin 
@RestController
public class PetListController {

    @Autowired
    private PetListService petListService;

    // 用List裝寵物資訊，就這樣
    public void petlist() {};

    @PostMapping(value = "pet/create")
    public BasicRes addpetinfo(@RequestBody CreateUpdateReq req) {
        return petListService.addpetinfo(req.getName(), req.getAge(), req.isCatStatus(), //
                req.getCatImg(), req.getCatInfo());
    };

    @PostMapping(value = "pet/update")
    public BasicRes updatepetinfo(@RequestBody CreateUpdateReq req) {
        return petListService.updatepetinfo(req.getCatId(), req.getName(), req.getAge(), //
                req.isCatStatus(), req.getCatImg(), req.getCatInfo());
    };

    @GetMapping(value = "pet/search")
    public PetListRes searchpetinfo(@RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "age") Integer age,
            @RequestParam(required = false, name = "catStatus") Boolean catStatus,
            @RequestParam(required = false, name = "catInfo") String catInfo) {
        // Service 層方法簽名需要與此處的參數類型匹配 (Integer, Boolean)
        return petListService.searchpetinfo(name, age, catStatus, catInfo);
    }

    @PostMapping(value = "pet/delete")
    public BasicRes deleteByPetId(@RequestBody DeleteReq req) throws Exception {
        return petListService.deleteByPetId(req.getPetIdList());
    };


}