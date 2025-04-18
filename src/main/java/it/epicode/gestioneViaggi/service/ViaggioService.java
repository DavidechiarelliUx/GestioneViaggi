package it.epicode.gestioneViaggi.service;

import it.epicode.gestioneViaggi.entity.Dipendente;
import it.epicode.gestioneViaggi.entity.Viaggio;
import it.epicode.gestioneViaggi.enums.Stato;
import it.epicode.gestioneViaggi.exception.NotFoundException;
import it.epicode.gestioneViaggi.payload.viaggioDTO.NewViaggioDTO;
import it.epicode.gestioneViaggi.payload.viaggioDTO.ViaggioResponseDTO;
import it.epicode.gestioneViaggi.repository.DipendenteRepository;
import it.epicode.gestioneViaggi.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

  //  @Autowired
    //    private ViaggioRepository viaggioRepository;
    //
    //    @Autowired
    //    private DipendenteRepository dipendenteRepository;
    //
    //    public Viaggio save(Viaggio viaggio) {
    //        return viaggioRepository.save(viaggio);
    //    }
    //
    //    public Viaggio findById(Long id) {
    //       return viaggioRepository.findById(id)
    //               .orElseThrow(() -> new NotFoundException(id));
    //    }
    //
    //    public Page<Viaggio> findAll(int page, int size, String sortBy) {
    //        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    //        return viaggioRepository.findAll(pageable);
    //    }
    //
    //    public Viaggio update(Long id, Viaggio update) {
    //        Viaggio existing = findById(id);
    //        existing.setDestinazione(update.getDestinazione());
    //        existing.setData(update.getData());
    //        existing.setStato(update.getStato());
    //        return viaggioRepository.save(existing);
    //    }
    //
    //
    //    public void delete(Long id) {
    //        Viaggio existing = this.findById(id);
    //        viaggioRepository.delete(existing);
    //
    //    }
    //
    //    public Viaggio addDipendente(Long idViaggio, Long idDipendente) {
    //        Viaggio viaggio = findById(idViaggio);
    //        Dipendente dipendente = dipendenteRepository.findById(idDipendente)
    //                .orElseThrow(() -> new NotFoundException(idDipendente));
    //        viaggio.getDipendenti().add(dipendente);
    //        return viaggioRepository.save(viaggio);
    //    }
    //
    //    public Viaggio removeDipendente(Long idViaggio, Long idDipendente) {
    //        Viaggio viaggio = findById(idViaggio);
    //        Dipendente dipendente = dipendenteRepository.findById(idDipendente)
    //                .orElseThrow(() -> new NotFoundException(idDipendente));
    //        viaggio.getDipendenti().remove(dipendente);
    //        return viaggioRepository.save(viaggio);
    //    }

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    private Viaggio toEntity(NewViaggioDTO newViaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(newViaggioDTO.destinazione());
        viaggio.setData(newViaggioDTO.data());
        viaggio.setStato(newViaggioDTO.stato());
        return viaggio;
    }

    private ViaggioResponseDTO toDTO(Viaggio viaggio) {
        List<Long> dipendentiId = viaggio.getDipendenti()
                .stream()
                .map(dipendente ->dipendente.getId())
                .toList();

        return new ViaggioResponseDTO(
              viaggio.getId(),
              viaggio.getDestinazione(),
              viaggio.getData(),
              viaggio.getStato(),
              dipendentiId
        );

        }
        public ViaggioResponseDTO save(NewViaggioDTO dto) {
            Viaggio v = toEntity(dto);
            Viaggio saved = viaggioRepository.save(v);
            return toDTO(saved);
       }

    public Page<ViaggioResponseDTO> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return viaggioRepository.findAll(pageable)
                .map(this::toDTO);
    }
    public ViaggioResponseDTO findByIdDTO(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return toDTO(viaggio);
    }

    public ViaggioResponseDTO update(Long id, NewViaggioDTO dto) {
        Viaggio existing = viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        existing.setDestinazione(dto.destinazione());
        existing.setData(dto.data());
        existing.setStato(dto.stato());
        Viaggio saved = viaggioRepository.save(existing);
        return toDTO(saved);
    }

    public void delete(Long id) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        viaggioRepository.delete(viaggio);
    }

    public ViaggioResponseDTO changeState(Long id, Stato nuovoStato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        viaggio.setStato(nuovoStato);
        return toDTO(viaggioRepository.save(viaggio));
    }

    // in ViaggioService
    public ViaggioResponseDTO addDipendente(Long viaggioId, Long dipendenteId) {
        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));
        Dipendente d = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException(dipendenteId));

        if (viaggio.getDipendenti().stream()
                .anyMatch(x -> x.getId().equals(dipendenteId))) {
            throw new IllegalStateException("Dipendente già assegnato");
        }
        viaggio.getDipendenti().add(d);
        return toDTO(viaggioRepository.save(viaggio));
    }

    public ViaggioResponseDTO removeDipendente(Long viaggioId, Long dipendenteId) {
        Viaggio viaggio = viaggioRepository.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));
        boolean removed = viaggio.getDipendenti().removeIf(x -> x.getId().equals(dipendenteId));
        if (!removed) {
            throw new IllegalStateException("Dipendente non era assegnato");
        }
        return toDTO(viaggioRepository.save(viaggio));
    }


}
