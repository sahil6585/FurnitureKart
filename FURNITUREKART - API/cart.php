<?php

    include('connect.php');
    
    $c_id = $_POST["c_id"];
    $p_brand = $_POST["p_brand"];
    $p_name = $_POST["p_name"];
    $p_mrp = $_POST["p_mrp"];
    $p_image = $_POST["p_image"];
   


    if($c_id=="" && $p_brand=="" && $p_name=="" && $p_mrp==""  && $p_image=="" )
    {
        echo '0';
    }
    else
    {
        $sql ="insert into cart(c_id,p_brand,p_name,p_mrp,p_image) values ('$c_id','$p_brand','$p_name','$p_mrp','$p_image')";
        mysqli_query($con,$sql);
        
    }

?>