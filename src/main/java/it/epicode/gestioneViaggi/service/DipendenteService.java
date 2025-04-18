package it.epicode.gestioneViaggi.service;

import it.epicode.gestioneViaggi.entity.Dipendente;
import it.epicode.gestioneViaggi.exception.NotFoundException;
import it.epicode.gestioneViaggi.payload.dipendenteDTO.DipendenteResponseDTO;
import it.epicode.gestioneViaggi.payload.dipendenteDTO.NewDipendenteDTO;
import it.epicode.gestioneViaggi.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

   // public Dipendente save(Dipendente dipendente) {
    //        return dipendenteRepository.save(dipendente);
    //    }
    //
    //    public Dipendente findById(Long id) {
    //        return dipendenteRepository.findById(id)
    //                .orElseThrow(() -> new NotFoundException(id));
    //
    //    }
    //
    //    public Page<Dipendente> findAll(int page, int size, String sortBy) {
    //       Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    //        return dipendenteRepository.findAll(pageable);
    //    }
    //
    //    public Dipendente update(Long id, Dipendente update){
    //        Dipendente existing = this.findById(id);
    //        existing.setUsername(update.getUsername());
    //        existing.setNome(update.getNome());
    //        existing.setCognome(update.getCognome());
    //        existing.setEmail(update.getEmail());
    //        return dipendenteRepository.save(existing);
    //    }
    //
    //    public void delete(Long id){
    //        Dipendente existing = this.findById(id);
    //        dipendenteRepository.delete(existing);
    //    }

    private Dipendente toEntity(NewDipendenteDTO newDipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(newDipendenteDTO.username());
        dipendente.setNome(newDipendenteDTO.nome());
        dipendente.setCognome(newDipendenteDTO.cognome());
        dipendente.setEmail(newDipendenteDTO.email());
        return dipendente;
    }

    private DipendenteResponseDTO toDTO(Dipendente dipendente) {
        return new DipendenteResponseDTO(
                dipendente.getId(),
                dipendente.getUsername(),
                dipendente.getNome(),
                dipendente.getCognome(),
                dipendente.getEmail()
        );
    }

    public DipendenteResponseDTO save(NewDipendenteDTO dto){
        Dipendente dipendente = toEntity(dto);
        Dipendente saved = dipendenteRepository.save(dipendente);
        return toDTO(saved);
    }
    public Page<DipendenteResponseDTO> findAll(int page, int size, String sortBy) {
        return dipendenteRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)))
                .map(this::toDTO);
    }

    public DipendenteResponseDTO findByIdDTO(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return toDTO(dipendente);
    }

    public DipendenteResponseDTO update(Long id, NewDipendenteDTO dto) {
        Dipendente existing = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        existing.setUsername(dto.username());
        existing.setNome(dto.nome());
        existing.setCognome(dto.cognome());
        existing.setEmail(dto.email());
        Dipendente saved = dipendenteRepository.save(existing);
        return toDTO(saved);
    }

    public void delete(Long id) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        dipendenteRepository.delete(dipendente);
    }
}
