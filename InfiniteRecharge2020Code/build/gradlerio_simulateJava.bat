@echo off
setlocal
set HALSIM_EXTENSIONS=C:\Users\sicp\Documents\GitHub\InfiniteRecharge-Robot\InfiniteRecharge2020Code\build\tmp\expandedArchives\halsim_gui-2020.1.2-windowsx86-64.zip_0ded91afab576f356c80ba4c4e7b8310\windows\x86-64\shared\halsim_gui.dll
set PATH=C:\Users\sicp\Documents\GitHub\InfiniteRecharge-Robot\InfiniteRecharge2020Code\build\tmp\jniExtractDir;C:\Users\sicp\Documents\GitHub\InfiniteRecharge-Robot\InfiniteRecharge2020Code\build\tmp\jniExtractDir
"C:\Users\Public\wpilib\2020\jdk\bin\java.exe" -Djava.library.path="C:\Users\sicp\Documents\GitHub\InfiniteRecharge-Robot\InfiniteRecharge2020Code\build\tmp\jniExtractDir" -jar "C:\Users\sicp\Documents\GitHub\InfiniteRecharge-Robot\InfiniteRecharge2020Code\build\libs\InfiniteRecharge2020Code.jar"
endlocal
