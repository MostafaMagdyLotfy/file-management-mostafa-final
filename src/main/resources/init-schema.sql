CREATE SCHEMA public;

DROP TYPE IF EXISTS permission_level;
CREATE TYPE public.permission_level AS ENUM (
    'VIEW',
    'EDIT'
    );

DROP TYPE IF EXISTS type_enum;
CREATE TYPE public.type_enum AS ENUM (
    'Space',
    'Folder',
    'File'
    );

DROP TABLE IF EXISTS public.files CASCADE;
CREATE TABLE public.files
(
    id          bigint NOT NULL,
    binary_data bytea  NOT NULL,
    item_id     bigint
);


ALTER TABLE public.files
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME public.files_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );

DROP TABLE IF EXISTS public.items CASCADE;
CREATE TABLE public.items
(
    id                  bigint            NOT NULL,
    type                public.type_enum  NOT NULL,
    name                character varying NOT NULL,
    permission_group_id bigint,
    parent_item_id      bigint
);



ALTER TABLE public.items
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME public.item_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );


DROP TABLE IF EXISTS public.permission_groups CASCADE;
CREATE TABLE public.permission_groups
(
    id         bigint            NOT NULL,
    group_name character varying NOT NULL
);



ALTER TABLE public.permission_groups
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME public.permission_groups_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );


DROP TABLE IF EXISTS public.permissions CASCADE;
CREATE TABLE public.permissions
(
    id               bigint                  NOT NULL,
    user_email       character varying       NOT NULL,
    permission_level public.permission_level NOT NULL,
    group_id         bigint
);



ALTER TABLE public.permissions
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME public.permissions_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );



ALTER TABLE ONLY public.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.items
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.permission_groups
    ADD CONSTRAINT permission_groups_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT user_email_unique UNIQUE (user_email);



ALTER TABLE ONLY public.permissions
    ADD CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES public.permission_groups (id) NOT VALID;



ALTER TABLE ONLY public.files
    ADD CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES public.items (id) NOT VALID;



ALTER TABLE ONLY public.items
    ADD CONSTRAINT parent_item_fk FOREIGN KEY (parent_item_id) REFERENCES public.items (id) NOT VALID;



ALTER TABLE ONLY public.items
    ADD CONSTRAINT permission_group_id_fk FOREIGN KEY (permission_group_id) REFERENCES public.permission_groups (id) NOT VALID;



