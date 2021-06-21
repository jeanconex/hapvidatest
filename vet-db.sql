--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 11.1


SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1971 (class 1262 OID 34113)
-- Name: vet; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


\connect vet

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 34116)
-- Name: animal; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.animal (
    id bigint NOT NULL,
    data_nascimento date,
    especie character varying(255),
    nome character varying(255),
    raca character varying(255),
    tutor_id bigint NOT NULL
);


--
-- TOC entry 171 (class 1259 OID 34114)
-- Name: animal_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1972 (class 0 OID 0)
-- Dependencies: 171
-- Name: animal_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;


--
-- TOC entry 174 (class 1259 OID 34127)
-- Name: consulta; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.consulta (
    id bigint NOT NULL,
    data_consulta timestamp without time zone,
    status character varying(255),
    animal_id bigint NOT NULL,
    veterinario_id bigint NOT NULL
);


--
-- TOC entry 173 (class 1259 OID 34125)
-- Name: consulta_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.consulta_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1973 (class 0 OID 0)
-- Dependencies: 173
-- Name: consulta_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.consulta_id_seq OWNED BY public.consulta.id;


--
-- TOC entry 176 (class 1259 OID 34135)
-- Name: tutor; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.tutor (
    id bigint NOT NULL,
    email character varying(255),
    nome character varying(255),
    telefone character varying(255)
);


--
-- TOC entry 175 (class 1259 OID 34133)
-- Name: tutor_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.tutor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1974 (class 0 OID 0)
-- Dependencies: 175
-- Name: tutor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.tutor_id_seq OWNED BY public.tutor.id;


--
-- TOC entry 178 (class 1259 OID 34146)
-- Name: veterinario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.veterinario (
    id bigint NOT NULL,
    email character varying(255),
    nome character varying(255),
    telefone character varying(255)
);


--
-- TOC entry 177 (class 1259 OID 34144)
-- Name: veterinario_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.veterinario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1975 (class 0 OID 0)
-- Dependencies: 177
-- Name: veterinario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.veterinario_id_seq OWNED BY public.veterinario.id;


--
-- TOC entry 1844 (class 2604 OID 34119)
-- Name: animal id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);


--
-- TOC entry 1845 (class 2604 OID 34130)
-- Name: consulta id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.consulta ALTER COLUMN id SET DEFAULT nextval('public.consulta_id_seq'::regclass);


--
-- TOC entry 1846 (class 2604 OID 34138)
-- Name: tutor id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tutor ALTER COLUMN id SET DEFAULT nextval('public.tutor_id_seq'::regclass);


--
-- TOC entry 1847 (class 2604 OID 34149)
-- Name: veterinario id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.veterinario ALTER COLUMN id SET DEFAULT nextval('public.veterinario_id_seq'::regclass);


--
-- TOC entry 1849 (class 2606 OID 34124)
-- Name: animal animal_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);


--
-- TOC entry 1851 (class 2606 OID 34132)
-- Name: consulta consulta_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT consulta_pkey PRIMARY KEY (id);


--
-- TOC entry 1853 (class 2606 OID 34143)
-- Name: tutor tutor_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.tutor
    ADD CONSTRAINT tutor_pkey PRIMARY KEY (id);


--
-- TOC entry 1855 (class 2606 OID 34154)
-- Name: veterinario veterinario_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.veterinario
    ADD CONSTRAINT veterinario_pkey PRIMARY KEY (id);


--
-- TOC entry 1856 (class 2606 OID 34155)
-- Name: animal fkggg7qolnxfaqedimuul5k9912; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fkggg7qolnxfaqedimuul5k9912 FOREIGN KEY (tutor_id) REFERENCES public.tutor(id);


--
-- TOC entry 1858 (class 2606 OID 34165)
-- Name: consulta fkht423dgn01c5evcn42tcvauj9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fkht423dgn01c5evcn42tcvauj9 FOREIGN KEY (veterinario_id) REFERENCES public.veterinario(id);


--
-- TOC entry 1857 (class 2606 OID 34160)
-- Name: consulta fkoqixw5gfyf05vkwf9xhy2423c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.consulta
    ADD CONSTRAINT fkoqixw5gfyf05vkwf9xhy2423c FOREIGN KEY (animal_id) REFERENCES public.animal(id);


-- Completed on 2021-06-21 06:56:39

--
-- PostgreSQL database dump complete
--

