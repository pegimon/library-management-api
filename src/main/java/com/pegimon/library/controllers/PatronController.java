package com.pegimon.library.controllers;

import com.pegimon.library.models.Entities.PatronEntity;
import com.pegimon.library.models.dto.PatronDto;
import com.pegimon.library.models.mappers.Mapper;
import com.pegimon.library.services.PatronService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronService patronService;
    private final Mapper<PatronEntity, PatronDto> patronMapper;

    public PatronController(PatronService patronService, Mapper<PatronEntity, PatronDto> patronMapper) {
        this.patronService = patronService;
        this.patronMapper = patronMapper;
    }

    @PostMapping
    public ResponseEntity<PatronDto> addPatron(@RequestBody PatronDto patronDto) {
        PatronEntity patronEntity = patronMapper.reverse(patronDto);
        PatronEntity savedPatron = patronService.addPatron(patronEntity);
        return new ResponseEntity<>(patronMapper.map(savedPatron), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDto> updatePatron(@PathVariable Long id, @RequestBody PatronDto patronDto) {
        PatronEntity patronEntity = patronMapper.reverse(patronDto);
        PatronEntity updatedPatron = patronService.updatePatron(id, patronEntity);
        return ResponseEntity.ok(patronMapper.map(updatedPatron));
    }

    @GetMapping
    public List<PatronDto> getAllPatrons() {
        List<PatronEntity> patrons = patronService.getAllPatrons();
        return patrons.stream().map(patronMapper::map).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronDto> getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id)
                .map(patronMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatronDto> deletePatron(@PathVariable Long id) {
        return patronService.deletePatron(id)
                .map(patronMapper::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
