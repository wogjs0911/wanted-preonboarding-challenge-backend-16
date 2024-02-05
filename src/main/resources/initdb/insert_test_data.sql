INSERT INTO `performance` (name, price, round, type, start_date, is_reserve)
VALUES ('Rebecca', 100000, 1, 0, '2024-01-20 19:30:00', 'disable');

INSERT INTO `performance` (name, price, round, type, start_date, is_reserve)
VALUES ('Franchesca', 100000, 1, 0, '2024-02-05 19:30:00', 'enable');

INSERT INTO `performance` (name, price, round, type, start_date, is_reserve)
VALUES ('Mammamia', 100000, 1, 0, '2024-01-06 19:30:00', 'enable');

INSERT INTO performance_seat_info VALUES
 (DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 1, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 2, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 3, 'enable', DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 1, 1, 'A', 4, 'enable', DEFAULT, DEFAULT);

INSERT INTO reservation VALUES
 (DEFAULT, (SELECT id FROM performance limit 1), 'Aden', '01045645654', 1, 1, 'A', 2, DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 'Baken', '01023423423', 2, 2, 'B', 3, DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 'Cater', '01023412351', 1, 3, 'Q', 4, DEFAULT, DEFAULT)
,(DEFAULT, (SELECT id FROM performance limit 1), 'Dreako', '01087687685', 3, 4, 'R', 5, DEFAULT, DEFAULT);
