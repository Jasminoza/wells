create TABLE if not exists 'wells' (
    'id' integer PRIMARY KEY AUTOINCREMENT NOT NULL,
    'name' varchar(32) UNIQUE NOT NULL
);