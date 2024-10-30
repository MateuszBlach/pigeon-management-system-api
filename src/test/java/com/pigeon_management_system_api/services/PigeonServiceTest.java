package com.pigeon_management_system_api.services;

import com.pigeon_management_system_api.dto.PigeonDTO;
import com.pigeon_management_system_api.mappers.PigeonMapper;
import com.pigeon_management_system_api.model.Pigeon;
import com.pigeon_management_system_api.repository.PigeonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PigeonServiceTest {

    @Mock
    private PigeonRepository pigeonRepository;

    @Mock
    private PigeonMapper pigeonMapper;

    @InjectMocks
    private PigeonService pigeonService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPigeonsByUserId() {
        Integer userId = 1;
        Pigeon pigeon = new Pigeon();
        List<Pigeon> pigeonList = Arrays.asList(pigeon);

        PigeonDTO pigeonDTO = new PigeonDTO();
        List<PigeonDTO> pigeonDTOList = Arrays.asList(pigeonDTO);

        when(pigeonRepository.findByUserId(userId)).thenReturn(pigeonList);
        when(pigeonMapper.toPigeonDTOList(pigeonList)).thenReturn(pigeonDTOList);

        List<PigeonDTO> result = pigeonService.getPigeonsByUserId(userId);

        assertEquals(pigeonDTOList, result);
        verify(pigeonRepository).findByUserId(userId);
        verify(pigeonMapper).toPigeonDTOList(pigeonList);
    }

    @Test
    void testAddPigeon() {
        PigeonDTO pigeonDTO = new PigeonDTO();
        Pigeon pigeon = new Pigeon();
        Pigeon savedPigeon = new Pigeon();
        PigeonDTO savedPigeonDTO = new PigeonDTO();

        when(pigeonMapper.toPigeon(pigeonDTO)).thenReturn(pigeon);
        when(pigeonRepository.save(pigeon)).thenReturn(savedPigeon);
        when(pigeonMapper.toPigeonDTO(savedPigeon)).thenReturn(savedPigeonDTO);

        PigeonDTO result = pigeonService.addPigeon(pigeonDTO);

        assertEquals(savedPigeonDTO, result);
        verify(pigeonMapper).toPigeon(pigeonDTO);
        verify(pigeonRepository).save(pigeon);
        verify(pigeonMapper).toPigeonDTO(savedPigeon);
    }

    @Test
    void testDeletePigeon() {
        String ringNumber = "P123";

        doNothing().when(pigeonRepository).deleteById(ringNumber);

        pigeonService.deletePigeon(ringNumber);

        verify(pigeonRepository).deleteById(ringNumber);
    }

    @Test
    void testUpdatePigeon() {
        PigeonDTO pigeonDTO = new PigeonDTO();
        Pigeon pigeon = new Pigeon();
        Pigeon updatedPigeon = new Pigeon();
        PigeonDTO updatedPigeonDTO = new PigeonDTO();

        when(pigeonMapper.toPigeon(pigeonDTO)).thenReturn(pigeon);
        when(pigeonRepository.save(pigeon)).thenReturn(updatedPigeon);
        when(pigeonMapper.toPigeonDTO(updatedPigeon)).thenReturn(updatedPigeonDTO);

        PigeonDTO result = pigeonService.updatePigeon(pigeonDTO);

        assertEquals(updatedPigeonDTO, result);
        verify(pigeonMapper).toPigeon(pigeonDTO);
        verify(pigeonRepository).save(pigeon);
        verify(pigeonMapper).toPigeonDTO(updatedPigeon);
    }
}
