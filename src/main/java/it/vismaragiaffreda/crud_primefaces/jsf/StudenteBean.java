package it.vismaragiaffreda.crud_primefaces.jsf;

import it.vismaragiaffreda.crud_primefaces.entity.Studente;
import it.vismaragiaffreda.crud_primefaces.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.io.Serializable;
import java.util.List;

@Component
@RequestScope
public class StudenteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StudenteService studenteService;

    private List<Studente> studenti;
    private Studente studenteSelezionato = new Studente();

    // Load Data

    // Carica la lista di tutti gli studenti
    public void loadStudenti() {
        this.studenti = studenteService.getAllStudenti();
    }

    // Crud Operations

    // Salva uno studente nuovo o aggiornato
    public String saveStudente() {
        if (studenteSelezionato.getNome() == null || studenteSelezionato.getNome().isEmpty()) {
            return "error_nome_vuoto";
        }
        if (studenteSelezionato.getCognome() == null || studenteSelezionato.getCognome().isEmpty()) {
            return "error_cognome_vuoto";
        }

        studenteService.saveStudente(studenteSelezionato);
        studenteSelezionato = new Studente(); // Reset form
        loadStudenti();
        return "success";
    }

    // Elimina uno studente
    public void deleteStudente(Long id) {
        studenteService.deleteStudente(id);
        loadStudenti();
    }


    public void newStudente() {
        this.studenteSelezionato = new Studente();
    }

    // Modifica studente
    public void selectStudente(Studente studente) {
        this.studenteSelezionato = studente;
    }

    // Getters and Setters

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
