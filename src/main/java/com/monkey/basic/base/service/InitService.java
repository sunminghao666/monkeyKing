package com.monkey.basic.base.service;

import java.util.List;

import com.monkey.db.base.pojo.PwbDictionary;

public interface InitService {
    public List < PwbDictionary > loadDicBytype(String type) throws Exception;
}
