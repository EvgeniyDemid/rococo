create extension if not exists "uuid-ossp";

create table if not exists "country"
(
    id        UUID unique        not null default uuid_generate_v1() primary key,
    name      varchar(255)  unique not null
);

alter table "country"
    owner to postgres;
    insert into "country" (name) values ('Russia'),
 ('Austria'),
 ('Rwanda'),
 ('Romania'),
 ('Slovakia'),
 ('Slovenia'),
 ('Finland'),
 ('France'),
 ('South Africa')
  ON CONFLICT (name) DO NOTHING;


