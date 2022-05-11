create or replace PROCEDURE  Deletfromsporto (sazon2 in NUMBER)is
begin
delete from sportorvos where sazon=sazon2;
exception 
when NO_data_Found then
dbms_output.put_line('Nincs ilyen azonositóju sportotvos');
end;