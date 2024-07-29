package com.example.railwayapplines.web;

import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.railwayapplines.model.dto.RailwayLineAddDto;
import com.example.railwayapplines.model.dto.RailwayLineDto;
import com.example.railwayapplines.model.entity.RailwayLine;
import com.example.railwayapplines.repository.RailwayLineRepository;
import com.example.railwayapplines.service.RailwayLineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class RailwayLineControllerIT {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private RailwayLineRepository railwayLineRepository;

        @Autowired
        private ModelMapper modelMapper;


        @BeforeEach
        public void setup() {
            railwayLineRepository.deleteAll();

            RailwayLine railwayLine = new RailwayLine();
            railwayLine.setNumber(101);
            railwayLine.setRoute("Route A-B");
            railwayLine.setLength(120.5);
            railwayLine.setDescription("Test description for Railway Line");
            railwayLineRepository.save(railwayLine);
        }

        @Test
        public void getAllLines_shouldReturnAllLines() throws Exception {
            mockMvc.perform(get("/lines"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].number", is(101)))
                    .andExpect(jsonPath("$[0].route", is("Route A-B")))
                    .andExpect(jsonPath("$[0].length", is(120.5)))
                    .andExpect(jsonPath("$[0].description", is("Test description for Railway Line")));
        }



        @Test
        public void getById_shouldReturnLineById() throws Exception {
            RailwayLine railwayLine = railwayLineRepository.findAll().get(0);
            Long id = railwayLine.getId();

            mockMvc.perform(get("/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.number", is(101)))
                    .andExpect(jsonPath("$.route", is("Route A-B")))
                    .andExpect(jsonPath("$.length", is(120.5)))
                    .andExpect(jsonPath("$.description", is("Test description for Railway Line")));
        }

        private String convertToJson(Object object) {
            RailwayLineDto railwayLineDto = modelMapper.map(object, RailwayLineDto.class);
            try {
                return modelMapper.map(railwayLineDto, String.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to convert to JSON", e);
            }
        }
    }

