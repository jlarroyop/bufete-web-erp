package com.bufete.backend.repository;

import java.util.List;

import com.bufete.backend.model.Role;
import com.bufete.backend.model.RoleAssign;
import com.bufete.backend.model.RoleOptionKey;
import com.bufete.backend.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleAssignRepository extends JpaRepository<RoleAssign, RoleOptionKey> {

  @Query("SELECT COUNT(ra.role.id) FROM RoleAssign ra WHERE ra.user.id = :userId AND ra.company.id = :companyId")
  long countRolesByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);

  @Query("SELECT ra.role FROM RoleAssign ra WHERE ra.user.id = :userId AND ra.company.id = :companyId")
  List<Role> getRolesByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);

  @Query("SELECT ra.user FROM RoleAssign ra WHERE ra.user.username = :username AND ra.company.id = :companyId")
  User findUserByUsernameAndCompany(@Param("username") String username, @Param("companyId") Long companyId);
}