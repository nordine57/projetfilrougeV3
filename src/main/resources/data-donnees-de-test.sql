INSERT INTO role (nom) VALUES ('admin'),('client'),('technicien');

INSERT INTO utilisateur (nom, prenom, email, mot_de_passe, administrateur, role_id) VALUES
                                                                                      ('Nom1', 'Prenom1', 'email1@example.com', 'motdepasse1', 0, 1),
                                                                                      ('Nom2', 'Prenom2', 'email2@example.com', 'motdepasse2', 0, 2),
                                                                                      ('Nom3', 'Prenom3', 'email3@example.com', 'motdepasse3', 0, 1),
                                                                                      ('Nom4', 'Prenom4', 'email4@example.com', 'motdepasse4', 0, 2),
                                                                                      ('Nom5', 'Prenom5', 'email5@example.com', 'motdepasse5', 1, 1),
                                                                                      ('Nom6', 'Prenom6', 'email6@example.com', 'motdepasse6', 0, 2),
                                                                                      ('Nom7', 'Prenom7', 'email7@example.com', 'motdepasse7', 0, 1),
                                                                                      ('Nom8', 'Prenom8', 'email8@example.com', 'motdepasse8', 0, 2),
                                                                                      ('Nom9', 'Prenom9', 'email9@example.com', 'motdepasse9', 0, 1),
                                                                                      ('Nom10', 'Prenom10', 'email10@example.com', 'motdepasse10', 0, 2);

