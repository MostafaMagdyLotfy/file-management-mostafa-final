DROP SCHEMA IF EXISTS file_management CASCADE;
CREATE SCHEMA file_management;


DROP TABLE IF EXISTS file_management.files CASCADE;
CREATE TABLE file_management.files
(
    id          bigint NOT NULL,
    binary_data bytea  NOT NULL,
    item_id     bigint
);


ALTER TABLE file_management.files
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME file_management.files_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );

DROP TYPE IF EXISTS type_enum;
CREATE TYPE type_enum AS ENUM (
    'Space',
    'Folder',
    'File'
    );
CREATE CAST (varchar AS type_enum) WITH INOUT AS IMPLICIT;

DROP TABLE IF EXISTS file_management.items CASCADE;
CREATE TABLE file_management.items
(
    id                  bigint            NOT NULL,
    type                type_enum         NOT NULL,
    name                character varying NOT NULL,
    permission_group_id bigint,
    parent_item_id      bigint
);



ALTER TABLE file_management.items
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME file_management.item_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );


DROP TABLE IF EXISTS file_management.permission_groups CASCADE;
CREATE TABLE file_management.permission_groups
(
    id         bigint            NOT NULL,
    group_name character varying NOT NULL
);



ALTER TABLE file_management.permission_groups
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME file_management.permission_groups_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );

DROP TYPE IF EXISTS permission_level;
CREATE TYPE permission_level AS ENUM (
    'VIEW',
    'EDIT'
    );

CREATE CAST (varchar AS permission_level) WITH INOUT AS IMPLICIT;

DROP TABLE IF EXISTS file_management.permissions CASCADE;
CREATE TABLE file_management.permissions
(
    id               bigint            NOT NULL,
    user_email       character varying NOT NULL,
    permission_level permission_level  NOT NULL,
    group_id         bigint
);



ALTER TABLE file_management.permissions
    ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
        SEQUENCE NAME file_management.permissions_id_seq
        START WITH 1
        INCREMENT BY 1
        NO MINVALUE
        NO MAXVALUE
        CACHE 1
        );



ALTER TABLE ONLY file_management.files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);



ALTER TABLE ONLY file_management.items
    ADD CONSTRAINT item_pkey PRIMARY KEY (id);



ALTER TABLE ONLY file_management.permission_groups
    ADD CONSTRAINT permission_groups_pkey PRIMARY KEY (id);



ALTER TABLE ONLY file_management.permissions
    ADD CONSTRAINT permissions_pkey PRIMARY KEY (id);



ALTER TABLE ONLY file_management.permissions
    ADD CONSTRAINT user_email_unique UNIQUE (user_email);



ALTER TABLE ONLY file_management.permissions
    ADD CONSTRAINT group_id_fk FOREIGN KEY (group_id) REFERENCES file_management.permission_groups (id) NOT VALID;



ALTER TABLE ONLY file_management.files
    ADD CONSTRAINT item_id_fk FOREIGN KEY (item_id) REFERENCES file_management.items (id) NOT VALID;



ALTER TABLE ONLY file_management.items
    ADD CONSTRAINT parent_item_fk FOREIGN KEY (parent_item_id) REFERENCES file_management.items (id) NOT VALID;



ALTER TABLE ONLY file_management.items
    ADD CONSTRAINT permission_group_id_fk FOREIGN KEY (permission_group_id) REFERENCES file_management.permission_groups (id) NOT VALID;



