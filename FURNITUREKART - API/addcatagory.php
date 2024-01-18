<?php 
	
//importing dbDetails file 
include 'connect.php';	

//this is our upload folder 
$upload_path = 'CATAGORY_IMAGES/';

//Getting the server ip 
$server_ip = gethostbyname(gethostname());

//creating the upload url 
//$upload_url = 'http://'.$server_ip.'/animal/'.$upload_path; 

$upload_url = 'https://'.$_SERVER['SERVER_NAME'] . "/API/Furniture App/" . $upload_path;
	
	
//getting name from the request 
 
 $pname = $_POST['product_name'];

  

//getting file info from the request 
$fileinfo = pathinfo($_FILES["product_image"]["product_name"]);

//getting the file extension 
$extension ="jpg" /*$fileinfo["extension"]*/;

//file url to store in the database 
$file_url = $upload_url . $pname . '.' .$extension;

//file path to upload in the server 
$file_path = $upload_path . $pname . '.'.$extension; 
			
//saving the file 
move_uploaded_file($_FILES["product_image"]["tmp_name"],$file_path);

if($pname=="" && $file_url=="")
{
       echo '0';
}
else
{
        $sql = "INSERT INTO product_category(product_name,product_image) VALUES ('$pname','$file_url')";
        $ex=mysqli_query($con,$sql);
}
echo $sql;
//exit;

			
//closing the connection 
mysqli_close($con);

?>