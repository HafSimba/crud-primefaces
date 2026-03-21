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

    // Crudoperations 

  
    public List<Corso> getAllCorsi() {
        return corsoRepository.findAll();
    }


    public Optional<Corso> getCorsoById(Long id) {
        return corsoRepository.findById(id);
    }


    public Corso saveCorso(Corso corso) {
        return corsoRepository.save(corso);
    }


    public void deleteCorso(Long id) {
        corsoRepository.deleteById(id);
    }


    public List<Corso> findByNome(String nome) {
        return corsoRepository.findByNome(nome);
    }


    public Optional<Corso> findByNomeIgnoreCase(String nome) {
        return corsoRepository.findByNomeIgnoreCase(nome);
    }
}
