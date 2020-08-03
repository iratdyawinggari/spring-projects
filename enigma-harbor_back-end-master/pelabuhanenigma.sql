-- DROP SCHEMA pelabuhanenigma;
CREATE SCHEMA pelabuhanenigma;
USE  pelabuhanenigma;

CREATE TABLE ms_user(
	user_id INT auto_increment,
	user_name varchar(100),
    user_password varchar(10),
    fullname VARCHAR(100),
    primary key(user_id)
);

INSERT INTO ms_user(user_name,user_password,fullname)
values('rey@gmail.com','123','reynaldi'),
('pascal@gmail.com','456','pascal enigma')
;

CREATE TABLE menu_header(
	header VARCHAR(50),
    primary key(header)    
);

insert into menu_header(header)
values
('Master'),('Transaction')
;

select * from menu_header;

CREATE TABLE menu_children(
	name VARCHAR(20),
    path VARCHAR(100),
    icon VARCHAR(20),
    header VARCHAR(50),
    primary key(name),
    foreign key(header) references menu_header(header)
); 

insert into menu_children(name,path,icon,header)
values
('Ship','/protected/main/masterShip','fa fa-ship','Master'),
('Harbor','/protected/main/masterHarbor','fas fa-anchor','Master'),
('Dock','/protected/main/masterDocks','fas fa-monument','Master'),
('DockStatus','/protected/main/masterDockStatus','fas fa-monument','Master'),
('HarborStatus','/protected/main/masterHarborStatus','fas fa-anchor','Master'),
('ShipStatus','/protected/main/masterShipStatus','fa fa-ship','Master'),
('Transaction','/protected/main/transaction','fas fa-shopping-cart','Transaction')
;

CREATE TABLE ms_ship_status(
	ship_status_id INT auto_increment,
    ship_status_name VARCHAR(50),
    primary key(ship_status_id)
);

insert into ms_ship_status(ship_status_name)
values
('Sailing'),('Berthing'),('Loading'),('Unloading'),('Suspend');

select * from ms_ship_status;

CREATE TABLE ms_ship(
	ship_code VARCHAR(10),
    ship_name VARCHAR(100),
    captain_name VARCHAR(100),
    ship_status_id INT,
    primary key(ship_code),
    foreign key(ship_status_id) references ms_ship_status(ship_status_id)
);

INSERT INTO ms_ship(ship_code,ship_name,captain_name,ship_status_id)
values
('S-0001','Ship1','Captain1',1),
('S-0002','Ship2','Captain2',1),
('S-0003','Ship3','Captain3',1),
('S-0004','Ship4','Captain4',1),
('S-0005','Ship5','Captain5',1),
('S-0006','Ship6','Captain6',1),
('S-0007','Ship7','Captain7',1),
('S-0008','Ship8','Captain8',1),
('S-0009','Ship9','Captain9',1),
('S-0010','Ship10','Captain10',1)
;

CREATE TABLE ms_harbor_status(
	harbor_status_id INT auto_increment,
    harbor_status_name VARCHAR(50),
    harbor_capacity INT,
    primary key(harbor_status_id)
);

INSERT INTO ms_harbor_status(harbor_status_name)
VALUES
('Available'),('Unavailable'),('Suspend')
;

CREATE TABLE ms_harbor(
	harbor_code VARCHAR(10),
    harbor_name VARCHAR(100),
    harbor_capacity INT,
    harbor_status_id INT,
    primary key(harbor_code),
    foreign key(harbor_status_id) references ms_harbor_status(harbor_status_id)
);

INSERT INTO ms_harbor(harbor_code,harbor_name,harbor_capacity,harbor_status_id)
values
('POK','Tanjung Priok',5,1),
('PRK','Tanjung Perak',5,1);

CREATE TABLE ms_dock_status(
	dock_status_id INT auto_increment,
    dock_status_name VARCHAR(100),
    primary key(dock_status_id)
);

INSERT INTO ms_dock_status(dock_status_name)
VALUES 
('Available'),('Unavailable'),('Suspend');

CREATE TABLE ms_dock(
	dock_code VARCHAR(10),
    dock_name VARCHAR(100),
    harbor_code VARCHAR(10),
    dock_status_id INT,
    primary key(dock_code),
    foreign key(dock_status_id) references ms_dock_status(dock_status_id),
    foreign key(harbor_code) references ms_harbor(harbor_code)
);

INSERT INTO ms_dock(dock_code,dock_name,harbor_code,dock_status_id)
VALUES
('POK1','Priok1','POK',1),
('POK2','Priok2','POK',1),
('POK3','Priok3','POK',1),
('POK4','Priok4','POK',1),
('POK5','Priok5','POK',1),
('PRK1','Perak1','PRK',1),
('PRK2','Perak2','PRK',1),
('PRK3','Perak3','PRK',1),
('PRK4','Perak4','PRK',1),
('PRK5','Perak5','PRK',1);

CREATE TABLE ms_transaction_status(
	transaction_status_id INT auto_increment,
    transaction_status_name VARCHAR(20),
    primary key(transaction_status_id)
);

INSERT INTO ms_transaction_status(transaction_status_name)
values('On Process'),('Finish');

CREATE TABLE trx_header(
	trx_hr_id VARCHAR(10),
    ship_code  VARCHAR(10),
    primary key(trx_hr_id),
    foreign key(ship_code) references ms_ship(ship_code)
);
/*
INSERT INTO trx_header
VALUES
('T-0001','S-0007');
*/
CREATE TABLE trx_detail(
	id INT auto_increment,
	trx_hr_id VARCHAR(15),
	entry_date DATE,
    exit_date DATE,
    dock_code VARCHAR(10),
    captain_name VARCHAR(100),
    ship_status_id INT,
    loading_unloading_weight INT,
    transaction_status_id INT,
    primary key(id),
    foreign key(trx_hr_id) references trx_header(trx_hr_id),
    foreign key(transaction_status_id) references ms_transaction_status(transaction_status_id),
    foreign key(dock_code) references ms_dock(dock_code),
    foreign key(ship_status_id) references ms_ship_status(ship_status_id)
);

/*
INSERT INTO trx_detail(trx_hr_id, entry_date, exit_date, dock_code,captain_name, ship_status_id, loading_unloading_weight, transaction_status_id)
values
('T-0001','2019-11-13', '2019-11-24','PRK1','New Captain 1',2,3000,1),
('T-0001','2019-11-24', '2019-11-30','PRK2','New Captain 2',2,3000,1),
('T-0001','2019-11-17', '2019-11-15','PRK2','New Captain 3',3,3000,2),
('T-0001','2019-11-23', '2019-11-20','PRK2','New Captain 4',4,3000,2)
;
*/
SELECT * from trx_detail;

SELECT td.id, th.trx_hr_id,s.ship_name,td.entry_date,td.exit_date,s.captain_name,mss.ship_status_name,td.loading_unloading_weight, h.harbor_name, d.dock_name, mts.transaction_status_name  from trx_detail td
INNER JOIN ms_dock d ON td.dock_code=d.dock_code
INNER JOIN ms_harbor h ON d.harbor_code = h.harbor_code
INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id 
INNER JOIN ms_ship s ON th.ship_code = s.ship_code
INNER JOIN ms_ship_status mss ON td.ship_status_id = mss.ship_status_id
inner join ms_transaction_status mts ON td.transaction_status_id = mts.transaction_status_id
order by(td.id) ;
;

SELECT COUNT(*) from trx_detail td 
INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id 
INNER JOIN ms_ship s ON th.ship_code = s.ship_code
WHERE s.ship_code = 'S-0001'
order by(td.id) LIMIT 1;

SELECT s.ship_name,s.captain_name, mss.ship_status_name FROM  ms_ship s 
INNER JOIN ms_ship_status mss ON mss.ship_status_id = s.ship_status_id
order by s.ship_status_id;

SELECT s.ship_code,td.captain_name,td.ship_status_id from trx_detail td
INNER JOIN trx_header th ON td.trx_hr_id = th.trx_hr_id 
INNER JOIN ms_ship s ON th.ship_code = s.ship_code
INNER JOIN ms_ship_status mss ON s.ship_status_id = mss.ship_status_id
WHERE td.transaction_status_id = 1 and s.ship_code ='S-0003' ;

select * from trx_header;
SELECT * from ms_dock;
select * from trx_detail;

SELECT COUNT(*) from ms_harbor h 
INNER JOIN ms_dock d ON d.harbor_code = h.harbor_code 
INNER JOIN ms_dock_status ds ON d.dock_status_id = ds.dock_status_id
WHERE ds.dock_status_id=1 and h.harbor_code = 'PRK'
;

SELECT COUNT(*) from trx_detail td   
inner join ms_dock d ON td.dock_code = d.dock_code
WHERE td.transaction_status_id = 1 and td.dock_code='POK1'