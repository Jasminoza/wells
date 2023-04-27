package org.example.view;

import org.example.controller.EquipmentController;
import org.example.controller.WellController;
import org.example.model.Well;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WellView {
    private final Scanner scanner;
    private final WellController wellController;
    private final EquipmentController equipmentController;

    public WellView() {
        this.scanner = new Scanner(System.in);
        this.wellController = new WellController();
        this.equipmentController = new EquipmentController();
    }

    public void showEquipmentCountByWells() {
        System.out.println("Please enter wells names with delimiter ' ' or ','");

        String inputString = scanner.nextLine();

        String[] wellsStrings = inputString.split("[ ,]+");

        Map<String, Long> wellCounts = new HashMap<>();

        Arrays.stream(wellsStrings).forEach(wellName -> {

            Well wellFromRepo = wellController.getWellByName(wellName);
            if (wellFromRepo.getId() != 0) {
                Long equipmentCount = equipmentController.getAllEquipments()
                        .stream()
                        .filter(eq -> wellFromRepo.getId().equals(eq.getWellId()))
                        .count();

                wellCounts.put(wellName, equipmentCount);
            } else {
                System.out.printf("Well with name '%s' did not found, skipping.%n", wellName);
            }
        });

        if (wellCounts.size() > 0) {
            System.out.println("Table of wells names and equipment count on it:");
            System.out.println(wellCounts);
        } else {
            System.out.println("No wells found.");
        }
    }
}
