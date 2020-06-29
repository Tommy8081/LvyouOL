package com.tommy.service.impl;

import com.tommy.entity.Type;
import com.tommy.mapper.TypeMapper;
import com.tommy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tommy on 2020/4/18 12:10
 */
@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeMapper typeMapper;

    @Override
    public int insertType(Type type) {
        return typeMapper.insert(type);
    }

    @Override
    public Type getType(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Type> listType() {
        return typeMapper.selectAllType();
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKey(type);
    }

    @Override
    public int deleteType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.selectByName(name);
    }
}
