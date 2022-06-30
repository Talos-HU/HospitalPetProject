CREATE TABLE public.employee (
	employee_id uuid NOT NULL,
	employee_address varchar(255) NULL,
	employee_age int4 NULL,
	employee_birthdate timestamp NOT NULL,
	employee_first_name varchar(255) NULL,
	employee_gender varchar(255) NULL,
	employee_last_name varchar(255) NULL,
	employee_phone_number varchar(255) NULL,
	employee_position varchar(255) NULL,
	employee_status bool NULL,
	CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
);

CREATE TABLE public.patient (
	patient_id uuid NOT NULL,
	patient_admission_date timestamp NOT NULL,
	patient_birthdate timestamp NOT NULL,
	patient_first_name varchar(255) NULL,
	patient_last_name varchar(255) NULL,
	patient_symptoms_at_admission varchar(255) NULL,
	doctor_employee_id uuid NULL,
	CONSTRAINT patient_pkey PRIMARY KEY (patient_id)
);

CREATE TABLE public.patient_list_of_supplies (
	patient_patient_id uuid NOT NULL,
	list_of_supplies_supply_id uuid NOT NULL
);

CREATE TABLE public.supply (
	supply_id uuid NOT NULL,
	supply_amount_on_storage int4 NULL,
	supply_name varchar(255) NULL,
	supply_pretence varchar(255) NULL,
	supply_price_with_coverage int4 NULL,
	supply_price_without_coverage int4 NULL
);