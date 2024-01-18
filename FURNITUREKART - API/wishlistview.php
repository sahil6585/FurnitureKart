<?php
    
    include('connect.php');
    
    $sql="select * from wishlist";
    
    $r=mysqli_query($con,$sql);
    $response=array();
    
    while($row=mysqli_fetch_array($r))
    {
        
        $value["id"]=$row["id"];
        $value["c_id"]=$row["c_id"];
        $value["p_brand"]=$row["p_brand"];
        $value["p_name"]=$row["p_name"];
        $value["p_mrp"]=$row["p_mrp"];
        $value["p_image"]=$row["p_image"];
      

          array_push($response, $value);
    }
    echo json_encode($response);
    mysqli_close($con);

?>
