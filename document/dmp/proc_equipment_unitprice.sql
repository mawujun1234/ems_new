create or replace procedure proc_equipment_unitprice()
as
begin
  /**更新所有现存的设备的初次安装时间，只需要执行一次就行了
  update ems_equipment a set first_install_date=(select operatedate from (
  select ecode,min(operatedate) operatedate from ems_equipmentcycle b
  where b.operatetype='task_install'
  group by ecode
  ) b
  where a.ecode=b.ecode)
  **/

  --更新每个设备的原值，因为原值会发生变化，所以每次更新的时候更新一次
  update ems_equipment a set unitprice=(
       select unitprice from ems_orderlist b where a.orderlist_id=b.id
  )
  commit;
  --更新净值
  update ems_equipment a 
  set unitprice_nav=unitprice-(unitprice*0.95/1825)*(ceil(TO_NUMBER(sysdate-trunc(first_install_date))))
 commit;
  

  
end;
