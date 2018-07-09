echo off
path c:\sfm;c:\sfm\bin;c:\sfm\bin64;c:\sfm\cygwin\bin;c:\sfm\bin32
echo " ===================================== "
echo " -------- Converting Sparse to PMVS input -------- "
echo " ===================================== "
echo Image Segments:  %1 	Level: %2	   csize: %3    threshold: %4

bundle2pmvs list.txt ./bundle/bundle.out
dos2unix prep_pmvs.sh
RadialUndistort list.txt ./bundle/bundle.out
sh prep_pmvs.sh
cp bundle.rd.out ./pmvs/bundle.rd.out
cd pmvs
CMVS ./ %1 2
GenOption ./ %2 %3 %4 7 3 2
rm option*.txt
ren option* option*.txt
cat pmvs.bat | sed -e 's/$/.txt/' > pmvs_2.bat
cat pmvs_2.bat | sed -e 's/ pmvs/ ./' > pmvs_3.bat
cat pmvs_3.bat | sed -e 's/.txt.txt/.txt/' > pmvs_4.bat
pmvs_4
