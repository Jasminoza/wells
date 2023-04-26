package org.example.view;

import org.example.controller.EquipmentController;
import org.example.controller.WellController;
import org.example.model.Equipment;
import org.example.model.Well;

import java.util.List;
import java.util.Scanner;

public class EquipmentView {
    private final Scanner scanner;
    private final EquipmentController equipmentController;
    private final WellController wellController;
    private final WellView wellView;

    public EquipmentView() {
        this.scanner = new Scanner(System.in);
        this.equipmentController = new EquipmentController();
        this.wellController = new WellController();
        this.wellView = new WellView();
    }

    public void showAllEquipments() {
        List<Equipment> equipments = equipmentController.getAllEquipments();

        if (equipments.isEmpty()) {
            printEquipmentsListIsEmpty();
        } else {
            printEquipmentsListInfo(equipments);
        }
    }

    public void getEquipmentById() {
        System.out.println("NOT IMPLEMENTED");
    }

    public void createEquipment() {
        List<Equipment> equipments = equipmentController.getAllEquipments();
        createNewEquipment(equipments);
    }

    public void deleteEquipment() {
        System.out.println("NOT IMPLEMENTED");
    }

    public void updateEquipment() {
        System.out.println("NOT IMPLEMENTED");
    }

    private static void printEquipmentsListIsEmpty() {
        System.out.println("Equipments list is empty.");
    }

    private static void printEquipmentsListInfo(List<Equipment> equipments) {
        System.out.println("Equipments:\n===============================");
        equipments.forEach(EquipmentView::printEquipmentInfo);
        System.out.println("===============================");
    }

    private static void printEquipmentInfo(Equipment equipment) {
        System.out.println(equipment.toString());
    }

    private void createNewEquipment(List<Equipment> equipments) {
        Well well = wellView.tryToGetWillById();

        if (well.getId() == null) {
            System.out.println("Cannot create equipment, wells list is empty");
            return;
        }

        createEquipmentsForWell(equipments, well);
    }

    private void createEquipmentsForWell(List<Equipment> equipments, Well well) {
        System.out.println("Enter equipment name: ");

        boolean nameIsAlreadyExist = true;
        String name;

        while (nameIsAlreadyExist) {
            name = scanner.nextLine();
            final String finalName = name;
            if (equipments.stream().anyMatch(equipment -> equipment.getName().equalsIgnoreCase(finalName))) {
                System.out.println("Equipment with such name is already exist. Please, enter another equipment name.");
            } else {
                nameIsAlreadyExist = false;
                Equipment equipment = equipmentController.createEquipment(name, well);
                System.out.print("Created equipment: ");
                printEquipmentInfo(equipment);
            }
        }
    }

    public void createMultiplyEquipment() {
        System.out.println("Enter well name: ");
        String wellName = scanner.nextLine();
        Well well = wellController.getWellByName(wellName);

        System.out.println("Please, enter number of equipments you want to add to well: " + well.getName());

        long neededEquipmentCount;
        boolean numberIsCorrect = false;

        while (!numberIsCorrect) {
            try {
                neededEquipmentCount = Long.parseLong(scanner.nextLine());

                if (neededEquipmentCount <= 0) {
                    System.out.println("Please, enter positive number.");
                    return;
                }

                if (neededEquipmentCount > 10) {
                    System.out.println("You cannot create more then 10 equipments at once.");
                    return;
                }

                numberIsCorrect = true;
                List<Equipment> createdEquipments = equipmentController.createMultiplyEquipments(neededEquipmentCount, well);
                System.out.printf("Created equipments at well '%s':%n", well.getName());
                printEquipmentsListInfo(createdEquipments);

            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number.");
            }
        }
    }
}