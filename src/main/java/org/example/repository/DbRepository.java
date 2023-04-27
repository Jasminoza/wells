package org.example.repository;

import org.example.dto.DBInfo;

public interface DbRepository extends GenericRepository<DBInfo> {
    public DBInfo getAllWellsWithEquipments();
}
