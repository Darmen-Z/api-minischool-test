package com.minischool.shool.repository;

import com.minischool.shool.models.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    @Query(value = "SELECT * \n" +
            "FROM group_entity\n" +
            "ORDER BY group_entity.name\n", nativeQuery = true)
    List<GroupEntity> orderGroupsByName();
}
