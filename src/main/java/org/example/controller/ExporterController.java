package org.example.controller;

import org.example.dto.DBInfo;
import org.example.service.ExporterService;

public class ExporterController {
    private final ExporterService exporterService;

    public ExporterController() {
        this.exporterService = new ExporterService();
    }

    public DBInfo getAllWellsWithEquipments() {
        return exporterService.getAllWellsWithEquipments();
    }
}
