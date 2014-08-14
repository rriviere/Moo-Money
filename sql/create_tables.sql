drop table user_roles;
drop table users;
drop table files;

CREATE TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  project VARCHAR(45) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  CONSTRAINT pk_users PRIMARY KEY (username,project));
  
CREATE TABLE user_roles (
  	user_role_id INT(11) NOT NULL AUTO_INCREMENT,
  	username VARCHAR(45) NOT NULL,
  	project VARCHAR(45) NOT NULL,
  	role VARCHAR(45) NOT NULL,
  CONSTRAINT pk_user_roles PRIMARY KEY (user_role_id),
  CONSTRAINT uk_role_username_proj UNIQUE (role,username,project),
  CONSTRAINT fk_user_role_user FOREIGN KEY (username,project) REFERENCES users (username,project));
  
CREATE TABLE file (
  	file_id MEDIUMINT(10) unsigned NOT NULL AUTO_INCREMENT,
  	filename VARCHAR(100) NOT NULL,
  	file_notes VARCHAR(100) DEFAULT NULL,
  	file_size MEDIUMINT(10) DEFAULT NULL,
  	file_type VARCHAR(40) DEFAULT NULL,
  	file longblob DEFAULT NULL,
  CONSTRAINT pk_file PRIMARY KEY (file_id)
);

CREATE TABLE transaction_category (
   tran_category_code VARCHAR(50) NOT NULL,
   tran_category_desc VARCHAR(100) NOT NULL,
   tran_category_seq BIGINT NOT NULL,
   btn_type enum('primary','important','success','default','warning') NOT NULL DEFAULT 'default',
CONSTRAINT pk_transaction_category PRIMARY KEY (tran_category_code)
);

CREATE TABLE transaction_category_keyword (
   transaction_category_keyword_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
   transaction_category_keyword VARCHAR(500) NOT NULL,
   tran_category_code VARCHAR(50) NOT NULL,
CONSTRAINT pk_transaction_category_keyword PRIMARY KEY (transaction_category_keyword_id),
CONSTRAINT fk_tck_tc FOREIGN KEY (tran_category_code) REFERENCES transaction_category(tran_category_code)   
);

CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_date` date NOT NULL,
  `description` varchar(100) NOT NULL,
  `debit` decimal(15,2) DEFAULT NULL,
  `credit` decimal(15,2) DEFAULT NULL,
  `tran_category_code` varchar(50) DEFAULT NULL,
  `receipt` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `fk_ft_tcc` (`tran_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- USER INSERTS
INSERT INTO users(username,password,firstname,lastname,project,enabled) VALUES ('richard','d8d7b0944cf2b88336c9afe487329939','richard','riviere','all',TRUE);
INSERT INTO users(username,password,firstname,lastname,project,enabled) VALUES ('ariane','6ae199a93c381bf6d5de27491139d3f9','ariane','riviere','all',TRUE); 

-- USER ROLE INSERTS
INSERT INTO user_roles (username,project,role) VALUES ('richard','all','ROLE_LEVEL1');
INSERT INTO user_roles (username,project,role) VALUES ('ariane','all','ROLE_LEVEL1');

-- LIST OF VALUES
INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('Electricity','Electricity',1,'default');

INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('CarParking','Car Parking',2,'default');

INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('Petrol','Petrol',3,'default');

INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('Groceries','Groceries',4,'default');

INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('HealthInsurance','Health Insurance',5,'default');

INSERT INTO transaction_category (tran_category_code, tran_category_desc,tran_category_seq,btn_type)
VALUES ('Phone','Phone',6,'default');

INSERT INTO transaction_category (tran_category_code,tran_category_desc,tran_category_seq,btn_type)
VALUES ('Other','Other',7,'default');

INSERT INTO transaction_category_keyword (transaction_category_keyword,tran_category_code)
VALUES ('Telstra','Phone');

INSERT INTO transaction_category_keyword (transaction_category_keyword,tran_category_code)
VALUES ('Park','CarParking');

INSERT INTO transaction_category_keyword (transaction_category_keyword,tran_category_code)
VALUES ('Coles','Groceries');

INSERT INTO transaction_category_keyword (transaction_category_keyword,tran_category_code)
VALUES ('Bp Connect','Petrol');

INSERT INTO transaction_category_keyword (transaction_category_keyword,tran_category_code)
VALUES ('Bupa','HealthInsurance');


COMMIT;
