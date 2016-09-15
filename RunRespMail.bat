@echo off
for /F "usebackq tokens=1,2 delims==" %%i in (`wmic os get LocalDateTime /VALUE 2^>NUL`) do if '.%%i.'=='.LocalDateTime.' set ldt=%%j
set ldt=%ldt:~0,4%-%ldt:~4,2%-%ldt:~6,2%@%ldt:~8,2%-%ldt:~10,2%-%ldt:~12,6%
set _my_Log=GetResponse[%ldt%].txt
echo Generating Log GetResponse[%ldt%].txt
echo ****************************************************** >  %_my_Log%
echo     INITIALIZING ENVIRONMENT FOR EXECUTOR [%DATE%]     >> %_my_Log%
echo ****************************************************** >> %_my_Log%
pushd %~dp0
echo PREPARING EXECUTOR  [ %TIME% ] >> %_my_Log%
echo Dim args , oExcel>Executor
echo Set args = wscript.Arguments>>Executor
echo Set oExcel = CreateObject("Excel.application")>>Executor
echo oExcel.workbooks.Open args(0)>>Executor
echo oExcel.Visible = True>>Executor
echo oExcel.Run(args(1))>>Executor
echo oExcel.ActiveWorkbook.Save>>Executor
echo oExcel.ActiveWorkbook.Close(0)>>Executor
echo oExcel.Quit >>Executor
ren Executor Executor.vbs
echo RUNNING   EXECUTOR  [ %TIME% ]>> %_my_Log%
cscript "Executor.vbs" %CD%\"ResponseMailer.xlsm" "GetResponse" >> %_my_Log%
echo FINISHED  EXECUTOR  [ %TIME% ] >> %_my_Log%
del /f Executor.vbs
exit
