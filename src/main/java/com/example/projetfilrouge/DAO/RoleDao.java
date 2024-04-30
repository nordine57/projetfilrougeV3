package com.example.projetfilrouge.DAO;

import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {


}
