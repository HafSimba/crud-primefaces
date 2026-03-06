package it.vismaragiaffreda.crud_primefaces.service;

import it.vismaragiaffreda.crud_primefaces.entity.Prova;
import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProvaService {

    @Autowired
    private ProvaRepository provaRepository;

    // ===== CRUD OPERATIONS =====

    /**
     * Ottiene tutte le prove registrate
     */
    public List<Prova> getAllProve() {
        return provaRepository.findAll();
    }

    /**
     * Ottiene una prova per ID
     */
    public Optional<Prova> getProvaById(Long id) {
        return provaRepository.findById(id);
    }

    /**
     * Registra una nuova prova (o aggiorna una esistente)
     * @param prova la prova da salvare
     * @return la prova salvata
     */
    public Prova registraProva(Prova prova) {
        // Validazione base: voto deve essere tra 1 e 30
        if (prova.getVoto() < 1 || prova.getVoto() > 30) {
            throw new IllegalArgumentException("Il voto deve essere tra 1 e 30");
        }
        return provaRepository.save(prova);
    }

    /**
     * Elimina una prova per ID
     */
    public void deleteProva(Long id) {
        provaRepository.deleteById(id);
    }

    // ===== QUERY OPERATIONS =====

    /**
     * Ottiene tutte le prove di uno studente
     */
    public List<Prova> getProveByStudente(Studente studente) {
        return provaRepository.findByStudente(studente);
    }

    /**
     * Ottiene tutte le prove di un corso
     */
    public List<Prova> getProveByCorso(Corso corso) {
        return provaRepository.findByCorso(corso);
    }

    /**
     * Cerca se uno studente ha già una prova registrata per un corso specifico
     */
    public Optional<Prova> cercaProva(Studente studente, Corso corso) {
        return provaRepository.findByStudenteAndCorso(studente, corso);
    }

    /**
     * Verifica se uno studente ha superato un corso (ha una prova con voto >= 18)
     */
    public boolean haStudenteSuperatoCorso(Studente studente, Corso corso) {
        Optional<Prova> prova = provaRepository.findByStudenteAndCorso(studente, corso);
        return prova.isPresent() && prova.get().getVoto() >= 18;
    }

    /**
     * Conta quante prove ha sostenuto uno studente
     */
    public int contaTotalProve(Studente studente) {
        return (int) provaRepository.findByStudente(studente).size();
    }
}
