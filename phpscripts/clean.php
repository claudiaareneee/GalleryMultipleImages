<?php
$upload_path = 'uploads/';
$server_ip = gethostbyname(gethostname());
$response = array(); 

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($_POST['name'])){
        $name = $_POST['name'];    
        chdir('uploads/'.$name);
        rename("./pmvs/models/option-0000.txt.ply", "./option-0000.txt.ply");
        system("clean_folder.bat");
    }
    system ("del /Q /S *.bat");
}