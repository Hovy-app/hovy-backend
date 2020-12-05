CREATE TABLE IF NOT EXISTS feedbacks (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    shop_id bigint not null,
    rate smallint,
    comment varchar(512)
);
