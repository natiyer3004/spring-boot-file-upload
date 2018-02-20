create sequence group_seq;
create table document
(
   id bigint default group_seq.nextval primary key,
   documentPath varchar(255) not null,
   fileName varchar(255) not null,
   fileType varchar(30),
   keyWords varchar(255),
   createdBy varchar(50),
   createdDate timestamp
);