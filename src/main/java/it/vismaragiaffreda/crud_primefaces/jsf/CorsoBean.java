package it.vismaragiaffreda.crud_primefaces.jsf;

import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class CorsoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private CorsoService corsoService;

    private List<Corso> corsi;
    private Corso corsoSelezionato = new Corso();

    // ===== LOAD DATA =====

    /**
     * Carica la lista di tutti i corsi
     */
    public void loadCorsi() {
        this.corsi = corsoService.getAllCorsi();
    }

    // ===== CRUD OPERATIONS =====

    /**
     * Salva un corso nuovo
     * @return outcome per JSF navigation
     */
    public String saveCorso() {
        if (corsoSelezionato.getNome() == null || corsoSelezionato.getNome().isEmpty()) {
            return "error_nome_vuoto";
        }

        corsoService.saveCorso(corsoSelezionato);
        corsoSelezionato = new Corso(); // Reset form
        loadCorsi();
        return "success";
    }

    /**
     * Elimina un corso
     */
    public void deleteCorso(Long id) {
        corsoService.deleteCorso(id);
        loadCorsi();
    }

    /**
     * Prepara un nuovo corso per l'inserimento
     */
    public void newCorso() {
        this.corsoSelezionato = new Corso();
    }

    /**
     * Seleziona un corso per la modifica
     */
    public void selectCorso(Corso corso) {
        this.corsoSelezionato = corso;
    }

    // ===== GETTERS AND SETTERS =====

    public List<Corso> getCorsi() {
        if (corsi == null) {
            loadCorsi();
        }
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    public Corso getCorsoSelezionato() {
        return corsoSelezionato;
    }

    public void setCorsoSelezionato(Corso corsoSelezionato) {
        this.corsoSelezionato = corsoSelezionato;
    }
}
