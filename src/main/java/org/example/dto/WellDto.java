package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WellDto {
    Long id;
    String name;
    private List<EquipmentDto> equipmentDtoList;
}
