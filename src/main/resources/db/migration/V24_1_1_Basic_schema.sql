create schema if not exists succulentum_profile;

create table succulentum_profile.t_user
(
    id         serial primary key,
    c_username varchar not null check ( length(trim(c_username)) > 0) unique,
    c_password varchar
);

create table succulentum_profile.t_authority
(
    id          serial primary key,
    c_authority varchar not null check ( length(trim(c_authority)) > 0)
);

create table succulentum_profile.t_user_authority
(
    id           serial primary key,
    id_user      int not null references succulentum_profile.t_user (id),
    id_authority int not null references succulentum_profile.t_authority (id),
    constraint uk_user_authority unique (id_user, id_authority)
)
