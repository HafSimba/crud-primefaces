package it.vismaragiaffreda.crud_primefaces.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "corsi")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 500)
    private String descrizione;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prova> prove;

    // Constructors
    public Corso() {
    }

    public Corso(String nome, String descrizione) {
        this.nome = nome;
        this.descrizione = descrizione;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Prova> getProve() {
        return prove;
    }

    public void setProve(List<Prova> prove) {
        this.prove = prove;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }
}
