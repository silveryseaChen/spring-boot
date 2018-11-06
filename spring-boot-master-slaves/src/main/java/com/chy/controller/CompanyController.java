package com.chy.controller;

import com.chy.dao.entity.Company;
import com.chy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chy on 2018/10/31.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/list")
    public List<Company> list(Company company){
        List<Company> list = companyService.query(company);
        list.addAll(companyService.query1(company));
        return list;
    }



}
