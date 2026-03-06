package it.vismaragiaffreda.crud_primefaces.service;

import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    @Autowired
    private CorsoRepository corsoRepository;

    // ===== CRUD OPERATIONS =====

    /**
     * Ottiene tutti i corsi
     */
    public List<Corso> getAllCorsi() {
        return corsoRepository.findAll();
    }

    /**
     * Ottiene un corso per ID
     */
    public Optional<Corso> getCorsoById(Long id) {
        return corsoRepository.findById(id);
    }

    /**
     * Salva un corso nuovo o aggiorna uno esistente
     */
    public Corso saveCorso(Corso corso) {
        return corsoRepository.save(corso);
    }

    /**
     * Elimina un corso per ID
     */
    public void deleteCorso(Long id) {
        corsoRepository.deleteById(id);
    }

    /**
     * Cerca corsi per nome
     */
    public List<Corso> findByNome(String nome) {
        return corsoRepository.findByNome(nome);
    }

    /**
     * Cerca un corso per nome (case-insensitive)
     */
    public Optional<Corso> findByNomeIgnoreCase(String nome) {
        return corsoRepository.findByNomeIgnoreCase(nome);
    }
}
