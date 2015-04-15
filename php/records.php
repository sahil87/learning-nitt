<HTML>
<HEAD><TITLE>Interaction with records in PHP</TITLE></HEAD>
<BODY>
<?php
#connecting to MySQL
$link=mysql_connect("localhost","sahil","sahil");//unix shell equivalent-- mysql -h hostname -u username -p password;
if(!$link)
  die("Could not connect to MySQL");
#opening database
$db="test";//>show databases; --shows the databases available
mysql_select_db($db,$link)//unix shell equivalent >use dbname;
  or die("Couldnt open $db: ".mysql_error());//>show tables; >describe tablename; --for tables
#querying table
//cant use this-- $tablename="students";
$query="INSERT INTO students ( `Rollno` , `First Name` , `Last Name` , `Address` , `Sex` , `DoB` )
        VALUES (
        'ic10439', 'Sandeep', 'Sahare', 'Madhya Pradesh', 'M', '1986-06-27'
        );";
mysql_query($query,$link)
  or die("Couldnt add data to \"students\" table: ".mysql_error());
#disconnecting from MySQL
mysql_close($link);
?>
</BODY>
</HTML>