package com.chy.service;

import com.chy.configuration.ds.DataSourceContextHolder;
import com.chy.configuration.ds.SlaveDataSource;
import com.chy.configuration.ds.TargetDataSource;
import com.chy.dao.entity.Company;
import com.chy.dao.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chy on 2018/9/8.
 */
@Service
public class CompanyService {

    @Autowired
    private Repository repository;

    @SlaveDataSource
    public List<Company> query(Company s){
        Example<Company> e = Example.of(s);
        return repository.findAll(e);
    }

    @TargetDataSource(value = DataSourceContextHolder.MasterDataSource)
    public List<Company> query1(Company s){
        Example<Company> e = Example.of(s);
        return repository.findAll(e);
    }

}
