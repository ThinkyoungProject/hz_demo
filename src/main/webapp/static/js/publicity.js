//参与信息 上拉加载更多
var myHuzhuScroll,myHuzhupullDownEl, myHuzhupullDownOffset,myHuzhupullUpEl, myHuzhupullUpOffset,myHuzhugeneratedCount = 0;  
var projectInfoScroll,projectInfopullDownEl, projectInfopullDownOffset,projectInfopullUpEl, projectInfopullUpOffset,projectInfogeneratedCount = 0;
//仅在进入页面时得出我的互助，切换后不重新获取，在切换时给重新赋值了
var currentTab = $.trim($(".weui-navbar__item.weui-bar__item_on").text());
//console.log(currentTab);
var url;
if(currentTab == "参与信息"){
	url = "wodehuzhu";
}else{
	url = "xiangmuxinxi";
}
/*navbar 切换*/
$('.weui-navbar__item').on('click', function () {
	var nodeName = $(this).text();
//	console.log(nodeName)
	if($.trim(nodeName) == "参与信息"){
		currentTab = "参与信息";
		url = "wodehuzhu";
		//切换至参与信息tab下面
//		console.log("参与信息");
		$("#myHuzhu").show();
		$("#project_info").hide();
	//	myHuzhupullDownAction();
		document.addEventListener('DOMContentLoaded', function () { setTimeout(myHuzhuloaded, 200); }, false);  
	}else{
		currentTab = "申请帮助";
		url = "xiangmuxinxi";
		//切换至申请帮助tab下面
//		console.log("申请帮助");
		$("#myHuzhu").hide();
		$("#project_info").show();
	//	myApplypullDownAction();
		document.addEventListener('DOMContentLoaded', function () { setTimeout(projectInfoloaded, 200); }, false);  
	}
	myHuzhugeneratedCount = 0 ; 
	projectInfogeneratedCount = 0;
    $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
});

  

function myHuzhuloaded() {
    //动画部分  
    myHuzhupullDownEl = document.getElementById('myHuzhupullDown');  
    myHuzhupullDownOffset = myHuzhupullDownEl.offsetHeight;  
    myHuzhupullUpEl = document.getElementById('myHuzhupullUp');     
    myHuzhupullUpOffset = myHuzhupullUpEl.offsetHeight;  
    myHuzhuScroll = new iScroll('myHuzhu', {  
        useTransition: true,  
        topOffset: myHuzhupullDownOffset,  
        onRefresh: function () {  
            if (myHuzhupullDownEl.className.match('loading')) {  
                myHuzhupullDownEl.className = '';  
                myHuzhupullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新';  
            } else if (myHuzhupullUpEl.className.match('loading')) {  
                myHuzhupullUpEl.className = '';  
                myHuzhupullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';  
            }  
        },  
        onScrollMove: function () {  
          
            if (this.y > 5 && !myHuzhupullDownEl.className.match('flip')) {  
                myHuzhupullDownEl.className = 'flip';  
                myHuzhupullDownEl.querySelector('.pullDownLabel').innerHTML = '释放刷新';  
                this.minScrollY = 0;  
            } else if (this.y < 5 && myHuzhupullDownEl.className.match('flip')) {  
                myHuzhupullDownEl.className = '';  
                myHuzhupullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';  
                this.minScrollY = -myHuzhupullDownOffset;  
            } else if (this.y < (this.maxScrollY - 5) && !myHuzhupullUpEl.className.match('flip')) {  
                myHuzhupullUpEl.className = 'flip';  
                myHuzhupullUpEl.querySelector('.pullUpLabel').innerHTML = '释放刷新';  
                this.maxScrollY = this.maxScrollY;  
            } else if (this.y > (this.maxScrollY + 5) && myHuzhupullUpEl.className.match('flip')) {  
                myHuzhupullUpEl.className = '';  
                myHuzhupullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';  
                this.maxScrollY = myHuzhupullUpOffset;  
            }  
        },  
        onScrollEnd: function () {  
            if (myHuzhupullDownEl.className.match('flip')) {  
                myHuzhupullDownEl.className = 'loading';  
                myHuzhupullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中';                 
                myHuzhupullDownAction();   // Execute custom function (ajax call?)  
            } else if (myHuzhupullUpEl.className.match('flip')) {  
                myHuzhupullUpEl.className = 'loading';  
                myHuzhupullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中';                 
                myHuzhupullUpAction(); // Execute custom function (ajax call?)  
            }  
        }  
    });  
    loadAction();  
}  

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);//阻止冒泡  
document.addEventListener('DOMContentLoaded', function () { setTimeout(myHuzhuloaded, 200); }, false);  

function projectInfoloaded() {
    //动画部分  
    projectInfopullDownEl = document.getElementById('projectInfopullDown');  
    projectInfopullDownOffset = projectInfopullDownEl.offsetHeight;  
    projectInfopullUpEl = document.getElementById('projectInfopullUp');     
    projectInfopullUpOffset = projectInfopullUpEl.offsetHeight;  
    projectInfoScroll = new iScroll('project_info', {  
        useTransition: true,  
        topOffset: projectInfopullDownOffset,  
        onRefresh: function () {  
            if (projectInfopullDownEl.className.match('loading')) {  
                projectInfopullDownEl.className = '';  
                projectInfopullDownEl.querySelector('.pullDownLabel').innerHTML = '下拉刷新';  
            } else if (projectInfopullUpEl.className.match('loading')) {  
                projectInfopullUpEl.className = '';  
                projectInfopullUpEl.querySelector('.pullUpLabel').innerHTML = '上拉加载更多';  
            }  
        },  
        onScrollMove: function () {  
          
            if (this.y > 5 && !projectInfopullDownEl.className.match('flip')) {  
                projectInfopullDownEl.className = 'flip';  
                projectInfopullDownEl.querySelector('.pullDownLabel').innerHTML = '释放刷新';  
                this.minScrollY = 0;  
            } else if (this.y < 5 && projectInfopullDownEl.className.match('flip')) {  
                projectInfopullDownEl.className = '';  
                projectInfopullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';  
                this.minScrollY = -projectInfopullDownOffset;  
            } else if (this.y < (this.maxScrollY - 5) && !projectInfopullUpEl.className.match('flip')) {  
                projectInfopullUpEl.className = 'flip';  
                projectInfopullUpEl.querySelector('.pullUpLabel').innerHTML = '释放刷新';  
                this.maxScrollY = this.maxScrollY;  
            } else if (this.y > (this.maxScrollY + 5) && projectInfopullUpEl.className.match('flip')) {  
                projectInfopullUpEl.className = '';  
                projectInfopullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';  
                this.maxScrollY = projectInfopullUpOffset;  
            }  
        },  
        onScrollEnd: function () {  
            if (projectInfopullDownEl.className.match('flip')) {  
                projectInfopullDownEl.className = 'loading';  
                projectInfopullDownEl.querySelector('.pullDownLabel').innerHTML = '加载中';                 
                myHuzhupullDownAction();   // Execute custom function (ajax call?)  
            } else if (projectInfopullUpEl.className.match('flip')) {  
                projectInfopullUpEl.className = 'loading';  
                projectInfopullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中';                 
                myHuzhupullUpAction(); // Execute custom function (ajax call?)  
            }  
        }  
    });  
    loadAction();  
}  


//初始状态，加载数据 ，请求第一页的数据  
function loadAction(){  
    var el, li;
    if(currentTab == "参与信息"){
    	el = document.getElementById('myHuzhuList');  
    }else{
    	el = document.getElementById('projectInfoList');  
    }
    
   /* for (i=0; i<10; i++) {  
        li = document.createElement('li');  
        li.innerText = '初始数据--' + (++myHuzhugeneratedCount);  
        el.appendChild(li, el.childNodes[0]);  
    }  */
   // myHuzhuScroll.refresh();  
}  
  
//下拉刷新当前数据  
function myHuzhupullDownAction () { 
	$("#myHuzhuList li").remove();
    setTimeout(function () {  
        //这里执行刷新操作
    	window.location.reload();
    }, 600);  
}  

//下拉刷新申请帮助当前数据  
function myApplypullDownAction () { 
	$("#myHuzhuList li").remove();
    setTimeout(function () {  
        //这里执行刷新操作
    	window.location.reload();
    }, 400);  
}  
  
//上拉加载更多数据  
function myHuzhupullUpAction () {  
    setTimeout(function () {  
    	
//      var el, li;  
//      el = document.getElementById('myHuzhuList');  
//      for (i=0; i<10; i++) {  
//          li = document.createElement('li');  
//          li.innerText = '上拉加载--' + (++myHuzhugeneratedCount);  
//          el.appendChild(li, el.childNodes[0]);  
//      }  
    //    myHuzhuScroll.refresh();  
    }, 400);  
}  


/*function tabHelp(obj) {
	if (obj == '1') {
		$("#myHuzhu").show();
		$("#project_info").hide();
	}
	if (obj == '2') {
		$("#myHuzhu").hide();
		$("#project_info").show();
	}
}*/

// 申请帮助
function helpInfo(obj) {
	if (obj != '') {
		$.ajax({
			type : "post",
			url : project.ctxPath+"/applayHelp.do",
			dataType : "json",
			async : false,
			data:{"id":obj},
			beforeSend : function() {
				//$("#loading").addClass("show");//展示loading框
			},
			complete : function() {
				//global.loadingEnd();
			},
			success : function(data) {
				if (data > 0) {
					openMsg("申请帮助成功");
				} else {
					openMsg("申请帮助失败");
				}
			}
		});
	}
}

function openMsg(ms) {
	$("#msgInfo").show();
	$("#errorInfo").html(ms);
}

function colseMcg() {
	$("#msgInfo").hide();
	window.location.reload();
}

