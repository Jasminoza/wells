package org.example.util;

import org.example.model.Entity;

import java.util.List;

public class PrintUtils {

    private PrintUtils() {

    }

    public static void printListInfo(List<? extends Entity> wells) {
        System.out.println("Wells:\n===============================");
        wells.forEach(PrintUtils::printEntityInfo);
        System.out.println("===============================");
    }

    public static void printEntityInfo(Entity entity) {
        System.out.println(entity.toString());
    }

    public static void printEquipmentsListIsEmpty() {
        System.out.println("Equipments list is empty.");
    }

    public static void printWellsListIsEmpty() {
        System.out.println("Wells list is empty.");
    }
}
