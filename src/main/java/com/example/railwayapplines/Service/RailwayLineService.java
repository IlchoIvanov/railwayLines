package com.example.railwayapplines.Service;

import com.example.railwayapplines.Model.Dto.RailwayLineAddDto;
import com.example.railwayapplines.Model.Dto.RailwayLineDto;
import com.example.railwayapplines.Model.Entity.RailwayLine;
import com.example.railwayapplines.Repository.RailwayLineRepository;
import com.example.railwayapplines.Service.Exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailwayLineService {
    private final RailwayLineRepository railwayLineRepository;

    public RailwayLineService(RailwayLineRepository repository) {
        this.railwayLineRepository = repository;
    }

    public List<RailwayLineDto> getAllLines() {
        return railwayLineRepository.findAll().stream().map(RailwayLineService::Map).toList();
    }

    private static RailwayLineDto Map(RailwayLine railwayLine){
        //todo:mapper
        RailwayLineDto railwayLineDto = new RailwayLineDto();
        railwayLineDto.setDescription(railwayLine.getDescription());
        railwayLineDto.setLength(railwayLine.getLength());
        railwayLineDto.setNumber(railwayLine.getNumber());
        railwayLineDto.setRoute(railwayLine.getRoute());
        railwayLineDto.setId(railwayLine.getId());
        return railwayLineDto;
    }

    public void deleteLine(Long id) {
        railwayLineRepository.deleteById(id);
    }

    public RailwayLineDto createLine(RailwayLineAddDto dto) {
        //todo: mapper
        RailwayLine railwayLine = new RailwayLine();
        railwayLine.setDescription(dto.getDescription());
        railwayLine.setLength(dto.getLength());
        railwayLine.setNumber(dto.getNumber());
        railwayLine.setRoute(dto.getRoute());
        railwayLineRepository.save(railwayLine);
        return Map(railwayLine);
    }

    public RailwayLineDto getLineById(Long id) {
        return railwayLineRepository
                .findById(id)
                .map(RailwayLineService::Map)
                .orElseThrow(ObjectNotFoundException::new);
    }
}
