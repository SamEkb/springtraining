create table authors
(
    id bigserial not null,
    name varchar(50),
    PRIMARY KEY (id)
);

create table genres
(
    id bigserial not null,
    name varchar(50),
    PRIMARY KEY (id)
);

create table books
(
    id bigserial not null,
    title varchar(50),
    author_id bigint not null,
    genre_id bigint not null,
    PRIMARY KEY (id),
    FOREIGN KEY (author_id) REFERENCES authors (id) on delete cascade,
    FOREIGN KEY (genre_id) REFERENCES genres (id) on delete cascade
);