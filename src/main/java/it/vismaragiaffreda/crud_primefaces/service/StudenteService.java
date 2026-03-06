package it.vismaragiaffreda.crud_primefaces.service;

import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.entity.Prova;
import it.vismaragiaffreda.crud_primefaces.repository.StudenteRepository;
import it.vismaragiaffreda.crud_primefaces.repository.ProvaRepository;
import it.vismaragiaffreda.crud_primefaces.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudenteService {

    @Autowired
    private StudenteRepository studenteRepository;

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private CorsoRepository corsoRepository;

    // ===== CRUD OPERATIONS =====

    /**
     * Ottiene tutti gli studenti
     */
    public List<Studente> getAllStudenti() {
        return studenteRepository.findAll();
    }

    /**
     * Ottiene uno studente per ID
     */
    public Optional<Studente> getStudenteById(Long id) {
        return studenteRepository.findById(id);
    }

    /**
     * Salva uno studente nuovo o aggiorna uno esistente
     */
    public Studente saveStudente(Studente studente) {
        return studenteRepository.save(studente);
    }

    /**
     * Elimina uno studente per ID
     */
    public void deleteStudente(Long id) {
        studenteRepository.deleteById(id);
    }

    /**
     * Cerca studente per email
     */
    public Optional<Studente> findByEmail(String email) {
        return studenteRepository.findByEmail(email);
    }

    /**
     * Cerca studente per nome e cognome
     */
    public Optional<Studente> findByNomeAndCognome(String nome, String cognome) {
        return studenteRepository.findByNomeAndCognome(nome, cognome);
    }

    // ===== BUSINESS LOGIC =====

    /**
     * Calcola la media voti di uno studente (solo prove superate: voto >= 18)
     * @return media aritmetica, oppure 0.0 se non ha prove superate
     */
    public Double calcoloMediaStudente(Studente studente) {
        List<Prova> prove = provaRepository.findByStudente(studente);

        // Filtra solo prove superate (voto >= 18)
        List<Integer> votiSuperati = prove.stream()
                .filter(prova -> prova.getVoto() >= 18)
                .map(Prova::getVoto)
                .collect(Collectors.toList());

        if (votiSuperati.isEmpty()) {
            return 0.0;
        }

        // Calcola media
        double somma = votiSuperati.stream()
                .mapToDouble(Integer::doubleValue)
                .sum();
        return somma / votiSuperati.size();
    }

    /**
     * Ottiene la lista dei moduli (corsi) ancora da superare per uno studente
     * (cioè corsi dove ha preso un voto < 18 oppure non ha mai fatto l'esame)
     * @return Lista di Corso non superati
     */
    public List<Corso> getModuliMancanti(Studente studente) {
        // Ottieni tutti i corsi disponibili
        List<Corso> tuttiCorsi = corsoRepository.findAll();

        // Ottieni le prove dello studente
        List<Prova> proveStudente = provaRepository.findByStudente(studente);

        // Crea un set di corsi superati (voto >= 18)
        Set<Long> corsiSuperati = proveStudente.stream()
                .filter(prova -> prova.getVoto() >= 18)
                .map(prova -> prova.getCorso().getId())
                .collect(Collectors.toSet());

        // Restituisci i corsi NON superati
        return tuttiCorsi.stream()
                .filter(corso -> !corsiSuperati.contains(corso.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Conta il numero di corsi superati da uno studente (voto >= 18)
     */
    public long contaCorsiSuperati(Studente studente) {
        return provaRepository.countByStudenteAndVotoGreaterThanEqual(studente, 18);
    }

    /**
     * Conta il numero di corsi falliti da uno studente (voto < 18)
     */
    public long contaCorsiFalliti(Studente studente) {
        return provaRepository.countByStudenteAndVotoLessThan(studente, 18);
    }

    /**
     * Ottiene il numero totale di prove sostenute da uno studente
     */
    public int getTotalProveStudente(Studente studente) {
        return provaRepository.findByStudente(studente).size();
    }
}
