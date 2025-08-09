insert into users (email, login, name, birthday)
select 'abb@mail.ru', 'bobab', 'Vasya', '201-05-25'
where not exists (select 1 from users where login = 'bobab');

insert into users (email, login, name, birthday)
select 'abcc@mail.ru', 'bibab', 'Petya', '2000-07-01'
where not exists (select 1, from users where login = 'bibab');

insert into films (name, description, release_date, duration)
select 'Timeless', 'something description', '1998-01-01', '121'
where not exists (select 1 from films where name = 'Timeless' and description = 'something description' and release_date = '1998-01-01');

insert into genre (genre)
select 'COMEDY'
where not exists (select 1 from genre where genre = 'COMEDY');

insert into genre (genre)
select 'DRAMA'
where not exists (select 1 from genre where genre = 'DRAMA');

insert into genre (genre)
select 'CARTOON'
where not exists (select 1 from genre where genre = 'CARTOON');

insert into genre (genre)
select 'THRILLER'
where not exists (select 1 from genre where genre = 'THRILLER');

insert into genre (genre)
select 'DOCUMENTARY'
where not exists (select 1 from genre where genre = 'DOCUMENTARY');

insert into genre (genre)
select 'ACTION'
where not exists (select 1 from genre where genre = 'ACTION');

insert into rating (rating)
select 'G'
where not exists (select 1 from rating where rating = 'G');

insert into rating (rating)
select 'PG'
where not exists (select 1 from rating where rating = 'PG');

insert into rating (rating)
select 'PG13'
where not exists (select 1 from rating where rating = 'PG13');

insert into rating (rating)
select 'R'
where not exists (select 1 from rating where rating = 'R');

insert into rating (rating)
select 'NC17'
where not exists (select 1 from rating where rating = 'NC17');