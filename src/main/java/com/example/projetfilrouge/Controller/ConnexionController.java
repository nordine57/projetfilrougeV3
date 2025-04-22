package com.example.projetfilrouge.Controller;

import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.RoleDao;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Service.EmailService;
import com.example.projetfilrouge.securiter.AppUserDetails;
import com.example.projetfilrouge.securiter.JwtUtils;
import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@CrossOrigin
public class ConnexionController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    GarageDAO garageDAO;

    @Autowired
    private EmailService emailService;

    @PostMapping("/connexion")
    public ResponseEntity<Map<String, Object>> connexion(@RequestBody Utilisateur utilisateur) {

        try {
            UserDetails userDetails = (UserDetails) authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            utilisateur.getEmail(),
                            utilisateur.getMotDePasse())).getPrincipal();

            return new ResponseEntity<>(Map.of("jwt",jwtUtils.generateToken(userDetails)), HttpStatus.OK);

        } catch (AuthenticationException ex) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }
    }

    @PostMapping("/inscription")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody Utilisateur utilisateur) throws MessagingException {

        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        // Créer une instance de Role avec l'identifiant du rôle à attribuer à l'utilisateur
        Role roleUtilisateur = new Role(); // par exemple, en utilisant l'identifiant du rôle
        // Rechercher le rôle dans la base de données pour s'assurer qu'il existe
        Role roleExist = roleDao.findById(roleUtilisateur.getId()).orElse(null);

        if (roleExist == null) {
            // Gérer le cas où le rôle n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le rôle n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        Garage garage = new Garage(); // par exemple, en utilisant l'identifiant du garage
        // Rechercher le garage dans la base de données pour s'assurer qu'il existe
        Garage GarageExist = garageDAO.findById(garage.getIdGarage()).orElse(null);

        if (GarageExist == null) {
            // Gérer le cas où le garage n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le garage n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le garage récupéré à l'utilisateur
        utilisateur.setGarage(GarageExist);

        // Assigner le rôle récupéré à l'utilisateur
        utilisateur.setRole(roleExist);
        utilisateurDao.save(utilisateur);



        return new ResponseEntity<>(Map.of("message","utilisateur créé"), HttpStatus.CREATED);

    }

    @PostMapping("/inscriptionadmin")
    public ResponseEntity<Map<String, Object>> inscriptionAdmin (@RequestBody Utilisateur utilisateur) throws MessagingException {

        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        // Créer une instance de Role avec l'identifiant du rôle à attribuer à l'utilisateur

        Garage garage = new Garage(); // par exemple, en utilisant l'identifiant du garage
        // Rechercher le garage dans la base de données pour s'assurer qu'il existe
        Garage GarageExist = garageDAO.findById(garage.getIdGarage()).orElse(null);

        if (GarageExist == null) {
            // Gérer le cas où le garage n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le garage n'existe pas."), HttpStatus.BAD_REQUEST);
        }
        Role roleExist = roleDao.findById(utilisateur.getRole().getId()).orElse(null);

        if (roleExist == null) {
            // Gérer le cas où le rôle n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le rôle n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le garage récupéré à l'utilisateur
        utilisateur.setGarage(GarageExist);

        utilisateur.setRole(roleExist);
        utilisateurDao.save(utilisateur);



        return new ResponseEntity<>(Map.of("message","utilisateur créé"), HttpStatus.CREATED);

    }



    @GetMapping("/profil")
    @JsonView(UtilisateurView.class)
    public ResponseEntity<Utilisateur> profil (@AuthenticationPrincipal AppUserDetails userDetails){

        Utilisateur user = userDetails.getUtilisateur();
        //user.setListeCommandes(null);

        return new ResponseEntity<>(user,HttpStatus.OK);
    }



}
