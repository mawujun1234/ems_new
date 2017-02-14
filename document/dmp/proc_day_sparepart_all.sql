--调用这个存储过程，将会计算所有的数据库的今日结算数据
create or replace procedure proc_day_sparepart_all(in_todaykey in varchar2)
as
begin
  for store in (
    select * from t_org  where orgtype in ('store_build','store_prepare')
  ) loop
    proc_day_sparepart(store.id,in_todaykey);
  END LOOP;
end;



