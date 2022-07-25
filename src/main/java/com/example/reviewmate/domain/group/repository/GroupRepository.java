package com.example.reviewmate.domain.group.repository;

import com.example.reviewmate.domain.group.entity.GroupVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupVO,Long> {
}
