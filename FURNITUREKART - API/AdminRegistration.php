<?php

    include('connect.php');
    
    $fname = $_POST["fname"];
    $lname = $_POST["lname"];
    $gender = $_POST["gender"];
    $email = $_POST["email"];
    $phone = $_POST["phone"];
    $password = $_POST["password"];


    if($fname=="" && $lname=="" && $gender=="" && $email=="" && $phone=="" && $password=="")
    {
        echo '0';
    }
    else
    {
        $sql ="insert into AdminUser(fname,lname,gender,email,phone,password) values ('$fname','$lname','$gender','$email','$phone','$password')";
        mysqli_query($con,$sql);
        
    }

?>