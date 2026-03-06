# Registro Progressivo Modifiche

Questo file va aggiornato ad ogni nuovo step del progetto.
Obiettivo: tracciare in modo chiaro cosa e stato inserito/modificato nei file.

## Regole di aggiornamento
- Aggiungere una nuova voce in cima alla sezione `Storico`.
- Indicare sempre file toccati e inserimenti principali.
- Se uno step modifica piu file, elencarli tutti.

## Storico

### 2026-03-06 - Fase 8 - Creazione Pagine XHTML + PrimeFaces (Frontend)
File creati:
- Layout master: `src/main/resources/templates/layout/template.xhtml`
- Homepage: `src/main/resources/templates/index.xhtml`
- Studenti: `lista.xhtml`, `inserisci.xhtml`, `stato.xhtml`
- Corsi: `lista.xhtml`, `inserisci.xhtml`
- Prove: `registrazione.xhtml`
- CSS: `src/main/resources/static/css/style.css`

Inserimenti effettuati:

**Layout Master**:
- Struttura di base HTML5 + JSF
- Header con titolo e sottotitolo
- Navbar con PrimeFaces menubar (navigazione principale)
- Sezione content (ui:insert per le pagine figlie)
- Footer
- Default ui:composition per tutte le pagine

**Index.xhtml (Homepage)**:
- Pagina di benvenuto
- Descrizione funzionalità principali
- Criteri di valutazione (voto >= 18 per superamento)
- Pulsanti di navigazione rapida

**Pagine Studenti**:
- **lista.xhtml**: DataTable con tutti gli studenti, CRUD inline (modifica/elimina con dialog)
- **inserisci.xhtml**: Form per inserimento nuovo studente (nome, cognome, email)
- **stato.xhtml**: Tabella espandibile con dettagli ogni studente:
  - Media voti (prove superate)
  - Moduli mancanti (corsi non superati)
  - Statistiche (superati, falliti, total prove)

**Pagine Corsi**:
- **lista.xhtml**: DataTable con tutti i corsi, CRUD inline
- **inserisci.xhtml**: Form per inserimento nuovo corso (nome, descrizione)

**Pagine Prove**:
- **registrazione.xhtml**: Form registrazione prova con:
  - SelectOneMenu per studente (dropdown)
  - SelectOneMenu per corso (dropdown)
  - SpinnerNumber per voto (1-30)
  - Validazioni lato client
  - Badge colore per stato (SUPERATO/FALLITO)
  - Tabella prove registrate

**CSS**:
- Tema moderno con gradiente viola (#667eea, #764ba2)
- Responsive design (mobile-friendly)
- Stili per datatable, buttons, forms, messages, badges, dialog
- Transitions e hover effects

Note:
- Tutti gli XHTML usano JSF + PrimeFaces
- Integrati i Managed Bean (StudenteBean, CorsoBean, ProvaBean, StatiStudentiBean)
- Validazione lato server mantenuta nei bean
- Pagine responsive per mobile/tablet
- Colori e icone accattivanti (emoji + PrimeFaces icons)
- Tutti i requisiti del task implementati e visibili nel frontend

### 2026-03-06 - Fase 7 - Creazione Managed Bean JSF (Controller Layer)
File creati:
- `src/main/java/.../jsf/StudenteBean.java`
- `src/main/java/.../jsf/CorsoBean.java`
- `src/main/java/.../jsf/ProvaBean.java`
- `src/main/java/.../jsf/StatiStudentiBean.java`

Inserimenti effettuati:

**StudenteBean**:
- loadStudenti() → carica lista studenti
- saveStudente() → salva nuovo studente con validazione
- deleteStudente(Long id) → elimina studente
- newStudente(), selectStudente(Studente) → gestione form
- @Component @RequestScope per integrazione JSF

**CorsoBean**:
- loadCorsi() → carica lista corsi
- saveCorso() → salva nuovo corso con validazione
- deleteCorso(Long id) → elimina corso
- newCorso(), selectCorso(Corso) → gestione form
- @Component @RequestScope

**ProvaBean**:
- loadProve() → carica lista prove
- loadDatiRegistrazione() → carica studenti e corsi per dropdown
- registraProva() → registra prova con validazione voto (1-30)
- deleteProva(Long id) → elimina prova
- Validazione: studente, corso, voto obbligatori
- Impostazione automatica data (LocalDate.now())
- Gestione messaggi di errore per UI

**StatiStudentiBean**:
- StatoStudente: inner class wrapper con aggregazione dati
- getStatiStudenti() → lista di StatoStudente per tutti gli studenti
- getStatoStudente(Studente) → dettaglio stato di uno studente
- Calcoli automatici per ogni studente:
  - mediaVoti (usando StudenteService.calcoloMediaStudente)
  - moduliMancanti (usando StudenteService.getModuliMancanti)
  - corsiSuperati, corsiFalliti, totalProve
- Metodi helper per formattazione (getMediaVotiFormatted, getStatoSintesi)

Note:
- Tutti i bean annotati con @Component e @RequestScope (Spring scope per JSF)
- Iniezione dipendenza tramite @Autowired
- Serializable per mantenere compatibilità session/view scope
- Validazioni lato server per integrità dati
- Pronti per collegamento alle pagine XHTML (Fase 8)

### 2026-03-06 - Fase 6 - Creazione Service Layer (Business Logic)
File creati:
- `src/main/java/.../service/StudenteService.java`
- `src/main/java/.../service/CorsoService.java`
- `src/main/java/.../service/ProvaService.java`

Inserimenti effettuati:

**StudenteService**:
- CRUD: getAllStudenti, getStudenteById, saveStudente, deleteStudente
- Query: findByEmail, findByNomeAndCognome
- **Business Logic**:
  - `calcoloMediaStudente(Studente)` → calcola media voti superati (voto >= 18)
  - `getModuliMancanti(Studente)` → lista dei corsi non ancora superati
  - `contaCorsiSuperati(Studente)` → conta prove riuscite
  - `contaCorsiFalliti(Studente)` → conta prove fallite
  - `getTotalProveStudente(Studente)` → totale prove sostenute

**CorsoService**:
- CRUD: getAllCorsi, getCorsoById, saveCorso, deleteCorso
- Query: findByNome, findByNomeIgnoreCase

**ProvaService**:
- CRUD: getAllProve, getProvaById, deleteProva
- `registraProva(Prova)` → salva prova con validazione voto (1-30)
- Query: getProveByStudente, getProveByCorso, cercaProva
- `haStudenteSuperatoCorso(Studente, Corso)` → verifica se superato (voto >= 18)
- `contaTotalProve(Studente)` → totale prove studente

Note:
- Tutti i servizi annotati con @Service per component scanning
- Iniezione dipendenza tramite @Autowired
- Logica concentrata nel Service (separazione di concerns)
- StudenteService contiene tutte le operazioni richieste dal task (media, moduli mancanti, etc.)

### 2026-03-06 - Fase 5 - Creazione Repository Spring Data JPA
File creati:
- `src/main/java/.../repository/StudenteRepository.java`
- `src/main/java/.../repository/CorsoRepository.java`
- `src/main/java/.../repository/ProvaRepository.java`

Inserimenti effettuati:
- **StudenteRepository**: findByNome, findByCognome, findByEmail, findByNomeAndCognome
- **CorsoRepository**: findByNome, findByNomeIgnoreCase
- **ProvaRepository**: findByStudente, findByCorso, findByStudenteAndCorso, countByStudenteAndVotoLessThan, countByStudenteAndVotoGreaterThanEqual

Note:
- Tutti estendono `JpaRepository<Entity, Long>` per CRUD automatico
- Metodi query generati automaticamente da Spring Data JPA
- @Repository per permettere l'iniezione di dipendenza nei Service
- custom query methods per gestire ricerche comuni (utili per Fase 6)

### 2026-03-06 - Fase 4 - Creazione Entità JPA (Modello Dati)
File creati: 
- `src/main/java/.../entity/Studente.java`
- `src/main/java/.../entity/Corso.java`
- `src/main/java/.../entity/Prova.java`

Inserimenti effettuati:
- **Studente**: id (PK), nome, cognome, email + relazione 1:N con Prova
- **Corso**: id (PK), nome, descrizione + relazione 1:N con Prova
- **Prova**: id (PK), studente_id (FK), corso_id (FK), voto (trentesimi), data

Note:
- Uso jakarta.persistence (Spring Boot 4.0.3)
- Cascade.ALL per mantenere integrità referenziale
- FetchType.LAZY per relazioni 1:N, EAGER per N:1
- Inclusi costruttori, getter/setter, toString

### 2026-03-06 - Fase 3 - Configurazione iniziale `application.properties`
File aggiornato: `src/main/resources/application.properties`

Inserimenti effettuati:
- `spring.mvc.welcome-page=index.xhtml`
- `spring.datasource.url=jdbc:h2:mem:esamedb`
- `spring.datasource.driverClassName=org.h2.Driver`
- `spring.datasource.username=sa`
- `spring.datasource.password=`
- `spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`
- `spring.jpa.hibernate.ddl-auto=update`
- `spring.jpa.show-sql=true`
- `spring.h2.console.enabled=true`

Nota:
- Mantenuta la proprieta gia esistente: `spring.application.name=crud-primefaces`.

### 2026-03-06 - Fase 2 - Integrazione JSF + PrimeFaces via JoinFaces
File aggiornato: `pom.xml`

Inserimenti/modifiche effettuati:
- In `properties`: confermata/aggiunta `joinfaces.version=5.2.0`
- Aggiunto blocco `dependencyManagement` con:
  - `org.joinfaces:joinfaces-bom:${joinfaces.version}`
  - `type=pom`, `scope=import`
- In `dependencies`: impostata dipendenza:
  - `org.joinfaces:primefaces-spring-boot-starter`
