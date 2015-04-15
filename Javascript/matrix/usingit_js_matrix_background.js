/*---------------------------------------------------------------------------

JavaScript Matrix Background for Web Designers Toolkit
(C)1999-2003 USINGIT.COM, All Rights Reserved.
To get more free and professional scripts, visit:
http://www.usingit.com/
http://www.usingit.com/products/webtoolkit/index.html
email: support@usingit.com

You may use this script for free if you don't alert the copyright info.

---------------------------------------------------------------------------*/

var jsMatrixBackground_DOM=(document.getElementById)?true:false;
var jsMatrixBackground_NS4=(document.layers)?true:false;
var jsMatrixBackground_IE4=(document.all&&!jsMatrixBackground_DOM)?true:false;
var jsMatrixBackground_IE5=(document.all&&jsMatrixBackground_DOM)?true:false;
var jsMatrixBackground_NS6=(jsMatrixBackground_DOM&&navigator.appName.indexOf("Netscape")>=0)?true:false;
var jsMatrixBackground_arId=new Array();
var jsMatrixBackground_winW=0;
var jsMatrixBackground_winH=0;
var jsMatrixBackground_colH=0;
var jsMatrixBackground_hT='';
var jsMatrixBackground_cnt=0;
var jsMatrixBackground_arImgId=new Array();
var jsMatrixBackground_arImg=new Array();
var jsMatrixBackground_imgWH=15;
var jsMatrixBackground_arImgD=['m1.jpg','m2.jpg','m3.jpg','m4.jpg','m5.jpg','m6.jpg','m7.jpg','m8.jpg','m9.jpg','m10.jpg']; 
var jsMatrixBackground_arImgB=['m1b.jpg','m2b.jpg','m3b.jpg','m4b.jpg','m5b.jpg','m6b.jpg','m7b.jpg','m8b.jpg','m9b.jpg','m10b.jpg']; 

for(i=0;i<jsMatrixBackground_ImageCount;i++){
jsMatrixBackground_arImgId[i]=Math.floor(Math.random()*jsMatrixBackground_arImgD.length);
jsMatrixBackground_hT+=(jsMatrixBackground_NS4)?'<layer name="jsMatrixBackgroundImage'+i+'" top="-'+jsMatrixBackground_imgWH+'" left="0" width="'+jsMatrixBackground_imgWH+'" height="'+jsMatrixBackground_imgWH+'" z-index="1">':'<div id="jsMatrixBackgroundImage'+i+'" style="position:absolute;top:-'+jsMatrixBackground_imgWH+'px;left:0px;width:'+jsMatrixBackground_imgWH+'px;height:'+jsMatrixBackground_imgWH+'px;z-index:'+((jsMatrixBackground_DOM&&(!document.all))?'1':'-1000')+'">';
jsMatrixBackground_hT+='<img src="'+jsMatrixBackground_arImgD[jsMatrixBackground_arImgId[i]]+'" width='+jsMatrixBackground_imgWH+' height='+jsMatrixBackground_imgWH+' name="mimg'+i+'">';
jsMatrixBackground_hT+=(jsMatrixBackground_NS4)?'</layer>':'</div>';
}
document.write(jsMatrixBackground_hT);

for(i=0;i<jsMatrixBackground_arImgD.length;i++){
jsMatrixBackground_arImg[i]=new Image();
jsMatrixBackground_arImg[i].src=jsMatrixBackground_arImgB[i];
}

window.onload=jsMatrixBackground_Init;
window.onresize=jsMatrixBackground_GetWinSize;

function jsMatrixBackground_Init(){
jsMatrixBackground_GetWinSize();
for(i=0;i<jsMatrixBackground_ImageCount;i++){
jsMatrixBackground_arId[i]=(jsMatrixBackground_NS4)?document.layers['jsMatrixBackgroundImage'+i]:(jsMatrixBackground_IE4)?document.all['jsMatrixBackgroundImage'+i]:document.getElementById('jsMatrixBackgroundImage'+i);
jsMatrixBackground_arId[i].jsMatrixBackground_arImgId=jsMatrixBackground_arImgId[i];
}
jsMatrixBackground_arImgId='';
jsMatrixBackground_DropAllImgs();
}

function jsMatrixBackground_Br(index,b){
if(jsMatrixBackground_NS4)jsMatrixBackground_arId[index].document.images['mimg'+index].src=(b)?jsMatrixBackground_arImgB[jsMatrixBackground_arId[index].jsMatrixBackground_arImgId]:jsMatrixBackground_arImgD[jsMatrixBackground_arId[index].jsMatrixBackground_arImgId];
else document.images['mimg'+index].src=(b)?jsMatrixBackground_arImgB[jsMatrixBackground_arId[index].jsMatrixBackground_arImgId]:jsMatrixBackground_arImgD[jsMatrixBackground_arId[index].jsMatrixBackground_arImgId];
}

function jsMatrixBackground_DropImg(index,x,y){
var id=jsMatrixBackground_arId[index];
if(jsMatrixBackground_NS4)id.moveTo(x,y);
else{
id.style.left=x+'px';
id.style.top=y+'px';
}
jsMatrixBackground_Br(index,true);
setTimeout('jsMatrixBackground_Br('+index+',false)',jsMatrixBackground_DropDownSpeed);
}

function jsMatrixBackground_GetWinSize(){
jsMatrixBackground_winW=(jsMatrixBackground_IE4||jsMatrixBackground_IE5)?document.body.clientWidth:window.innerWidth;
jsMatrixBackground_winH=(jsMatrixBackground_IE4||jsMatrixBackground_IE5)?document.body.clientHeight:window.innerHeight;
jsMatrixBackground_colH=Math.min(Math.floor(jsMatrixBackground_winH/1.33/jsMatrixBackground_imgWH)-1,30);
}

function jsMatrixBackground_DropAllImgs(){
var lx=(jsMatrixBackground_IE4||jsMatrixBackground_IE5)?document.body.scrollLeft:window.pageXOffset;
var ly=(jsMatrixBackground_IE4||jsMatrixBackground_IE5)?document.body.scrollTop:window.pageYOffset;
var px=Math.floor(Math.random()*jsMatrixBackground_winW/(jsMatrixBackground_imgWH+1))*jsMatrixBackground_imgWH+lx;
var py=Math.floor(Math.random()*(jsMatrixBackground_winH-(jsMatrixBackground_colH*jsMatrixBackground_imgWH)))+ly;
for(i=0;i<jsMatrixBackground_colH;i++){
setTimeout('jsMatrixBackground_DropImg('+jsMatrixBackground_cnt+','+px+','+(py+jsMatrixBackground_imgWH*i)+')',jsMatrixBackground_DropDownSpeed*i);
jsMatrixBackground_cnt=((jsMatrixBackground_cnt+jsMatrixBackground_colH)>jsMatrixBackground_ImageCount)?0:jsMatrixBackground_cnt+1;
}
setTimeout('jsMatrixBackground_DropAllImgs()', jsMatrixBackground_DropDownSpeed*jsMatrixBackground_colH/2);
}