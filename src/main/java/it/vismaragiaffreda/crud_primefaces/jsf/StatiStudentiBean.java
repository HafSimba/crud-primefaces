package it.vismaragiaffreda.crud_primefaces.jsf;

import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.entity.Corso;
import it.vismaragiaffreda.crud_primefaces.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.io.Serializable;
import java.util.List;

/**
 * Managed Bean JSF per la visualizzazione dello stato degli studenti.
 * Mostra per ogni studente:
 * - Media voti (solo prove superate, voto >= 18)
 * - Lista di moduli ancora da superare
 * - Numero di corsi superati/falliti
 */
@Component
@RequestScope
public class StatiStudentiBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StudenteService studenteService;

    private List<Studente> studenti;
    private Studente studenteSelezionato;

    // ===== INNER CLASS FOR DISPLAY =====

    /**
     * Classe wrapper per visualizzare le informazioni aggregate di uno studente
     */
    public class StatoStudente {
        private Studente studente;
        private Double mediaVoti;
        private List<Corso> moduliMancanti;
        private long corsiSuperati;
        private long corsiFalliti;
        private int totalProve;

        public StatoStudente(Studente studente, Double mediaVoti, List<Corso> moduliMancanti,
                            long corsiSuperati, long corsiFalliti, int totalProve) {
            this.studente = studente;
            this.mediaVoti = mediaVoti;
            this.moduliMancanti = moduliMancanti;
            this.corsiSuperati = corsiSuperati;
            this.corsiFalliti = corsiFalliti;
            this.totalProve = totalProve;
        }

        public Studente getStudente() {
            return studente;
        }

        public Double getMediaVoti() {
            return mediaVoti;
        }

        public String getMediaVotiFormatted() {
            if (mediaVoti == null || mediaVoti == 0.0) {
                return "N/A";
            }
            return String.format("%.2f", mediaVoti);
        }

        public List<Corso> getModuliMancanti() {
            return moduliMancanti;
        }

        public long getCorsiSuperati() {
            return corsiSuperati;
        }

        public long getCorsiFalliti() {
            return corsiFalliti;
        }

        public int getTotalProve() {
            return totalProve;
        }

        public String getStatoSintesi() {
            return "Superati: " + corsiSuperati + " | Falliti: " + corsiFalliti + " | Totale prove: " + totalProve;
        }
    }

    // ===== LOAD DATA =====

    /**
     * Carica la lista di tutti gli studenti e calcola il stato per ciascuno
     */
    public void loadStudenti() {
        this.studenti = studenteService.getAllStudenti();
    }

    // ===== RESULT GENERATION =====

    /**
     * Genera la lista di StatoStudente per la visualizzazione
     */
    public List<StatoStudente> getStatiStudenti() {
        List<Studente> lista = getStudenti();
        return lista.stream()
                .map(studente -> new StatoStudente(
                        studente,
                        studenteService.calcoloMediaStudente(studente),
                        studenteService.getModuliMancanti(studente),
                        studenteService.contaCorsiSuperati(studente),
                        studenteService.contaCorsiFalliti(studente),
                        studenteService.getTotalProveStudente(studente)
                ))
                .toList(); // Java 16+
    }

    /**
     * Ottiene il dettaglio dello stato di uno studente specifico
     */
    public StatoStudente getStatoStudente(Studente studente) {
        return new StatoStudente(
                studente,
                studenteService.calcoloMediaStudente(studente),
                studenteService.getModuliMancanti(studente),
                studenteService.contaCorsiSuperati(studente),
                studenteService.contaCorsiFalliti(studente),
                studenteService.getTotalProveStudente(studente)
        );
    }

    // ===== SELECTION =====

    /**
     * Seleziona uno studente per visualizzare il dettaglio
     */
    public void selectStudente(Studente studente) {
        this.studenteSelezionato = studente;
    }

    // ===== GETTERS AND SETTERS =====

    public List<Studente> getStudenti() {
        if (studenti == null) {
            loadStudenti();
        }
        return studenti;
    }

    public void setStudenti(List<Studente> studenti) {
        this.studenti = studenti;
    }

    public Studente getStudenteSelezionato() {
        return studenteSelezionato;
    }

    public void setStudenteSelezionato(Studente studenteSelezionato) {
        this.studenteSelezionato = studenteSelezionato;
    }
}
