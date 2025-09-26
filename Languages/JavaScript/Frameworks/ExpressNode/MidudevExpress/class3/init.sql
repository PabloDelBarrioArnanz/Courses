USE moviesdb;

CREATE TABLE IF NOT EXISTS MOVIES
(
    ID       VARCHAR(36)   NOT NULL PRIMARY KEY,
    TITLE    VARCHAR(100)  NOT NULL,
    YEAR     INT           NOT NULL,
    DIRECTOR VARCHAR(100)  NOT NULL,
    POSTER   VARCHAR(350)  NOT NULL,
    RATE     DECIMAL(2, 1) NOT NULL
);

CREATE TABLE IF NOT EXISTS GENRE
(
    ID   INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS MOVIE_GENRE
(
    MOVIE_ID VARCHAR(36) NOT NULL,
    GENRE_ID INT         NOT NULL,
    FOREIGN KEY (MOVIE_ID) REFERENCES MOVIES (ID),
    FOREIGN KEY (GENRE_ID) REFERENCES GENRE (ID),
    PRIMARY KEY (MOVIE_ID, GENRE_ID)
);

INSERT INTO GENRE (NAME)
VALUES ('Drama'),
       ('Action'),
       ('Crime'),
       ('Adventure'),
       ('Sci-Fi'),
       ('Romance'),
       ('Animation'),
       ('Biography'),
       ('Fantasy');

-- Insert or update movies
INSERT INTO MOVIES (ID, TITLE, YEAR, DIRECTOR, POSTER, RATE)
VALUES
    ('dcdd0fad-a94c-4810-8acc-5f108d3b18c3','The Shawshank Redemption',1994,'Frank Darabont','https://i.ebayimg.com/images/g/4goAAOSwMyBe7hnQ/s-l1200.webp',9.3),
    ('c8a7d63f-3b04-44d3-9d95-8782fd7dcfaf','The Dark Knight',2008,'Christopher Nolan','https://i.ebayimg.com/images/g/yokAAOSw8w1YARbm/s-l1200.jpg',9.0),
    ('5ad1a235-0d9c-410a-b32b-220d91689a08','Inception',2010,'Christopher Nolan','https://m.media-amazon.com/images/I/91Rc8cAmnAL._AC_UF1000,1000_QL80_.jpg',8.8),
    ('241bf55d-b649-4109-af7c-0e6890ded3fc','Pulp Fiction',1994,'Quentin Tarantino','https://www.themoviedb.org/t/p/original/vQWk5YBFWF4bZaofAbv0tShwBvQ.jpg',8.9),
    ('9e6106f0-848b-4810-a11a-3d832a5610f9','Forrest Gump',1994,'Robert Zemeckis','https://i.ebayimg.com/images/g/qR8AAOSwkvRZzuMD/s-l1600.jpg',8.8),
    ('7ddc5700-7e8f-407c-a482-3b4dc031e31b','Gladiator',2000,'Ridley Scott','https://img.fruugo.com/product/0/60/14417600_max.jpg',8.5),
    ('c906673b-3948-4402-ac7f-73ac3a9e3105','The Matrix',1999,'Lana Wachowski','https://i.ebayimg.com/images/g/QFQAAOSwAQpfjaA6/s-l1200.jpg',8.7),
    ('b6e03689-cccd-478e-8565-d92f40813b13','Interstellar',2014,'Christopher Nolan','https://m.media-amazon.com/images/I/91obuWzA3XL._AC_UF1000,1000_QL80_.jpg',8.6),
    ('aa391090-b938-42eb-b520-86ea0aa3917b','The Lord of the Rings: The Return of the King',2003,'Peter Jackson','https://i.ebayimg.com/images/g/0hoAAOSwe7peaMLW/s-l1600.jpg',8.9),
    ('2e6900e2-0b48-4fb6-ad48-09c7086e54fe','The Lion King',1994,'Roger Allers, Rob Minkoff','https://m.media-amazon.com/images/I/81BMmrwSFOL._AC_UF1000,1000_QL80_.jpg',8.5),
    ('04986507-b3ed-442c-8ae7-4c5df804f896','The Avengers',2012,'Joss Whedon','https://img.fruugo.com/product/7/41/14532417_max.jpg',8.0),
    ('7d2832f8-c70a-410e-8963-4c93bf36cc9c','Jurassic Park',1993,'Steven Spielberg','https://vice-press.com/cdn/shop/products/Jurassic-Park-Editions-poster-florey.jpg?v=1654518755&width=1024',8.1),
    ('ccf36f2e-8566-47f7-912d-9f4647250bc7','Titanic',1997,'James Cameron','https://i.pinimg.com/originals/42/42/65/4242658e6f1b0d6322a4a93e0383108b.png',7.8),
    ('8fb17ae1-bdfe-45e5-a871-4772d7e526b8','The Social Network',2010,'David Fincher','https://i.pinimg.com/originals/7e/37/b9/7e37b994b613e94cba64f307b1983e39.jpg',7.7),
    ('6a360a18-c645-4b47-9a7b-2a71babbf3e0','Avatar',2009,'James Cameron','https://i.etsystatic.com/35681979/r/il/dfe3ba/3957859451/il_fullxfull.3957859451_h27r.jpg',7.8);

-- Map movies to genres (idempotent)
INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'dcdd0fad-a94c-4810-8acc-5f108d3b18c3', ID FROM GENRE WHERE NAME IN ('Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'c8a7d63f-3b04-44d3-9d95-8782fd7dcfaf', ID FROM GENRE WHERE NAME IN ('Action','Crime','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '5ad1a235-0d9c-410a-b32b-220d91689a08', ID FROM GENRE WHERE NAME IN ('Action','Adventure','Sci-Fi');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '241bf55d-b649-4109-af7c-0e6890ded3fc', ID FROM GENRE WHERE NAME IN ('Crime','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '9e6106f0-848b-4810-a11a-3d832a5610f9', ID FROM GENRE WHERE NAME IN ('Drama','Romance');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '7ddc5700-7e8f-407c-a482-3b4dc031e31b', ID FROM GENRE WHERE NAME IN ('Action','Adventure','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'c906673b-3948-4402-ac7f-73ac3a9e3105', ID FROM GENRE WHERE NAME IN ('Action','Sci-Fi');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'b6e03689-cccd-478e-8565-d92f40813b13', ID FROM GENRE WHERE NAME IN ('Adventure','Drama','Sci-Fi');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'aa391090-b938-42eb-b520-86ea0aa3917b', ID FROM GENRE WHERE NAME IN ('Action','Adventure','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '2e6900e2-0b48-4fb6-ad48-09c7086e54fe', ID FROM GENRE WHERE NAME IN ('Animation','Adventure','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '04986507-b3ed-442c-8ae7-4c5df804f896', ID FROM GENRE WHERE NAME IN ('Action','Adventure','Sci-Fi');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '7d2832f8-c70a-410e-8963-4c93bf36cc9c', ID FROM GENRE WHERE NAME IN ('Adventure','Sci-Fi');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT 'ccf36f2e-8566-47f7-912d-9f4647250bc7', ID FROM GENRE WHERE NAME IN ('Drama','Romance');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '8fb17ae1-bdfe-45e5-a871-4772d7e526b8', ID FROM GENRE WHERE NAME IN ('Biography','Drama');

INSERT INTO MOVIE_GENRE (MOVIE_ID, GENRE_ID)
SELECT '6a360a18-c645-4b47-9a7b-2a71babbf3e0', ID FROM GENRE WHERE NAME IN ('Action','Adventure','Fantasy');

COMMIT;