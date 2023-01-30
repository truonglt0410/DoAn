package com.edu.fpt.hoursemanager.management.assets.repository;

import com.edu.fpt.hoursemanager.management.assets.entity.TypeAssets;
import com.edu.fpt.hoursemanager.management.assets.model.response.GetTypeAssetsResponseFromQuery;
import com.edu.fpt.hoursemanager.management.assets.model.response.TypeAssetResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeAssetRepository extends JpaRepository<TypeAssets, Long> {
    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.GetTypeAssetsResponseFromQuery(t.id,t.name,t.code, a.id) from TypeAssets t left join t.assets a where t.deleted = false ")
    List<GetTypeAssetsResponseFromQuery> getAllTypeAssets();

    @Query("select t from TypeAssets t where t.id = :id and t.deleted = false ")
    TypeAssets getTypeAssetById(@Param("id") Long id);

    @Query("select t from TypeAssets t where t.idBuilding = :id and t.deleted = false ")
    List<TypeAssets> getTypeAssetsByBuilding(@Param("id") Long id);

    @Query("select new com.edu.fpt.hoursemanager.management.assets.model.response.TypeAssetResponse(t.id, t.name, t.code, t.idBuilding) from TypeAssets t where t.idBuilding = :id and t.deleted = false ")
    List<TypeAssetResponse> getTypeAssetsByBuilding1(@Param("id") Long id);
}
