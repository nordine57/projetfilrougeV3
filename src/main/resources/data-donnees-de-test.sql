INSERT INTO role (nom) VALUES ('ROLE_ADMIN'),('ROLE_CLIENT'),('ROLE_TECH');

INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, role_id) VALUES
                                                                                      ('Nom1', 'Prenom1', 'email1@example.com', '$2a$10$82wSHZqkS/yjcZEMl9xrSepPQKoZb9cQUA.QcfBaCgBewuy0Nt4fm',  1),
                                                                                      ('Nom2', 'Prenom2', 'email2@example.com', '$2a$10$82wSHZqkS/yjcZEMl9xrSepPQKoZb9cQUA.QcfBaCgBewuy0Nt4fm', 2),
                                                                                      ('Nom3', 'Prenom3', 'email3@example.com', '$2a$10$82wSHZqkS/yjcZEMl9xrSepPQKoZb9cQUA.QcfBaCgBewuy0Nt4fm',  3);
                                                                                      ;

INSERT INTO `_voiture_occasion` (`prix_voiture_occasion`, `date_arriver`, `nom_voiture_occasion`, `description`, `image`)
VALUES ('12000', '2024-04-01 10:30:00',  'Renault Clio', 'Description de la Renault Clio', 'chemin/vers/image.jpg');
INSERT INTO `_voiture_occasion` (`prix_voiture_occasion`, `date_arriver`,`nom_voiture_occasion`, `description`, `image`)
VALUES ('15000', '2024-04-02 14:45:00', 'Volkswagen Golf', 'Description de la Volkswagen Golf', NULL);
INSERT INTO `_voiture_occasion` (`prix_voiture_occasion`, `date_arriver`, `nom_voiture_occasion`, `description`, `image`)
VALUES ('18000', '2024-04-03 09:00:00', 'Toyota Corolla', 'Description de la Toyota Corolla', 'chemin/vers/image.jpg');
INSERT INTO `_voiture_occasion` (`prix_voiture_occasion`, `date_arriver`, `nom_voiture_occasion`, `description`, `image`)
VALUES ('20000', '2024-04-04 11:20:00','Ford Focus', NULL, 'chemin/vers/image.jpg');
