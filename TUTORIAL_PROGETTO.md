# Tutorial Rapido Progetto CRUD PrimeFaces

## 1) Prerequisiti minimi
- Git installato.
- JDK 17 installato e variabile `JAVA_HOME` configurata.
- VS Code installato.
- Connessione Internet (al primo avvio Maven scarica dipendenze).

## 2) Estensioni VS Code consigliate (necessarie per lavorare bene)
- `vscjava.vscode-java-pack` (Extension Pack for Java)
- `vmware.vscode-boot-dev-pack` (Spring Boot Extension Pack)
- `redhat.vscode-xml` (supporto XML, utile per `pom.xml`)
- `GitHub.vscode-pull-request-github` (collaborazione GitHub in editor)

## 3) Setup base del progetto (clone e import)
1. Clona il repository:
```powershell
git clone <URL_REPOSITORY>
cd crud-primefaces
```
2. Apri la cartella in VS Code:
```powershell
code .
```
3. Quando VS Code rileva Maven/Java, conferma:
- `Yes` su import/sync del progetto Maven.
- Eventuale `Reload` Maven quando richiesto.

## 4) Dipendenze e `gitignore` (lavoro in team)
Il repository ignora file generati/locali, ad esempio:
- `target/`
- `.vscode/`
- `.idea/`
- `.classpath`, `.project`, ecc.

Cosa significa in pratica:
- Ogni collaboratore rigenera in locale dipendenze e build output.
- Non vanno versionati artefatti compilati o impostazioni IDE personali.

Primo download dipendenze:
```powershell
.\mvnw.cmd -q -DskipTests dependency:resolve
```
oppure direttamente build/test:
```powershell
.\mvnw.cmd clean test
```

Nota:
- Anche se `.mvn/wrapper/maven-wrapper.jar` e ignorato, `mvnw` puo riscaricare i componenti necessari al primo run.

## 5) Avvio applicazione
Avvio con Maven Wrapper:
```powershell
.\mvnw.cmd spring-boot:run
```

Endpoint utili dopo l'avvio:
- App: `http://localhost:8080/`
- Console H2: `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:esamedb`
  - User: `sa`
  - Password: (vuota)

## 6) Routine consigliata per collaborazione GitHub
1. Aggiorna branch locale:
```powershell
git pull
```
2. Verifica build prima del commit:
```powershell
.\mvnw.cmd clean test
```
3. Commit solo di codice/config necessari (non `target/`, non settaggi locali IDE).
4. Apri Pull Request con descrizione delle modifiche.

## 7) Configurazione base gia impostata nel progetto
- JSF welcome page: `spring.mvc.welcome-page=index.xhtml`
- H2 in-memory configurato.
- JPA auto update schema: `spring.jpa.hibernate.ddl-auto=update`
- SQL logging attivo: `spring.jpa.show-sql=true`
- H2 console attiva.
