package com.chenly.retry.spring;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chenly
 * @create 2021-03-21 17:52
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}