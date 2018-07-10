@echo off
del /Q /S *.rd.jpg
del /Q /S *.key
del /Q bundle.rd.out
del /Q /S *.txt
rd /Q /S bundle
rd /Q /S pmvs
del /Q prep_pmvs.sh
REM del /Q denseRecon64.bat
REM del /Q makeList.bat
REM del /Q sparseRecon.bat
del /Q denseRecon.vbs
REM del /Q /S *.bat