package com.devcamp.menudrink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.devcamp.menudrink.models.Menu;

@Repository
@Component
public interface IMenuRepository extends JpaRepository<Menu, Integer> {
}
