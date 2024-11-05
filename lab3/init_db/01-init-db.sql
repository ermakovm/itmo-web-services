CREATE TABLE movies
(
    id               bigserial primary key not null,
    title            varchar(255)          not null,
    year             smallint              not null,
    duration_minutes smallint              not null,
    studio           varchar(255)          not null,
    director         varchar(255)          not null
);

insert into movies(title, year, duration_minutes, studio, director)
values ('The Godfather', 1972, 175, 'Paramount Pictures', 'Francis Ford Coppola'),
       ('The Shawshank Redemption', 1994, 142, 'Columbia Pictures', 'Frank Darabont'),
       ('The Dark Knight', 2008, 152, 'Warner Bros. Pictures', 'Christopher Nolan'),
       ('The Lord of the Rings: The Return of the King', 2003, 201, 'New Line Cinema', 'Peter Jackson'),
       ('Fight Club', 1999, 139, '20th Century Fox', 'David Fincher');