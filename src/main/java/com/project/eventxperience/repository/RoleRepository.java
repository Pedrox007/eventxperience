package com.project.eventxperience.repository;

import com.project.eventxperience.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

    @Query(nativeQuery = true, value = "select dr.* from db_user as u join user_role ur on (ur.user_id = u.id) join db_role dr on(dr.id = ur.role_id) where u.username = :name")
    //@Query("select User.roles from User where User.username = ?1")
    List<Role> findRoleByUserName(@Param("name") String name);
}
