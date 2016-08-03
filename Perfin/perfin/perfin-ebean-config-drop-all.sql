alter table t_perfin_constants drop constraint if exists fk_t_perfin_constants_caseid;
drop index if exists ix_t_perfin_constants_caseid;

alter table t_perfin_record drop constraint if exists fk_t_perfin_record_mainuserid;
drop index if exists ix_t_perfin_record_mainuserid;

alter table t_perfin_record drop constraint if exists fk_t_prfn_rcrd_ssgnsrd;
drop index if exists ix_t_prfn_rcrd_ssgnsrd;

alter table t_perfin_record drop constraint if exists fk_t_prfn_rcrd_ctgrytyp;
drop index if exists ix_t_prfn_rcrd_ctgrytyp;

alter table t_perfin_record drop constraint if exists fk_t_perfin_record_banks;
drop index if exists ix_t_perfin_record_banks;

alter table t_perfin_user drop constraint if exists fk_t_perfin_user_account;
drop index if exists ix_t_perfin_user_account;

drop table t_perfin_account cascade constraints purge;
drop sequence S_PERFIN_ACCOUNT;

drop table t_perfin_bankcard cascade constraints purge;
drop sequence S_PERFIN_BANK;

drop table t_perfin_constants cascade constraints purge;
drop sequence S_PERFIN_CONSTANTS;

drop table t_perfin_record cascade constraints purge;
drop sequence S_PERFIN_RECORD;

drop table t_perfin_user cascade constraints purge;
drop sequence S_PERFIN_USER;

