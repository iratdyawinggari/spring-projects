drop schema fuzzy_sukolilo;
create schema fuzzy_sukolilo;
use fuzzy_sukolilo;
create table master_gender(
	id int auto_increment,
    gender_code char,
    gender_name varchar(20),
    primary key(id)
);

create table master_role(
	id int auto_increment,
    role_name varchar(20),
    primary key(id)
);

insert into master_role(role_name)
values
('super admin'),('admin'),('user');

INSERT INTO master_gender(gender_code, gender_name)
VALUES
	('F', 'Female'),
    ('M', 'Male')
;

create table master_user_info(
	id int auto_increment,
    email varchar(100),
    address varchar(200),
    birth_date date,
    fullname varchar(100),
    no_telp varchar(13),
    primary key(id)
    );

insert into  master_user_info(email,  address,  birth_date, fullname, no_telp )
values 
('iratdyawinggari@gmail.com', 'Jl Sumengko,Gresik, Jawa Timur', '1995-03-12', 'Iratdya Winggar', '089766543123' ),
('helen@gmail.com', 'Jl bekasi, Jawa barat', '1985-03-12', 'ryehel7', '0836565663434' )
;

create table master_user(
	id int auto_increment,
    username varchar(100),
    password varchar(20),
    gender_id INT,
    user_info_id INT,
    role_id INT,
    primary key(id),
    foreign key(role_id) references master_role(id),
    foreign key(user_info_id) references master_user_info(id),
    foreign key(gender_id) references master_gender(id)
    
);

insert into master_user (username,password,user_info_id,gender_id,role_id)
values 
('winggar','123',1,2,1),
('helen','456',2,1,2)
;

CREATE TABLE menu_header(
	id int auto_increment,
	header_name VARCHAR(50),
    primary key(id)
);

insert into menu_header(header_name)
values
('Master'),('User'),('Information'),('Transaction'),('Master'),('Information'),('Transaction');

select * from menu_header;

CREATE TABLE menu_children(
	id int auto_increment,
	name VARCHAR(50),
    path VARCHAR(100),
    icon VARCHAR(50),
    header_id INT,
    role_id INT,
    primary key(id),
    foreign key(header_id) references menu_header(id),
    foreign key(role_id) references master_role(id)
); 

insert into menu_children(name,path,icon,header_id,role_id)
values
('Blog/Content','/protected/main/masterBlog','fa fa-blog',1,1),
('Patients','/protected/main/masterPatients','fas fa-wheelchair',1,1),
('Bank Data','/protected/main/masterBankData','fas fa-medkit',1,1),
('Gender','/protected/main/masterGender','fas fa-anchor',1,1),
('Hospital','/protected/main/masterHospital','fas fa-hospital',1,1),
('User','/protected/main/masterUser','fa fa-user-tie',2,1),
('User Info','/protected/main/masterUserInfo','fas fa-user-tie',2,1),
('Status','/protected/main/status','fad fa-clipboard-check',3,1),
('Criteria','/protected/main/criteria','fad fa-clipboard-check',3,1),
('Maps','/protected/main/maps','fad fa-clipboard-check',3,1),
('Transaction','/protected/main/transaction','fad fa-stethoscope',4,1),
('Reservation','/protected/main/reservation','fal fa-money-bill-alt',4,1),

('Blog/Content','/protected/main/masterBlog','fa fa-blog',5,2),
('Patients','/protected/main/masterPatients','fas fa-wheelchair',5,2),
('Gender','/protected/main/masterGender','fas fa-anchor',5,2),
('Hospital','/protected/main/masterHospital','fas fa-hospital',5,2),
('Status','/protected/main/status','fad fa-clipboard-check',6,2),
('Criteria','/protected/main/criteria','fad fa-clipboard-check',6,2),
('Maps','/protected/main/maps','fad fa-clipboard-check',6,2),
('Transaction','/protected/main/transaction','fad fa-stethoscope',7,2),
('Reservation','/protected/main/reservation','fal fa-money-bill-alt',7,2)
;

create table master_disease(
	id int auto_increment,
    disease_name varchar(100),
    primary key(id)
);

insert into master_disease(disease_name)
values('Penyiraman Springkle'),
('Produksi Barang');

create table master_output(
	id int auto_increment,
    output_name varchar(200),
    disease_id INT,
	primary key(id),
    foreign key(disease_id) references master_disease(id)
);

insert into master_output(output_name,disease_id)
values('output springkle',1)
,('output produk',2)
;


create table master_output_status(
	id int auto_increment,
    output_status_name varchar(150),
    min_point INT,
    max_point INT,
    output_id INT,
    primary key(id),
    foreign key(output_id) references master_output(id)
);

insert into master_output_status(output_status_name, min_point, max_point, output_id)
values
('Singkat',0,28,1),
('Sedang',20,48,1),
('Lama',40,90,1),
('Berkurang',0,6000,2),
('Bertambah',3000,9000,2)
;

create table master_indicator(
	id int auto_increment,
    indicator_name varchar(200),
    indicator_description text,
    disease_id INT,
	primary key(id),
    foreign key(disease_id) references master_disease(id)
);

insert into master_indicator(indicator_name,indicator_description,disease_id)
values
('Suhu',1),('Kelembaban','nanananananananananananan',1),
('Permintaan',2),('Persediaan','dkfhdkrfhdgihtrjlgtijrglotrjgltoghrk.rl',2)

;


create table master_indicator_status(
	id int auto_increment,
    indicator_status_name varchar(150),
    min_point INT,
    max_point INT,
    indicator_id INT,
    primary key(id),
    foreign key(indicator_id) references master_indicator(id)
);

insert into master_indicator_status(indicator_status_name, min_point, max_point, indicator_id)
values
('Dingin',-10,3,1),
('Sejuk',0,15,1),
('Normal',12,27,1),
('Hangat',24,39,1),
('Panas',36,50,1),
('Kering',0,20,2),
('Lembab',10,50,2),
('Basah',40,70,2),
('Turun',0,6000,3),
('Naik',500,9000,3),
('Sedikit',0,600,4),
('Banyak',100,1000,4)
;

create table master_rule(
	id int auto_increment,
    rule_combination varchar(300),
    output_status_id INT,
    primary key(id),
    foreign key(output_status_id) references master_output_status(id) 
);

insert into master_rule(rule_combination ,output_status_id)
values
('Suhu Is Dingin,Kelembaban Is Kering',3),
('Suhu Is Dingin,Kelembaban Is Lembab',1),
('Suhu Is Dingin,Kelembaban Is Basah',1),
('Suhu Is Sejuk,Kelembaban Is Kering',3),
('Suhu Is Sejuk,Kelembaban Is Lembab',1),
('Suhu Is Sejuk,Kelembaban Is Basah',1),
('Suhu Is Normal,Kelembaban Is Kering',3),
('Suhu Is Normal,Kelembaban Is Lembab',2),
('Suhu Is Normal,Kelembaban Is Basah',1),
('Suhu Is Hangat,Kelembaban Is Kering',3),
('Suhu Is Hangat,Kelembaban Is Lembab',2),
('Suhu Is Hangat,Kelembaban Is Basah',1),
('Suhu Is Panas,Kelembaban Is Kering',3),
('Suhu Is Panas,Kelembaban Is Lembab',2),
('Suhu Is Panas,Kelembaban Is Basah',1),
('Permintaan Is Turun,Persediaan Is Banyak',4),
('Permintaan Is Turun,Persediaan Is Sedikit',4),
('Permintaan Is Naik,Persediaan Is Banyak',5),
('Permintaan Is Naik,Persediaan Is Sedikit',5)
;

create table master_patient(
    id int auto_increment,
    name varchar(100),
    user_id INT,
    age int,
    gender_id INT,
    primary key(id),
    foreign key(gender_id) references master_gender(id),
    foreign key(user_id) references master_user(id)
);

INSERT INTO master_patient(name, age, gender_id,user_id)
VALUES
	('Munthe', 22, 1, 1)
--     ('Hans', 19, '2000-10-12', 2, 2, 1),
--     ('Wisnu', 35, '1984-03-05', 2, 2, 2),
--     ('Nana', 20, '1999-12-22', 1, 1, 1)
;


create table master_transaction(
	id int auto_increment,
    patient_id int,
    disease_id INT,
    input_point_combination varchar(300),
    output_status_id INT,
    primary key(id),
	foreign key(disease_id) references master_disease(id),
    foreign key(output_status_id) references master_output_status(id)
);

insert into master_transaction(patient_id,disease_id, input_point_combination, output_status_id)
values 
(1,1,'Suhu 37,Kelembaban 12',3)
;

create table master_hospital(
	id INT auto_increment,
    hospital_name varchar(50),
    no_telp varchar(13),
    district varchar(100),
    city varchar(100),
    province varchar(100),
    address varchar(200),
    zip_code varchar(10),
    latitude decimal(12,8),
    longitude decimal(12,8),
	primary key(id)
);

insert into master_hospital(hospital_name, no_telp, district,city, province, address,zip_code,latitude,longitude)
values
	('Rumah Sakit Cipto Mangunkusumo','(021) 1500135', 'Senen', 'Jakarta Pusat', 'Jakarta', 'Jl. Salemba Raya Jl. Pangeran Diponegoro No.71, RW.5, Kenari', '10430',-6.1971803,106.8446813),
	('Rumah Sakit Umum Pusat Fatmawati', '(021) 7501524', 'Cilandak', 'Jakarta Selatan', 'Jakarta', 'Jl. TB Simatupang c No.18, RT.4/RW.9', '12430',-6.2951723,106.7939325),
	('Rumah Sakit Husada', '(021) 6260108','Sawah Besar',  'Jakarta Pusat', 'Jakarta', 'Jl. Raya Mangga Besar No.137-139, RW.1, Mangga Dua Sel', '10730',-6.1477713,106.8271456),
    ('Cilandak Marine Hospital', '(021) 7805296',' Ps. Minggu',  'Jakarta Selatan', 'Jakarta', 'Jl. Raya Cilandak Kko, RT.3/RW.5, Cilandak Timur', '12560',-6.3048025,106.808221);


create table master_doctor(
	id int auto_increment,
	name_doctor varchar(100),
	hours_practice time,
	hours_end time,
    hospital_id int,
    primary key(id),
    foreign key(hospital_id) references master_hospital
);    

create table master_reservation(
	id Int auto_increment primary key,
    name varchar(100),
    hospital_id int,
    reservation_date date,
    bpjs_number varchar(13),
    identity_card_number varchar(16),
    arrival_status INT,
    foreign key (hospital_id) references master_hospital(id)
);

insert into master_reservation(name, hospital_id, reservation_date, bpjs_number, identity_card_number)
values('Iratdya Winggari',1,'2019-12-05', '4574857485745','2344234344345454',0);

SELECT * from master_hospital ;

-- select * from master_transaction;

select * from master_patient;
select * from master_user;
