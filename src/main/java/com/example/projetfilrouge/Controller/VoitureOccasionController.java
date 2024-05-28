package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.DAO.VoitureOccasionDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Model.VoitureOccasion;
import com.example.projetfilrouge.Service.EmailService;
import com.example.projetfilrouge.Service.EmailUtilisateurSercice;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.securiter.IsUser;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class VoitureOccasionController {

    @Autowired
    VoitureOccasionDao voitureOccasionDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    GarageDAO garageDao;

    List<List> myList = new ArrayList();


    @GetMapping("/voitureoccasion/liste")
    public List<VoitureOccasion> liste() {

        return voitureOccasionDao.findAll();

    }


    @PostMapping("/voitureoccasion")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody VoitureOccasion nouveauVoitureOccasion) throws MessagingException {

        Garage garage = new Garage(); // par exemple, en utilisant l'identifiant du garage
        // Rechercher le garage dans la base de données pour s'assurer qu'il existe
        Garage GarageExist = garageDao.findById(garage.getIdGarage()).orElse(null);

        if (GarageExist == null) {
            // Gérer le cas où le garage n'est pas trouvé
            return new ResponseEntity<>(Map.of("error", "Le garage n'existe pas."), HttpStatus.BAD_REQUEST);
        }

        // Assigner le garage récupéré à l'utilisateur
        nouveauVoitureOccasion.setGarage(GarageExist);

        voitureOccasionDao.save(nouveauVoitureOccasion);
        try {
            String subject = "Nouveau voiture disponible en garage !";
            String text = "Nous sommes heureux de vous annoncer que la voiture ".concat(nouveauVoitureOccasion.getNomVoitureOccasion()).concat("est maintenant disponible en boutique.") ;
            emailService.sendNotificationToAllUsers(subject, text);
        } catch (MessagingException e) {
            // Gérez les exceptions d'envoi d'email
            e.printStackTrace();
        }
        return new ResponseEntity<>(Map.of("message","voiture ajouter créé"), HttpStatus.CREATED);

        // Envoyez des notifications par email à tous les utilisateurs



    }

    @IsAdmin
    @GetMapping("/voitureoccasion/{id}")
    public ResponseEntity<VoitureOccasion> get(@PathVariable long id) {

        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(id);

        if(voitureOccasionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(voitureOccasionOptional.get(),HttpStatus.OK);
    }



//    @PostMapping("/voitureOccasion")
//    public ResponseEntity<VoitureOccasion> add (@RequestBody VoitureOccasion nouveauVoitureOccasion) {
//        nouveauVoitureOccasion.setId(null);
//        voitureOccasionDao.save(nouveauVoitureOccasion);
//        return new ResponseEntity<>(nouveauVoitureOccasion, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/voitureOccasion/{id}")
//    public ResponseEntity<VoitureOccasion> update (@RequestBody VoitureOccasion voitureOccasionAmodifier, @PathVariable int id) {
//        voitureOccasionAmodifier.setId(id);
//
//        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(voitureOccasionAmodifier.getId());
//
//        //l'utilisateur tente de modifier un voitureOccasion qui n'existe pas/plus
//        if(voitureOccasionOptional.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        voitureOccasionDao.save(voitureOccasionAmodifier);
//        return new ResponseEntity<>(voitureOccasionOptional.get(), HttpStatus.OK);
//    }

    @IsAdmin
    @DeleteMapping("/voitureoccasion/{id}")
    public ResponseEntity<VoitureOccasion> delete (@PathVariable long id) {

        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(id);

        if(voitureOccasionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        voitureOccasionDao.deleteById(id);

        return new ResponseEntity<>(voitureOccasionOptional.get(),HttpStatus.OK);
    }
}