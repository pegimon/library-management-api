package com.pegimon.library.models.mappers;

public interface Mapper <T, U>{
    U map(T object);
    T reverse(U object);
}
