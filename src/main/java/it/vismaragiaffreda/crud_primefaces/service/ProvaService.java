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

    // Crud operations


    public List<Prova> getAllProve() {
        return provaRepository.findAll();
    }


    public Optional<Prova> getProvaById(Long id) {
        return provaRepository.findById(id);
    }


    public Prova registraProva(Prova prova) {
        // Validazione base: voto deve essere tra 1 e 30
        if (prova.getVoto() < 1 || prova.getVoto() > 30) {
            throw new IllegalArgumentException("Il voto deve essere tra 1 e 30");
        }
        return provaRepository.save(prova);
    }

 
    public void deleteProva(Long id) {
        provaRepository.deleteById(id);
    }

    // Query Methods


    public List<Prova> getProveByStudente(Studente studente) {
        return provaRepository.findByStudente(studente);
    }


    public List<Prova> getProveByCorso(Corso corso) {
        return provaRepository.findByCorso(corso);
    }

 
    public Optional<Prova> cercaProva(Studente studente, Corso corso) {
        return provaRepository.findByStudenteAndCorso(studente, corso);
    }

 
    public boolean haStudenteSuperatoCorso(Studente studente, Corso corso) {
        Optional<Prova> prova = provaRepository.findByStudenteAndCorso(studente, corso);
        return prova.isPresent() && prova.get().getVoto() >= 18;
    }


    public int contaTotalProve(Studente studente) {
        return (int) provaRepository.findByStudente(studente).size();
    }
}
