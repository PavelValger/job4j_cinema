insert into film_sessions(film_id, halls_id, start_time, end_time, price)
values  (1, 1, timestamp '2023-07-16 12:00', timestamp '2023-07-16 14:00', 400),
        (1, 2, timestamp '2023-07-16 12:00', timestamp '2023-07-16 14:00', 300),

        (2, 1, timestamp '2023-07-16 14:30', timestamp '2023-07-16 17:00', 400),
        (2, 2, timestamp '2023-07-16 14:30', timestamp '2023-07-16 17:00', 300),

        (3, 1, timestamp '2023-07-16 17:30', timestamp '2023-07-16 19:00', 400),
        (3, 2, timestamp '2023-07-16 17:30', timestamp '2023-07-16 19:00', 300),

        (1, 1, timestamp '2023-07-16 19:30', timestamp '2023-07-16 21:30', 500),
        (2, 2, timestamp '2023-07-16 19:30', timestamp '2023-07-16 22:00', 400),
        (3, 1, timestamp '2023-07-16 22:00', timestamp '2023-07-16 23:30', 600);