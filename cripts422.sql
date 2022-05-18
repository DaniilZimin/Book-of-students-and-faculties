CREATE TABLE auto
(
    id    SERIAL PRIMARY KEY,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    price MONEY
);


CREATE TABLE people
(
    id      SERIAL PRIMARY KEY,
    name    TEXT NOT NULL,
    age     SMALLSERIAL,
    rights  BOOLEAN DEFAULT 'false',
    auto_id SERIAL REFERENCES auto (id)
);