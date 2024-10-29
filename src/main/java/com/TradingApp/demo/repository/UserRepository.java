package com.TradingApp.demo.repository;

import com.TradingApp.demo.modal.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
