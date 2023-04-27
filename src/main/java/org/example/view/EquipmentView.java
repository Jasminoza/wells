package org.example.view;

import org.example.controller.EquipmentController;
import org.example.controller.WellController;
import org.example.model.Equipment;
import org.example.model.Well;

import java.util.List;
import java.util.Scanner;

import static org.example.util.PrintUtils.printListInfo;

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

    public void createMultiplyEquipment() {
        System.out.println("Enter well name: ");
        String wellName = scanner.nextLine();

        System.out.println("Please, enter number of equipments you want to add to well: " + wellName);

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
                Well wellForEquipment = getWellForEquipment(wellName);
                List<Equipment> createdEquipments = equipmentController.createMultiplyEquipments(neededEquipmentCount, wellForEquipment.getId());

                System.out.printf("Created equipments at well '%s':%n", wellForEquipment.getName());
                printListInfo(createdEquipments);

            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number.");
            }
        }
    }

    private Well getWellForEquipment(String wellName) {
        Well wellFromRepo = wellController.getWellByName(wellName);
        return wellFromRepo.getId() != 0 ? wellFromRepo : wellController.createWell(wellName);
    }
}