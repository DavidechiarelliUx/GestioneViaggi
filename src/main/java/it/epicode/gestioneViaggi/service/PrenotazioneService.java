package it.epicode.gestioneViaggi.service;

import it.epicode.gestioneViaggi.entity.Dipendente;
import it.epicode.gestioneViaggi.entity.Prenotazione;
import it.epicode.gestioneViaggi.entity.Viaggio;
import it.epicode.gestioneViaggi.exception.NotFoundException;
import it.epicode.gestioneViaggi.payload.responseDTO.NewPrenotazioneDTO;
import it.epicode.gestioneViaggi.payload.responseDTO.PrenotazioneResponseDTO;
import it.epicode.gestioneViaggi.repository.DipendenteRepository;
import it.epicode.gestioneViaggi.repository.PrenotazioneRepository;
import it.epicode.gestioneViaggi.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    private Prenotazione toEntity(NewPrenotazioneDTO newPrenotazioneDTO) {
        Dipendente dipendente = dipendenteRepository.findById(newPrenotazioneDTO.dipendenteId())
                .orElseThrow(() -> new NotFoundException(newPrenotazioneDTO.dipendenteId()));
        Viaggio viaggio = viaggioRepository.findById(newPrenotazioneDTO.viaggioId())
                .orElseThrow(() -> new NotFoundException(newPrenotazioneDTO.viaggioId()));

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataRichiesta(newPrenotazioneDTO.dataRichiesta());
        prenotazione.setNote(newPrenotazioneDTO.note());
        return prenotazione;
    }

    private PrenotazioneResponseDTO toDTO(Prenotazione prenotazione) {
        return new PrenotazioneResponseDTO(
                prenotazione.getId(),
                prenotazione.getDipendente().getId(),
                prenotazione.getViaggio().getId(),
                prenotazione.getDataRichiesta(),
                prenotazione.getNote()
        );
    }

    public PrenotazioneResponseDTO save(NewPrenotazioneDTO dto) {
        Prenotazione p = toEntity(dto);
        Prenotazione saved = prenotazioneRepository.save(p);
        return toDTO(saved);
    }

    public Page<PrenotazioneResponseDTO> findAll(int page, int size, String sortBy) {
        return prenotazioneRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)))
                .map(this::toDTO);
    }

    public PrenotazioneResponseDTO findByIdDTO(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return toDTO(prenotazione);
    }

    public PrenotazioneResponseDTO update(Long id, NewPrenotazioneDTO dto) {
        Prenotazione existing = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        existing.setDipendente(dipendenteRepository.findById(dto.dipendenteId())
                .orElseThrow(() -> new NotFoundException(dto.dipendenteId())));
        existing.setViaggio(viaggioRepository.findById(dto.viaggioId())
                .orElseThrow(() -> new NotFoundException(dto.viaggioId())));
        existing.setDataRichiesta(dto.dataRichiesta());
        existing.setNote(dto.note());
        Prenotazione saved = prenotazioneRepository.save(existing);
        return toDTO(saved);
    }
    public void delete(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        prenotazioneRepository.delete(prenotazione);
    }

}
