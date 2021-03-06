USE [baozhang]
GO
/****** Object:  Table [dbo].[tbl_baozhang]    Script Date: 01/22/2018 16:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_baozhang](
	[Status] [varchar](20) NOT NULL,
	[BranchCompany] [varchar](50) NOT NULL,
	[DistrictCompany] [varchar](50) NOT NULL,
	[CostObjectId] [varchar](50) NOT NULL,
	[CostObjectName] [varchar](200) NOT NULL,
	[EntityId] [varchar](25) NOT NULL,
	[EntityName] [varchar](200) NOT NULL,
	[CostType] [varchar](10) NOT NULL,
	[AccountDate] [varchar](15) NOT NULL,
	[AccountPerson] [varchar](8) NOT NULL,
	[StartedDate] [varchar](50) NOT NULL,
	[EndedDate] [varchar](50) NOT NULL,
	[AccountType] [varchar](10) NOT NULL,
	[BusinessType] [varchar](15) NOT NULL,
	[AccountMethod] [varchar](50) NOT NULL,
	[ElectricType] [varchar](30) NOT NULL,
	[ElectricAmount] [decimal](18, 1) NOT NULL,
	[ElectricPrice] [decimal](18, 2) NOT NULL,
	[AccountAmount] [decimal](18, 2) NOT NULL,
	[Tax] [decimal](18, 2) NOT NULL,
	[PayMethod] [varchar](10) NOT NULL,
	[UtilitiesFee] [money] NOT NULL,
	[SewageFee] [money] NOT NULL,
	[GarbageFee] [money] NOT NULL,
	[AccountCenterName] [varchar](50) NOT NULL,
	[AccountCenterId] [varchar](25) NOT NULL,
	[UserId] [varchar](50) NOT NULL,
	[AdvancePayment] [varchar](50) NOT NULL,
	[SettlementType] [varchar](15) NOT NULL,
	[BillType] [varchar](30) NOT NULL,
	[Is10KV] [char](10) NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
CREATE CLUSTERED INDEX [SearchIndex] ON [dbo].[tbl_baozhang] 
(
	[DistrictCompany] DESC,
	[AccountDate] DESC,
	[BusinessType] DESC,
	[AccountMethod] DESC,
	[ElectricType] DESC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_10KVEntity]    Script Date: 01/22/2018 16:23:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_10KVEntity](
	[EntityName] [nvarchar](255) NULL,
	[UserId] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[vw_AllConditionsYear]    Script Date: 01/22/2018 16:23:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[vw_AllConditionsYear] as
select sum(case AccountMethod
 when '收回代垫外单位或员工费用并冲销已列成本' then -ElectricAmount
 when '(调账)冲销成本' then -ElectricAmount 
 when '冲销预列' then -ElectricAmount 
else ElectricAmount
end) as TotalElectricity,sum(case AccountMethod
 when '收回代垫外单位或员工费用并冲销已列成本' then -UtilitiesFee
 when '(调账)冲销成本' then -UtilitiesFee 
 when '冲销预列' then -UtilitiesFee 
else UtilitiesFee
end) as TotalAmount,ElectricType,subString(AccountDate,0,5) as 'DateYear',DistrictCompany
from dbo.tbl_baozhang
group by ElectricType,subString(AccountDate,0,5),DistrictCompany
GO
/****** Object:  View [dbo].[vw_AllConditions]    Script Date: 01/22/2018 16:23:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[vw_AllConditions] as
select sum(case AccountMethod
 when '收回代垫外单位或员工费用并冲销已列成本' then -ElectricAmount
 when '(调账)冲销成本' then -ElectricAmount 
 when '冲销预列' then -ElectricAmount 
else ElectricAmount
end) as TotalElectricity,sum(case AccountMethod
 when '收回代垫外单位或员工费用并冲销已列成本' then -UtilitiesFee
 when '(调账)冲销成本' then -UtilitiesFee 
 when '冲销预列' then -UtilitiesFee 
else UtilitiesFee
end) as TotalAmount,ElectricType,AccountDate,DistrictCompany
from dbo.tbl_baozhang
group by ElectricType,AccountDate,DistrictCompany
GO
/****** Object:  View [dbo].[vw_10KVEntity]    Script Date: 01/22/2018 16:23:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[vw_10KVEntity]
as
select EntityName,EntityId 
from tbl_baozhang
where Is10KV='是' and CostType='电费'
group by EntityName,EntityId
GO
/****** Object:  View [dbo].[vw_10KVData]    Script Date: 01/22/2018 16:23:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE view [dbo].[vw_10KVData] as
select EntityName,sum(ElectricAmount) as 'TotalElectricity',AccountDate,UserId,DistrictCompany,SUM(AccountAmount) as 'TotalAmount'
from dbo.tbl_baozhang
where Is10KV='是' and AccountMethod='委托划扣' and CostType='电费'
group by AccountDate ,Is10KV,EntityName,UserId,DistrictCompany
GO
/****** Object:  StoredProcedure [dbo].[update_10kvData]    Script Date: 01/22/2018 16:23:29 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[update_10kvData]
as
update tbl_baozhang set Is10KV='是'
where EntityName in (select distinct EntityName from tbl_10KVEntity)
and CostType='电费'
GO
/****** Object:  Default [DF_tbl_baozhang_Status]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_Status]  DEFAULT ('') FOR [Status]
GO
/****** Object:  Default [DF_tbl_baozhang_BranchCompany]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_BranchCompany]  DEFAULT ('') FOR [BranchCompany]
GO
/****** Object:  Default [DF_tbl_baozhang_DistrictCompany]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_DistrictCompany]  DEFAULT (' ') FOR [DistrictCompany]
GO
/****** Object:  Default [DF_tbl_baozhang_CostObjectId]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_CostObjectId]  DEFAULT ('') FOR [CostObjectId]
GO
/****** Object:  Default [DF_tbl_baozhang_CostObjectName]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_CostObjectName]  DEFAULT ('') FOR [CostObjectName]
GO
/****** Object:  Default [DF_tbl_baozhang_EntityId]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_EntityId]  DEFAULT ('') FOR [EntityId]
GO
/****** Object:  Default [DF_tbl_baozhang_EntityName]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_EntityName]  DEFAULT ('') FOR [EntityName]
GO
/****** Object:  Default [DF_tbl_baozhang_CostType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_CostType]  DEFAULT ('') FOR [CostType]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountDate]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountDate]  DEFAULT ('') FOR [AccountDate]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountPerson]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountPerson]  DEFAULT (' ') FOR [AccountPerson]
GO
/****** Object:  Default [DF_tbl_baozhang_StartedDate]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_StartedDate]  DEFAULT (' ') FOR [StartedDate]
GO
/****** Object:  Default [DF_tbl_baozhang_EndedDate]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_EndedDate]  DEFAULT (' ') FOR [EndedDate]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountType]  DEFAULT (' ') FOR [AccountType]
GO
/****** Object:  Default [DF_tbl_baozhang_BusinessType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_BusinessType]  DEFAULT (' ') FOR [BusinessType]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountMethod]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountMethod]  DEFAULT (' ') FOR [AccountMethod]
GO
/****** Object:  Default [DF_tbl_baozhang_ElectricType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_ElectricType]  DEFAULT ('('' '')') FOR [ElectricType]
GO
/****** Object:  Default [DF_tbl_baozhang_ElectricAmount]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_ElectricAmount]  DEFAULT ((0)) FOR [ElectricAmount]
GO
/****** Object:  Default [DF_tbl_baozhang_ElectricPrice]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_ElectricPrice]  DEFAULT ((0)) FOR [ElectricPrice]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountAmount]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountAmount]  DEFAULT ((0)) FOR [AccountAmount]
GO
/****** Object:  Default [DF_tbl_baozhang_Tax]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_Tax]  DEFAULT ((0)) FOR [Tax]
GO
/****** Object:  Default [DF_tbl_baozhang_PayMethod]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_PayMethod]  DEFAULT (' ') FOR [PayMethod]
GO
/****** Object:  Default [DF_tbl_baozhang_UtilitiesFee]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_UtilitiesFee]  DEFAULT ((0)) FOR [UtilitiesFee]
GO
/****** Object:  Default [DF_tbl_baozhang_SewageFee]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_SewageFee]  DEFAULT ((0)) FOR [SewageFee]
GO
/****** Object:  Default [DF_tbl_baozhang_GarbageFee]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_GarbageFee]  DEFAULT ((0)) FOR [GarbageFee]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountCenterName]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountCenterName]  DEFAULT (' ') FOR [AccountCenterName]
GO
/****** Object:  Default [DF_tbl_baozhang_AccountCenterId]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AccountCenterId]  DEFAULT (' ') FOR [AccountCenterId]
GO
/****** Object:  Default [DF_tbl_baozhang_UserId]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_UserId]  DEFAULT (' ') FOR [UserId]
GO
/****** Object:  Default [DF_tbl_baozhang_AdvancePayment]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_AdvancePayment]  DEFAULT (' ') FOR [AdvancePayment]
GO
/****** Object:  Default [DF_tbl_baozhang_SettlementType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_SettlementType]  DEFAULT (' ') FOR [SettlementType]
GO
/****** Object:  Default [DF_tbl_baozhang_BillType]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_BillType]  DEFAULT (' ') FOR [BillType]
GO
/****** Object:  Default [DF_tbl_baozhang_Is10KV]    Script Date: 01/22/2018 16:23:31 ******/
ALTER TABLE [dbo].[tbl_baozhang] ADD  CONSTRAINT [DF_tbl_baozhang_Is10KV]  DEFAULT ('否') FOR [Is10KV]
GO
