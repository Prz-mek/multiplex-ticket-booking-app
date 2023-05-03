INSERT INTO room (id, name) VALUES (1, '1');
INSERT INTO room (id, name) VALUES (2, '2');

INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (1, 1, 1, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (2, 1, 1, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (3, 1, 1, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (4, 1, 2, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (5, 1, 2, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (6, 1, 2, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (7, 2, 1, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (8, 2, 1, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (9, 2, 1, 3);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (10, 2, 2, 1);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (11, 2, 2, 2);
INSERT INTO seat (id, room_id, seats_row, number_in_row) VALUES (12, 2, 2, 3);

INSERT INTO movie (id, title) VALUES (1, 'The Imitation Game');
INSERT INTO movie (id, title) VALUES (2, 'Beautiful mind');
INSERT INTO movie (id, title) VALUES (3, 'The Godfather');

INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (1, 1, 1, '2023-04-29 12:00:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (2, 1, 2, '2023-04-29 15:00:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (3, 2, 2, '2023-04-29 14:30:00');
INSERT INTO screening (id, room_id, movie_id, start_time) VALUES (4, 1, 3, '2023-04-29 11:00:00');