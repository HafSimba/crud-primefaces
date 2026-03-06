package it.vismaragiaffreda.crud_primefaces.repository;

import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, Long> {

    /**
     * Cerca un corso per nome
     */
    List<Corso> findByNome(String nome);

    /**
     * Cerca esattamente un corso per nome (assumendo sia unico)
     */
    Optional<Corso> findByNomeIgnoreCase(String nome);
}
