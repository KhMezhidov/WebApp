# Like Hero To Zero - CO2 Emissions Manager
Enterprise Webanwendung

## Technologien
* **Frontend:** Jakarta Server Faces (JSF 4.0), PrimeFaces 14
* **Backend:** Java Enterprise (CDI, Managed Beans)
* **Datenbank:** MySQL, Hibernate (JPA)
* **Server:** Apache TomEE

## Features
* Anzeige weltweiter Emissionsdaten (World Bank Import).
* Login-System für Wissenschaftler (Session Management).
* CRUD-Funktionalität (Erstellen, Lesen, Bearbeiten, Löschen) im internen Bereich.
* Inline-Editing in Datentabellen.

* ## Installation & Start

Um das Projekt lokal auszuführen:

1.  **Voraussetzungen:**
    * JDK 21
    * Apache TomEE 10 (Plume/Plus)
    * MySQL Server 8.4

2.  **Datenbank einrichten:**
    * MySQL starten.
    * Eine leere Datenbank anlegen: `CREATE DATABASE hero_to_zero_db;`
    * (Optional) Benutzer/Passwort in der Server-Config (`tomee.xml`) entsprechend anpassen.

3.  **Projekt starten:**
    * Repository klonen oder herunterladen.
    * In IntelliJ IDEA öffnen.
    * `pom.xml` als Maven-Projekt laden.
    * Server-Konfiguration (TomEE) erstellen und Artefakt `Projektarbeit:war exploded` hinzufügen.
    * Server starten.

4.  **Login:**
    * URL: `http://localhost:8080/Projektarbeit/`
    * Beim ersten Start: Über "Registrieren" einen neuen Benutzer anlegen.
