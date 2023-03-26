package com.example.springbootstartertestmvc.mapper;

public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject){
        return toObject;
    }
}
