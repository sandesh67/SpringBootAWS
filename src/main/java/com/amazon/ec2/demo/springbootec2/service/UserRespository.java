package com.amazon.ec2.demo.springbootec2.service;

import com.amazon.ec2.demo.springbootec2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRespository extends JpaRepository<User, Integer> {
}
