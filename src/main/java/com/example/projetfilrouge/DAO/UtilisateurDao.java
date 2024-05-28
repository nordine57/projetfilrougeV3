package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);

    //@Query("SELECT u FROM Utilisateur u JOIN u.role r WHERE r.nom = :roleName")
    //List<Utilisateur> findByRoleName(@Param("roleName") String roleName);
    






}
