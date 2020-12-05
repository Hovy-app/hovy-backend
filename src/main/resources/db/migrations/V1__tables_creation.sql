CREATE TABLE IF NOT EXISTS shops (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(50) not null,
    template varchar(512) not null,
    publicKey varchar(512) not null
);
