package it.epicode.gestioneViaggi.controller;

import it.epicode.gestioneViaggi.enums.Stato;
import it.epicode.gestioneViaggi.payload.viaggioDTO.NewViaggioDTO;
import it.epicode.gestioneViaggi.payload.viaggioDTO.ViaggioResponseDTO;
import it.epicode.gestioneViaggi.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
@Validated
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponseDTO save(@RequestBody @Valid NewViaggioDTO newViaggioDTO) {

        return viaggioService.save(newViaggioDTO);
    }

    @GetMapping("/{id}")
    public ViaggioResponseDTO findById(@PathVariable Long id) {
        return viaggioService.findByIdDTO(id);
    }
    @GetMapping
    public Page<ViaggioResponseDTO> findAll(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return viaggioService.findAll(page, size, sortBy);
    }

    @PutMapping("/{id}")
    public ViaggioResponseDTO update(@PathVariable Long id, @RequestBody @Valid NewViaggioDTO dto) {
        return viaggioService.update(id, dto);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }

    @PatchMapping("/{viaggioId}/dipendenti/{dipendenteId}")
    public ViaggioResponseDTO addDipendente(@PathVariable Long viaggioId, @PathVariable Long dipendenteId) {
        return viaggioService.addDipendente(viaggioId, dipendenteId);
    }

    @DeleteMapping("/{viaggioId}/dipendenti/{dipendenteId}")
    public ViaggioResponseDTO removeDipendente(@PathVariable Long viaggioId, @PathVariable Long dipendenteId) {
        return viaggioService.removeDipendente(viaggioId, dipendenteId);
    }

    @PatchMapping("/{id}/stato")
    public ViaggioResponseDTO changeState(
            @PathVariable Long id,
            @RequestParam Stato nuovoStato
    ) {
        return viaggioService.changeState(id, nuovoStato);
    }

}
