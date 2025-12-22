package com.example.GroupProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.GroupProject.vo.PetListVo;

@Mapper
public interface PetListDao {

    // 1. CREATE: 新增寵物資訊
    public void addpetinfo(//
            @Param("inputName") String name, //
            @Param("inputAge") int age, //
            @Param("inputStatus") boolean catStatus, //
            @Param("inputCatImg") String catImg, //
            @Param("inputCatInfo") String catInfo);

    // 2. 輔助方法：檢查 ID 是否存在或查詢單筆
    public int getByCatId(@Param("catId") int catId);

    // 3. UPDATE: 更新寵物資訊
    public void updatepetinfo(@Param("inputId") int catId, //
            @Param("inputName") String name, //
            @Param("inputAge") int age, //
            @Param("inputStatus") boolean catStatus, //
            @Param("inputCatImg") String catImg, //
            @Param("inputCatInfo") String catInfo);

    // 4. DELETE: 刪除寵物資訊 - 返回 int (影響的行數)
    public void deleteByPetId(@Param("catList") List<Integer> petIdList);

    // 3. SEARCH: 根據條件查詢列表 - 返回 List<Pet>
    public List<PetListVo> searchpetinfo(@Param("inputName") String name, @Param("inputAge") Integer age,
            @Param("inputStatus") Boolean catStatus, @Param("inputCatInfo") String catInfo);


}