package com.tommy.service;

import com.tommy.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by tommy on 2020/4/18 12:07
 */
@Service
public interface TypeService {
    int insertType(Type type);
    Type getType(Integer id);
    List<Type> listType();
    int updateType(Type type);
    int deleteType(Integer id);
    Type getTypeByName(String name);
}
