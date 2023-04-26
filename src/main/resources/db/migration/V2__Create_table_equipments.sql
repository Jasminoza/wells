create TABLE if not exists 'equipments' (
    'id' integer PRIMARY KEY AUTOINCREMENT NOT NULL,
    'name' varchar(32) UNIQUE NOT NULL,
    'well_id' integer NOT NULL,
    FOREIGN KEY (well_id) REFERENCES wells(id)
);