package com.chy.service.impl;

import com.chy.dao.ds1.Index1Mapper;
import com.chy.dao.ds2.Index2Mapper;
import com.chy.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by chy on 2017/5/6.
 */
@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private Index1Mapper index1Mapper;

    @Autowired
    private Index2Mapper index2Mapper;

    public int get() {
        return index1Mapper.get() + index2Mapper.get();
    }

    @Transactional(rollbackOn={RuntimeException.class})
    public int update()  throws Exception{

        int cnt = index1Mapper.update();
        if(true) throw new RuntimeException();    //回滚
//        if(true) throw new Exception();             //不回滚
        cnt += index2Mapper.update();

        return cnt;
    }

}
