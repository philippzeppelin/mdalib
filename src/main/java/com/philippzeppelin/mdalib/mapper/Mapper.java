package com.philippzeppelin.mdalib.mapper;

public interface Mapper<F, T> {

    T mapToDto(F object);
}
