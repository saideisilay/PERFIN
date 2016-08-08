create table t_perfin_account (
  accountid                     number(19) not null,
  accountname                   varchar2(255),
  accountdesc                   varchar2(255),
  address                       varchar2(255),
  refcode                       varchar2(255),
  constraint pk_t_perfin_account primary key (accountid)
);
create sequence S_PERFIN_ACCOUNT increment by 1;

create table t_perfin_bankcard (
  bankid                        number(19) not null,
  cardnumber                    varchar2(255),
  cardname                      varchar2(255),
  cardtype                      varchar2(10),
  description                   varchar2(255),
  limit                         number(38),
  dept                          number(38),
  constraint ck_t_perfin_bankcard_cardtype check (CARDTYPE in ('DebitCard','CreditCard')),
  constraint pk_t_perfin_bankcard primary key (bankid)
);
create sequence S_PERFIN_BANK increment by 1;

create table t_perfin_constants (
  constid                       number(19) not null,
  categories                    varchar2(7),
  classification                varchar2(255),
  caseid                        number(19),
  constraint ck_t_prfn_cnstnts_ctgrs check (CATEGORIES in ('INCOME','EXPENSE')),
  constraint pk_t_perfin_constants primary key (constid)
);
create sequence S_PERFIN_CONSTANTS increment by 1;

create table t_perfin_record (
  recordid                      number(19) not null,
  recdate                       varchar2(255),
  descript                      varchar2(255),
  amount                        number(38),
  incomeexpense                 varchar2(7),
  mainuserid                    number(19),
  paytype                       varchar2(10),
  categorytype                  number(19),
  banks                         number(19),
  constraint ck_t_prfn_rcrd_ncmxpns check (INCOMEEXPENSE in ('INCOME','EXPENSE')),
  constraint ck_t_perfin_record_paytype check (PAYTYPE in ('Cash','CreditCard','DebitCard')),
  constraint pk_t_perfin_record primary key (recordid)
);
create sequence S_PERFIN_RECORD increment by 1;

create table t_perfin_user (
  userid                        number(19) not null,
  name                          varchar2(255),
  surname                       varchar2(255),
  username                      varchar2(255),
  email                         varchar2(255),
  password                      varchar2(255),
  account                       number(19),
  constraint pk_t_perfin_user primary key (userid)
);
create sequence S_PERFIN_USER increment by 1;

alter table t_perfin_constants add constraint fk_t_perfin_constants_caseid foreign key (caseid) references t_perfin_account (accountid);
create index ix_t_perfin_constants_caseid on t_perfin_constants (caseid);

alter table t_perfin_record add constraint fk_t_perfin_record_mainuserid foreign key (mainuserid) references t_perfin_user (userid);
create index ix_t_perfin_record_mainuserid on t_perfin_record (mainuserid);

alter table t_perfin_record add constraint fk_t_prfn_rcrd_ctgrytyp foreign key (categorytype) references t_perfin_constants (constid);
create index ix_t_prfn_rcrd_ctgrytyp on t_perfin_record (categorytype);

alter table t_perfin_record add constraint fk_t_perfin_record_banks foreign key (banks) references t_perfin_bankcard (bankid);
create index ix_t_perfin_record_banks on t_perfin_record (banks);

alter table t_perfin_user add constraint fk_t_perfin_user_account foreign key (account) references t_perfin_account (accountid);
create index ix_t_perfin_user_account on t_perfin_user (account);

