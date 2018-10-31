package com.chy.dao.repository;

import com.chy.dao.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chy on 2018/9/8.
 */
public interface Repository extends JpaRepository<Company, Long> {
}
