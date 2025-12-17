package com.example.demo;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    @PersistenceContext(unitName = "heroUnit")
    private EntityManager em;

    private String username;
    private String password;
    private AppUser currentUser;

    //Methoden

    // 1. Einloggen
    public String doLogin() {
        List<AppUser> results = em.createQuery(
                        "SELECT u FROM AppUser u WHERE u.username = :user AND u.password = :pass", AppUser.class)
                .setParameter("user", username)
                .setParameter("pass", password)
                .getResultList();

        if (!results.isEmpty()) {
            currentUser = results.get(0);
            return "internal.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Falsch!"));
            return null;
        }
    }

    // 2. Ausloggen
    public String doLogout() {
        currentUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    // 3. Prüfen - eingeloggt
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // Getter und Setter
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public AppUser getCurrentUser() { return currentUser; }
}