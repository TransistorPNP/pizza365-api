package com.devcamp.menudrink.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.menudrink.models.Drink;

public interface IDrinkRepository extends JpaRepository<Drink, Integer> {

}
