<?php
$upload_path = 'uploads/';
$server_ip = gethostbyname(gethostname());
$response = array(); 

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($_POST['name'])){
        $name = $_POST['name'];

        copy("./SFM_core_files/clean_folder.bat",    "./uploads/".$name."/clean_folder.bat");
        chdir('uploads/'.$name);
        system("clean_folder.bat");
        chdir('../..');

        
        try{
            
            echo getcwd() . "<br>";

            copy("./SFM_core_files/makeList.bat",        "./uploads/".$name."/makeList.bat");
            copy("./SFM_core_files/denseRecon_batch.bat","./uploads/".$name."/denseRecon_batch.bat");
            copy("./SFM_core_files/denseRecon.vbs",      "./uploads/".$name."/denseRecon.vbs");
            copy("./SFM_core_files/sparseRecon64.bat",   "./uploads/".$name."/sparseRecon64.bat");
            copy("./SFM_core_files/clean_folder.bat",    "./uploads/".$name."/clean_folder.bat");


            echo getcwd() . "<br>";
            chdir('uploads/'.$name);
            echo getcwd() . "<br>";

            echo "starting makeList.bat";
            system("makeList.bat");
            
            echo "starting sparseRecon64.bat";
            set_time_limit(90);
            system("sparseRecon64.bat");

            echo "starting denseRecon.vbs";
            set_time_limit(90);
            system("denseRecon.vbs");

            chdir('../..');
            echo getcwd() . "<br>";
            $response['error'] = false; 

        }catch(Exception $e){
            $response['error'] = false; 
            $response['message'] = $e->getMessage(); 
        }
    }else{
        $response['error'] = true; 
        $response['message'] = 'please choose a file';
    }
    echo json_encode($response);
    echo "<br>SUCCESS!";
}

