package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.securiter.IsUser;
import com.example.projetfilrouge.view.UtilisateurAvecCommandeView;
import com.example.projetfilrouge.view.UtilisateurView;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UtilisateurController {

    @Autowired
    UtilisateurDao utilisateurDao;


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
    public ResponseEntity<Utilisateur> update (@RequestBody Utilisateur utilisateurAmodifier, @PathVariable int id) {
        utilisateurAmodifier.setIdUser(id);

        Optional<Utilisateur> utilisateurOptional = utilisateurDao.findById(utilisateurAmodifier.getIdUser());

        //l'utilisateur tente de modifier un utilisateur qui n'existe pas/plus
        if(utilisateurOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       utilisateurDao.save(utilisateurAmodifier);
        return new ResponseEntity<>(utilisateurOptional.get(), HttpStatus.OK);
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
}