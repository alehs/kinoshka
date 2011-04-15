CREATE TABLE GENRES (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(128) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ACTORS (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DIRECTORS (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

/** COUNTRY */
CREATE TABLE REGIONS (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(128),
    SHORT VARCHAR(32) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE FILMS (
    ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR(128) NOT NULL,
    ORIGINAL_NAME VARCHAR(128),
    DESCRIPTION VARCHAR(128),
    IMAGE_NAME VARCHAR(128),
    FILM_YEAR INTEGER,
    FILM_TIME INTEGER,
    BOX INTEGER,
    DISK INTEGER,
    PRIMARY KEY (ID)
);

/** LINKING TABLES - MANY TO MANY */

CREATE TABLE FILM_GENRE (
    FILM_ID INTEGER NOT NULL,
    GENRE_ID INTEGER NOT NULL,
    PRIMARY KEY (FILM_ID, GENRE_ID),
    FOREIGN KEY (FILM_ID) REFERENCES FILMS(ID),
    FOREIGN KEY (GENRE_ID) REFERENCES GENRES(ID)
);

CREATE TABLE FILM_ACTOR (
    FILM_ID INTEGER NOT NULL CONSTRAINT FILM_FOREIGN_KEY REFERENCES FILMS(ID),
    ACTOR_ID INTEGER NOT NULL CONSTRAINT ACTOR_FOREIGN_KEY REFERENCES ACTORS(ID),
    PRIMARY KEY (FILM_ID, ACTOR_ID)
);

CREATE TABLE FILM_DIRECTOR (
    FILM_ID INTEGER NOT NULL,
    DIRECTOR_ID INTEGER NOT NULL,
    PRIMARY KEY (FILM_ID, DIRECTOR_ID),
    FOREIGN KEY (FILM_ID) REFERENCES FILMS(ID),
    FOREIGN KEY (DIRECTOR_ID) REFERENCES DIRECTORS(ID)
);

CREATE TABLE FILM_REGION (
    FILM_ID INTEGER NOT NULL,
    REGION_ID INTEGER NOT NULL,
    PRIMARY KEY (FILM_ID, REGION_ID),
    FOREIGN KEY (FILM_ID) REFERENCES FILMS(ID),
    FOREIGN KEY (REGION_ID) REFERENCES REGIONS(ID)
);

CREATE VIEW PAGED_FILMS(ID, NUM) AS
SELECT ID, ROW_NUMBER() OVER ()
FROM FILMS;