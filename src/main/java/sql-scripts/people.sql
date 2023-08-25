CREATE DATABASE db_barberssystems
    WITH 
    OWNER = postgres
    TEMPLATE = template0
    ENCODING = 'UTF8'
    LC_COLLATE = 'Slovak_Slovakia.1250'
    CONNECTION LIMIT = -1;
    
    CREATE SCHEMA public
    AUTHORIZATION postgres;
    
    CREATE TABLE IF NOT EXISTS public.t_customers
(
    id bigserial NOT NULL,
    code character varying(6) NOT NULL,
    name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.t_customers
    OWNER to postgres;
    
    CREATE TABLE IF NOT EXISTS public.t_employees
(
    id bigserial NOT NULL,
    code character varying(6) NOT NULL,
    name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    email character varying(30) NOT NULL,
    password character varying(30) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.t_employees
    OWNER to postgres;