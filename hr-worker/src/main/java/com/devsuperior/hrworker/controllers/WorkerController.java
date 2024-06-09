package com.devsuperior.hrworker.controllers;

import com.devsuperior.hrworker.models.entities.dto.WorkerDTO;
import com.devsuperior.hrworker.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAll() {
        List<WorkerDTO> list = workerService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {
        WorkerDTO dto = workerService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
