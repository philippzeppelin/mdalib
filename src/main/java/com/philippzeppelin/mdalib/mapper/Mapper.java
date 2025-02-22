package com.philippzeppelin.mdalib.mapper;

public interface Mapper<F, T> {

    T map(F object); // GET / POST

    default T map(F fromOjbect, T toObject) { // PUT
        return toObject;
    }
}
