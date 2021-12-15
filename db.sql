
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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 200 (class 1259 OID 16521)
-- Name: persona; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persona (
    nombre character varying NOT NULL,
    cedula integer NOT NULL,
    "contraseña" character varying(300)
);


ALTER TABLE public.persona OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16782)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    sku character varying,
    nombre character varying,
    precio double precision,
    oferta double precision,
    stock double precision,
    peso double precision
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16779)
-- Name: producto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.producto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 999999999
    CACHE 1;


ALTER TABLE public.producto_id_seq OWNER TO postgres;

--
-- TOC entry 3034 (class 0 OID 16521)
-- Dependencies: 200
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persona (nombre, cedula, "contraseña") FROM stdin;
admin	123456	admin
\.


--
-- TOC entry 3036 (class 0 OID 16782)
-- Dependencies: 202
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.producto (sku, nombre, precio, oferta, stock, peso) FROM stdin;
M1	Maquillaje	123	0	1	0
\.


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 201
-- Name: producto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.producto_id_seq', 2, true);
