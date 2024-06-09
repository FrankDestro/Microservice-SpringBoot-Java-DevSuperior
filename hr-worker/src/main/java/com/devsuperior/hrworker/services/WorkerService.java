package com.devsuperior.hrworker.services;

import com.devsuperior.hrworker.models.entities.Worker;
import com.devsuperior.hrworker.models.entities.dto.WorkerDTO;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import com.devsuperior.hrworker.services.exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Transactional(readOnly = true)
    public List<WorkerDTO> findAll() {
        List<Worker> list = workerRepository.findAll();
        return list.stream().map(workers -> new WorkerDTO(workers)).toList();
    }

    @Transactional(readOnly = true)
    public WorkerDTO findById(Long id) {
        Worker worker = getEntityById(id);
        return new WorkerDTO(worker);
    }

    private Worker getEntityById(Long id) {
        Optional<Worker> result = workerRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
    }
}
