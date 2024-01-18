<?php
    
    include('connect.php');
    
    $id = $_POST["id"];
    $product_name = $_POST["product_name"];
    $product_image = $_POST["product_image"];
    
    
    
    $sql ="update product_category set product_name='$product_name',product_image='$product_image' where id = '$id'";
    
    if(mysqli_query($con,$sql))
    {
        echo 'updated Succesfully';
    }
    else
    {
        echo 'something went wrong';
    }

?>