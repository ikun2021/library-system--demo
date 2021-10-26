package com.zk.repository;

import com.zk.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username, String password);
}
