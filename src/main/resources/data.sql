ALTER TABLE image
ADD CONSTRAINT unique_filename
UNIQUE (filename);

insert into role(name) Select 'ROLE_USER' Where not exists(select * from role where name='ROLE_USER');
