package com.example.GroupProject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.GroupProject.entity.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "insert into product(workstation_id,type,name,description,price,image_url,active)" //
			+ " values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
	public void addProduct(int workStationId,String type,String name,String description,int price,String imageUrl,boolean active);
	
	@Query(value = "select * from product" ,nativeQuery = true)
	public List<Product> getProductList();

}
