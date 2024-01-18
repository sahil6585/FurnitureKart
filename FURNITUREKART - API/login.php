<?php
    
    include('connect.php');
    
    $phone=$_REQUEST["phone"];
    $password=$_REQUEST["password"];
    
    $sql="select * from userdata where phone='$phone' and password ='$password'";
    
    
    $ex=mysqli_query($con,$sql);
    
    $no=mysqli_num_rows($ex);
    
    
    if($no>0)
    {
    $fet=mysqli_fetch_object($ex);
    echo json_encode(['code'=>200]);
    }
    else
    {
    echo "0";
    }

?>
