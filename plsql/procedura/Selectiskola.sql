create or replace PROCEDURE Selectiskola (also in NUMBER,felso in NUMBER) IS
i iskola%rowtype;

begin
SELECT * into i FROM iskola where iszam between also and felso;
dbms_output.put_line('auto: '||' : '|| i.nev || i.iszam);
exception

when NO_DATA_FOUND then
dbms_output.put_line('Nincs adat teso');
end;