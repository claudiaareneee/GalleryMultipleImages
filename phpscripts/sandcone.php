<?php
$upload_path = 'uploads/';
$server_ip = gethostbyname(gethostname());
$response = array(); 

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    if(isset($_POST['name'])){
        $name = $_POST['name'];

        try{
            echo getcwd() . "<br>";
            chdir('uploads/'.$name);
            echo getcwd() . "<br>";

            //shell_exec('makeList.bat');
            system("makeList.bat");
            chdir('../..');
            echo getcwd() . "<br>";

        }catch(Exception $e){
            $response['error'] = false; 
            $response['message'] = $e->getMessage(); 
        }
    }else{
        $response['error'] = true; 
        $response['message'] = 'please choose a file';
    }
    echo json_encode($response);
}
