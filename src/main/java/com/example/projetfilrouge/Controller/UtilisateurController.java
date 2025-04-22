package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.RoleDao;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Service.EmailService;
import com.example.projetfilrouge.securiter.AppUserDetails;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.securiter.IsUser;
import com.example.projetfilrouge.view.UtilisateurAvecCommandeView;
import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    GarageDAO garageDAO;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;


    @GetMapping("/utilisateur/liste")
    @JsonView(UtilisateurView.class)
    public List<Utilisateur> liste() {
        return utilisateurDao.findAll();
    }

    @GetMapping("/utilisateur/{id}")
    @JsonView(UtilisateurAvecCommandeView.class)
    public ResponseEntity<Utilisateur> get(@PathVariable int id) {

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(utilisateurOptional.get(),HttpStatus.OK);
    }


    @PostMapping("/utilisateur")
    @JsonView(UtilisateurView.class)
    @IsAdmin
    public ResponseEntity<Utilisateur> add(@Valid @RequestBody Utilisateur nouveauUtilisateur) {

        //C'est une mise à jour
        if(nouveauUtilisateur.getIdUser() != null) {

            Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(nouveauUtilisateur.getIdUser());

            //l'utilisateur tente de modifier un utilisateur qui n'existe pas/plus
            if(utilisateurOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


            utilisateurDao.save(nouveauUtilisateur);

            return new ResponseEntity<>(utilisateurOptional.get(), HttpStatus.OK);
        }

        utilisateurDao.save(nouveauUtilisateur);

        return new ResponseEntity<>(nouveauUtilisateur, HttpStatus.CREATED);
    }

//    @PostMapping("/utilisateur")
//    public ResponseEntity<Utilisateur> add (@RequestBody Utilisateur nouveauUtilisateur) {
//        nouveauUtilisateur.setId(null);
//        utilisateurDao.save(nouveauUtilisateur);
//        return new ResponseEntity<>(nouveauUtilisateur, HttpStatus.CREATED);
//    }
//
    @PutMapping("/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Map<String, Object>> update (@RequestBody Utilisateur utilisateurAmodifier, @PathVariable int id,@AuthenticationPrincipal AppUserDetails user) {
        utilisateurAmodifier.setIdUser(id);

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(utilisateurAmodifier.getIdUser());

        //l'utilisateur tente de modifier un utilisateur qui n'existe pas/plus
        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        utilisateurAmodifier.setMotDePasse(bCryptPasswordEncoder.encode(utilisateurAmodifier.getMotDePasse()));


        Garage garage = new Garage(); // par exemple, en utilisant l'identifiant du garage
        // Rechercher le garage dans la base de données pour s'assurer qu'il existe
        Garage GarageExist = garageDAO.findById(garage.getIdGarage()).orElse(null);

        if (GarageExist == null) {
            // Gérer le cas où le garage n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le garage n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le garage récupéré à l'utilisateur
        utilisateurAmodifier.setGarage(GarageExist);

        // Assigner le rôle récupéré à l'utilisateur
        utilisateurAmodifier.setRole(user.getUtilisateur().getRole());

       utilisateurDao.save(utilisateurAmodifier);
        return new ResponseEntity<>(Map.of("reussi", "utilisateur modifier."), HttpStatus.OK);
    }

    @DeleteMapping("/utilisateur/{id}")
    @JsonView(UtilisateurView.class)
    @IsAdmin
    public ResponseEntity<Utilisateur> delete (@PathVariable int id) {

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(id);

        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        utilisateurDao.deleteById(id);

        return new ResponseEntity<>(utilisateurOptional.get(),HttpStatus.OK);
    }

    @JsonView(UtilisateurView.class)
    @PostMapping("/utilisateur/contact")
    public ResponseEntity<String> add(
            String mail,
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message
            ) throws MessagingException {
        mail = "nordine.nair@gmail.com";
        message = message + " Le mail de la personne qui a renseigné ce message est " + email;
        // Envoyer l'email
        emailService.sendEmail(mail, subject, message);

        // Vous pouvez également enregistrer le message de contact dans la base de données si nécessaire

        return new ResponseEntity<>(HttpStatus.OK);
    }

}