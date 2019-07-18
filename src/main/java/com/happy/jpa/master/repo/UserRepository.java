package com.happy.jpa.master.repo;

import com.happy.jpa.master.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jun.wang
 * @title: UserRepository
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/3/2814:34
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select * from user", nativeQuery = true)
    List<User> getUserList();
}
