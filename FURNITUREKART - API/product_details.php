<?php
    include('connect.php');

    $cid=$_REQUEST["data"];
    
    $select="SELECT * from product_details where c_id='$cid'";
   // $select="select * from Category_Images";
    $result=mysqli_query($con,$select);
 
    $response= array();
         
    while ($row = mysqli_fetch_array($result))
    {
        $value = array();
        $value["id"] = $row["id"];
        $value["c_id"] = $row["c_id"];
        $value["p_brand"] = $row["p_brand"];
        $value["p_info"] = $row["p_info"];
        $value["p_image"] = $row["p_image"];
        $value["p_name"] = $row["p_name"];
        $value["p_mrp"] = $row["p_mrp"];
        $value["p_color"] = $row["p_color"];
        $value["p_material"] = $row["p_material"];
        $value["p_weight"] = $row["p_weight"];
        $value["number_of_item"] = $row["number_of_item"];
        $value["number_of_pieces"] = $row["number_of_pieces"];
        $value["p_menufacturer"] = $row["p_menufacturer"];
        $value["p_country"] = $row["p_country"];
         $value["fake_mrp"] = $row["fake_mrp"];
         $value["p_per"] = $row["p_per"];
        
        
        array_push($response, $value);
    }
    echo json_encode($response);
?>  