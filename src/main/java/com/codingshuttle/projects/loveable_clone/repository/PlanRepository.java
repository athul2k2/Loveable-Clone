package com.codingshuttle.projects.loveable_clone.repository;

import com.codingshuttle.projects.loveable_clone.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
