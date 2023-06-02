function getParameter(name) {
   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
   var r = location.search.substr(1).match(reg);
   if (r!=null) return (r[2]); return null;
}
function loadPostShow(cid,currentPage,pageSize=15,pname){

   $.get("postshow/pageQuery",{cid:cid,currentPage:currentPage,pageSize:pageSize,pname:pname},function (pb){

      $("#totalPage").html(pb.totalPage);
      $("#totalCount").html(pb.totalCount);

      alert("再次加载时，pname:"+pname);

      var lis = "";
      var fristPage = '<li onclick="loadPostShow('+cid+',1,15);alert(1);"><a href="javascript:void(0)">首页</a></li>';
      var beforeNum = pb.currentPage - 1;
      if(beforeNum <= 0){
         beforeNum = 1;
      }
      var beforePage = '<li onclick="loadPostShow('+cid+','+beforeNum+',15)"><a href="javascript:void(0)">上一页</a></li>'
      lis += fristPage;
      lis += beforePage;
      let begin; // 开始位置
      let end ; // 结束位置
      if(pb.totalPage < 10){
         //总页码不够 10 页
         begin = 1;
         end = pb.totalPage;
      }else{
         //总页码超过 10 页
         begin = pb.currentPage - 5 ;
         end = pb.currentPage + 4 ;
         36
         //2.如果前边不够 5 个，后边补齐 10 个
         if(begin < 1){
            begin = 1;
            end = begin + 9;
         }
         //3.如果后边不足 4 个，前边补齐 10 个
         if(end > pb.totalPage){
            end = pb.totalPage;
            begin = end - 9 ;
         }
      }
      for (var i = begin; i <= end ; i++) {
         var li;
         //判断当前页码是否等于 i
         if(pb.currentPage == i){
            li = '<li class="active" onclick="loadPostShow('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
         }else{
            //创建页码的 li
            li = '<li onclick="loadPostShow('+cid+','+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
         }
         //拼接字符串
         lis += li;
         var nextNum = pb.currentPage + 1;
         if(nextNum >= pb.totalPage){
            nextNum = pb.totalPage;
         }

         var lastPage = '<li onclick="loadPostShow('+cid+','+pb.totalPage+',15)" ><a href="javascript:void(0)">末页 </a></li>';
         var nextPage = '<li onclick="loadPostShow('+cid+','+nextNum+',15)" ><a href="javascript:void(0)">下一页</a></li>';
         lis += nextPage;
         lis += lastPage;
         $("#pageNum").html(lis);


         // <li className="item">
         //    <a href="#" className="pic">
         //       <img src="images/cont/waterfall_img2.jpg" alt="#">
         //    </a>
         //    <div className="waterfall-hover">
         //       <span className="mask"></span>
         //       <a href="#" className="btn-collect">收集 45</a>
         //       <a href="#" className="btn-white btn-like"></a>
         //       <a href="#" className="btn-white btn-comment"></a>
         //    </div>
         //    <div className="waterfall-info">
         //       <p className="title">title111</p>
         //       <p className="icon"><span className="icon-star">89</span><span className="icon-like">10</span></p>
         //    </div>
         //    <div className="collect-info">
         //       <a href="#" className="headImg"><img src="images/cont/waterfall_headImg1.jpeg" alt="#"></a>
         //       <p className="title"><a href="#">user</a></p>
         //
         //    </div>
         // </li>
         var Postshowlis ="";
         for (var i=0;i<pb.list.length;i++)
         {  var Post=pb.list[i];
            var img_path='post/'+Post.pid+'/'+Post.floorList[0].num+'.png';
            // alert("img_src="+Post.floorList[0].image);
            // alert('rebuild_img_src='+'post/'+Post.pid+'/'+Post.floorList[0].num+'.png');
            var li='     <li class="item">\n' +
                '            <a href="post.html?pid='+Post.pid+'" class="pic">\n' +
                // '               <img src="images/'+Post.floorList[0].image+'" alt="#">\n' +
                '               <img src="images/'+img_path+'" alt="#">\n' +
                '            </a>\n' +
                '            <div class="waterfall-hover">\n' +
                '               <span class="mask"></span>\n' +
                '               <a href="#" class="btn-collect">收集 45</a>\n' +
                '               <a href="#" class="btn-white btn-like"></a>\n' +
                '               <a href="#" class="btn-white btn-comment"></a>\n' +
                '            </div>\n' +
                '            <div class="waterfall-info">\n' +
                '               <p class="title">'+Post.title+'</p>\n' +
                '               <p class="icon"><span class="icon-star">'+Post.favornum+'</span><span class="icon-like">'+Post.collectnum+'</span></p>\n' +
                '            </div>\n' +
                '            <div class="collect-info">\n' +
                '               <a href="#" class="headImg"><img src="images/'+Post.user.image+'" alt="#"></a>\n' +
                '               <p class="title"><a href="#">'+Post.user.name+'</a></p>\n' +
                '\n' +
                '            </div>\n' +
                '         </li>'

            Postshowlis+=li;
            $("#postShowul").html(Postshowlis);


         }




      }
   })
}
$(function (){

   var cid=getParameter("cid");
   var pname=getParameter("pname");
   pname=decodeURI(pname);
   alert("页面开始时，pname："+pname)
   if (!pname)
   {
      pname="";
      alert("load:");
   }
   // alert("load:"+cid+" "+pname+"！！");

loadPostShow(cid,1,15,pname);

   var headlis="   <li role=\"presentation\" ><a href=\"postshow.html?cid=0&pname="+pname+"\">全部</a></li>\n" +
       "        <li role=\"presentation\"><a href=\"postshow.html?cid=1&pname="+pname+"\" >绘画接力</a></li>\n" +
       "        <li role=\"presentation\"><a href=\"postshow.html?cid=2&pname="+pname+"\">脑洞向</a></li>\n" +
       "        <li role=\"presentation\"><a href=\"postshow.html?cid=3&pname="+pname+"\">视觉向</a></li>\n" +
       "        <li role=\"presentation\"><a href=\"postshow.html?cid=4&pname="+pname+"\">搞笑向</a></li>"

   $("#classify ").html(headlis);
   var num =Number(cid)+1;
   $("#classify li:nth-child("+num+")").addClass("active");


});
