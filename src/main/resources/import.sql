INSERT INTO room (id, name) VALUES (1, '1');
INSERT INTO room (id, name) VALUES (2, '2');
INSERT INTO room (id, name) VALUES (3, '3');

--Room 1
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (1, 1, 1, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (2, 1, 1, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (3, 1, 1, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (4, 1, 2, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (5, 1, 2, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (6, 1, 2, 3);
--Room 2
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (7, 2, 1, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (8, 2, 1, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (9, 2, 1, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (10, 2, 2, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (11, 2, 2, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (12, 2, 2, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (13, 2, 3, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (14, 2, 4, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (15, 2, 5, 3);
--Room 3
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (16, 3, 1, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (17, 3, 1, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (18, 3, 1, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (19, 3, 2, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (20, 3, 2, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (21, 3, 2, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (22, 3, 3, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (23, 3, 4, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (24, 3, 5, 3);

INSERT INTO movie (id, title, duration) VALUES (1, 'American Psycho', 102);
INSERT INTO movie (id, title, duration) VALUES (2, 'A Beautiful mind', 135);
INSERT INTO movie (id, title, duration) VALUES (3, 'The Godfather', 175);

INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (1, 1, 1, '2023-05-29 12:00:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (2, 1, 2, '2023-05-29 15:00:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (3, 2, 2, '2023-05-29 14:30:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (4, 2, 3, '2023-05-29 11:00:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (5, 3, 3, '2023-05-29 13:30:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (6, 3, 1, '2023-05-29 18:00:00');