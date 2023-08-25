CREATE TABLE IF NOT EXISTS public.t_orders
(
    id bigserial NOT NULL,
    customer_id bigserial NOT NULL,
    employee_id bigserial NOT NULL,
    ordering_time timestamp with time zone NOT NULL,
    "date_expousre:" date NOT NULL,
    status "char" NOT NULL,
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

ALTER TABLE public.t_orders
    OWNER to postgres;
    
    CREATE TABLE IF NOT EXISTS public.t_orders_actions
(
    action_id bigserial NOT NULL,
    order_id bigserial NOT NULL,
    FOREIGN KEY (action_id)
        REFERENCES public.t_actions (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    FOREIGN KEY (order_id)
        REFERENCES public.t_orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

ALTER TABLE public.t_orders_actions
    OWNER to postgres;