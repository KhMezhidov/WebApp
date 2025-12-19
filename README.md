# Like Hero To Zero - CO2 Emissions Manager

Eine Enterprise Webanwendung zur Verwaltung und Analyse von weltweiten CO2-Emissionsdaten. Entwickelt als Fallstudie im Modul Software Engineering.

Die Anwendung ermöglicht es, öffentliche Emissionsdaten einzusehen und bietet Wissenschaftlern einen geschützten Bereich, um eigene Daten zu erfassen und zu verwalten.

---

## 📊 Datenquelle & Lizenz

Die in diesem Projekt verwendeten CO2-Emissionsdaten stammen aus dem Repository von **Our World in Data** (OWID).

* **Datensatz:** Data on CO2 and greenhouse gas emissions
* **Hauptquelle:** Global Carbon Project (GCP) & Jones et al.
* **Autoren:** Pablo Rosado, Hannah Ritchie, Max Roser, Edouard Mathieu, Bobbie Macdonald
* **Lizenz:** Creative Commons Attribution 4.0 (CC-BY 4.0)
* **URL:** [https://github.com/owid/co2-data](https://github.com/owid/co2-data)

---

## 🛠 Technologien

Das Projekt basiert auf einer modernen **3-Schichten-Architektur (MVC)**:

* **Frontend:** Jakarta Server Faces (JSF 4.0), PrimeFaces 14.0
* **Backend:** Jakarta EE (CDI, Managed Beans), Java 21
* **Datenbank:** MySQL 8.4, Hibernate (JPA)
* **Server:** Apache TomEE 10 (Plume/Plus)
* **Build Tool:** Maven

---

## ✨ Features

* **Öffentlicher Bereich:**
    * Anzeige von tausenden Emissionsdatensätzen (Importiert von Our World in Data).
    * Filterung und Sortierung der Daten in Echtzeit.
* **Interner Bereich (für Wissenschaftler):**
    * Sicherer Login & Logout (Session Management).
    * **CRUD-Funktionalität:** Erstellen, Lesen, Aktualisieren und Löschen eigener Datensätze.
    * **Inline-Editing:** Bearbeitung von Werten direkt in der Datentabelle.
    * Automatische Zuordnung: Neue Datensätze werden dem eingeloggten Nutzer zugewiesen.
* **Benutzerverwaltung:**
    * Registrierung neuer Nutzer über die Oberfläche.

---

## 🚀 Installation & Start

### 1. Voraussetzungen
* Java Development Kit (JDK) 21
* IntelliJ IDEA (Ultimate empfohlen)
* MySQL Server
* Apache TomEE 10

### 2. Datenbank einrichten
1.  Starten Sie Ihren MySQL Server.
2.  Erstellen Sie eine leere Datenbank:
    ```sql
    CREATE DATABASE hero_to_zero_db;
    ```
3.  **WICHTIG (Daten-Import):**
    * Im Ordner `ressources/` dieses Repositories finden Sie die Datei `co2_data.csv`.
    * Importieren Sie diese Datei (z. B. via MySQL Workbench "Table Data Import Wizard") in die Tabelle `emissions`.
    * *Mapping:* `country` -> `country`, `year` -> `year`, `co2` -> `co2_value`.

### 3. Server konfigurieren (IntelliJ)
* Das Projekt als Maven-Projekt öffnen.
* Eine neue "TomEE Server" Run-Configuration erstellen.
* Im Reiter "Deployment" das Artefakt `Projektarbeit:war exploded` hinzufügen.
* Den "Application Context" auf `/Projektarbeit`setzen.

### 4. Starten
* Den Server starten.
* Folgende URL aufrufen: `http://localhost:8080/Projektarbeit/`

---

## 🗄 Datenbank Struktur

Die Anwendung nutzt zwei zentrale Tabellen:

### 1. `emissions` (Emissionsdaten)
Beinhaltet die CO2-Werte.
* **Quellen:** Our World in Data (Import) und User-Eingaben.
* **Unterscheidung:** Importierte Daten haben `creator_id = NULL`.

### 2. `users` (Benutzer)
Verwaltet die Zugangsdaten der Benutzer.
* **Spalten:** `id` (Auto-Increment), `username`, `password`.
* **Hinweis:** Diese Tabelle wird beim ersten Start durch Hibernate automatisch erstellt (wenn in `persistence.xml` konfiguriert). Sie ist initial leer. Bitte die **Registrieren-Funktion** auf der Login-Seite nutzen, um einen ersten Benutzer anzulegen.

---

## 📸 Screenshots

### Startseite (Öffentlicher Bereich)
![Startseite](screenshots/startseite.png)

### Login
![Login Screen](screenshots/login.png)

### Interner Bereich (Datenverwaltung & Inline-Editing)
![Interner Bereich](screenshots/internal.png)

### Datenbank (Beweis des Datenbestands)
![Datenbank Ansicht](screenshots/database_emissions.png)
