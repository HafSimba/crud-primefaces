package it.vismaragiaffreda.crud_primefaces.jsf;

import it.vismaragiaffreda.crud_primefaces.entity.Prova;
import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.service.ProvaService;
import it.vismaragiaffreda.crud_primefaces.service.StudenteService;
import it.vismaragiaffreda.crud_primefaces.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequestScope
public class ProvaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProvaService provaService;

    @Autowired
    private StudenteService studenteService;

    @Autowired
    private CorsoService corsoService;

    private List<Prova> prove;
    private Prova provaSelezionata = new Prova();

    private List<Studente> studenti;
    private List<Corso> corsi;

    private Long studenteIdSelezionato;
    private Long corsoIdSelezionato;
    private Integer votoInserito;
    private String messaggioErrore;

    // ===== INIT / LOAD DATA =====

    /**
     * Carica tutte le prove registrate
     */
    public void loadProve() {
        this.prove = provaService.getAllProve();
    }

    /**
     * Carica i dati necessari per il form di registrazione
     */
    public void loadDatiRegistrazione() {
        this.studenti = studenteService.getAllStudenti();
        this.corsi = corsoService.getAllCorsi();
    }

    // ===== CRUD OPERATIONS =====

    /**
     * Registra una nuova prova (esame superato/fallito)
     * @return outcome per JSF navigation
     */
    public String registraProva() {
        messaggioErrore = null;

        // Validazione
        if (studenteIdSelezionato == null) {
            messaggioErrore = "Selezionare uno studente";
            return "error";
        }
        if (corsoIdSelezionato == null) {
            messaggioErrore = "Selezionare un corso";
            return "error";
        }
        if (votoInserito == null || votoInserito < 1 || votoInserito > 30) {
            messaggioErrore = "Il voto deve essere tra 1 e 30";
            return "error";
        }

        try {
            Optional<Studente> studente = studenteService.getStudenteById(studenteIdSelezionato);
            Optional<Corso> corso = corsoService.getCorsoById(corsoIdSelezionato);

            if (!studente.isPresent() || !corso.isPresent()) {
                messaggioErrore = "Studente o corso non trovato";
                return "error";
            }

            Prova prova = new Prova();
            prova.setStudente(studente.get());
            prova.setCorso(corso.get());
            prova.setVoto(votoInserito);
            prova.setData(LocalDate.now());

            provaService.registraProva(prova);

            // Reset form
            studenteIdSelezionato = null;
            corsoIdSelezionato = null;
            votoInserito = null;

            loadProve();
            return "success";
        } catch (Exception e) {
            messaggioErrore = "Errore durante la registrazione: " + e.getMessage();
            return "error";
        }
    }

    /**
     * Elimina una prova
     */
    public void deleteProva(Long id) {
        provaService.deleteProva(id);
        loadProve();
    }

    /**
     * Prepara una nuova prova per l'inserimento
     */
    public void newProva() {
        this.provaSelezionata = new Prova();
        this.studenteIdSelezionato = null;
        this.corsoIdSelezionato = null;
        this.votoInserito = null;
        this.messaggioErrore = null;
    }

    // ===== GETTERS AND SETTERS =====

    public List<Prova> getProve() {
        if (prove == null) {
            loadProve();
        }
        return prove;
    }

    public void setProve(List<Prova> prove) {
        this.prove = prove;
    }

    public Prova getProvaSelezionata() {
        return provaSelezionata;
    }

    public void setProvaSelezionata(Prova provaSelezionata) {
        this.provaSelezionata = provaSelezionata;
    }

    public List<Studente> getStudenti() {
        if (studenti == null) {
            loadDatiRegistrazione();
        }
        return studenti;
    }

    public void setStudenti(List<Studente> studenti) {
        this.studenti = studenti;
    }

    public List<Corso> getCorsi() {
        if (corsi == null) {
            loadDatiRegistrazione();
        }
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    public Long getStudenteIdSelezionato() {
        return studenteIdSelezionato;
    }

    public void setStudenteIdSelezionato(Long studenteIdSelezionato) {
        this.studenteIdSelezionato = studenteIdSelezionato;
    }

    public Long getCorsoIdSelezionato() {
        return corsoIdSelezionato;
    }

    public void setCorsoIdSelezionato(Long corsoIdSelezionato) {
        this.corsoIdSelezionato = corsoIdSelezionato;
    }

    public Integer getVotoInserito() {
        return votoInserito;
    }

    public void setVotoInserito(Integer votoInserito) {
        this.votoInserito = votoInserito;
    }

    public String getMessaggioErrore() {
        return messaggioErrore;
    }

    public void setMessaggioErrore(String messaggioErrore) {
        this.messaggioErrore = messaggioErrore;
    }
}
