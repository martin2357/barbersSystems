CREATE TABLE IF NOT EXISTS public.t_invoices
(
    id bigserial NOT NULL,
    customer_id bigserial NOT NULL,
    employee_id bigserial NOT NULL,
    price numeric(10, 2) NOT NULL,
    "number" character(9) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id)
        REFERENCES public.t_customers (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (employee_id)
        REFERENCES public.t_employees (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_invoices
    OWNER to postgres;
    
    
    CREATE TABLE IF NOT EXISTS public.t_measure_units
(
    id bigserial NOT NULL,
    code character(5) NOT NULL,
    name character varying(20) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.t_measure_units
    OWNER to postgres;
    
    
    CREATE TABLE IF NOT EXISTS public.t_consumables
(
    id bigserial NOT NULL,
    code character(5) NOT NULL,
    name character varying(20) NOT NULL,
    price numeric(10, 2) NOT NULL,
    mesure_units_id bigserial NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (mesure_units_id)
        REFERENCES public.t_measure_units (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_consumables
    OWNER to postgres;
    
    
    CREATE TABLE IF NOT EXISTS public.t_consumption_invoice
(
    id bigserial NOT NULL,
    quantity numeric(8, 2) NOT NULL,
    total_price numeric(8, 2) NOT NULL,
    unit_price numeric(6, 2) NOT NULL,
    invoice_id bigserial NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id)
        REFERENCES public.t_invoices (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_consumption_invoice
    OWNER to postgres;
    
    
    CREATE TABLE IF NOT EXISTS public.t_actions
(
    id bigserial NOT NULL,
    code character(3) NOT NULL,
    name character varying(20) NOT NULL,
    minute_rate numeric(5, 2) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE public.t_actions
    OWNER to postgres;
    
    
    CREATE TABLE IF NOT EXISTS public.t_invoice_item
(
    id bigserial NOT NULL,
    work_time numeric(4, 2) NOT NULL,
    price_total numeric(6, 2) NOT NULL,
    minute_rate numeric(4, 2) NOT NULL,
    invoice_id bigserial NOT NULL,
    action_id bigserial NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (invoice_id)
        REFERENCES public.t_invoices (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (action_id)
        REFERENCES public.t_actions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_invoice_item
    OWNER to postgres;