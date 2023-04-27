package org.example.controller;

import org.example.model.Well;
import org.example.service.WellService;

public class WellController {
    private final WellService wellService;

    public WellController() {
        this.wellService = new WellService();
    }

    public Well createWell(String name) {
        Well well = new Well();
        well.setName(name);
        return wellService.create(well);
    }

    public Well getWellByName(String wellName) {
        return wellService.getWellByName(wellName);
    }
}
