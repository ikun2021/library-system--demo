package com.zk.repository;

import com.zk.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
