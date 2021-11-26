toc.dat                                                                                             0000600 0004000 0002000 00000024603 14150204017 0014436 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        PGDMP       
                
    y         
   library_db #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) #   12.9 (Ubuntu 12.9-0ubuntu0.20.04.1) (    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false         �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false         �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false         �           1262    16435 
   library_db    DATABASE     |   CREATE DATABASE library_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE library_db;
                postgres    false         	            2615    16502    library    SCHEMA        CREATE SCHEMA library;
    DROP SCHEMA library;
                postgres    false                     2615    16436    security    SCHEMA        CREATE SCHEMA security;
    DROP SCHEMA security;
                postgres    false                     2615    16448    users    SCHEMA        CREATE SCHEMA users;
    DROP SCHEMA users;
                postgres    false         �            1259    16505    books    TABLE       CREATE TABLE library.books (
    id smallint NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(250) NOT NULL,
    page_number smallint NOT NULL,
    publication_date date NOT NULL,
    author_id character varying(8) NOT NULL
);
    DROP TABLE library.books;
       library         heap    postgres    false    9         �            1259    16503    books_id_seq    SEQUENCE     �   CREATE SEQUENCE library.books_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE library.books_id_seq;
       library          postgres    false    9    211         �           0    0    books_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE library.books_id_seq OWNED BY library.books.id;
          library          postgres    false    210         �            1259    16520 
   editorials    TABLE     �   CREATE TABLE library.editorials (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    nit character varying(255) NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE library.editorials;
       library         heap    postgres    false    9         �            1259    16518    editorials_id_seq    SEQUENCE     �   CREATE SEQUENCE library.editorials_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE library.editorials_id_seq;
       library          postgres    false    213    9         �           0    0    editorials_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE library.editorials_id_seq OWNED BY library.editorials.id;
          library          postgres    false    212         �            1259    16439    users    TABLE     �   CREATE TABLE security.users (
    id smallint NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    role character varying(24),
    name character varying(48)
);
    DROP TABLE security.users;
       security         heap    postgres    false    8         �            1259    16437    users_id_seq    SEQUENCE     �   CREATE SEQUENCE security.users_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE security.users_id_seq;
       security          postgres    false    8    206         �           0    0    users_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE security.users_id_seq OWNED BY security.users.id;
          security          postgres    false    205         �            1259    16463    authors    TABLE     �   CREATE TABLE users.authors (
    orcid character varying(8) NOT NULL,
    names character varying(24) NOT NULL,
    last_names character varying(24) NOT NULL,
    email text NOT NULL
);
    DROP TABLE users.authors;
       users         heap    postgres    false    6         �            1259    16493 
   librarians    TABLE     �   CREATE TABLE users.librarians (
    id smallint NOT NULL,
    names character varying(24) NOT NULL,
    last_names character varying(24) NOT NULL,
    email text
);
    DROP TABLE users.librarians;
       users         heap    postgres    false    6         �            1259    16491    librarians_id_seq    SEQUENCE     �   CREATE SEQUENCE users.librarians_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE users.librarians_id_seq;
       users          postgres    false    6    209         �           0    0    librarians_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE users.librarians_id_seq OWNED BY users.librarians.id;
          users          postgres    false    208         /           2604    16508    books id    DEFAULT     f   ALTER TABLE ONLY library.books ALTER COLUMN id SET DEFAULT nextval('library.books_id_seq'::regclass);
 8   ALTER TABLE library.books ALTER COLUMN id DROP DEFAULT;
       library          postgres    false    210    211    211         0           2604    16523    editorials id    DEFAULT     p   ALTER TABLE ONLY library.editorials ALTER COLUMN id SET DEFAULT nextval('library.editorials_id_seq'::regclass);
 =   ALTER TABLE library.editorials ALTER COLUMN id DROP DEFAULT;
       library          postgres    false    213    212    213         -           2604    16442    users id    DEFAULT     h   ALTER TABLE ONLY security.users ALTER COLUMN id SET DEFAULT nextval('security.users_id_seq'::regclass);
 9   ALTER TABLE security.users ALTER COLUMN id DROP DEFAULT;
       security          postgres    false    205    206    206         .           2604    16496    librarians id    DEFAULT     l   ALTER TABLE ONLY users.librarians ALTER COLUMN id SET DEFAULT nextval('users.librarians_id_seq'::regclass);
 ;   ALTER TABLE users.librarians ALTER COLUMN id DROP DEFAULT;
       users          postgres    false    209    208    209         �          0    16505    books 
   TABLE DATA           a   COPY library.books (id, name, description, page_number, publication_date, author_id) FROM stdin;
    library          postgres    false    211       3009.dat �          0    16520 
   editorials 
   TABLE DATA           ;   COPY library.editorials (id, email, nit, name) FROM stdin;
    library          postgres    false    213       3011.dat �          0    16439    users 
   TABLE DATA           B   COPY security.users (id, email, password, role, name) FROM stdin;
    security          postgres    false    206       3004.dat �          0    16463    authors 
   TABLE DATA           A   COPY users.authors (orcid, names, last_names, email) FROM stdin;
    users          postgres    false    207       3005.dat �          0    16493 
   librarians 
   TABLE DATA           A   COPY users.librarians (id, names, last_names, email) FROM stdin;
    users          postgres    false    209       3007.dat �           0    0    books_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('library.books_id_seq', 2, true);
          library          postgres    false    210         �           0    0    editorials_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('library.editorials_id_seq', 1, false);
          library          postgres    false    212         �           0    0    users_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('security.users_id_seq', 4, true);
          security          postgres    false    205         �           0    0    librarians_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('users.librarians_id_seq', 1, false);
          users          postgres    false    208         8           2606    16510    books books_pkey 
   CONSTRAINT     O   ALTER TABLE ONLY library.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);
 ;   ALTER TABLE ONLY library.books DROP CONSTRAINT books_pkey;
       library            postgres    false    211         ;           2606    16528    editorials editorials_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY library.editorials
    ADD CONSTRAINT editorials_pkey PRIMARY KEY (id);
 E   ALTER TABLE ONLY library.editorials DROP CONSTRAINT editorials_pkey;
       library            postgres    false    213         2           2606    16447    users users_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY security.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY security.users DROP CONSTRAINT users_pkey;
       security            postgres    false    206         4           2606    16472    authors autores_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY users.authors
    ADD CONSTRAINT autores_pkey PRIMARY KEY (orcid);
 =   ALTER TABLE ONLY users.authors DROP CONSTRAINT autores_pkey;
       users            postgres    false    207         6           2606    16501    librarians librarians_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY users.librarians
    ADD CONSTRAINT librarians_pkey PRIMARY KEY (id);
 C   ALTER TABLE ONLY users.librarians DROP CONSTRAINT librarians_pkey;
       users            postgres    false    209         9           1259    16516    fki_users_author_books    INDEX     N   CREATE INDEX fki_users_author_books ON library.books USING btree (author_id);
 +   DROP INDEX library.fki_users_author_books;
       library            postgres    false    211         <           2606    16511    books users_author_books    FK CONSTRAINT     �   ALTER TABLE ONLY library.books
    ADD CONSTRAINT users_author_books FOREIGN KEY (author_id) REFERENCES users.authors(orcid) NOT VALID;
 C   ALTER TABLE ONLY library.books DROP CONSTRAINT users_author_books;
       library          postgres    false    211    207    2868                                                                                                                                     3009.dat                                                                                            0000600 0004000 0002000 00000000167 14150204017 0014243 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        2	El coronel no tiene quien le escriba	Descripción del coronel no tiene quien le escriba	250	2000-02-27	12345678
\.


                                                                                                                                                                                                                                                                                                                                                                                                         3011.dat                                                                                            0000600 0004000 0002000 00000000005 14150204017 0014223 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           3004.dat                                                                                            0000600 0004000 0002000 00000000373 14150204017 0014235 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        1	jhonattanpulido@outlook.com	$2a$12$qsfMSUa79yrIYi3PfGuxK.irAIJoeRatV2TxbJRl64im9gwrF9Rsq	BIBLIOTECARIO	Jhonattan Alejandro Pulido Arenas
4	gabo@gmail.com	$2a$10$6SdFWh1hC/vShqvHo849KuHbBBbt2U365zTtmC6uhTvuKubc6dmle	AUTOR	Gabriel Garcia Marquez
\.


                                                                                                                                                                                                                                                                     3005.dat                                                                                            0000600 0004000 0002000 00000000064 14150204017 0014233 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        12345678	Gabriel	Garcia Marquez	gabo@gmail.com
\.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                            3007.dat                                                                                            0000600 0004000 0002000 00000000005 14150204017 0014230 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        \.


                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           restore.sql                                                                                         0000600 0004000 0002000 00000021362 14150204017 0015362 0                                                                                                    ustar 00postgres                        postgres                        0000000 0000000                                                                                                                                                                        --
-- NOTE:
--
-- File paths need to be edited. Search for $$PATH$$ and
-- replace it with the path to the directory containing
-- the extracted data files.
--
--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE library_db;
--
-- Name: library_db; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE library_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE library_db OWNER TO postgres;

\connect library_db

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: library; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA library;


ALTER SCHEMA library OWNER TO postgres;

--
-- Name: security; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA security;


ALTER SCHEMA security OWNER TO postgres;

--
-- Name: users; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA users;


ALTER SCHEMA users OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: books; Type: TABLE; Schema: library; Owner: postgres
--

CREATE TABLE library.books (
    id smallint NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(250) NOT NULL,
    page_number smallint NOT NULL,
    publication_date date NOT NULL,
    author_id character varying(8) NOT NULL
);


ALTER TABLE library.books OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: library; Owner: postgres
--

CREATE SEQUENCE library.books_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE library.books_id_seq OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE OWNED BY; Schema: library; Owner: postgres
--

ALTER SEQUENCE library.books_id_seq OWNED BY library.books.id;


--
-- Name: editorials; Type: TABLE; Schema: library; Owner: postgres
--

CREATE TABLE library.editorials (
    id integer NOT NULL,
    email character varying(255) NOT NULL,
    nit character varying(255) NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE library.editorials OWNER TO postgres;

--
-- Name: editorials_id_seq; Type: SEQUENCE; Schema: library; Owner: postgres
--

CREATE SEQUENCE library.editorials_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE library.editorials_id_seq OWNER TO postgres;

--
-- Name: editorials_id_seq; Type: SEQUENCE OWNED BY; Schema: library; Owner: postgres
--

ALTER SEQUENCE library.editorials_id_seq OWNED BY library.editorials.id;


--
-- Name: users; Type: TABLE; Schema: security; Owner: postgres
--

CREATE TABLE security.users (
    id smallint NOT NULL,
    email text NOT NULL,
    password text NOT NULL,
    role character varying(24),
    name character varying(48)
);


ALTER TABLE security.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: security; Owner: postgres
--

CREATE SEQUENCE security.users_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE security.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: security; Owner: postgres
--

ALTER SEQUENCE security.users_id_seq OWNED BY security.users.id;


--
-- Name: authors; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.authors (
    orcid character varying(8) NOT NULL,
    names character varying(24) NOT NULL,
    last_names character varying(24) NOT NULL,
    email text NOT NULL
);


ALTER TABLE users.authors OWNER TO postgres;

--
-- Name: librarians; Type: TABLE; Schema: users; Owner: postgres
--

CREATE TABLE users.librarians (
    id smallint NOT NULL,
    names character varying(24) NOT NULL,
    last_names character varying(24) NOT NULL,
    email text
);


ALTER TABLE users.librarians OWNER TO postgres;

--
-- Name: librarians_id_seq; Type: SEQUENCE; Schema: users; Owner: postgres
--

CREATE SEQUENCE users.librarians_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE users.librarians_id_seq OWNER TO postgres;

--
-- Name: librarians_id_seq; Type: SEQUENCE OWNED BY; Schema: users; Owner: postgres
--

ALTER SEQUENCE users.librarians_id_seq OWNED BY users.librarians.id;


--
-- Name: books id; Type: DEFAULT; Schema: library; Owner: postgres
--

ALTER TABLE ONLY library.books ALTER COLUMN id SET DEFAULT nextval('library.books_id_seq'::regclass);


--
-- Name: editorials id; Type: DEFAULT; Schema: library; Owner: postgres
--

ALTER TABLE ONLY library.editorials ALTER COLUMN id SET DEFAULT nextval('library.editorials_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY security.users ALTER COLUMN id SET DEFAULT nextval('security.users_id_seq'::regclass);


--
-- Name: librarians id; Type: DEFAULT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.librarians ALTER COLUMN id SET DEFAULT nextval('users.librarians_id_seq'::regclass);


--
-- Data for Name: books; Type: TABLE DATA; Schema: library; Owner: postgres
--

COPY library.books (id, name, description, page_number, publication_date, author_id) FROM stdin;
\.
COPY library.books (id, name, description, page_number, publication_date, author_id) FROM '$$PATH$$/3009.dat';

--
-- Data for Name: editorials; Type: TABLE DATA; Schema: library; Owner: postgres
--

COPY library.editorials (id, email, nit, name) FROM stdin;
\.
COPY library.editorials (id, email, nit, name) FROM '$$PATH$$/3011.dat';

--
-- Data for Name: users; Type: TABLE DATA; Schema: security; Owner: postgres
--

COPY security.users (id, email, password, role, name) FROM stdin;
\.
COPY security.users (id, email, password, role, name) FROM '$$PATH$$/3004.dat';

--
-- Data for Name: authors; Type: TABLE DATA; Schema: users; Owner: postgres
--

COPY users.authors (orcid, names, last_names, email) FROM stdin;
\.
COPY users.authors (orcid, names, last_names, email) FROM '$$PATH$$/3005.dat';

--
-- Data for Name: librarians; Type: TABLE DATA; Schema: users; Owner: postgres
--

COPY users.librarians (id, names, last_names, email) FROM stdin;
\.
COPY users.librarians (id, names, last_names, email) FROM '$$PATH$$/3007.dat';

--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: library; Owner: postgres
--

SELECT pg_catalog.setval('library.books_id_seq', 2, true);


--
-- Name: editorials_id_seq; Type: SEQUENCE SET; Schema: library; Owner: postgres
--

SELECT pg_catalog.setval('library.editorials_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: security; Owner: postgres
--

SELECT pg_catalog.setval('security.users_id_seq', 4, true);


--
-- Name: librarians_id_seq; Type: SEQUENCE SET; Schema: users; Owner: postgres
--

SELECT pg_catalog.setval('users.librarians_id_seq', 1, false);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: library; Owner: postgres
--

ALTER TABLE ONLY library.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: editorials editorials_pkey; Type: CONSTRAINT; Schema: library; Owner: postgres
--

ALTER TABLE ONLY library.editorials
    ADD CONSTRAINT editorials_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: security; Owner: postgres
--

ALTER TABLE ONLY security.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: authors autores_pkey; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.authors
    ADD CONSTRAINT autores_pkey PRIMARY KEY (orcid);


--
-- Name: librarians librarians_pkey; Type: CONSTRAINT; Schema: users; Owner: postgres
--

ALTER TABLE ONLY users.librarians
    ADD CONSTRAINT librarians_pkey PRIMARY KEY (id);


--
-- Name: fki_users_author_books; Type: INDEX; Schema: library; Owner: postgres
--

CREATE INDEX fki_users_author_books ON library.books USING btree (author_id);


--
-- Name: books users_author_books; Type: FK CONSTRAINT; Schema: library; Owner: postgres
--

ALTER TABLE ONLY library.books
    ADD CONSTRAINT users_author_books FOREIGN KEY (author_id) REFERENCES users.authors(orcid) NOT VALID;


--
-- PostgreSQL database dump complete
--

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              