package it.epicode.gestioneViaggi.controller;

import it.epicode.gestioneViaggi.payload.dipendenteDTO.DipendenteResponseDTO;
import it.epicode.gestioneViaggi.payload.dipendenteDTO.NewDipendenteDTO;
import it.epicode.gestioneViaggi.service.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
@Validated
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO save(@RequestBody @Valid NewDipendenteDTO dto){
        return dipendenteService.save(dto);
    }

    @GetMapping
    public Page<DipendenteResponseDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy){

        return dipendenteService.findAll(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public DipendenteResponseDTO findById(@PathVariable Long id){
        return dipendenteService.findByIdDTO(id);
    }

    @PutMapping("/{id}")
    public DipendenteResponseDTO update(@PathVariable Long id, @RequestBody @Valid NewDipendenteDTO dto){
        return dipendenteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        dipendenteService.delete(id);
    }
}
