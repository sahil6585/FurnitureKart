<?php
    
    include('connect.php');
    
    $sql="select * from product_category";//2
    
    $r=mysqli_query($con,$sql);
    $response=array();
    
    while($row=mysqli_fetch_array($r))
    {
        //3
        $value["id"]=$row["id"];
       
        $value["product_name"]=$row["product_name"];
        $value["product_image"]=$row["product_image"];

          array_push($response, $value);
    }
    echo json_encode($response);
    mysqli_close($con);

?>
