# Registro Progressivo Modifiche

Questo file va aggiornato ad ogni nuovo step del progetto.
Obiettivo: tracciare in modo chiaro cosa e stato inserito/modificato nei file.

## Regole di aggiornamento
- Aggiungere una nuova voce in cima alla sezione `Storico`.
- Indicare sempre file toccati e inserimenti principali.
- Se uno step modifica piu file, elencarli tutti.

## Storico

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
