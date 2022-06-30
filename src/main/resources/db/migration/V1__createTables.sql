drop table if exists public.employee cascade;
drop table if exists public.patient cascade;
drop table if exists public.supply cascade;
drop table if exists public.patient_list_of_supplies cascade;

CREATE TABLE employee (
	employee_id uuid NOT NULL,
	employee_address varchar(255) NULL,
	employee_birthdate timestamp NOT NULL,
	employee_first_name varchar(255) NULL,
	employee_gender varchar(255) NULL,
	employee_last_name varchar(255) NULL,
	employee_phone_number varchar(255) NULL,
	employee_position varchar(255) NULL,
	employee_status bool NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);

CREATE TABLE patient (
	patient_id uuid NOT NULL,
	patient_admission_date timestamp NOT NULL,
	patient_birthdate timestamp NOT NULL,
	patient_first_name varchar(255) NULL,
	patient_last_name varchar(255) NULL,
	patient_symptoms_at_admission varchar(255) NULL,
	doctor_employee_id uuid NULL,
	CONSTRAINT patient_pkey PRIMARY KEY (patient_id)
);

CREATE TABLE supply (
	supply_id uuid NOT NULL,
	supply_amount_on_storage int4 NULL,
	supply_name varchar(255) NULL,
	supply_pretence varchar(255) NULL,
	supply_price_with_coverage int4 NULL,
	supply_price_without_coverage int4 NULL,
	CONSTRAINT supply_pkey PRIMARY KEY (supply_id)
);

CREATE TABLE patient_list_of_supplies (
	patient_patient_id uuid NOT NULL,
	list_of_supplies_supply_id uuid NOT NULL
);

alter table if exists patient_list_of_supplies add constraint UK_sjk8hj1irwiya3c2q3a4vt2wt unique (list_of_supplies_supply_id);
alter table if exists patient add constraint FKf2ho4o4ikuk6gfdptlpvhdajh foreign key (doctor_employee_id) references employee;
alter table if exists patient_list_of_supplies add constraint FKmqg3r8rq5d8t6cu864h0rvcwh foreign key (list_of_supplies_supply_id) references supply;
alter table if exists patient_list_of_supplies add constraint FK9qbmeb3qfkq0bciqaqs01equl foreign key (patient_patient_id) references patient;