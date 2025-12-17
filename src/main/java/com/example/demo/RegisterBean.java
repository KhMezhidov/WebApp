package com.example.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped

public class RegisterBean {
    @PersistenceContext(unitName = "heroUnit")
    private EntityManager em;

    private AppUser newUser = new AppUser();

    @Transactional
    public String doRegister() {
        try {
            long count = em.createQuery("select count(u) from AppUser u WHERE u.username = :name" ,  Long.class)
                    .setParameter("name", newUser.getUsername())
                    .getSingleResult();
            if (count > 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername vergeben!"));
                return null;

            }

            //Speichern
            em.persist(newUser);

            //Zurück zum Login
            return  "login.xhtml?faces-redirect=true";

        }  catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Registrierung fehlgeschlagen"));
            return null;

        }

    }


    //Getter und Setter
    public AppUser getNewUser() {
        return newUser;
    }

    public void setNewUser(AppUser newUser) {
        this.newUser = newUser;
    }
}
