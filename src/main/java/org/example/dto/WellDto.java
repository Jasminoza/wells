package org.example.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WellDto {
    Long id;
    String name;
    private List<EquipmentDto> equipmentDtoList;
}
