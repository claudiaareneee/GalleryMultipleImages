@echo off 
path c:\sfm;c:\sfmin;c:\sfm\bin64;c:\sfm\cygwin\bin;c:\sfm\bin32 
echo  ============================= 
echo  ---- Finding feature points ----- 
echo  ============================= 
                                    
echo  ============================= 
echo  ---- Bundle Matching ----- 
echo  ============================= 
BundlerMatcher list.txt gpu.matches.txt 0.8 0 
sh 2_RunBundler.gpu.sh 
