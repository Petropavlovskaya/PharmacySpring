create database pharmacy_spring;

CREATE TABLE public.login
(
    id         int4    NOT NULL GENERATED ALWAYS AS IDENTITY,
    login      varchar NOT NULL,
    "password" varchar NOT NULL,
    salt       varchar NOT NULL,
    CONSTRAINT login_pk PRIMARY KEY (id),
    CONSTRAINT login_un UNIQUE (login)
);


CREATE TABLE public.user_role
(
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    role_name varchar NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_un UNIQUE (role_name)
);

CREATE TABLE public.account
(
    id         int4    NOT NULL,
    surname    varchar NOT NULL,
    "name"     varchar NOT NULL,
    patronymic varchar NULL,
    phone      varchar NULL,
    status     bool    NULL,
    balance    int4    NULL     DEFAULT 0,
    fk_role    int4    NOT NULL,
    credit     int4    NOT NULL DEFAULT 0,
    CONSTRAINT account_pk PRIMARY KEY (id),
    CONSTRAINT account_fk FOREIGN KEY (id) REFERENCES login (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT account_fk_1 FOREIGN KEY (fk_role) REFERENCES role (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.recipe
(
    recipe_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    medicine varchar NOT NULL,
    doctor_id int4 NULL,
    fk_customer int4 NOT NULL,
    id_medicine_in_order int4 NULL,
    validity date NULL,
    need_extension bool NOT NULL DEFAULT false,
    dosage varchar NULL,
    CONSTRAINT recipe_pk PRIMARY KEY (recipe_id),
    CONSTRAINT recipe_un UNIQUE (medicine, doctor_id, fk_customer, id_medicine_in_order, validity, dosage),
    CONSTRAINT recipe_fk FOREIGN KEY (fk_customer) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.medicine
(
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    "name" varchar NOT NULL,
    indivisible_amount int4 NOT NULL, -- Indivisible amount of medicine for sell
    amount int4 NOT NULL,
    dosage varchar NOT NULL,
    exp_date date NOT NULL, -- Medicine expiration date
    recipe_required bool NOT NULL DEFAULT false,
    price int4 NOT NULL,
    added_by int4 NOT NULL,
    pharm_form varchar NOT NULL,
    CONSTRAINT medicine_pk PRIMARY KEY (id),
    CONSTRAINT medicine_un UNIQUE (name, dosage, exp_date, price)
);

CREATE TABLE public.price_history
(
    id       int4 NOT NULL,
    validity date NOT NULL,
    price  int4 NOT NULL,
    fk_medicine  int4 NOT NULL,
    CONSTRAINT price_history_pk PRIMARY KEY (id),
    CONSTRAINT price_history_fk FOREIGN KEY (id) REFERENCES medicine (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public."order"
(
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    order_price int4 NOT NULL DEFAULT 0,
    order_date timestamp NULL,
    fk_customer int4 NOT NULL,
    CONSTRAINT order_pk PRIMARY KEY (id),
    CONSTRAINT order_fk FOREIGN KEY (fk_customer) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.medicine_in_order
(
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    fk_order int4 NOT NULL,
    fk_medicine int4 NOT NULL,
    quantity int4 NOT NULL,
    CONSTRAINT medicine_in_order_pk PRIMARY KEY (id),
    CONSTRAINT medicine_in_order_fk FOREIGN KEY (fk_order) REFERENCES "order"(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT medicine_in_medicine_fk FOREIGN KEY (fk_medicine) REFERENCES medicine(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.cart
(
    id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
    fk_account int4 NOT NULL,
    fk_medicine int4 NOT NULL,
    quantity int4 NOT NULL,
    CONSTRAINT cart_pk PRIMARY KEY (id),
    CONSTRAINT cart_account_fk FOREIGN KEY (fk_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT cart_medicine_fk FOREIGN KEY (fk_medicine) REFERENCES medicine(id) ON UPDATE CASCADE ON DELETE CASCADE
);