--select tempindex0_.id as id3_0_, tempolyee1_.uid as uid5_1_, tindextarg2_.id as id6_2_, tempindex0_.act_output as act2_3_0_, tempindex0_.score as score3_0_, tempindex0_.percentage as percentage3_0_, tempindex0_.indexID as indexID3_0_, tempindex0_.uid as uid3_0_, tempolyee1_.departmentid as departme2_5_1_, tempolyee1_.email as email5_1_, tempolyee1_.name as name5_1_, tempolyee1_.type as type5_1_, tempolyee1_.isSeparation as isSepara6_5_1_, tempolyee1_.leaderID as leaderID5_1_, tempolyee1_.level as level5_1_, tempolyee1_.baseSalary as baseSalary5_1_, tempolyee1_.floatingSalary as floatin10_5_1_, tempolyee1_.SalaryType as SalaryType5_1_, tindextarg2_.name as name6_2_, tindextarg2_.version as version6_2_, tindextarg2_.type as type6_2_, tindextarg2_.isChoice as isChoice6_2_, tindextarg2_.formula as formula6_2_, tindextarg2_.A as A6_2_, tindextarg2_.B as B6_2_, tindextarg2_.C as C6_2_, tindextarg2_.departID as departID6_2_ from hr1.dbo.tEmpIndex tempindex0_ inner join hr1.dbo.tEmpolyee tempolyee1_ on tempindex0_.uid=tempolyee1_.uid inner join hr1.dbo.tindex_target tindextarg2_ on tempindex0_.indexID=tindextarg2_.id where tempindex0_.uid='1382' and tindextarg2_.version='2009-07'


update hrinbound set inbound=16983 where id=1229


select pd.total_saving,[all],approval,finish,d.name as departName,e.name as empName,hlh,WAITCHANGE,DO_PROJECT,BEGIN_PROJECT  
from  tEmpolyee as e inner join  
(select emp.uid as empUid, sum(case when state>=0 then 1 else 0 end) as [all],  sum(case when state>3 then 1 else 0 end) as [approval], 
 sum(case when state=7 then 1 else 0 end) as [finish] , 
sum(case when state=8 then 1 else 0 end) as [hlh] , 
sum(case when state=9 then 1 else 0 end) as [WAITCHANGE] , 
sum(case when state=6 then 1 else 0 end) as [DO_PROJECT] , 
sum(case when state=4 then 1 else 0 end) as [BEGIN_PROJECT] 
 from dbo.tProposal as p inner join dbo.tEmpolyee as emp  on p.collection_persion=emp.uid  
where p.source='公司' and year(lastModifyTime)=2009 and state>0 group by emp.uid) as cc 
 on e.uid = cc.empUid
 inner join dbo.tDepartment as d on d.id=e.departmentid
left join (select * from vave_pointsDetails  where _year=2009) pd on pd.uid=e.uid 

select * from  tEmpolyee as e left join (select * from vave_pointsDetails  where _year=2009) pd on pd.uid=e.uid 


select * from vave_proposalmore where proposalId='VAVE_1228_Thu Apr 30 12:23:48 CST 2009'


select * from tproposal as p inner join vave_proposalmore as m on p.id=m.proposalId

group by p.collection_persion

---
select a.* from table1 a 
left join 
(select name,max(time)as time from table1 group by name)b 
on a.name=b.name and a.time=b.time
---


--sum(cast(size   as   numeric(39)))   
--统计每个人节约金额
select collection_persion,sum(cast(expectSaving as numeric(39))) from tproposal as p inner join 
(select a.proposalId,expectSaving from vave_proposalmore a right join
(select proposalId,max(version) as version from vave_proposalmore group by proposalId )b
on a.proposalId=b.proposalId and a.version=b.version) as v on p.id=v.proposalId
--inner join tproposal as p on p.id=a.proposalId
where state>=4 and state<>8 and year(lastModifyTime)=2009 and source='公司'--and collection_persion<>1 and collection_persion<> 1096
group by p.collection_persion

--统计供应商节约金额
select suppliers,sum(cast(expectSaving as numeric(39))) from tproposal as p inner join 
(select a.proposalId,expectSaving from vave_proposalmore a right join
(select proposalId,max(version) as version from vave_proposalmore group by proposalId )b
on a.proposalId=b.proposalId and a.version=b.version) as v on p.id=v.proposalId
--inner join tproposal as p on p.id=a.proposalId
where state>=4 and state<>8 and year(lastModifyTime)=2009 and source='供应商'--and collection_persion<>1 and collection_persion<> 1096
group by p.suppliers




select count(*) from tproposal
where year(lastModifyTime)=2009
group by collection_persion


select * from tproposal where  year(lastModifyTime)=2009 and source='供应商'




select [all],approval,finish,d.name as departName,e.name as empName,hlh,WAITCHANGE,DO_PROJECT,BEGIN_PROJECT,pd.total_saving  from  tEmpolyee as e inner join  (select emp.uid as empUid, sum(case when state>=0 then 1 else 0 end) as [all],  sum(case when state>3 then 1 else 0 end) as [approval],  sum(case when state=7 then 1 else 0 end) as [finish] , sum(case when state=8 then 1 else 0 end) as [hlh] , sum(case when state=9 then 1 else 0 end) as [WAITCHANGE] , sum(case when state=6 then 1 else 0 end) as [DO_PROJECT] , sum(case when state=4 then 1 else 0 end) as [BEGIN_PROJECT]  from dbo.tProposal as p inner join dbo.tEmpolyee as emp  on p.collection_persion=emp.uid  where p.source='公司' and year(lastModifyTime)=2009 and state>0 group by emp.uid) as cc  on e.uid = cc.empUid inner join dbo.tDepartment as d on d.id=e.departmentid left join (select * from vave_pointsDetails  where _year=2009) pd on pd.uid=e.uid



select * from logs where datetime>'2010-3-18'


select * from tproposal where id='83'


select [all],approval,finish,d.name as departName,e.name as empName,hlh,WAITCHANGE,DO_PROJECT,BEGIN_PROJECT,pd.total_saving 
 from  tEmpolyee as e
 inner join  (select emp.uid as empUid, sum(case when state>=0 then 1 else 0 end) as [all],  
sum(case when state>3 then 1 else 0 end) as [approval],  
sum(case when state=7 then 1 else 0 end) as [finish] , 
sum(case when state=8 then 1 else 0 end) as [hlh] , 
sum(case when state=9 then 1 else 0 end) as [WAITCHANGE] ,
 sum(case when state=6 then 1 else 0 end) as [DO_PROJECT] ,
 sum(case when state=4 then 1 else 0 end) as [BEGIN_PROJECT] 
 from dbo.tProposal as p inner join dbo.tEmpolyee as emp  on p.collection_persion=emp.uid 
 where p.source='公司' and year(lastModifyTime)=2009 and state>0 group by emp.uid) as cc  on e.uid = cc.empUid 
inner join dbo.tDepartment as d on d.id=e.departmentid 
left join (select * from vave_pointsDetails  where _year=2009) pd on pd.uid=e.uid



select * from dbo.logs where datetime>'2010-3-22'


-----游标 添加图号的新名称
declare @id varchar(50)
declare @name varchar(50)
declare myCursor cursor for 
select figureid,name from fn2
open myCursor
fetch next from myCursor into @id,@name
while(@@fetch_status=0)
begin
	--print @name print @id
	update dbo.HRBounceFigure set name=@name where FigureId=@id
	fetch next from myCursor into @id,@name
	
end
close myCursor
deallocate myCursor
-------------end---------------
select * from dbo.HRBounceFigure where figureId = '52442895'

update figureName set 图号='52423318' where 物料号(sap号)='52423318.0'


select dd.figureId,remark,CarType,standWorkTime,inbound,picesNum,al  from HRBounceFigure p 
right join (select figureId,sum(worktime) al from tEmployeeProduct ep 
where 1=1  and type=2 and  year(datatime)=2010 and  month(datatime)=01 
group by figureId) dd on p.figureId=dd.figureId  
left join (select * from dbo.HRInbound 
where 1=1  and  datediff(day,'2010-01-01',inboundTime)>=0  
and  datediff(day,inboundTime,'2010-01-15')>=0  ) b on b.figureId=p.remark  order by al desc

select * from dbo.tEmployeeProduct e
left join dbo.HRBounceFigure f on e.figureId=f.FigureId
where type=1 and standworktime!=null
and 
--group by f.FigureId