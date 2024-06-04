package com.example.projetfilrouge.Service;

import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;



    @Autowired
    UtilisateurDao utilisateurDao;
// Assurez-vous d'avoir un UserService pour récupérer les adresses email

    public void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        emailSender.send(message);
    }

    public void sendNotificationToAllUsers(String subject, String text) throws MessagingException {
        List<String> allUserEmails = getAllUserEmails();
        for (String email : allUserEmails) {
            sendEmail(email, subject, text);
        }
    }

    public List<String> getAllUserEmails() {
        // Récupère tous les utilisateurs et extrait leurs adresses e-mail
        return utilisateurDao.findAll().stream()
                .map(Utilisateur::getEmail)
                .collect(Collectors.toList());
    }
}





