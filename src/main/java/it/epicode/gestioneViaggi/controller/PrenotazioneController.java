package it.epicode.gestioneViaggi.controller;

import it.epicode.gestioneViaggi.payload.responseDTO.NewPrenotazioneDTO;
import it.epicode.gestioneViaggi.payload.responseDTO.PrenotazioneResponseDTO;
import it.epicode.gestioneViaggi.service.DipendenteService;
import it.epicode.gestioneViaggi.service.PrenotazioneService;
import it.epicode.gestioneViaggi.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    // 1) Create – POST /prenotazioni
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponseDTO create(@RequestBody @Valid NewPrenotazioneDTO dto) {
        return prenotazioneService.save(dto);
    }

    // 2) Read all (paginated) – GET /prenotazioni
    @GetMapping
    public Page<PrenotazioneResponseDTO> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        return prenotazioneService.findAll(page, size, sortBy);
    }

    // 3) Read one – GET /prenotazioni/{id}
    @GetMapping("/{id}")
    public PrenotazioneResponseDTO findById(@PathVariable Long id) {
        return prenotazioneService.findByIdDTO(id);
    }

    // 4) Update – PUT /prenotazioni/{id}
    @PutMapping("/{id}")
    public PrenotazioneResponseDTO update(
            @PathVariable Long id,
            @RequestBody @Valid NewPrenotazioneDTO dto) {
        return prenotazioneService.update(id, dto);
    }

    // 5) Delete – DELETE /prenotazioni/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        prenotazioneService.delete(id);
    }


}
