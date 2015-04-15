<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
<xsl:output method="html" indent="yes" encoding="UTF-8" />

<!-- global variables      -->
<!-- ............................................................ -->
<xsl:variable name="nmap_xsl_version">0.9b</xsl:variable>
<!-- ............................................................ -->
<xsl:variable name="start"><xsl:value-of select="/nmaprun/@startstr" /></xsl:variable>
<xsl:variable name="end"><xsl:value-of select="/nmaprun/runstats/finished/@timestr" /> </xsl:variable>
<xsl:variable name="totaltime"><xsl:value-of select="/nmaprun/runstats/finished/@time -/nmaprun/@start" /></xsl:variable>
<!-- ............................................................ -->


<xsl:template match="/">
	<xsl:apply-templates/>
</xsl:template>


<!-- root -->
<!-- ............................................................ -->
<xsl:template match="/nmaprun">
<html>
<head>

<xsl:comment>generated with nmap.xsl - version <xsl:value-of select="$nmap_xsl_version" /> by Benjamin Erb - http://www.benjamin-erb.de/nmap_xsl.php </xsl:comment>

<style type="text/css">
/* stylesheet print */
@media print
{
	#menu
	{
		display:none;
	}

	h1
	{
    	font-size: 13pt;
    	font-weight:bold;
    	margin:4pt 0pt 0pt 0pt;
    	padding:0;
	}

	h2
	{
    	font-size: 12pt;
    	font-weight:bold;
    	margin:3pt 0pt 0pt 0pt;
    	padding:0;
	}
	h3
	{
    	font-size: 9pt;
    	font-weight:bold;
    	margin:1pt 0pt 0pt 20pt;
    	padding:0;
	}

	p,ul
	{
    	font-size: 9pt;
    	margin:1pt 0pt 8pt 40pt;
    	padding:0;
    	text-align:left;

	}

	li
	{
    	font-size: 9pt;
    	margin:0;
    	padding:0;
    	text-align:left;

	}

	table
	{
    	margin:1pt 0pt 8pt 40pt;
    	border:0px;
    	width:90%
	}

	td
	{
    	border:0px;
    	border-top:1px solid black;
    	font-size: 9pt;
	}

	.head td
	{
		border:0px;
    	font-weight:bold;
    	font-size: 9pt;
	}


}

/* stylesheet screen */
@media screen
{
    body
    {
    	margin: 0px;
    	background-color: #FFFFFF;
    	color: #000000;
    	text-align: center;
    }

    #container
    {
        text-align:center;
        margin: 10px auto;
        width: 90%;
    }

    h1
    {
    	font-family: Verdana, Helvetica, sans-serif;
    	font-weight:bold;
    	font-size: 14pt;
    	color: #000000;
        background-color:#87CEFA;
        margin:0px 0px 0px 0px;
        padding: 2px 4px 5px 4px;
        width: 100%;
        border:1px solid black;
        text-align: left;
    }

    h2
    {
        font-family: Verdana, Helvetica, sans-serif;
        font-weight:bold;
        font-size: 11pt;
        color: #000000;
        margin:5px 0px 0px 0px;
        padding:4px;
        width: 100%;
        border:1px solid black;
        background-color:#F0F8FF;
        text-align: left;
    }

    h2.green
    {
        color: #000000;
        background-color:#CCFFCC;
        border-color:#006400;
    }

    h2.red
    {
        color: #000000;
        background-color:#FFCCCC;
        border-color:#8B0000;
    }
   
    h3
    {
        font-family: Verdana, Helvetica, sans-serif;
        font-weight:bold;
        font-size: 10pt;
        color:#000000;
        background-color: #FFFFFF;
        width: 75%;
        text-align: left;
    }

    p
    {
        font-family: Verdana, Helvetica, sans-serif;
        font-size: 8pt;
        color:#000000;
        background-color: #FFFFFF;
        width: 75%;
        text-align: left;
    }

    p i
    {
        font-family: "Courier New", Courier, mono;
        font-size: 8pt;
        color:#000000;
        background-color: #CCCCCC;
    }

    ul
    {
        font-family: Verdana, Helvetica, sans-serif;
        font-size: 8pt;
        color:#000000;
        background-color: #FFFFFF;
        width: 75%;
        text-align: left;
    }

    a
    {
        font-family: Verdana, Helvetica, sans-serif;
        text-decoration: none;
        font-size: 8pt;
        color:#000000;
        font-weight:bold;
        background-color: #FFFFFF;
        color: #000000;
    }

    li a
    {
        font-family: Verdana, Helvetica, sans-serif;
        text-decoration: none;
        font-size: 10pt;
        color:#000000;
        font-weight:bold;
        background-color: #FFFFFF;
        color: #000000;
    }

    a:hover
    {
	    text-decoration: underline;
    }

    a.red
    {
        color:#8B0000;
    }
    a.green
    {
        color:#006400;
    }

    table
    {
        width: 80%;
        border:0px;
        color: #000000;
        background-color: #000000;
        margin:2px;
    }

    tr
    {
        vertical-align:top;
        font-family: Verdana, Helvetica, sans-serif;
        font-size: 8pt;
        color:#000000;
background-color:#CCFFCC;
border-color:#006400;

    }

    tr.head
    {
        background-color: #E1E1E1;
        color: #000000;
        font-weight:bold;
    }

    tr.open
    {
        background-color: #CCFFCC;
        color: #000000;
    }

    tr.filtered
    {
        background-color: #FFDDBB;
        color: #000000;
    }

    tr.closed
    {
        background-color: #FFAFAF;
        color: #000000;
    }

    th
    {
        font-family: Verdana, Helvetica, sans-serif;
        font-weight:bold;
        font-size: 11pt;
    }

    td
    {
        padding:2px;
    }
    
    .status
    {
        display:none;
    }
    
    #menu li
    {
        display         : inline;
        margin          : 0;
        /*margin-right    : 10px;*/
        padding         : 0;
        list-style-type : none;
    }    
}
</style>
	<title>Nmap Report</title>
</head>
<body>
  <div id="container">
	<h1>nmap scan report - scan @ <xsl:value-of select="$start" />
	</h1>

    <h2>scan summary</h2>
    <p>
	<xsl:value-of select="@scanner"/> was initiated at <xsl:value-of select="$start" /> with these arguments:<br/>
    <i><xsl:value-of select="@args" /></i><br/>
    The process stopped at <xsl:value-of select="$end" />.
	<xsl:choose>
        <xsl:when test="debugging/@level = '0'">Debuging was disabled, </xsl:when>
        <xsl:otherwise>Debugging was enabled, </xsl:otherwise>
    </xsl:choose>
    the verbosity level was <xsl:value-of select="verbose/@level" />.

    </p>


    <table>
      <tr><th>ftp</th><th>html</th></tr>
      <xsl:apply-templates select="host"/>
    </table>

</div>
</body>
</html>
</xsl:template>

<xsl:template match="host">
  <tr><td>
    <xsl:element name="a">
       <xsl:attribute name="class">
                	<xsl:choose>
                        <xsl:when test="status/@state = 'up'">green</xsl:when>
                        <xsl:otherwise>red</xsl:otherwise>                    
                	</xsl:choose>
                    </xsl:attribute>
      <xsl:attribute name="href">ftp://<xsl:value-of select="address/@addr"/>
      </xsl:attribute>
      <xsl:value-of select="address/@addr"/>
    </xsl:element>    
  </td>
  <td>
    <xsl:choose>
      <xsl:when test="ports/port[@portid='21']/state/@state='open'">
	<xsl:element name="a">
	  <xsl:attribute name="class">
            <xsl:choose>
              <xsl:when test="status/@state = 'up'">green</xsl:when>
              <xsl:otherwise>red</xsl:otherwise>                    
            </xsl:choose>
          </xsl:attribute>
	  <xsl:attribute name="href">http://<xsl:value-of select="address/@addr"/>
	  </xsl:attribute>
	  <xsl:value-of select="address/@addr"/>
	</xsl:element>    
      </xsl:when>
    </xsl:choose>
  </td></tr>

</xsl:template>


<!--

<xsl:template match="host">
 	<xsl:element name="a">
		<xsl:attribute name="name"><xsl:value-of select="translate(address/@addr, '.', '_') " /></xsl:attribute>
	</xsl:element>
  
    <xsl:choose>
        <xsl:when test="state/@state = 'up'">
            <h2 class="green"><xsl:value-of select="address/@addr"/>
         <span class="state">(online)</span>0
            </h2>
        </xsl:when>
        <xsl:otherwise>
            <h2 class="red"><xsl:value-of select="address/@addr"/>
            <xsl:if test="count(hostnames/hostname) > 0">
              <xsl:for-each select="hostnames/hostname">
                <xsl:sort select="@name" order="ascending" data-type="text"/>
                <xsl:text> / </xsl:text><xsl:value-of select="@name"/>
              </xsl:for-each>            
            </xsl:if>             
            <span class="status">(offline)</span></h2>
        </xsl:otherwise>
    </xsl:choose>

    <xsl:if test="count(address) > 0">    
        <table>
            <xsl:for-each select="host">
                <tr>
		  <td>Just a test</td>
			<td><xsl:if test="ports/port[0]/status/@state='open'">
				ftp
			</xsl:if></td>
			<td><xsl:if test="ports/port[1]/status/@state='open'">
				http<xsl:value-of select="@addr"/>
			</xsl:if></td>
			

		</tr>
            </xsl:for-each>
        </table>
    </xsl:if>

	<xsl:apply-templates/>
    
</xsl:template>
-->

</xsl:stylesheet>
