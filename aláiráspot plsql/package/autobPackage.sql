create or replace package autobPackage as 
PROCEDURE  insertautob (jarat in NUMBER,uticel char,intdulas in char,tipus in char);
PROCEDURE aubuszmod ( jarataz in number, ujtipus in CHAR);
PROCEDURE  Deletautob (jarat2 in NUMBER);
end;


create or replace PACKAGE BODY autobPackage
AS
PROCEDURE  insertautob (jarat in NUMBER,uticel char,intdulas in char,tipus in char)is
begin
insert into autobuszjaratok(jarat,uticel,indulas,autobusz) values(jarat,uticel,intdulas,autobusz);
end insertautob;

PROCEDURE aubuszmod ( jarataz in number, ujtipus in CHAR) is

begin
update autobuszjaratok set tipus=ujtipus where jarat =jarataz;
end aubuszmod;

PROCEDURE  Deletautob (jarat2 in NUMBER)is
begin
delete from autobuszjaratok where jarat=jarat2;
exception 
when NO_data_Found then
dbms_output.put_line('Nincs ilyen azonositóju autobusz');
end Deletautob;

END autobPackage;