--
-- PostgreSQL database dump
--

-- Dumped from database version 10.13 (Ubuntu 10.13-1.pgdg18.04+1)
-- Dumped by pg_dump version 10.13 (Ubuntu 10.13-1.pgdg18.04+1)

-- Started on 2020-05-28 22:32:55 CEST

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
-- TOC entry 3319 (class 1262 OID 46282)
-- Name: ubilibet; Type: DATABASE; Schema: -; Owner: ubilibet
--

CREATE ROLE ubilibet WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  NOREPLICATION
  ENCRYPTED PASSWORD 'md5afc1f4e500014b958ea0186584a31805';

CREATE DATABASE ubilibet WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'ca_ES.UTF-8' LC_CTYPE = 'ca_ES.UTF-8';


ALTER DATABASE ubilibet OWNER TO ubilibet;

\connect ubilibet

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
-- TOC entry 1 (class 3079 OID 13091)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 3322 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 717 (class 1255 OID 79467)
-- Name: array_accum(anyelement); Type: AGGREGATE; Schema: public; Owner: ubilibet
--

CREATE AGGREGATE public.array_accum(anyelement) (
    SFUNC = array_append,
    STYPE = anyarray,
    INITCOND = '{}'
);


ALTER AGGREGATE public.array_accum(anyelement) OWNER TO ubilibet;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 230 (class 1259 OID 82111)
-- Name: contactes_admin_tec; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.contactes_admin_tec (
    id integer NOT NULL,
    first_name character varying(150) NOT NULL,
    last_name character varying(50) DEFAULT NULL::character varying,
    nif character varying(15) DEFAULT NULL::character varying,
    organization character varying(100) DEFAULT NULL::character varying,
    street character varying(150) DEFAULT NULL::character varying,
    street2 character varying(50) DEFAULT NULL::character varying,
    city character varying(80) DEFAULT NULL::character varying,
    state character varying(40) DEFAULT NULL::character varying,
    cp character varying(15) DEFAULT NULL::character varying,
    country character varying(4) DEFAULT NULL::character varying,
    phone character varying(20) DEFAULT NULL::character varying,
    fax character varying(20) DEFAULT NULL::character varying,
    mail character varying(150) DEFAULT NULL::character varying,
    lang character varying(3) DEFAULT 'es'::character varying,
    form_juridica integer,
    organization_number character varying(200) DEFAULT NULL::character varying,
    type character varying(200) DEFAULT NULL::character varying,
    title character varying(50) DEFAULT NULL::character varying
);


ALTER TABLE public.contactes_admin_tec OWNER TO ubilibet;

--
-- TOC entry 229 (class 1259 OID 82109)
-- Name: contactes_admin_tec_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.contactes_admin_tec_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contactes_admin_tec_id_seq OWNER TO ubilibet;

--
-- TOC entry 3324 (class 0 OID 0)
-- Dependencies: 229
-- Name: contactes_admin_tec_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.contactes_admin_tec_id_seq OWNED BY public.contactes_admin_tec.id;


--
-- TOC entry 204 (class 1259 OID 79562)
-- Name: contactes_fac; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.contactes_fac (
    id integer NOT NULL,
    id_grup integer NOT NULL,
    nom character varying(200) NOT NULL,
    nif character varying(20) DEFAULT NULL::character varying,
    direccio character varying(150) NOT NULL,
    city character varying(80) DEFAULT NULL::character varying,
    state character varying(50) DEFAULT NULL::character varying,
    cp character varying(15) DEFAULT NULL::character varying,
    country character varying(4) DEFAULT NULL::character varying,
    phone character varying(20) DEFAULT NULL::character varying,
    fax character varying(20) DEFAULT NULL::character varying,
    mail_consultor character varying(255) DEFAULT NULL::character varying,
    compte_bancari character varying(30) DEFAULT NULL::character varying,
    admin boolean DEFAULT false NOT NULL,
    send_email boolean DEFAULT false NOT NULL,
    send_mail boolean DEFAULT false NOT NULL,
    lang character varying(3) DEFAULT 'es'::character varying NOT NULL,
    fac_unica_type smallint DEFAULT 0 NOT NULL,
    fac_unica_month smallint DEFAULT 1 NOT NULL,
    fac_unica_month2 smallint DEFAULT 0 NOT NULL,
    domiciliacio boolean DEFAULT false NOT NULL,
    atencio character varying(255) DEFAULT NULL::character varying,
    avisos boolean DEFAULT false NOT NULL,
    c1 boolean DEFAULT false NOT NULL,
    c2 boolean DEFAULT false NOT NULL,
    incidencies text,
    compte_comptable character varying(10) DEFAULT NULL::character varying,
    fiscalitat smallint DEFAULT 0 NOT NULL,
    needpo boolean DEFAULT false NOT NULL,
    defaultpo character varying(250) DEFAULT NULL::character varying,
    tipus smallint DEFAULT 0 NOT NULL,
    avis1 smallint DEFAULT 0 NOT NULL,
    avis2 smallint DEFAULT 0 NOT NULL,
    avis3 smallint DEFAULT 0 NOT NULL,
    fac_e boolean DEFAULT false NOT NULL,
    usuari_zona character varying(255),
    contrasenya_zona character varying(32),
    mail_zona character varying(255),
    tipus_zona smallint DEFAULT 0 NOT NULL,
    categoria smallint DEFAULT 0 NOT NULL,
    crm_url character varying(255) DEFAULT NULL::character varying,
    crm_login character varying(50) DEFAULT NULL::character varying,
    crm_password character varying(50) DEFAULT NULL::character varying,
    crm_comentari text,
    autofirma boolean DEFAULT false NOT NULL,
    enviament text,
    incidencies_pagaments text,
    usuari_extranet character varying(255) DEFAULT NULL::character varying,
    contrasenya_extranet character varying(32) DEFAULT NULL::character varying,
    esgrup_extranet boolean DEFAULT false NOT NULL,
    tipus_annexos smallint DEFAULT 0 NOT NULL,
    nom_intern character varying(40) DEFAULT NULL::character varying,
    id_nominalia character varying(15) DEFAULT NULL::character varying,
    id_cons smallint DEFAULT 0
);


ALTER TABLE public.contactes_fac OWNER TO ubilibet;

--
-- TOC entry 3325 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN contactes_fac.mail_consultor; Type: COMMENT; Schema: public; Owner: ubilibet
--

COMMENT ON COLUMN public.contactes_fac.mail_consultor IS 'antic mail_bcc';


--
-- TOC entry 3326 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN contactes_fac.tipus; Type: COMMENT; Schema: public; Owner: ubilibet
--

COMMENT ON COLUMN public.contactes_fac.tipus IS '0 Pressupost
1 Actiu
2 Obsolet';


--
-- TOC entry 3327 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN contactes_fac.tipus_zona; Type: COMMENT; Schema: public; Owner: ubilibet
--

COMMENT ON COLUMN public.contactes_fac.tipus_zona IS '0 User Read Only
1 User update Only
2 User Advanced
3 Partner Read Only
4 Partner Advanced
5 Partner Administrator';


--
-- TOC entry 3328 (class 0 OID 0)
-- Dependencies: 204
-- Name: COLUMN contactes_fac.tipus_annexos; Type: COMMENT; Schema: public; Owner: ubilibet
--

COMMENT ON COLUMN public.contactes_fac.tipus_annexos IS '''0 Sols text
1 Amb csv
2 amb PDF';


--
-- TOC entry 206 (class 1259 OID 79665)
-- Name: contactes_fac_autoritzats; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.contactes_fac_autoritzats (
    id bigint NOT NULL,
    id_c_f bigint NOT NULL,
    nom character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    observacions character varying(255) DEFAULT NULL::character varying,
    tipus_correu integer DEFAULT 0 NOT NULL,
    telefon character varying(20) DEFAULT NULL::character varying
);


ALTER TABLE public.contactes_fac_autoritzats OWNER TO ubilibet;

--
-- TOC entry 207 (class 1259 OID 79674)
-- Name: contactes_fac_autoritzats_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.contactes_fac_autoritzats_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contactes_fac_autoritzats_id_seq OWNER TO ubilibet;

--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 207
-- Name: contactes_fac_autoritzats_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.contactes_fac_autoritzats_id_seq OWNED BY public.contactes_fac_autoritzats.id;


--
-- TOC entry 213 (class 1259 OID 80424)
-- Name: contactes_fac_departaments; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.contactes_fac_departaments (
    id_c_f bigint NOT NULL,
    tipus smallint DEFAULT 0 NOT NULL,
    codi_face character varying(20) NOT NULL,
    nom character varying(200) DEFAULT NULL::character varying,
    direccio character varying(150) DEFAULT NULL::character varying,
    city character varying(80) DEFAULT NULL::character varying,
    state character varying(50) DEFAULT NULL::character varying,
    cp character varying(15) DEFAULT NULL::character varying,
    country character varying(4) DEFAULT NULL::character varying
);


ALTER TABLE public.contactes_fac_departaments OWNER TO ubilibet;

--
-- TOC entry 205 (class 1259 OID 79611)
-- Name: contactes_fac_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.contactes_fac_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contactes_fac_id_seq OWNER TO ubilibet;

--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 205
-- Name: contactes_fac_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.contactes_fac_id_seq OWNED BY public.contactes_fac.id;


--
-- TOC entry 214 (class 1259 OID 80437)
-- Name: contactes_fac_sectors; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.contactes_fac_sectors (
    id_c_f integer NOT NULL,
    id_sector integer NOT NULL
);


ALTER TABLE public.contactes_fac_sectors OWNER TO ubilibet;

--
-- TOC entry 212 (class 1259 OID 80420)
-- Name: country; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.country (
    iso character(2) NOT NULL,
    iso3 character(3),
    numcode smallint,
    name_en character varying(80) NOT NULL,
    name_es character varying(80),
    name_cat character varying(80),
    ue boolean DEFAULT false NOT NULL,
    phone character varying(10),
    continent character varying(30)
);


ALTER TABLE public.country OWNER TO ubilibet;

--
-- TOC entry 197 (class 1259 OID 46334)
-- Name: departaments; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.departaments (
    id integer NOT NULL,
    nom character varying(50) NOT NULL
);


ALTER TABLE public.departaments OWNER TO ubilibet;

--
-- TOC entry 196 (class 1259 OID 46332)
-- Name: departaments_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.departaments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.departaments_id_seq OWNER TO ubilibet;

--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 196
-- Name: departaments_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.departaments_id_seq OWNED BY public.departaments.id;


--
-- TOC entry 217 (class 1259 OID 80498)
-- Name: descomptes; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.descomptes (
    id_c_f integer NOT NULL,
    id_prod integer NOT NULL,
    descompte real DEFAULT 0 NOT NULL
);


ALTER TABLE public.descomptes OWNER TO ubilibet;

--
-- TOC entry 218 (class 1259 OID 80508)
-- Name: descomptes_families; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.descomptes_families (
    id_c_f integer NOT NULL,
    id_familia integer NOT NULL,
    descompte real DEFAULT 0 NOT NULL
);


ALTER TABLE public.descomptes_families OWNER TO ubilibet;

--
-- TOC entry 219 (class 1259 OID 80518)
-- Name: divises; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.divises (
    codi character varying(3) NOT NULL,
    numero smallint DEFAULT 0 NOT NULL,
    nom character varying(100) NOT NULL,
    zona text,
    decimals smallint DEFAULT 0 NOT NULL,
    activa boolean DEFAULT false NOT NULL
);


ALTER TABLE public.divises OWNER TO ubilibet;

--
-- TOC entry 220 (class 1259 OID 80527)
-- Name: dns; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.dns (
    id integer NOT NULL,
    nom character varying(150) NOT NULL,
    ip character varying(30),
    prioritari boolean DEFAULT false NOT NULL
);


ALTER TABLE public.dns OWNER TO ubilibet;

--
-- TOC entry 221 (class 1259 OID 80534)
-- Name: dns_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.dns_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.dns_id_seq OWNER TO ubilibet;

--
-- TOC entry 3343 (class 0 OID 0)
-- Dependencies: 221
-- Name: dns_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.dns_id_seq OWNED BY public.dns.id;


--
-- TOC entry 222 (class 1259 OID 80642)
-- Name: families; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.families (
    id integer NOT NULL,
    nom character varying(80) NOT NULL,
    nleft integer,
    nright integer,
    nlevel integer,
    parent_id integer,
    editable boolean DEFAULT true NOT NULL
);


ALTER TABLE public.families OWNER TO ubilibet;

--
-- TOC entry 223 (class 1259 OID 80646)
-- Name: families_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.families_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.families_id_seq OWNER TO ubilibet;

--
-- TOC entry 3346 (class 0 OID 0)
-- Dependencies: 223
-- Name: families_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.families_id_seq OWNED BY public.families.id;


--
-- TOC entry 201 (class 1259 OID 46357)
-- Name: funcions; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.funcions (
    id integer NOT NULL,
    descripcio character varying(50) NOT NULL,
    comentari text
);


ALTER TABLE public.funcions OWNER TO ubilibet;

--
-- TOC entry 200 (class 1259 OID 46355)
-- Name: funcions_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.funcions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcions_id_seq OWNER TO ubilibet;

--
-- TOC entry 3348 (class 0 OID 0)
-- Dependencies: 200
-- Name: funcions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.funcions_id_seq OWNED BY public.funcions.id;


--
-- TOC entry 210 (class 1259 OID 80306)
-- Name: grups; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.grups (
    id integer NOT NULL,
    nom character varying(200) NOT NULL,
    nif character varying(15),
    direccio character varying(150),
    poblacio character varying(80),
    cp character varying(15),
    pais character varying(4),
    telefon character varying(20),
    fax character varying(20),
    state character varying(40),
    lang character varying(3) DEFAULT 'es'::character varying,
    cf_principal integer
);


ALTER TABLE public.grups OWNER TO ubilibet;

--
-- TOC entry 224 (class 1259 OID 80651)
-- Name: grups_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.grups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.grups_id_seq OWNER TO ubilibet;

--
-- TOC entry 3350 (class 0 OID 0)
-- Dependencies: 224
-- Name: grups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.grups_id_seq OWNED BY public.grups.id;


--
-- TOC entry 203 (class 1259 OID 46383)
-- Name: permisos_departaments; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.permisos_departaments (
    id_funcio integer NOT NULL,
    id_departament integer NOT NULL,
    nivell smallint DEFAULT 0 NOT NULL
);


ALTER TABLE public.permisos_departaments OWNER TO ubilibet;

--
-- TOC entry 202 (class 1259 OID 46366)
-- Name: permisos_usuaris; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.permisos_usuaris (
    id_funcio integer NOT NULL,
    id_usuari integer NOT NULL,
    nivell smallint DEFAULT 0 NOT NULL,
    contrasenya boolean DEFAULT false NOT NULL
);


ALTER TABLE public.permisos_usuaris OWNER TO ubilibet;

--
-- TOC entry 225 (class 1259 OID 80880)
-- Name: plantilles_certificats; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.plantilles_certificats (
    id_c_f integer NOT NULL,
    id_titular integer,
    id_c_a integer,
    id_c_t integer,
    id_tipus integer,
    years smallint DEFAULT 1 NOT NULL
);


ALTER TABLE public.plantilles_certificats OWNER TO ubilibet;

--
-- TOC entry 215 (class 1259 OID 80440)
-- Name: plantilles_dominis; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.plantilles_dominis (
    id_c_f integer NOT NULL,
    id_titular integer,
    id_c_a integer,
    id_c_t integer,
    id_dns1 integer,
    id_dns2 integer,
    id_dns3 integer,
    id_dns4 integer,
    id_dns5 integer,
    id_dns6 integer,
    redirec boolean DEFAULT false NOT NULL,
    dns_type smallint DEFAULT 0 NOT NULL,
    id_c_pf integer
);


ALTER TABLE public.plantilles_dominis OWNER TO ubilibet;

--
-- TOC entry 226 (class 1259 OID 80884)
-- Name: plantilles_marks; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.plantilles_marks (
    id_c_f integer NOT NULL,
    id_titular integer
);


ALTER TABLE public.plantilles_marks OWNER TO ubilibet;

--
-- TOC entry 216 (class 1259 OID 80445)
-- Name: sectors; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.sectors (
    id integer NOT NULL,
    nom character varying(50),
    general boolean DEFAULT false NOT NULL
);


ALTER TABLE public.sectors OWNER TO ubilibet;

--
-- TOC entry 227 (class 1259 OID 80903)
-- Name: sectors_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.sectors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sectors_id_seq OWNER TO ubilibet;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 227
-- Name: sectors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.sectors_id_seq OWNED BY public.sectors.id;


--
-- TOC entry 208 (class 1259 OID 80126)
-- Name: tarifes; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.tarifes (
    id integer NOT NULL,
    id_familia smallint DEFAULT 1 NOT NULL,
    fixe boolean DEFAULT false NOT NULL,
    codi character varying(30) NOT NULL,
    tarifa double precision DEFAULT 0 NOT NULL,
    tarifa_adm double precision DEFAULT 0 NOT NULL,
    desc_es text,
    desc_cat text,
    desc_en text,
    tarifa_premium double precision DEFAULT 0 NOT NULL,
    tarifa_cost double precision DEFAULT 0 NOT NULL,
    divisa character varying(3) DEFAULT NULL::character varying,
    tarifa_divisa double precision DEFAULT 0 NOT NULL,
    abr_es character varying(50),
    abr_cat character varying(50),
    abr_en character varying(50),
    nominalia character varying(30)
);


ALTER TABLE public.tarifes OWNER TO ubilibet;

--
-- TOC entry 209 (class 1259 OID 80140)
-- Name: tarifes_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.tarifes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tarifes_id_seq OWNER TO ubilibet;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 209
-- Name: tarifes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.tarifes_id_seq OWNED BY public.tarifes.id;


--
-- TOC entry 211 (class 1259 OID 80380)
-- Name: titulars; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.titulars (
    id integer NOT NULL,
    first_name character varying(150) NOT NULL,
    last_name character varying(50) DEFAULT NULL::character varying,
    organization character varying(100) DEFAULT NULL::character varying,
    nif character varying(20) DEFAULT NULL::character varying,
    street character varying(150) DEFAULT NULL::character varying,
    street2 character varying(50) DEFAULT NULL::character varying,
    city character varying(80) DEFAULT NULL::character varying,
    state character varying(40) DEFAULT NULL::character varying,
    cp character varying(30) DEFAULT NULL::character varying,
    country character varying(4) DEFAULT NULL::character varying,
    phone character varying(20) DEFAULT NULL::character varying,
    fax character varying(20) DEFAULT NULL::character varying,
    mail character varying(150) DEFAULT NULL::character varying,
    registrant_number character varying(50) DEFAULT NULL::character varying,
    registrant_type character varying(40) DEFAULT NULL::character varying,
    registrant_date character varying(20) DEFAULT NULL::character varying,
    lang character varying(3) DEFAULT 'es'::character varying,
    form_juridica integer DEFAULT 1,
    registrant_class character varying(255) DEFAULT NULL::character varying,
    uin character varying(20) DEFAULT NULL::character varying,
    duns character varying(20) DEFAULT NULL::character varying
);


ALTER TABLE public.titulars OWNER TO ubilibet;

--
-- TOC entry 228 (class 1259 OID 80920)
-- Name: titulars_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.titulars_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.titulars_id_seq OWNER TO ubilibet;

--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 228
-- Name: titulars_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.titulars_id_seq OWNED BY public.titulars.id;


--
-- TOC entry 199 (class 1259 OID 46342)
-- Name: usuaris; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.usuaris (
    id integer NOT NULL,
    nom character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    contrasenya character varying(50) NOT NULL,
    certificat boolean DEFAULT false NOT NULL,
    idioma smallint DEFAULT 0 NOT NULL,
    id_departament integer NOT NULL
);


ALTER TABLE public.usuaris OWNER TO ubilibet;

--
-- TOC entry 232 (class 1259 OID 82157)
-- Name: usuaris_contactes_fac; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.usuaris_contactes_fac (
    id_usuari integer NOT NULL,
    id_c_f integer NOT NULL
);


ALTER TABLE public.usuaris_contactes_fac OWNER TO ubilibet;

--
-- TOC entry 231 (class 1259 OID 82142)
-- Name: usuaris_grups; Type: TABLE; Schema: public; Owner: ubilibet
--

CREATE TABLE public.usuaris_grups (
    id_usuari integer NOT NULL,
    id_grup integer NOT NULL
);


ALTER TABLE public.usuaris_grups OWNER TO ubilibet;

--
-- TOC entry 198 (class 1259 OID 46340)
-- Name: usuaris_id_seq; Type: SEQUENCE; Schema: public; Owner: ubilibet
--

CREATE SEQUENCE public.usuaris_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuaris_id_seq OWNER TO ubilibet;

--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 198
-- Name: usuaris_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ubilibet
--

ALTER SEQUENCE public.usuaris_id_seq OWNED BY public.usuaris.id;


--
-- TOC entry 3077 (class 2604 OID 82114)
-- Name: contactes_admin_tec id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_admin_tec ALTER COLUMN id SET DEFAULT nextval('public.contactes_admin_tec_id_seq'::regclass);


--
-- TOC entry 3018 (class 2604 OID 80948)
-- Name: contactes_fac id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac ALTER COLUMN id SET DEFAULT nextval('public.contactes_fac_id_seq'::regclass);


--
-- TOC entry 3019 (class 2604 OID 80949)
-- Name: contactes_fac_autoritzats id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_autoritzats ALTER COLUMN id SET DEFAULT nextval('public.contactes_fac_autoritzats_id_seq'::regclass);


--
-- TOC entry 2967 (class 2604 OID 46337)
-- Name: departaments id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.departaments ALTER COLUMN id SET DEFAULT nextval('public.departaments_id_seq'::regclass);


--
-- TOC entry 3072 (class 2604 OID 80951)
-- Name: dns id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.dns ALTER COLUMN id SET DEFAULT nextval('public.dns_id_seq'::regclass);


--
-- TOC entry 3074 (class 2604 OID 80962)
-- Name: families id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.families ALTER COLUMN id SET DEFAULT nextval('public.families_id_seq'::regclass);


--
-- TOC entry 2971 (class 2604 OID 46360)
-- Name: funcions id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.funcions ALTER COLUMN id SET DEFAULT nextval('public.funcions_id_seq'::regclass);


--
-- TOC entry 3032 (class 2604 OID 80963)
-- Name: grups id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.grups ALTER COLUMN id SET DEFAULT nextval('public.grups_id_seq'::regclass);


--
-- TOC entry 3065 (class 2604 OID 80988)
-- Name: sectors id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.sectors ALTER COLUMN id SET DEFAULT nextval('public.sectors_id_seq'::regclass);


--
-- TOC entry 3023 (class 2604 OID 80990)
-- Name: tarifes id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.tarifes ALTER COLUMN id SET DEFAULT nextval('public.tarifes_id_seq'::regclass);


--
-- TOC entry 3054 (class 2604 OID 80995)
-- Name: titulars id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.titulars ALTER COLUMN id SET DEFAULT nextval('public.titulars_id_seq'::regclass);


--
-- TOC entry 2968 (class 2604 OID 46345)
-- Name: usuaris id; Type: DEFAULT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris ALTER COLUMN id SET DEFAULT nextval('public.usuaris_id_seq'::regclass);


--
-- TOC entry 3095 (class 2606 OID 46339)
-- Name: departaments Departament_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.departaments
    ADD CONSTRAINT "Departament_pkey" PRIMARY KEY (id);


--
-- TOC entry 3155 (class 2606 OID 82135)
-- Name: contactes_admin_tec contactes_admin_tec_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_admin_tec
    ADD CONSTRAINT contactes_admin_tec_pkey PRIMARY KEY (id);


--
-- TOC entry 3112 (class 2606 OID 81023)
-- Name: contactes_fac_autoritzats contactes_fac_autoritzats_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_autoritzats
    ADD CONSTRAINT contactes_fac_autoritzats_pkey PRIMARY KEY (id);


--
-- TOC entry 3124 (class 2606 OID 81025)
-- Name: contactes_fac_departaments contactes_fac_departaments_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_departaments
    ADD CONSTRAINT contactes_fac_departaments_pkey PRIMARY KEY (id_c_f, tipus);


--
-- TOC entry 3107 (class 2606 OID 81027)
-- Name: contactes_fac contactes_fac_id_nominalia_key; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac
    ADD CONSTRAINT contactes_fac_id_nominalia_key UNIQUE (id_nominalia);


--
-- TOC entry 3110 (class 2606 OID 81029)
-- Name: contactes_fac contactes_fac_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac
    ADD CONSTRAINT contactes_fac_pkey PRIMARY KEY (id);


--
-- TOC entry 3126 (class 2606 OID 81031)
-- Name: contactes_fac_sectors contactes_fac_sectors_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_sectors
    ADD CONSTRAINT contactes_fac_sectors_pkey PRIMARY KEY (id_c_f, id_sector);


--
-- TOC entry 3122 (class 2606 OID 81041)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (iso);


--
-- TOC entry 3136 (class 2606 OID 81043)
-- Name: descomptes_families descomptes_families_idx; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes_families
    ADD CONSTRAINT descomptes_families_idx PRIMARY KEY (id_c_f, id_familia);


--
-- TOC entry 3139 (class 2606 OID 81045)
-- Name: descomptes_families descomptes_families_pk; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes_families
    ADD CONSTRAINT descomptes_families_pk UNIQUE (id_c_f, id_familia);


--
-- TOC entry 3134 (class 2606 OID 81047)
-- Name: descomptes descomptes_pk; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes
    ADD CONSTRAINT descomptes_pk PRIMARY KEY (id_c_f, id_prod);


--
-- TOC entry 3141 (class 2606 OID 81049)
-- Name: divises divises_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.divises
    ADD CONSTRAINT divises_pkey PRIMARY KEY (codi);


--
-- TOC entry 3143 (class 2606 OID 81053)
-- Name: dns dns_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.dns
    ADD CONSTRAINT dns_pkey PRIMARY KEY (id);


--
-- TOC entry 3149 (class 2606 OID 81093)
-- Name: families families_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.families
    ADD CONSTRAINT families_pkey PRIMARY KEY (id);


--
-- TOC entry 3101 (class 2606 OID 46365)
-- Name: funcions funcions_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.funcions
    ADD CONSTRAINT funcions_pkey PRIMARY KEY (id);


--
-- TOC entry 3118 (class 2606 OID 81097)
-- Name: grups grups_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.grups
    ADD CONSTRAINT grups_pkey PRIMARY KEY (id);


--
-- TOC entry 3105 (class 2606 OID 46389)
-- Name: permisos_departaments permisos_departaments_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_departaments
    ADD CONSTRAINT permisos_departaments_pkey PRIMARY KEY (id_funcio, id_departament);


--
-- TOC entry 3103 (class 2606 OID 46372)
-- Name: permisos_usuaris permisos_usuaris_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_usuaris
    ADD CONSTRAINT permisos_usuaris_pkey PRIMARY KEY (id_funcio, id_usuari);


--
-- TOC entry 3151 (class 2606 OID 81165)
-- Name: plantilles_certificats plantilles_certificats_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_certificats
    ADD CONSTRAINT plantilles_certificats_pkey PRIMARY KEY (id_c_f);


--
-- TOC entry 3128 (class 2606 OID 81167)
-- Name: plantilles_dominis plantilles_dominis_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_pkey PRIMARY KEY (id_c_f);


--
-- TOC entry 3153 (class 2606 OID 81169)
-- Name: plantilles_marks plantilles_marks_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_marks
    ADD CONSTRAINT plantilles_marks_pkey PRIMARY KEY (id_c_f);


--
-- TOC entry 3130 (class 2606 OID 81179)
-- Name: sectors sectors_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.sectors
    ADD CONSTRAINT sectors_pkey PRIMARY KEY (id);


--
-- TOC entry 3116 (class 2606 OID 81183)
-- Name: tarifes tarifes_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.tarifes
    ADD CONSTRAINT tarifes_pkey PRIMARY KEY (id);


--
-- TOC entry 3120 (class 2606 OID 81195)
-- Name: titulars titulars_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.titulars
    ADD CONSTRAINT titulars_pkey PRIMARY KEY (id);


--
-- TOC entry 3159 (class 2606 OID 82161)
-- Name: usuaris_contactes_fac usuaris_contactes_fac_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_contactes_fac
    ADD CONSTRAINT usuaris_contactes_fac_pkey PRIMARY KEY (id_usuari, id_c_f);


--
-- TOC entry 3097 (class 2606 OID 82173)
-- Name: usuaris usuaris_email_key; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris
    ADD CONSTRAINT usuaris_email_key UNIQUE (email);


--
-- TOC entry 3157 (class 2606 OID 82146)
-- Name: usuaris_grups usuaris_grups_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_grups
    ADD CONSTRAINT usuaris_grups_pkey PRIMARY KEY (id_usuari, id_grup);


--
-- TOC entry 3099 (class 2606 OID 46349)
-- Name: usuaris usuaris_pkey; Type: CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris
    ADD CONSTRAINT usuaris_pkey PRIMARY KEY (id);


--
-- TOC entry 3108 (class 1259 OID 81208)
-- Name: contactes_fac_idx; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX contactes_fac_idx ON public.contactes_fac USING btree (id_grup);


--
-- TOC entry 3137 (class 1259 OID 81210)
-- Name: descomptes_families_idx1; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX descomptes_families_idx1 ON public.descomptes_families USING btree (id_c_f);


--
-- TOC entry 3131 (class 1259 OID 81211)
-- Name: descomptes_nou_idx; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX descomptes_nou_idx ON public.descomptes USING btree (id_c_f);


--
-- TOC entry 3132 (class 1259 OID 81212)
-- Name: descomptes_nou_idx1; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX descomptes_nou_idx1 ON public.descomptes USING btree (id_prod, id_c_f);


--
-- TOC entry 3144 (class 1259 OID 81229)
-- Name: families_idx; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX families_idx ON public.families USING btree (nleft);


--
-- TOC entry 3145 (class 1259 OID 81230)
-- Name: families_idx1; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX families_idx1 ON public.families USING btree (nright);


--
-- TOC entry 3146 (class 1259 OID 81231)
-- Name: families_idx2; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX families_idx2 ON public.families USING btree (nlevel);


--
-- TOC entry 3147 (class 1259 OID 81232)
-- Name: families_idx3; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX families_idx3 ON public.families USING btree (parent_id);


--
-- TOC entry 3113 (class 1259 OID 81243)
-- Name: tarifes_idx; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE INDEX tarifes_idx ON public.tarifes USING btree (id_familia);


--
-- TOC entry 3114 (class 1259 OID 81244)
-- Name: tarifes_idx1; Type: INDEX; Schema: public; Owner: ubilibet
--

CREATE UNIQUE INDEX tarifes_idx1 ON public.tarifes USING btree (codi);


--
-- TOC entry 3166 (class 2606 OID 81368)
-- Name: contactes_fac_autoritzats contactes_fac_autoritzats_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_autoritzats
    ADD CONSTRAINT contactes_fac_autoritzats_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3170 (class 2606 OID 81373)
-- Name: contactes_fac_departaments contactes_fac_departaments_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_departaments
    ADD CONSTRAINT contactes_fac_departaments_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3165 (class 2606 OID 81378)
-- Name: contactes_fac contactes_fac_fk; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac
    ADD CONSTRAINT contactes_fac_fk FOREIGN KEY (id_grup) REFERENCES public.grups(id) ON DELETE RESTRICT;


--
-- TOC entry 3171 (class 2606 OID 81383)
-- Name: contactes_fac_sectors contactes_fac_sectors_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_sectors
    ADD CONSTRAINT contactes_fac_sectors_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3172 (class 2606 OID 81388)
-- Name: contactes_fac_sectors contactes_fac_sectors_id_sector_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.contactes_fac_sectors
    ADD CONSTRAINT contactes_fac_sectors_id_sector_fkey FOREIGN KEY (id_sector) REFERENCES public.sectors(id) ON DELETE CASCADE;


--
-- TOC entry 3183 (class 2606 OID 81423)
-- Name: descomptes_families descomptes_families_fk; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes_families
    ADD CONSTRAINT descomptes_families_fk FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3184 (class 2606 OID 81428)
-- Name: descomptes_families descomptes_families_fk1; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes_families
    ADD CONSTRAINT descomptes_families_fk1 FOREIGN KEY (id_familia) REFERENCES public.families(id) ON DELETE CASCADE;


--
-- TOC entry 3181 (class 2606 OID 81433)
-- Name: descomptes descomptes_fk; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes
    ADD CONSTRAINT descomptes_fk FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3182 (class 2606 OID 81438)
-- Name: descomptes descomptes_fk1; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.descomptes
    ADD CONSTRAINT descomptes_fk1 FOREIGN KEY (id_prod) REFERENCES public.tarifes(id) ON DELETE CASCADE;


--
-- TOC entry 3169 (class 2606 OID 81673)
-- Name: grups grups_cf_principal_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.grups
    ADD CONSTRAINT grups_cf_principal_fkey FOREIGN KEY (cf_principal) REFERENCES public.contactes_fac(id) ON DELETE SET NULL;


--
-- TOC entry 3164 (class 2606 OID 46395)
-- Name: permisos_departaments permisos_departaments_id_departament_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_departaments
    ADD CONSTRAINT permisos_departaments_id_departament_fkey FOREIGN KEY (id_departament) REFERENCES public.departaments(id) ON DELETE CASCADE;


--
-- TOC entry 3163 (class 2606 OID 46390)
-- Name: permisos_departaments permisos_departaments_id_funcio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_departaments
    ADD CONSTRAINT permisos_departaments_id_funcio_fkey FOREIGN KEY (id_funcio) REFERENCES public.funcions(id) ON DELETE CASCADE;


--
-- TOC entry 3161 (class 2606 OID 46373)
-- Name: permisos_usuaris permisos_usuaris_id_funcio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_usuaris
    ADD CONSTRAINT permisos_usuaris_id_funcio_fkey FOREIGN KEY (id_funcio) REFERENCES public.funcions(id) ON DELETE CASCADE;


--
-- TOC entry 3162 (class 2606 OID 46378)
-- Name: permisos_usuaris permisos_usuaris_id_usuari_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.permisos_usuaris
    ADD CONSTRAINT permisos_usuaris_id_usuari_fkey FOREIGN KEY (id_usuari) REFERENCES public.usuaris(id);


--
-- TOC entry 3185 (class 2606 OID 81918)
-- Name: plantilles_certificats plantilles_certificats_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_certificats
    ADD CONSTRAINT plantilles_certificats_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3186 (class 2606 OID 81933)
-- Name: plantilles_certificats plantilles_certificats_id_titular_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_certificats
    ADD CONSTRAINT plantilles_certificats_id_titular_fkey FOREIGN KEY (id_titular) REFERENCES public.titulars(id) ON DELETE SET NULL;


--
-- TOC entry 3173 (class 2606 OID 81943)
-- Name: plantilles_dominis plantilles_dominis_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3174 (class 2606 OID 81958)
-- Name: plantilles_dominis plantilles_dominis_id_dns1_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns1_fkey FOREIGN KEY (id_dns1) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3175 (class 2606 OID 81963)
-- Name: plantilles_dominis plantilles_dominis_id_dns2_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns2_fkey FOREIGN KEY (id_dns2) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3176 (class 2606 OID 81968)
-- Name: plantilles_dominis plantilles_dominis_id_dns3_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns3_fkey FOREIGN KEY (id_dns3) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3177 (class 2606 OID 81973)
-- Name: plantilles_dominis plantilles_dominis_id_dns4_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns4_fkey FOREIGN KEY (id_dns4) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3178 (class 2606 OID 81978)
-- Name: plantilles_dominis plantilles_dominis_id_dns5_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns5_fkey FOREIGN KEY (id_dns5) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3179 (class 2606 OID 81983)
-- Name: plantilles_dominis plantilles_dominis_id_dns6_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_dns6_fkey FOREIGN KEY (id_dns6) REFERENCES public.dns(id) ON DELETE SET NULL;


--
-- TOC entry 3180 (class 2606 OID 81988)
-- Name: plantilles_dominis plantilles_dominis_id_titular_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_dominis
    ADD CONSTRAINT plantilles_dominis_id_titular_fkey FOREIGN KEY (id_titular) REFERENCES public.titulars(id) ON DELETE SET NULL;


--
-- TOC entry 3187 (class 2606 OID 81993)
-- Name: plantilles_marks plantilles_marks_id_c_f_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_marks
    ADD CONSTRAINT plantilles_marks_id_c_f_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON DELETE CASCADE;


--
-- TOC entry 3188 (class 2606 OID 81998)
-- Name: plantilles_marks plantilles_marks_id_titular_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.plantilles_marks
    ADD CONSTRAINT plantilles_marks_id_titular_fkey FOREIGN KEY (id_titular) REFERENCES public.titulars(id) ON DELETE SET NULL;


--
-- TOC entry 3167 (class 2606 OID 82033)
-- Name: tarifes tarifes_divisa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.tarifes
    ADD CONSTRAINT tarifes_divisa_fkey FOREIGN KEY (divisa) REFERENCES public.divises(codi);


--
-- TOC entry 3168 (class 2606 OID 82038)
-- Name: tarifes tarifes_fk; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.tarifes
    ADD CONSTRAINT tarifes_fk FOREIGN KEY (id_familia) REFERENCES public.families(id) ON DELETE RESTRICT;


--
-- TOC entry 3192 (class 2606 OID 82162)
-- Name: usuaris_contactes_fac usuaris_contactes_fac_id_grup_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_contactes_fac
    ADD CONSTRAINT usuaris_contactes_fac_id_grup_fkey FOREIGN KEY (id_c_f) REFERENCES public.contactes_fac(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 3191 (class 2606 OID 82167)
-- Name: usuaris_contactes_fac usuaris_contactes_fac_id_usuari_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_contactes_fac
    ADD CONSTRAINT usuaris_contactes_fac_id_usuari_fkey FOREIGN KEY (id_usuari) REFERENCES public.usuaris(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 3190 (class 2606 OID 82147)
-- Name: usuaris_grups usuaris_grups_id_grup_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_grups
    ADD CONSTRAINT usuaris_grups_id_grup_fkey FOREIGN KEY (id_grup) REFERENCES public.grups(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 3189 (class 2606 OID 82152)
-- Name: usuaris_grups usuaris_grups_id_usuari_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris_grups
    ADD CONSTRAINT usuaris_grups_id_usuari_fkey FOREIGN KEY (id_usuari) REFERENCES public.usuaris(id) ON UPDATE RESTRICT ON DELETE CASCADE;


--
-- TOC entry 3160 (class 2606 OID 46350)
-- Name: usuaris usuaris_id_departament_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ubilibet
--

ALTER TABLE ONLY public.usuaris
    ADD CONSTRAINT usuaris_id_departament_fkey FOREIGN KEY (id_departament) REFERENCES public.departaments(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 3321 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: ubilibet
--

REVOKE ALL ON SCHEMA public FROM postgres;
REVOKE ALL ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO ubilibet;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- TOC entry 3323 (class 0 OID 0)
-- Dependencies: 230
-- Name: TABLE contactes_admin_tec; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.contactes_admin_tec TO PUBLIC;


--
-- TOC entry 3329 (class 0 OID 0)
-- Dependencies: 204
-- Name: TABLE contactes_fac; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.contactes_fac TO PUBLIC;


--
-- TOC entry 3330 (class 0 OID 0)
-- Dependencies: 206
-- Name: TABLE contactes_fac_autoritzats; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.contactes_fac_autoritzats TO PUBLIC;


--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 207
-- Name: SEQUENCE contactes_fac_autoritzats_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.contactes_fac_autoritzats_id_seq TO PUBLIC;


--
-- TOC entry 3333 (class 0 OID 0)
-- Dependencies: 213
-- Name: TABLE contactes_fac_departaments; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.contactes_fac_departaments TO PUBLIC;


--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 205
-- Name: SEQUENCE contactes_fac_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.contactes_fac_id_seq TO PUBLIC;


--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 214
-- Name: TABLE contactes_fac_sectors; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.contactes_fac_sectors TO PUBLIC;


--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 212
-- Name: TABLE country; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.country TO PUBLIC;


--
-- TOC entry 3339 (class 0 OID 0)
-- Dependencies: 217
-- Name: TABLE descomptes; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.descomptes TO PUBLIC;


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 218
-- Name: TABLE descomptes_families; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.descomptes_families TO PUBLIC;


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 219
-- Name: TABLE divises; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.divises TO PUBLIC;


--
-- TOC entry 3342 (class 0 OID 0)
-- Dependencies: 220
-- Name: TABLE dns; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.dns TO PUBLIC;


--
-- TOC entry 3344 (class 0 OID 0)
-- Dependencies: 221
-- Name: SEQUENCE dns_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.dns_id_seq TO PUBLIC;


--
-- TOC entry 3345 (class 0 OID 0)
-- Dependencies: 222
-- Name: TABLE families; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.families TO PUBLIC;


--
-- TOC entry 3347 (class 0 OID 0)
-- Dependencies: 223
-- Name: SEQUENCE families_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.families_id_seq TO PUBLIC;


--
-- TOC entry 3349 (class 0 OID 0)
-- Dependencies: 210
-- Name: TABLE grups; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.grups TO PUBLIC;


--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 224
-- Name: SEQUENCE grups_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.grups_id_seq TO PUBLIC;


--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 225
-- Name: TABLE plantilles_certificats; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.plantilles_certificats TO PUBLIC;


--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 215
-- Name: TABLE plantilles_dominis; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.plantilles_dominis TO PUBLIC;


--
-- TOC entry 3354 (class 0 OID 0)
-- Dependencies: 226
-- Name: TABLE plantilles_marks; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.plantilles_marks TO PUBLIC;


--
-- TOC entry 3355 (class 0 OID 0)
-- Dependencies: 216
-- Name: TABLE sectors; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.sectors TO PUBLIC;


--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 227
-- Name: SEQUENCE sectors_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.sectors_id_seq TO PUBLIC;


--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 208
-- Name: TABLE tarifes; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.tarifes TO PUBLIC;


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 209
-- Name: SEQUENCE tarifes_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.tarifes_id_seq TO PUBLIC;


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 211
-- Name: TABLE titulars; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON TABLE public.titulars TO PUBLIC;


--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 228
-- Name: SEQUENCE titulars_id_seq; Type: ACL; Schema: public; Owner: ubilibet
--

GRANT ALL ON SEQUENCE public.titulars_id_seq TO PUBLIC;


--
-- TOC entry 1797 (class 826 OID 82100)
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES  TO PUBLIC;


--
-- TOC entry 1798 (class 826 OID 82101)
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES  TO PUBLIC;


-- Completed on 2020-05-28 22:32:55 CEST

--
-- PostgreSQL database dump complete
--

