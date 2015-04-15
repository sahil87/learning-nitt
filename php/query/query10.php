<!--//A script to keep on running PHP queries and displaying their results on the same window-->
<!--//To edit the make_table function to enable it to edit the fields using the edit and delete buttons-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>Running MySQL scripts in PHP</title></head>
<body>

<?php
#connecting to the database
$link=mysql_connect("178.1.3.202","pragyan","weshallrule");			//unix shell equivalent-- mysql -h hostname -u username -p password;
if(!$link)								//type this to start mysql-- mysql -p databasename
  die("Could not connect to MySQL");
$db="pragyan_cms";							//>show databases; --shows the databases available
mysql_select_db($db,$link)					//unix shell equivalent >use dbname;
  or die("Couldnt open $db: ".mysql_error());			//>show tables; >describe tablename; --for tables
								//cant use this-- $tablename="students";

//print_r($GLOBALS['_POST']);
if(isset($GLOBALS['_POST']['querystring']))
  {
    $text_in_textarea=$GLOBALS['_POST']['querystring'];
    @ $result=mysql_query($GLOBALS['_POST']['querystring'],$link);//@suppresses error messages
    if(!$result)                                                  // and allows to put cutom error messages like this one - Error: (used here)
      {
	echo "Error: ".mysql_error();
	//exit;
      }
    else
      {
	$table1 = new tbman($GLOBALS['_POST']['querystring'],$link);
	$temp_result = $table1->make_table();
      }
  }
 else
   $text_in_textarea="Write your mysql script here";



class tbman
{
  var $tablename;
  var $link;
  var $result;
  var $editable;
  /*
TO DO-- add functions to EDIT , DELETE , and to ADD new record
and provision to select all checkboxes like phpmyadmin here-- such that
if edit pressed then all change to input boxes 
ie change javascript such that it changes all those whose checkboxes are checked
1)update button which updates all changes simultaneously
2)delete independent of update
3)1 variable for each record which tracks if any change has been or not to that record
4)check all,uncheck all,delete all
   */
  
  function tbman($querystring,$link)
  {
    $this->link=$link;
    $this->tablename=$this->get_tablename_from_query($querystring);
    @ $result=mysql_query($querystring,$link);//@suppresses error messages
    if(!$result)                              // and allows to put custom error messages like this one - Error: (used here)
      {
	echo ("Error: ".mysql_error());
	//exit;
      }
    else
      $this->result= $result;
    if(stristr($querystring,"select"))
       $this->editable="yes";
  }
  
  function make_table()
  {
    $result=$this->result;
#javascript functions-
    $java_str='<SCRIPT type=text/javascript>

	function toggle(imgname)
	{
		if(document.getElementById("chan"+imgname).value=="notchanged")
		{	
			document.getElementById("dat"+imgname).style.display="none";
			document.getElementById("inp"+imgname).style.display="";
			document.getElementById("chan"+imgname).value="changed";
			document.getElementById("checkd"+imgname).checked="checked";
			document.getElementById("checki"+imgname).checked="checked";
		}
		else if(document.getElementById("chan"+imgname).value=="changed")
		{	
			document.getElementById("dat"+imgname).style.display="";
			document.getElementById("inp"+imgname).style.display="none";
			document.getElementById("chan"+imgname).value="notchanged";
			document.getElementById("checkd"+imgname).checked="";
			document.getElementById("checki"+imgname).checked="";
		}
	}
	function challtoggle(t)
	{
		var i=0;
		var j=document.f1.elements.length;
		for(i=0;i<j;i++)
		document.f1.elements[i].checked=t.checked;
	}
	
</SCRIPT>';

    
    $str="\n<form id='f1' name='f1' metbod='post' action=''>";
    $str.="\n<table cellpadding=2 cellspacing=1>";
    
    for($i=0;$i<mysql_num_rows($result);$i++)
      {
	$a_rows=mysql_fetch_assoc($result);

#Field name row-
	if($i==0)
	  {
	    $str.="\n<tr bgcolor=#d3dce3>\n";
	    if($this->editable=="yes")	    
	    {
		    $str.='<td><input type="checkbox" onClick="challtoggle(this)"></td>';
		    $str.="<td colspan=2></td>";
	    }
	    foreach($a_rows as $key=>$value)
	      {
		$str.="<td>".$key."</td>";
	      }
	    $str.="\n</tr>";
	  }
	
#data rows-
	$dat_str="\n<tr id=\"dat".$i."\" bgcolor=".(($i%2==0)?"#e5e5e5":"#d5d5d5").">\n";
	$dat_str.="\n\t<input id=\"chan".$i."\" type=\"hidden\" value=\"notchanged\">";
	$inp_str="\n<tr id=\"inp".$i."\" style=\"display:none\" bgcolor=".(($i%2==0)?"#e5e5e5":"#d5d5d5").">";

#edit columns-
	if($this->editable=="yes")
	  {
	    $dat_str.="\n\t<td><input id=checkd".$i." type=checkbox /></td>";
	    $dat_str.="\n\t<td><a href=\"javascript:toggle(".$i.")\"><img src=\"b_edit.png\" border=0 alt=\"edit\"></a></td>";
	    $dat_str.="\n\t<td><a href=\"javascript:delete(".$i.")\"><img src=\"b_drop.png\" border=0 alt=\"delete\"/></a></td>";
	    
	    $inp_str.="\n\t<td><input onClick=\"toggle(".$i.")\" id=checki".$i." type=checkbox /></td>";
	    $inp_str.="\n\t<td><a href=\"javascript:toggle(".$i.")\"><img src=\"b_edit.png\" border=0 alt=\"edit\"></a></td>";
	    $inp_str.="\n\t<td><a href=\"javascript:delete(".$i.")\"><img src=\"b_drop.png\" border=0 alt=\"delete\"/></a></td>";
	  }
#data columns- 
	//  print_r($a_rows);
	foreach($a_rows as $key=>$value)
	  {
	    $dat_str.="\n\t<td>".$value."</td>";
	    $inp_str.="\n\t<td id='.$key.$value.' display=\"none\">";
	    $inp_str.='<input type="text" value="'.$value.'" size="'.$this->get_size_of_field($key)./*mysql_field_len($result,$key).*/'" name='.str_replace(" ","_",$key).$i.' /></td>';
	  }
	$dat_str.="\n</tr>";
	$inp_str.="\n</tr>";
	if($this->editable=="yes")
	  $str.=$dat_str.$inp_str;
	else
	  $str.=$dat_str;
	
      }
    
    
    $str.="\n</table>";
    $str.="\n</form>";
    
    $str=$java_str.$str;
    
    return $str;
  }
  
  function get_tablename_from_query($query)
  {
  //selection of tablename-
    $a=strtok($query,' ');
    while(is_string($a)) 
      { 
	if($a)
	  { 
	    if (strtolower($a)!='from')
	      {
		$a=strtok(' ');
		continue;
	      }
	    else
	      {
		$a=strtok(' ');
		break;
	      }
	  }
      }
    return $a;
  }
  
  function get_size_of_field($fieldname)
  {
    if(!is_string($this->tablename)) return 15;

    //making query to get info about the column named $key-
    $b=mysql_fetch_assoc(mysql_query("explain ".$this->tablename." '".$fieldname."'"));

    $a=$b['Type'];//gives datatype of this format - varchar(20)
    $a=substr($a,strpos($a,'(')+1,strpos($a,')')-strpos($a,'(')-1);//extracting 20 out of varchar(20)

    if(!is_string($a)) return 15; 
    return $a;
  }
}


?>
   
  <br/>Enter the query you want to run -
  <form id="f2" action=<?php echo "\"".$GLOBALS['_SERVER']['PHP_SELF']."\""?> method="post">
  <textarea name="querystring" rows="5" cols="60"><?php echo $text_in_textarea ?></textarea>
  <br/><INPUT type="submit" />
  </form>
       
<?php
 if(isset($temp_result))
 echo $temp_result;
  
 //print_r($GLOBALS);
 mysql_close($link);
  ?>
    
</body>
</html>