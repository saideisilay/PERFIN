alter table t_perfin_constants drop constraint if exists fk_t_prfn_cnstnts_rfrnccd;
drop index if exists ix_t_prfn_cnstnts_rfrnccd;

alter table t_perfin_user drop constraint if exists fk_t_perfin_user_account;
drop index if exists ix_t_perfin_user_account;

drop table t_perfin_account cascade constraints purge;
drop sequence S_PERFIN_ACCOUNT;

drop table t_perfin_bankcard cascade constraints purge;
drop sequence S_PERFIN_BANK;

drop table t_perfin_constincomeexpense cascade constraints purge;
drop sequence S_PERFIN_CONSTINCOMEEXPENSE;

drop table t_perfin_constants cascade constraints purge;
drop sequence S_PERFIN_CONSTANTS;

drop table t_perfin_record cascade constraints purge;
drop sequence T_PERFIN_RECORD;

drop table t_perfin_user cascade constraints purge;
drop sequence S_PERFIN_USER;

