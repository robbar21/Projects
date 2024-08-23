--------------------------------------------
--  Authentication and Authorization
-- (Tables used by the authentication and authorization system)
--------------------------------------------
drop table if exists users, user_roles cascade;
create table users (
    username varchar(255) primary key,
    email varchar(255),
    password varchar(255)
    -- Additional fields can be added here,
    -- such as first name, last name, etc.
    -- Just also update the spring user model/dao
);

create table user_roles (
    username varchar(255),
    role varchar(255),
    primary key (username, role)
);

-- Create the admin/admin user with the ADMIN role
insert into users (username, password, email) values ('admin', '$2a$10$kRbQq1xPISiteFw/LMEoi.Cid/tKI4.flGJB.05hhtPpgIYu.LPbS', 'admin@example.com');
insert into user_roles (username, role) values ('admin', 'ADMIN');

--------------------------------------------
--  Application
-- (Tables used by the application)
--------------------------------------------

-- TODO: Add your tables here!
drop table if exists teams cascade;
drop table if exists user_comments cascade;
drop table if exists posts cascade;

create table posts (
	post_id serial primary key,
	username varchar(200) not null,
	title varchar(200) not null,
	user_posts text not null,
	created_at timestamp default current_timestamp,
	foreign key (username) references users(username)
);

create table user_comments (
	comment_id serial primary key,
	post_id int not null,
	username varchar(200) not null,
	user_comments text not null,
	created_at timestamp default current_timestamp,
	foreign key(post_id) references posts(post_id),
	foreign key(username) references users(username)
);

create table teams (
	team_id serial primary key,
	name varchar(100) not null,
	sport varchar(50) not null,
	league varchar(50)
);

insert into teams(name, sport, league) values('Steelers', 'Football', 'NFL');
insert into teams(name, sport, league) values('Penguins', 'Hockey', 'NHL');
insert into teams(name, sport, league) values('Pirates', 'Baseball', 'MLB');