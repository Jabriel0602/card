(function(){
    var Silder=function(a){
        a=a||{};
        var opt={
            id:a.id||"silder",//banner鐨刬d
            palyTime:a.palyTime||4000,//鎾斁鐨勬椂闂�
            delayTime:a.delayTime|| 500,//绉诲姩鐨勬椂闂�
            titCell:a.titCell||"li",//鍖呭惈鍥剧墖鐨勫璞�
            creObj:a.creObj||"div",
            creObjClassN:a.creObjClassN||"slider-dots",
            creObjC:a.creObjC||"span",
            creObjCOn:a.creObjCOn||"on",
            index:a.index||1,//浠庣鍑犱釜寮€濮嬫挱鏀�
            titCellPadding:parseInt(a.titCellPadding,10)||0,
            titCellMargin:parseInt(a.titCellMargin,10)||0,
            scrollFag:a.scrollFag||false
        }
        if(document.getElementById(opt.id)){} else {return}
        var a1=a2=a3=a4=0;
        opt.play=a.play==false?false:true;
        var obj=typeof opt.id=="string"?document.getElementById(opt.id.replace("#","")):(opt.id  instanceof jQuery?opt.id[0]:opt.id);
        var width=obj.offsetWidth;
        var arrLi=obj.querySelectorAll(opt.titCell);
        var _ul=arrLi[0].parentNode;
        var maxL=arrLi.length;
        var tx=0,ty=0,x=0,y=0;
        var index=opt.index;
        var speen=opt.palyTime;
        var clearTime=null;
        var arrS=[];//瀹氫箟瀛樻寜閽綅缃殑鏁扮粍
        var init=function(){
            if(opt.titCellMargin){_ul.style.marginLeft=opt.titCellMargin+"px";}
            if(maxL<=1){
                _ul.style.cssText+='display:table;width:'+(width-opt.titCellMargin*2)*maxL+'px; overflow:hidden;';
                if(maxL==1){
                    arrLi[0].style.cssText="display:table-cell;vertical-align:top;width:"+(width-opt.titCellMargin*2)+"px";
                }
                return false;
            }
            _ul.insertBefore(arrLi[maxL-1].cloneNode(true),arrLi[0]);
            var endLi=arrLi[0].cloneNode(true);
            _ul.appendChild(endLi);
            arrLi=obj.querySelectorAll(opt.titCell);
            maxL=arrLi.length;
            _ul.style.cssText+='display:table;width:'+(width-opt.titCellMargin*2)*maxL+'px; overflow:hidden;';
            Transform(-(index*(width-opt.titCellMargin*2)));
            var div=document.createElement(opt.creObj);
            div.className=opt.creObjClassN;
            obj.appendChild(div);

            for(var i=0;i<maxL;i++){
                arrLi[i].style.cssText="display:table-cell;vertical-align:top;width:"+(width-opt.titCellMargin*2)+"px";
                var s=document.createElement(opt.creObjC);
                if(i===0||i===maxL-1){s.style.display="none"}
                if(i==index)s.className=opt.creObjCOn;
                s.index=i;
                div.appendChild(s);
                arrS.push(s);
                s.addEventListener("click",function(){
                    if(this.index!=index){
                        arrS[index].className="";
                        this.className=opt.creObjCOn;
                        index=this.index;
                        _ul.style.webkitTransitionDuration=_ul.style.MozTransitionDuration =_ul.style.msTransitionDuration =_ul.style.OTransitionDuration =_ul.style.transitionDuration=opt.delayTime+'ms';
                        Transform(-(index*(width-opt.titCellMargin*2)));
                    }
                },false);
            }
        }
        var clearFn=function(){
            if(clearTime){clearTimeout(clearTime);clearTime=null;}
        }
        var setFan=function(){
            if(clearTime){clearTimeout(clearTime);clearTime=null;}
            if(opt.play){clearTime=setTimeout(intval,speen);clearTime=null;}
        }
        window.addEventListener("resize",function(){
            var _width=obj.offsetWidth;
            opt.titCellMargin=Math.floor(opt.titCellMargin*width/_width);
            width=_width;
            _ul.style.marginLeft=opt.titCellMargin+"px";
            _ul.style.width=(width-opt.titCellMargin*2)*maxL+"px";
            for(var i=0;i<arrLi.length;i++){
                arrLi[i].style
                arrLi[i].style.width=(width-opt.titCellMargin*2)+"px";
            }
        },false);
        var intval=function(){
            for(var i=0;i<maxL;i++){arrS[i].className="";}//鍘绘帀褰撳墠鏁堟灉
            index++;
            if(index>=maxL)index=0;
            _ul.style.webkitTransitionDuration=_ul.style.MozTransitionDuration =_ul.style.msTransitionDuration =_ul.style.OTransitionDuration =_ul.style.transitionDuration=opt.delayTime+'ms';
            Transform(-(index*(width-opt.titCellMargin*2)));
            if(clearTime){clearTimeout(clearTime);clearTime=null;}
            if(index===0){
                arrS[maxL-2].className=opt.creObjCOn;
                topEnd();
            }else if(index===maxL-1){
                arrS[1].className=opt.creObjCOn;
                topEnd();
            }else{
                arrS[index].className=opt.creObjCOn;
            }

            if(opt.play){clearTime=setTimeout(intval,speen);}
        }
        var topEnd=function(){
            var d=setTimeout(function(){
                _ul.style.webkitTransitionDuration=_ul.style.MozTransitionDuration =_ul.style.msTransitionDuration =_ul.style.OTransitionDuration =_ul.style.transitionDuration=0+'ms';
                if(index===0){
                    index=maxL-2;
                }else if(index===maxL-1){
                    index=1;
                }
                Transform(-(index*(width-opt.titCellMargin*2)));
                clearTimeout(d); d=null;
            },opt.delayTime);
        }
        var _start=function(e){//瑙︽懜寮€濮�
            if(clearTime){clearFn()}

            x=tx=ty=y=0;
            tx=e.touches[0].pageX;ty=e.touches[0].pageY;
            _ul.style.webkitTransitionDuration=_ul.style.MozTransitionDuration =_ul.style.msTransitionDuration =_ul.style.OTransitionDuration =_ul.style.transitionDuration='0ms';
            _ul.addEventListener("touchmove",_startMove,false);
            _ul.addEventListener("touchend",_startEnd,false);
        }
        var _startMove=function(e){//瑙︽懜绉诲姩涓�
            if(clearTime){clearFn()}
            x=e.touches[0].pageX-tx,y=e.touches[0].pageY-ty;
            e.preventDefault();
            var w=index==0?x:-(index*(width-opt.titCellMargin*2)-x);
            Transform(w);
            if(opt.scrollFag)document.body.scrollTop-=y;
        }
        var _startEnd=function(e){//瑙︽懜缁撴潫
            arrS[index].className="";
            if((width-opt.titCellMargin*2)/4<Math.abs(x)){

                if(x>0){
                    if(index!=0){index--};
                }else if(x<0){
                    if(index!=maxL-1){index++};
                }

            }

            x=tx=0;
            _ul.style.webkitTransitionDuration=_ul.style.MozTransitionDuration =_ul.style.msTransitionDuration =_ul.style.OTransitionDuration =_ul.style.transitionDuration=opt.delayTime+'ms';
            Transform(-(index*(width-opt.titCellMargin*2)));
            for(var i=0;i<maxL;i++){arrS[i].className="";}//鍘绘帀褰撳墠鏁堟灉
            if(index===0){
                arrS[maxL-2].className=opt.creObjCOn;
                topEnd();
            }else if(index===maxL-1){
                arrS[1].className=opt.creObjCOn;
                topEnd();
            }else{
                arrS[index].className=opt.creObjCOn;
            }

            if(opt.play)clearTime=setTimeout(intval,speen);
            _ul.removeEventListener("touchmove",_startMove,false);
            _ul.removeEventListener("touchend",_startEnd,false);
        }
        var Transform=function(w){
            _ul.style.webkitTransform = 'translate('+w+'px,0)' + 'translateZ(0)';
            _ul.style.msTransform =_ul.style.MozTransform = _ul.style.OTransform = 'translateX(' + w + 'px)';
        }

        init();
        if(maxL>1){//褰撴暟閲忎笉澶т簬涓€鏃讹紝璇ョ▼搴忎笉鎵ц
            if(opt.play)clearTime=setTimeout(intval,speen);
            _ul.addEventListener("touchstart",_start,false);
        }
    }
    window.Silder=Silder;
}());