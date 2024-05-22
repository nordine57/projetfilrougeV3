package com.example.projetfilrouge.Service;

import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailUtilisateurSercice {



    @Autowired
    UtilisateurDao utilisateurDao;

    public List<String> getAllUserEmails() {
        // Récupère tous les utilisateurs et extrait leurs adresses e-mail
        return utilisateurDao.findAll().stream()
                .map(Utilisateur::getEmail)
                .collect(Collectors.toList());
    }


}
