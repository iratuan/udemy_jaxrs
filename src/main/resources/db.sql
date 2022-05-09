CREATE TABLE public.brands (
    id serial NOT NULL,
    name character varying(50)
);

CREATE TABLE public.products (
    id serial NOT NULL,
    brand_id integer,
    category character varying(50),
    price numeric(15,2),
    name character varying(50)
);

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES public.brands(id);