package com.example.demo;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject; // WICHTIG
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import java.util.List;

@Named
@RequestScoped
public class EmissionBean {

    @PersistenceContext(unitName = "heroUnit")
    private EntityManager em;

    @Inject // Zugriff auf Login-Seccion
    private LoginBean loginBean;

    private EmissionData newEmission = new EmissionData();


    //Methoden

    // 1. Alle Daten
    public List<EmissionData> getAllEmissions() {
        return em.createQuery("SELECT e FROM EmissionData e ORDER BY e.id ASC", EmissionData.class).getResultList();
    }

    // 2. Meine Datensätze
    public List<EmissionData> getMyEmissions() {
        if (!loginBean.isLoggedIn()) return List.of(); // Sicherheit

        return em.createQuery("SELECT e FROM EmissionData e WHERE e.creator.id = :uid ORDER BY e.id DESC", EmissionData.class)
                .setParameter("uid", loginBean.getCurrentUser().getId())
                .getResultList();
    }

    // 3. Speichern (mit User-Stempel)
    @Transactional
    public String add() {

        newEmission.setCreator(loginBean.getCurrentUser());

        em.persist(newEmission);
        newEmission = new EmissionData();

        return "internal.xhtml?faces-redirect=true";
    }

    // 4. Löschen
    @Transactional
    public void delete(EmissionData e) {
        EmissionData toDelete = em.find(EmissionData.class, e.getId());
        if (toDelete != null) em.remove(toDelete);
    }

    // 5. Bearbeiten
    @Transactional
    public void onRowEdit(RowEditEvent<EmissionData> event) {
        em.merge(event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Gespeichert."));
    }

    // 6. Bearbeitung abbrechen
    public void onRowCancel(RowEditEvent<EmissionData> event) {}

    // Getter/Setter newEmission
    public EmissionData getNewEmission() { return newEmission; }
    public void setNewEmission(EmissionData newEmission) { this.newEmission = newEmission; }
}