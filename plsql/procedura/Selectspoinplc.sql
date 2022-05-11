create or replace PROCEDURE Selectspoinplc (isza in NUMBER) IS
db number(10);
begin
SELECT COUNT(nev) into db FROM sportorvos where iszam > isza;
dbms_output.put_line('soprtorvos '||' : '|| db);
end;