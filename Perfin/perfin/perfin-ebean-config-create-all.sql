create table t_perfin_account (
  accountid                     number(19) not null,
  accountname                   varchar2(255),
  accountdesc                   varchar2(255),
  address                       varchar2(255),
  referencecode                 varchar2(255),
  constraint pk_t_perfin_account primary key (accountid)
);
create sequence S_PERFIN_ACCOUNT increment by 1;

create table t_perfin_bankcard (
  bankid                        number(19) not null,
  cardname                      varchar2(255),
  cardtype                      varchar2(10),
  description                   varchar2(255),
  limit                         number(19,4),
  dept                          number(19,4),
  constraint ck_t_perfin_bankcard_cardtype check (CARDTYPE in ('DebitCard','CreditCard')),
  constraint pk_t_perfin_bankcard primary key (bankid)
);
create sequence S_PERFIN_BANK increment by 1;

create table t_perfin_constincomeexpense (
  constincexid                  number(19) not null,
  amount                        number(19,4),
  date                          varchar2(255),
  periodconstant                varchar2(11),
  type                          varchar2(12),
  incomeexpense                 varchar2(8),
  user_id                       number(19),
  constraint ck_t_prfn_cnstncmxpns_prdcn_1 check (PERIODCONSTANT in ('everyDay','everyWeek','everyMounth')),
  constraint ck_t_prfn_cnstncmxpns_typ check (TYPE in ('Cash','CreditCard','DebitCard','OverdraftAcc')),
  constraint ck_t_prfn_cnstncmxpns_ncmxpns check (INCOMEEXPENSE in ('INCOME','EXPENSE','ASSIGN','OUTSTAND')),
  constraint pk_t_prfn_cnstncmxpns primary key (constincexid)
);
create sequence S_PERFIN_CONSTINCOMEEXPENSE increment by 1;

create table t_perfin_constants (
  constid                       number(19) not null,
  categories                    varchar2(8),
  classification                varchar2(255),
  caseid                        number(19),
  constraint ck_t_prfn_cnstnts_ctgrs check (CATEGORIES in ('INCOME','EXPENSE','ASSIGN','OUTSTAND')),
  constraint pk_t_perfin_constants primary key (constid)
);
create sequence S_PERFIN_CONSTANTS increment by 1;

create table t_perfin_record (
  recordid                      number(19) not null,
  date                          varchar2(255),
  descript                      varchar2(255),
  recordamount                  number(19,4),
  incomeexpense                 varchar2(8),
  periodconst                   varchar2(11),
  constraint ck_t_prfn_rcrd_ncmxpns check (INCOMEEXPENSE in ('INCOME','EXPENSE','ASSIGN','OUTSTAND')),
  constraint ck_t_prfn_rcrd_prdcnst check (PERIODCONST in ('everyDay','everyWeek','everyMounth')),
  constraint pk_t_perfin_record primary key (recordid)
);
create sequence T_PERFIN_RECORD increment by 1;

create table t_perfin_user (
  userid                        number(19) not null,
  name                          varchar2(255),
  username                      varchar2(255),
  surname                       varchar2(255),
  email                         varchar2(255),
  password                      varchar2(255),
  account                       number(19),
  constraint pk_t_perfin_user primary key (userid)
);
create sequence S_PERFIN_USER increment by 1;

alter table t_perfin_constants add constraint fk_t_perfin_constants_caseid foreign key (caseid) references t_perfin_account (accountid);
create index ix_t_perfin_constants_caseid on t_perfin_constants (caseid);

alter table t_perfin_user add constraint fk_t_perfin_user_account foreign key (account) references t_perfin_account (accountid);
create index ix_t_perfin_user_account on t_perfin_user (account);

