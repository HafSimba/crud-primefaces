package it.vismaragiaffreda.crud_primefaces.repository;

import it.vismaragiaffreda.crud_primefaces.entity.Prova;
import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long> {

    // Trova tutte le prove di uno specifico studente
    List<Prova> findByStudente(Studente studente);

    // Trova tutte le prove di un corso specifico
    List<Prova> findByCorso(Corso corso);

    // Trova una prova specifica per studente e corso
    Optional<Prova> findByStudenteAndCorso(Studente studente, Corso corso);

    // Conta numero di prove non riuscite (voto < 18) per uno studente
    long countByStudenteAndVotoLessThan(Studente studente, Integer voto);

    // Conta numero di prove superate (voto >= 18) per uno studente
    long countByStudenteAndVotoGreaterThanEqual(Studente studente, Integer voto);
}
