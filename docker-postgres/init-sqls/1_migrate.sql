--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

--
-- Name: authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authority (
    name text NOT NULL
);


ALTER TABLE public.authority OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name text
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_id_seq OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO postgres;

--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    user_id bigint,
    category_id bigint NOT NULL,
    name text,
    description text,
    qty bigint,
    price numeric(8,2),
    is_sold_out boolean DEFAULT false,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by text,
    modified_by text,
    best_before timestamp without time zone
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username text,
    password text,
    name text,
    email text,
    date_of_birth date,
    address text,
    nation text,
    enabled boolean DEFAULT true,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    created_by text,
    modified_by text
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_authority; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_authority (
    user_id bigint,
    authority text NOT NULL
);


ALTER TABLE public.user_authority OWNER TO postgres;



--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);



--
-- Data for Name: authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authority (name) FROM stdin;
ROLE_ADMIN
ROLE_USER
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name) FROM stdin;
1	Toy
2	Food
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, user_id, category_id, name, description, qty, price, is_sold_out, created_at, updated_at, created_by, modified_by, best_before) FROM stdin;
3	4	1	Jack Toy	This is a Jack ass	999	100.00	f	2020-01-23 21:16:53.018	2020-01-23 22:33:34.15	jack	jack	2020-01-15 20:32:12.101
2	4	1	Jack Toy	This is a Jack ass	99	100.00	f	2020-01-23 21:15:50.389	2020-01-23 21:15:50.389	jack	jack	2020-01-15 20:32:12.111
1	1	1	Apple Toy	This is a toy	1	100.00	f	2020-01-15 02:45:20.017986	2020-01-15 11:32:48.93	jack	jack	2020-01-15 20:32:12.111
5	3	2	Orange Pie	This is a Orange Pie	10	700.00	f	2020-02-08 19:03:44.873	2020-02-08 19:03:44.873	jack	jack	2020-01-20 20:32:12.111
6	4	2	Tiger Pie	This is a Tiger Pie	10	700.00	f	2020-02-08 19:30:15.01	2020-02-08 19:30:15.01	jack	jack	2020-01-20 20:32:12.111
4	1	1	Apple Pie	This is a Pie	10	700.00	f	2020-02-08 18:58:35.541	2020-02-08 18:58:35.541	jack	jack	2020-01-20 20:32:12.111
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, username, password, name, email, date_of_birth, address, nation, enabled, created_at, updated_at, created_by, modified_by) FROM stdin;
1	jack	$2a$10$mD1dvNOs5G7P2mMmL/bcw.X3ksrcGYH9O3HXVC.EMXh8NM7X.nZ/O	jack	test	2020-01-25	test	test	t	2020-01-05 12:00:14.822	2020-01-15 11:41:40.488	jack	jack
2	peterer	peter	peter	test	2020-01-01	test	test	t	2020-01-05 12:32:12.816	2020-01-26 19:52:22.61	jack	jack
3	sam	sam	sam	sam@gmail.com	2020-01-01	sam house	hk	t	2020-01-07 21:16:46.287	2020-01-07 21:16:46.287	jack	jack
4	tom	tom	tom	tom@gmail.com	2020-01-01	tom house	hk	t	2020-01-23 20:52:11.075	2020-01-23 20:52:11.075	jack	jack
7	jen	$2a$10$bjpX48OcrKOS1VtUd9oTV.VWQUqGXoXQf1nPE52Tunbaea2bYt2XG	jen	jen	2020-09-21	test	test	t	2020-02-09 13:20:31.431	2020-02-09 13:20:31.431	jack	jack
\.


--
-- Data for Name: user_authority; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_authority (user_id, authority) FROM stdin;
1	ROLE_ADMIN
7	ROLE_USER
\.


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 10, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 10, false);



--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_id_seq', 10, false);


--
-- Name: authority authority_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authority
    ADD CONSTRAINT authority_pkey PRIMARY KEY (name);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);



--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: product product_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: product product_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: user_authority user_authority_authority_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authority
    ADD CONSTRAINT user_authority_authority_fkey FOREIGN KEY (authority) REFERENCES public.authority(name);


--
-- Name: user_authority user_authority_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_authority
    ADD CONSTRAINT user_authority_user_id_fkey FOREIGN KEY (user_id) REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

