package com.cyh.dao;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class BookDao {

    private String label = "default";

    @Override
    public String toString() {
        return "BookDao{" + "label='" + label + '\'' + '}';
    }
}
