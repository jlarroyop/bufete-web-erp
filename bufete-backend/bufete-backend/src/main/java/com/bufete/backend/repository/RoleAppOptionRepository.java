package com.bufete.backend.repository;

import java.util.List;

import com.bufete.backend.model.AppOption;
import com.bufete.backend.model.RoleAppOption;
import com.bufete.backend.model.RoleOptionKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAppOptionRepository extends JpaRepository<RoleAppOption, RoleOptionKey> {

  @Query("SELECT ra.appOption FROM RoleAppOption ra WHERE ra.roleOption.id = :roleId AND ra.appOption.status = 'ACTIVE'")
  List<AppOption> getAppOptionByRoleId(@Param("roleId") Long roleId);

  @Query("SELECT ao FROM RoleAppOption ra LEFT JOIN AppOption ao ON ao.id = ra.appOption.id OR ao.parent_app_option.id = ra.appOption.id WHERE ra.roleOption.id IN (:roleIds) AND ra.roleOption.status = 'ACTIVE' AND ra.appOption.status = 'ACTIVE'")
  List<AppOption> getAppOotionByRoleIds(@Param("roleIds") List<Long> roleIds);
}