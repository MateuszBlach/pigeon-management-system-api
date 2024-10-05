package com.pigeon_management_system_api.mappers;

import com.pigeon_management_system_api.dto.PigeonDTO;
import com.pigeon_management_system_api.model.Pigeon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PigeonMapper {

    PigeonDTO toPigeonDTO(Pigeon pigeon);

    Pigeon toPigeon(PigeonDTO pigeonDTO);

    List<PigeonDTO> toPigeonDTOList(List<Pigeon> pigeonList);
}
