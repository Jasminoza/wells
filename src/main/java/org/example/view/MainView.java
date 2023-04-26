package org.example.view;

import java.util.Scanner;

public class MainView {
    private final WellView wellView;
    private final EquipmentView equipmentView;
    private final Scanner sc;

    public MainView() {
        this.wellView = new WellView();
        this.equipmentView = new EquipmentView();
        this.sc = new Scanner(System.in);
    }

    public void mainMenu() {
        String choice = "";
        while (!"0".equals(choice)) {
            System.out.println("""
                    \nHello. Enter a digit from the following:\s
                    1. to see all wells.            11. to see all equipments.\s
                    2. to show well by its id.      12. to show equipment by its id.\s
                    3. to add a well.               13. to add an equipment.\s
                    4. to delete a well.            14. to delete an equipment.\s
                    5. to update well.              15. to update an equipment.\s
                    6. to create .....              16. to create multiply equipments at well.\s
                                        

                    0. to exit the program.
                    """);

            choice = sc.nextLine();
            switch (choice) {

                case "1" -> wellView.showAllWells();
                case "2" -> wellView.getWellById();
                case "3" -> wellView.createWell();
                case "4" -> wellView.deleteWell();
                case "5" -> wellView.updateWell();

                case "11" -> equipmentView.showAllEquipments();
                case "12" -> equipmentView.getEquipmentById();
                case "13" -> equipmentView.createEquipment();
                case "14" -> equipmentView.deleteEquipment();
                case "15" -> equipmentView.updateEquipment();
                case "16" -> equipmentView.createMultiplyEquipment();

                case "0" -> {
                    System.out.println("Goodbye.");
                    System.exit(0);
                }
                default -> System.out.println("Please, enter a correct digit.");
            }
        }
    }
}
