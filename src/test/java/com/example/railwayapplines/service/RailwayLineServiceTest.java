package com.example.railwayapplines.service;
import com.example.railwayapplines.model.dto.RailwayLineAddDto;
import com.example.railwayapplines.model.dto.RailwayLineDto;
import com.example.railwayapplines.model.entity.RailwayLine;
import com.example.railwayapplines.repository.RailwayLineRepository;
import com.example.railwayapplines.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RailwayLineServiceTest {

    @Mock
    private RailwayLineRepository railwayLineRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RailwayLineService railwayLineService;

    private RailwayLine railwayLine;
    private RailwayLineDto railwayLineDto;
    private RailwayLineAddDto railwayLineAddDto;

    @BeforeEach
    public void setup() {
        railwayLine = new RailwayLine();
        railwayLine.setId(1L);
        railwayLine.setNumber(101);
        railwayLine.setRoute("Route A-B");
        railwayLine.setLength(120.5);
        railwayLine.setDescription("Test description for Railway Line");

        railwayLineDto = new RailwayLineDto();
        railwayLineDto.setId(1L);
        railwayLineDto.setNumber(101);
        railwayLineDto.setRoute("Route A-B");
        railwayLineDto.setLength(120.5);
        railwayLineDto.setDescription("Test description for Railway Line");

        railwayLineAddDto = new RailwayLineAddDto();
        railwayLineAddDto.setNumber(101);
        railwayLineAddDto.setRoute("Route A-B");
        railwayLineAddDto.setLength(120.5);
        railwayLineAddDto.setDescription("Test description for Railway Line");
    }

    @Test
    public void getAllLines_shouldReturnAllLines() {
        when(railwayLineRepository.findAll()).thenReturn(List.of(railwayLine));
        when(modelMapper.map(any(RailwayLine.class), eq(RailwayLineDto.class))).thenReturn(railwayLineDto);

        List<RailwayLineDto> result = railwayLineService.getAllLines();

        assertEquals(1, result.size());
        assertEquals(101, result.get(0).getNumber());
        assertEquals("Route A-B", result.get(0).getRoute());
        assertEquals(120.5, result.get(0).getLength());
        assertEquals("Test description for Railway Line", result.get(0).getDescription());
        verify(railwayLineRepository, times(1)).findAll();
    }

    @Test
    public void deleteLine_shouldDeleteLine() {
        Long id = 1L;
        doNothing().when(railwayLineRepository).deleteById(id);

        railwayLineService.deleteLine(id);

        verify(railwayLineRepository, times(1)).deleteById(id);
    }

    @Test
    public void createLine_shouldCreateAndReturnLine() {
        when(modelMapper.map(any(RailwayLineAddDto.class), eq(RailwayLine.class))).thenReturn(railwayLine);
        when(modelMapper.map(any(RailwayLine.class), eq(RailwayLineDto.class))).thenReturn(railwayLineDto);
        when(railwayLineRepository.save(any(RailwayLine.class))).thenReturn(railwayLine);

        RailwayLineDto result = railwayLineService.createLine(railwayLineAddDto);

        assertEquals(101, result.getNumber());
        assertEquals("Route A-B", result.getRoute());
        assertEquals(120.5, result.getLength());
        assertEquals("Test description for Railway Line", result.getDescription());
        verify(railwayLineRepository, times(1)).save(any(RailwayLine.class));
    }

    @Test
    public void getLineById_shouldReturnLineById() {
        Long id = 1L;
        when(railwayLineRepository.findById(id)).thenReturn(Optional.of(railwayLine));
        when(modelMapper.map(any(RailwayLine.class), eq(RailwayLineDto.class))).thenReturn(railwayLineDto);

        RailwayLineDto result = railwayLineService.getLineById(id);

        assertEquals(101, result.getNumber());
        assertEquals("Route A-B", result.getRoute());
        assertEquals(120.5, result.getLength());
        assertEquals("Test description for Railway Line", result.getDescription());
        verify(railwayLineRepository, times(1)).findById(id);
    }

    @Test
    public void getLineById_shouldThrowObjectNotFoundException() {
        Long id = 1L;
        when(railwayLineRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            railwayLineService.getLineById(id);
        });

        verify(railwayLineRepository, times(1)).findById(id);
    }
}