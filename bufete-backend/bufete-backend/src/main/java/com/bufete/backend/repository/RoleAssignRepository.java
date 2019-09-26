package com.bufete.backend.repository;

import java.util.List;
import java.util.Optional;

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

  @Query("SELECT COUNT(ra.role.id) FROM RoleAssign ra WHERE ra.user.id = :userId AND ra.company.id = :companyId  AND ra.user.status = 'ACTIVE' and ra.company.status = 'ACTIVE'")
  long countRolesByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);

  @Query("SELECT ra.role FROM RoleAssign ra WHERE ra.user.id = :userId AND ra.company.id = :companyId AND ra.user.status = 'ACTIVE' and ra.company.status = 'ACTIVE'")
  List<Role> getRolesByUserIdAndCompanyId(@Param("userId") Long userId, @Param("companyId") Long companyId);

  @Query("SELECT ra.user FROM RoleAssign ra WHERE ra.user.username = :username AND ra.company.id = :companyId AND ra.user.status = 'ACTIVE' and ra.company.status = 'ACTIVE'")
  Optional<User> findUserByUsernameAndCompany(@Param("username") String username, @Param("companyId") Long companyId);

}