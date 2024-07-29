package com.example.railwayapplines.service;

import com.example.railwayapplines.model.dto.RailwayLineAddDto;
import com.example.railwayapplines.model.dto.RailwayLineDto;
import com.example.railwayapplines.model.entity.RailwayLine;
import com.example.railwayapplines.repository.RailwayLineRepository;
import com.example.railwayapplines.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailwayLineService {
    private final RailwayLineRepository railwayLineRepository;
    private final ModelMapper modelMapper;

    public RailwayLineService(RailwayLineRepository repository, ModelMapper modelMapper) {
        this.railwayLineRepository = repository;
        this.modelMapper = modelMapper;
    }

    public List<RailwayLineDto> getAllLines() {
        return railwayLineRepository.findAll().stream().map(r->modelMapper.map(r, RailwayLineDto.class)).toList();
    }



    public void deleteLine(Long id) {
        railwayLineRepository.deleteById(id);
    }

    public RailwayLineDto createLine(RailwayLineAddDto dto) {

        RailwayLine railwayLine = modelMapper.map(dto, RailwayLine.class);
        railwayLineRepository.save(railwayLine);
        return modelMapper.map(railwayLine, RailwayLineDto.class);
    }

    public RailwayLineDto getLineById(Long id) {
        return railwayLineRepository
                .findById(id)
                .map(r-> modelMapper.map(r, RailwayLineDto.class))
                .orElseThrow(ObjectNotFoundException::new);
    }
}
