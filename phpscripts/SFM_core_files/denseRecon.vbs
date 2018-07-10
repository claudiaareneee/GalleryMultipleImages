sub Main() 
    command = "denseRecon_batch.bat " & 30 & " " & 1 & " " & 2 & " " & 0.6 
  Set oShell = WScript.CreateObject ("WScript.Shell") 
  windowStyle = 8 
  waitOnReturn = true 
  oShell.run command, windowStyle, waitOnReturn 
  Set oShell = Nothing 
   end sub
   Main()