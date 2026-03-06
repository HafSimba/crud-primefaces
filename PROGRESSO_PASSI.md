# Registro Progressivo Modifiche

Questo file va aggiornato ad ogni nuovo step del progetto.
Obiettivo: tracciare in modo chiaro cosa e stato inserito/modificato nei file.

## Regole di aggiornamento
- Aggiungere una nuova voce in cima alla sezione `Storico`.
- Indicare sempre file toccati e inserimenti principali.
- Se uno step modifica piu file, elencarli tutti.

## Storico

### 2026-03-06 - Hotfix Avvio Runtime (Porta e Mapping JSF)
File modificati:
- `src/main/resources/application.properties`

Modifica effettuata:
- Corretto mapping FacesServlet da `*.xhtml,/` a `*.xhtml` per evitare intercettazione di endpoint tecnici come `/error`.

Esito verifica:
- Applicazione avviata con `./mvnw.cmd spring-boot:run`.
- Verifica HTTP OK su `http://localhost:8080/index.xhtml` (status `200`).

### 2026-03-06 - Refactor Architettura JSF Pura + Bean View Scope
File modificati:
- `src/main/resources/application.properties`
- `src/main/java/it/vismaragiaffreda/crud_primefaces/jsf/StudenteBean.java`
- `src/main/java/it/vismaragiaffreda/crud_primefaces/jsf/CorsoBean.java`
- `src/main/java/it/vismaragiaffreda/crud_primefaces/jsf/ProvaBean.java`
- `src/main/java/it/vismaragiaffreda/crud_primefaces/jsf/StatiStudentiBean.java`
- `src/main/java/it/vismaragiaffreda/crud_primefaces/controller/HomeController.java` (rimosso)
- `src/main/resources/templates/index.xhtml` (rimosso)
- `src/main/resources/templates/studenti/lista.xhtml` (rimosso)
- `src/main/resources/templates/studenti/inserisci.xhtml` (rimosso)
- `src/main/resources/templates/studenti/stato.xhtml` (rimosso)
- `src/main/resources/templates/corsi/lista.xhtml` (rimosso)
- `src/main/resources/templates/corsi/inserisci.xhtml` (rimosso)
- `src/main/resources/templates/prove/registrazione.xhtml` (rimosso)

Modifiche effettuate:
- Eliminata architettura mista MVC/JSF, mantenendo solo frontend JSF sotto `META-INF/resources`.
- Rimossi property MVC (`spring.mvc.view.*`, `spring.mvc.pathmatch.*`, `spring.mvc.welcome-page-location`).
- Aggiunta configurazione JoinFaces per mapping servlet JSF su root e `.xhtml`:
  - `joinfaces.faces-servlet.url-mappings=*.xhtml,/`
- Refactor scope bean JSF da `@RequestScope` a `@Scope("view")` per UI stateful.

Risultato:
- Architettura coerente con approccio JSF puro.
- Stato UI mantenuto meglio durante interazioni sulla stessa view (datatable/form PrimeFaces).

### 2026-03-06 - Fase 9 - Refinement UI e Styling Finale
File modificati:
- `src/main/resources/META-INF/resources/index.xhtml`
- `src/main/resources/META-INF/resources/studenti/lista.xhtml`
- `src/main/resources/META-INF/resources/studenti/inserisci.xhtml`
- `src/main/resources/META-INF/resources/studenti/stato.xhtml`
- `src/main/resources/META-INF/resources/corsi/lista.xhtml`
- `src/main/resources/META-INF/resources/corsi/inserisci.xhtml`
- `src/main/resources/META-INF/resources/prove/registrazione.xhtml`

Modifiche effettuate:

**Google Font Integration**:
- Aggiunto link: `https://fonts.googleapis.com/css2?family=LINE+Seed+JP`
- Applicato font-family "LINE Seed JP" con font-weight 800 (extra bold) a tutti gli h1

**Color Scheme Update**:
- Card background: da gradienti (#6320EE, #7c3aed, #0891b2) a colore solido **#388697** (teal)
- Bottoni: da gradienti a colore solido **#32936F** (verde)
- Bottoni hover: **#287557** (verde scuro)
- Rimossi tutti i bordi neri (2px solid #1a1a1a) dalle card

**Animation Removal**:
- Rimossi: `transition: all 0.3s ease` dai selettori .card e .card-large
- Rimossi: `transform: translateY(-8px)` dagli hover
- Bottoni rimangono fermi senza effetti di scala

**Button Interaction**:
- Card container: `cursor: default` (non cliccabili)
- Solo bottoni freccia: `cursor: pointer` (cliccabili)
- Aggiunti `!important` ai selettori .btn-primary per sovrascrivere PrimeFaces

**Bug Fixes**:
- Corretto selettore dataList in studenti/stato.xhtml: `var="mod"` → `var="modulo"` (mod è parola riservata EL)
- Riparati selettori CSS: `&gt;` → `>` in tutti i file (errore di parsing XHTML entity in CSS)

**CSS Selector Fixes**:
- `.ui-datatable .ui-datatable-thead > tr > th` (da &gt;)
- `.ui-datatable .ui-datatable-tbody > tr > td` (da &gt;)
- `.ui-datatable .ui-datatable-tbody > tr:hover` (da &gt;)
- `.ui-selectonemenu > .ui-selectonemenu-label` (da &gt;)

Risultato: Dashboard moderna con colori coerenti, font personalizzato, interazione intuitiva (solo freccie cliccabili), senza animazioni, layout stabile.

### 2026-03-06 - Fase 8 - Pagine XHTML Semplici (Frontend Base)
File creati:
- `src/main/resources/templates/index.xhtml` - Homepage
- `src/main/resources/templates/studenti/lista.xhtml` - Lista studenti
- `src/main/resources/templates/studenti/inserisci.xhtml` - Form nuovo studente
- `src/main/resources/templates/studenti/stato.xhtml` - Stato studenti
- `src/main/resources/templates/corsi/lista.xhtml` - Lista corsi
- `src/main/resources/templates/corsi/inserisci.xhtml` - Form nuovo corso
- `src/main/resources/templates/prove/registrazione.xhtml` - Registrazione prove

Correzioni pom.xml:
- Aggiornato JoinFaces da 5.2.0 a 6.0.3 (compatibile con Spring Boot 4.0.3)
- Rimosso spring-boot-h2console (inesistente)
- Aggiunto spring-boot-starter-web
- Stabilizzato spring-boot-starter-test

Pagine create: SEMPLICI E FUNZIONALI (senza CSS elaborato)
- Input/output di base con JSF + PrimeFaces
- Integrazione completa con ManageBean
- Pronte per personalizzazione estetica

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
