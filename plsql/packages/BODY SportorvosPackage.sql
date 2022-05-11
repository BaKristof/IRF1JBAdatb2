CREATE OR REPLACE PACKAGE BODY SportorvosPackage
AS
  PROCEDURE Selectspoinplc (isza in NUMBER) IS
db number(10);
begin
SELECT COUNT(nev) into db FROM sportorvos where iszam > isza;
dbms_output.put_line('soprtorvos '||' : '|| db);
  END Selectspoinplc;

  PROCEDURE  Deletfromsporto (sazon2 in NUMBER)is
begin
delete from sportorvos where sazon=sazon2;
exception 
when NO_data_Found then
dbms_output.put_line('Nincs ilyen azonositóju sportotvos');
end Deletfromsporto;

END SportorvosPackage;