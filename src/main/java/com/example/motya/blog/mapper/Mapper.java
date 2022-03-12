package com.example.motya.blog.mapper;

public interface Mapper<F, T> {

    T mapFrom(F object);
}
