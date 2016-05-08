CREATE OR REPLACE PROCEDURE initDatabase AS
  /**变量声明**/
  each_num int; --循环次数
  each_time date; --循环年份
  startTime varchar2(20);
  endTime varchar2(20);
  i int:= 0; --循环变量
BEGIN
    
   startTime := '2010';
   endTime := '2015';
   
   --清除历史数据
   delete from t_rk_analyse_1;
   delete from t_rk_analyse_2;
   delete from t_rk_analyse_3;
   delete from t_rk_analyse_4;
   delete from t_rk_analyse_5; 
   delete from t_rk_analyse_6; 
   delete from t_rk_analyse_7;
   delete from t_rk_analyse_8;
   delete from t_rk_analyse_9;
   
   delete from t_rk_analyse_12;
   delete from t_rk_analyse_13;
   delete from t_rk_analyse_14;
   delete from t_rk_analyse_15;
   
   --计算开始结束时间相差年份
   each_num := round(months_between(to_date(endTime,'YYYY'), TO_DATE(startTime, 'YYYY')) / 12);
   
   while i<= each_num loop
      IF i = 0 THEN
        each_time := to_date(startTime,'YYYY');
      ELSE
        each_time := add_months(each_time,12);
      END IF;
      
      --图形1
      insert into t_rk_analyse_1 (number_,time_,c_rklb_)
      select 
        (select count(*) from t_rk_gaj_ldrkb temp1 where to_char(to_date(temp1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'),'yyyy') = to_char(each_time, 'yyyy'))+
        (select case when number_  is null then 0 else to_number(number_) end from t_rk_analyse_1 temp1 where time_ = to_char(add_months(each_time,-12), 'yyyy')),
        to_char(each_time, 'yyyy'),'流动人口' from dual;
      commit;
      
      --图形2
      insert into t_rk_analyse_2 (time_,c_nl_,c_xb_,number_,sort_) 
      select * from (
      select to_char(each_time, 'yyyy'),'0-4岁','男',count(*),'1' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 0 and 4
                   and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'0-4岁','女',count(*),'1' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 0 and 4
                   and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'10-14岁','男',count(*),'2' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 10 and 14
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'10-14岁','女',count(*),'2' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 10 and 14
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'20-24岁','男',count(*),'3' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 20 and 24
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'20-24岁','女',count(*),'3' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 20 and 24
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'30-34岁','男',count(*),'4' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 30 and 34
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'30-34岁','女',count(*),'4' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 30 and 34
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'40-44岁','男',count(*),'5' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 40 and 44
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'40-44岁','女',count(*),'5' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 40 and 44
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'50-54岁','男',count(*),'6' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 50 and 54
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'50-54岁','女',count(*),'6' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 50 and 54
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'60-64岁','男',count(*),'7' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 60 and 64
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'60-64岁','女',count(*),'7' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 between 60 and 64
             and xbmc_='女'
      union all
      select to_char(each_time, 'yyyy'),'70岁以上','男',count(*),'8' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 >=70
             and xbmc_='男'
      union all
      select to_char(each_time, 'yyyy'),'70岁以上','女',count(*),'8' as xb from t_rk_gaj_ldrkb 
             where to_date(LJRQ_LAI_,'yymmdd')<each_time
                   and floor(each_time - to_date(csrq_,'yyyymmdd'))/365 >=70
             and xbmc_='女'
      );
      
      --图形3
      insert into t_rk_analyse_3 (time_,c_whcd_,c_rklb_,number_,sort_)
      select * from （
      select to_char(each_time, 'yyyy'),'文肓或半文盲','流动人口',count(*),'1' from t_rk_gaj_ldrkb t1 
             left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
             where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
             and t1.whcd_*1 >= 90
      union all
      select to_char(each_time, 'yyyy'),'小学','流动人口',count(*),'2' from t_rk_gaj_ldrkb t1 
             left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
             where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
             and t1.whcd_*1 >= 80 
             and t1.whcd_*1 < 90 
      union all
      select to_char(each_time, 'yyyy'),'初中','流动人口',count(*),'3' from t_rk_gaj_ldrkb t1 
             left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
             where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
             and t1.whcd_*1 >= 70 
             and t1.whcd_*1 < 80 
      union all
      select to_char(each_time, 'yyyy'),'高中','流动人口',count(*),'4' from t_rk_gaj_ldrkb t1 
             left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
             where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
             and t1.whcd_*1 >= 40 
             and t1.whcd_*1 < 70
      union all
      select to_char(each_time, 'yyyy'),'大专以上','流动人口',count(*),'5' from t_rk_gaj_ldrkb t1 
             left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
             where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
             and t1.whcd_*1 <= 39）t;
      
      --图形4
      insert into t_rk_analyse_4 (time_,c_cy_,c_rklb_,number_)
      select * from (select to_char(each_time, 'yyyy'),xzy_,'流动人口',count(*) from T_RK_GAJ_LDRKB t 
      where to_char(to_date(ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') 
      and xzy_ is not null
      group by xzy_ order by count(*) desc) where rownum < 11;
      
      --图形5
      insert into t_rk_analyse_5 (name_,c_nl,time_)
      select * from (
      select '15岁以下', count(*), to_char(each_time, 'yyyy')
        from t_base_gaj
       where round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 15
      union all
      select '15-60岁', count(*), to_char(each_time, 'yyyy')
        from t_base_gaj
       where round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 15
         and round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 60
      union all
      select '60岁以上', count(*), to_char(each_time, 'yyyy')
        from t_base_gaj
       where round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 60
       );
      
      --图形6
      insert into t_rk_analyse_6 (name_,c_rs,c_zzl,time_) 
          select '人口数',sn,to_char(((xn-sn)/sn)*100,'999,999,999.99'),to_char(each_time,'yyyy') from (select (select count(*)
          from t_base_gaj
         where round(months_between(add_months(each_time,-12),TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 15
           and round(months_between(add_months(each_time,-12),TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 60) as sn,
           (select count(*)
              from t_base_gaj
             where round(months_between(each_time,TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 15
               and round(months_between(each_time,TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 60) as xn
      from dual);
      
      --图形7
      insert into T_RK_ANALYSE_7 (name_, c_fyb, time_)
      select *  from (select '少年儿童人口数量', (fz) * 1,to_char(each_time,'yyyy')
                from (select (select count(*)  from t_base_gaj
               where round(months_between(each_time,TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 15) as fz,
               (select count(*) from t_base_gaj) as fm from dual)
               union all  select '老年人口数量', (fz) * 1, to_char(each_time,'yyyy')  from (select (select count(*) from t_base_gaj
               where round(months_between(each_time, TO_DATE(csrq,  'YY/MM/DD HH24:MI:SS')) / 12) > 60) as fz,
               (select count(*) from t_base_gaj) as fm from dual)
               union all  select '劳动人口数量', (fz) * 1, to_char(each_time,'yyyy')  from (select (select count(*) from t_base_gaj
               where round(months_between(each_time, TO_DATE(csrq,  'YY/MM/DD HH24:MI:SS')) / 12) between 15 and 60 ) as fz,
               (select count(*) from t_base_gaj) as fm  from dual)

      );
      
      --图形8
      insert into T_RK_ANALYSE_8 (name_,c_xzldl,time_)
      select '劳动力预测',count(*),to_char(each_time,'yyyy')
        from t_base_gaj
      where round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 15
      and round(months_between(each_time, TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) < 60;
      
      --图形9
      insert into T_RK_ANALYSE_9 (name_, c_rksl, c_zb, time_)
      select '人口数', xn, to_char(((xn - sn) / sn) * 100,'999,999,999.99'), to_char(each_time,'yyyy')
      from (select (select count(*)  from t_base_gaj  where round(months_between(add_months(each_time,-12),TO_DATE(csrq, 'YY/MM/DD HH24:MI:SS')) / 12) > 65) as sn,
                   (select count(*)  from t_base_gaj  where round(months_between(each_time,TO_DATE(csrq,'YY/MM/DD HH24:MI:SS')) / 12) > 65) as xn
      from dual);
      
      --图形10
       
       --图形11
       
       --图形12
       insert into T_RK_ANALYSE_12 (c_hj_,c_nl_,number2_,time_,number1_)
       select 
       '外地',
       '',
       case when to_char(each_time, 'yyyy') = 2010 then '0' else to_char(((xn-sn)/sn)*100,'999,999,999.99') end ,
       to_char(each_time, 'yyyy'),xn 
       from (
       select (select count(*) from t_rk_gaj_ldrkb t1 
       left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
       where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(add_months(each_time,-12), 'yyyy') and t1.whcd_*1 >= 39) as sn,
       (select count(*) from t_rk_gaj_ldrkb t1 
               left join wb_rk_wenhuacd t2 on t1.whcd_ = t2.id_ 
       where to_char(to_date(t1.ljrq_lai_, 'YYYY/MM/DD HH24:MI:SS'), 'yyyy') <= to_char(each_time, 'yyyy') and t1.whcd_*1 >= 39) as xn from dual) t;
       
       --图形13
       insert into t_rk_analyse_13 (name_,number_,time_,name_group_)
       select hy_,decode( bs_+ss_+bk_+dz_+zz_,null,0, bs_+ss_+bk_+dz_+zz_),to_char(each_time, 'yyyy'),'' from T_BASE_QYXL t where time_ = to_char(each_time, 'yyyy');
       
       --图形14
       insert into t_rk_analyse_14(c_nl_,number_,time_)
        select * from ( 
        select '6-9岁' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365 between 6 and 9
        union all 
        select '20-29岁' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365 between 20 and 29
        union all 
        select '30-39岁' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365 between 30 and 39
        union all 
        select '40-49岁' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365 between 40 and 49
        union all 
        select '50-59岁' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365 between 50 and 59
        union all 
        select '60岁以上' as name,count(*),to_char(each_time, 'yyyy') as time  from t_base_gaj
               where whcd<=39 
               and floor(each_time - to_date(csrq,'yyyymmdd'))/365> 60
        );
        
          --图形15
         insert into T_RK_ANALYSE_15(c_xl_,Number_,Time_,sort_) 
          select * from (
          select '文盲' as name,count(*),to_char(each_time, 'yyyy') as time,'1' from t_rk_gaj_ldrkb
          where whcd_>=90 and LJRQ_LAI_< to_char(each_time, 'yyyy')
          union all
          select '小学' as name,count(*),to_char(each_time, 'yyyy') as time,'2' from t_rk_gaj_ldrkb
          where whcd_>=80 and whcd_<90 and LJRQ_LAI_< to_char(each_time, 'yyyy')
          union all
          select '初中' as name,count(*),to_char(each_time, 'yyyy') as time,'3' from t_rk_gaj_ldrkb
          where whcd_>=70 and whcd_<80 and LJRQ_LAI_< to_char(each_time, 'yyyy')
          union all
          select '高中' as name,count(*),to_char(each_time, 'yyyy') as time,'4' from t_rk_gaj_ldrkb
          where whcd_>=40 and whcd_<70 and LJRQ_LAI_< to_char(each_time, 'yyyy')
          union all
          select '大专及以上' as name,count(*),to_char(each_time, 'yyyy') as time,'5' from t_rk_gaj_ldrkb
          where whcd_<=39 and LJRQ_LAI_< to_char(each_time, 'yyyy')
         );
         
      i:=i+1;    
   end loop;
END;
