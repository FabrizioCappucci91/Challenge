INSERT INTO cinema (id, nome, indirizzo, citta) VALUES (1, 'Cinema Mille Firenze', 'Via Roma 142', 'Firenze');

INSERT INTO sala (id, numero_sala, capienza, tipo_tecnologia, cinema_id) VALUES (1, 1, 100, '2D',1);
INSERT INTO sala (id, numero_sala, capienza, tipo_tecnologia, cinema_id) VALUES (2, 2, 150, '3D',1);
INSERT INTO sala (id, numero_sala, capienza, tipo_tecnologia, cinema_id) VALUES (3, 3, 80, 'IMAX',1);

INSERT INTO film (id, titolo, descrizione, durata, genere) VALUES (1, 'Inception', 'Thriller fantascientifico diretto da Nolan', 148, 'Thriller');
INSERT INTO film (id, titolo, descrizione, durata, genere) VALUES (2, 'Interstellar', 'Viaggio nello spazio e nel tempo', 169, 'Avventura');
INSERT INTO film (id, titolo, descrizione, durata, genere) VALUES (3, 'The Matrix', 'Cyberpunk, realtà virtuale', 136,'Fantascienza');

INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (1, '2025-08-05', '20:30:00', 1, 1);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (2, '2025-08-07', '21:00:00', 1, 1);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (3, '2025-08-10', '20:00:00', 1, 2);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (4, '2025-08-10', '18:30:00', 2, 1);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (5, '2025-08-12', '21:00:00', 2, 2);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (6, '2025-08-14', '20:00:00', 2, 3);

INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (7, '2025-08-01', '20:00:00', 3, 1);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (8, '2025-08-03', '22:00:00', 3, 1);
INSERT INTO proiezione (id, data, orario, film_id, sala_id) VALUES (9, '2025-08-06', '21:00:00', 3, 2);
