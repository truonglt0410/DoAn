package com.edu.fpt.hoursemanager.management.assets.repository;

import com.edu.fpt.hoursemanager.management.assets.entity.Assets;
import com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Assets, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse(a.id,a.name,a.color,a.status,a.model, ta.id) from Assets a left join a.typeAssets ta where a.deleted = false ")
    List<AssetResponse> getAllAssets();

    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse(a.id,a.name,a.color,a.status,a.model, ta.id) from Assets a inner join a.typeAssets ta where a.deleted = false and ta.id = :id")
    List<AssetResponse> getAllAssetsByTypeAsset(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse(a.id,a.name,a.color,a.status,a.model, ta.id) from Assets a inner join a.room r left join a.typeAssets ta where a.deleted = false and r.deleted = false and r.id is null")
    List<AssetResponse> getAllAssetsNotRoom();

    @Query("select a from Assets a inner join a.room r where r.id = :id")
    List<Assets> getAllAssetByRoom(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.AssetResponse(a.id,a.name,a.color,a.status,a.model, ta.id) " +
            "from Assets a inner join a.room r left join a.typeAssets ta where r.id = :id")
    List<AssetResponse> getAssetByRoom(@Param("id") Long id);

    @Query("select a from Assets a where a.id in :id")
    List<Assets> getAllAssetById(@Param("id") List<Long> id);


}
