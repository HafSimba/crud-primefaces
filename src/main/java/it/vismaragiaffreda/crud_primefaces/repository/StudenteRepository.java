package it.vismaragiaffreda.crud_primefaces.repository;

import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

    // Cerca uno studente per nome
    List<Studente> findByNome(String nome);

    // Cerca uno studente per cognome
    List<Studente> findByCognome(String cognome);

    // Cerca uno studente per nome e cognome
    Optional<Studente> findByEmail(String email);

    // Cerca uno studente per nome e cognome
    Optional<Studente> findByNomeAndCognome(String nome, String cognome);
}
