package com.devsuperior.hrworker.controllers;

import com.devsuperior.hrworker.models.entities.dto.WorkerDTO;
import com.devsuperior.hrworker.services.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    private Environment env;

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<WorkerDTO>> findAll() {
        List<WorkerDTO> list = workerService.findAll();
        return ResponseEntity.ok().body(list);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {

        logger.info("PORT = {}", env.getProperty("local.server.port"));

        WorkerDTO dto = workerService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
}
