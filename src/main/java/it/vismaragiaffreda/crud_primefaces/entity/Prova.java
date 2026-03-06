package it.vismaragiaffreda.crud_primefaces.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prove")
public class Prova {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studente_id", nullable = false)
    private Studente studente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "corso_id", nullable = false)
    private Corso corso;

    @Column(nullable = false)
    private Integer voto; // Voto in trentesimi (1-30)

    @Column(nullable = false)
    private LocalDate data;

    // Constructors
    public Prova() {
    }

    public Prova(Studente studente, Corso corso, Integer voto, LocalDate data) {
        this.studente = studente;
        this.corso = corso;
        this.voto = voto;
        this.data = data;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Prova{" +
                "id=" + id +
                ", studente=" + studente +
                ", corso=" + corso +
                ", voto=" + voto +
                ", data=" + data +
                '}';
    }
}
