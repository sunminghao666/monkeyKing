package com.monkey.basic.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkey.basic.base.service.InitService;
import com.monkey.db.base.dao.PwbDictionaryMapper;
import com.monkey.db.base.pojo.PwbDictionary;

@Service
public class InitServiceImpl implements InitService {
    @Autowired
    private PwbDictionaryMapper pwbDictionaryMapper;

    @Override
    public List < PwbDictionary > loadDicBytype(String type) throws Exception {
        return pwbDictionaryMapper.selectByType(type);
    }

}
