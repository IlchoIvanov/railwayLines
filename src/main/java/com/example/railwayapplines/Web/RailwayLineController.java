package com.example.railwayapplines.Web;

import com.example.railwayapplines.Model.Dto.RailwayLineAddDto;
import com.example.railwayapplines.Model.Dto.RailwayLineDto;
import com.example.railwayapplines.Service.RailwayLineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class RailwayLineController {

    private final RailwayLineService railwayLineService;

    public RailwayLineController(RailwayLineService railwayLineService) {
        this.railwayLineService = railwayLineService;
    }

    @GetMapping("/lines")
    public ResponseEntity<List<RailwayLineDto>> getAllLines() {
        return ResponseEntity
                .ok(railwayLineService.getAllLines());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RailwayLineDto> deleteById(@PathVariable("id") Long id) {
        railwayLineService.deleteLine(id);
        return ResponseEntity
                .noContent()
                .build();
    }


    @PostMapping("/add")
    public ResponseEntity<RailwayLineDto> createLine(
            @RequestBody RailwayLineAddDto dto
    ) {


        RailwayLineDto railwayLineDto = railwayLineService.createLine(dto);
        return ResponseEntity.
                created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(railwayLineDto.getId())
                                .toUri()
                ).body(railwayLineDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RailwayLineDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok(railwayLineService.getLineById(id));
    }
}
