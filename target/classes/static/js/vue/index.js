if(sessionStorage.getItem("checkpost")!=null){

  var item = sessionStorage.getItem("checkpost");
  var post = JSON.parse(item);
  var pid = post[0].id
  var IndexUrl = post[0].indexImg
  var title = post[0].title
  var EditorTXT = post[0].content
  var newType = post[0].type
  var published = post[0].published
  var tag = post[0].tag
  var user = post[0].user

  // console.log(post)  
}else{
  var IndexUrl = '../../images/main-bg1.jpg'
}
if(sessionStorage.getItem("checkplan")!=null){

  var item = sessionStorage.getItem("checkplan");
  var plan = JSON.parse(item);
  var id = plan.id
  var title = plan.plan_title
  var startDate = plan.startDate
  var endDate = plan.endDate

  // console.log(plan)  
}
// if(sessionStorage.getItem("user")!=null){

//   var item = sessionStorage.getItem("user");
//   var user = JSON.parse(item);
//   var id = user.uid
//   var avatar = user.avatar
//   var uname = user.uname

//   // console.log(plan)  
// }
var Main = {
    data(){
        return{
            log:'',
            pid:'',
            zanN:'',
            list:[],
            Post:[],
            type:[],
            PComment:[],
            totalPC:'',
            Comment:[],
            imageUrl: IndexUrl,
            selectType:[],
            user:{
              uid:'',
              avatar:'',
              username: '',
              password: '',
              tel: '',
              email: '',				
            },
            liked:false,
            form: {
              indexUrl:'',
              title: '',
              content:'',
              type:[],
              tag:false,
              published: true,
            },
            updateform:{
              indexUrl:'',
              title: title,
              content:'',
              type:newType,
              tag:false,
              published: true,
            },
            oldEditorTXT:EditorTXT,
            plan:[{
              plan_title:'',
              startDate:'',
              endDate:'',
              createDate:'',
              updateDate:''
            }],
            addplan:{
              plan_title:'',
              startDate:'',
              endDate:'',
              createDate:'',
              updateDate:''
            },
            updateplan:{
              plan_title:title,
              startDate:startDate,
              endDate:endDate,
              createDate:'',
              updateDate:''
            },
            
            rules: {
              title: [
                { required: true, message: '请输入文章标题', trigger: 'blur' },
                { min: 5, message: '长度在 5 个字符以上', trigger: 'blur' }
              ],
              type: [
                { required: true, message: '请选择类型', trigger: 'change' }
              ]
            },
            dialogImageUrl: '',
            dialogVisible: false,
            disabled: false,
            JingPost:[],
            ownerPost:[],
            Jtotal:0,
            JcurrentPage:1,
            JpageSize:4,
            search: '',
            PCcurrentPage:1,
            PCpageSize:5,
            PCtotal:0,
            ws: null,
            count: 0,
            userId: id, // 当前用户ID
            username: uname, // 当前用户昵称
            avatar: avatar, // 当前用户头像
            listmsg: [], // 聊天记录的数组
            contentText: "" // input输入的值
        }; 
    },
    updated () {
      var el = this.$refs["msg-box"];
    },
    created () {
        if(localStorage.getItem("uname")!=null){
        this.log = localStorage.getItem("uname");
        $("#log").replaceWith("<li id='log'><el-row >"+
        "<el-col :span='12'>"+
        "<el-avatar src="+this.user.avatar+">"+
          "</el-avatar>"+
          "<el-dropdown>"+
            "<span class='el-dropdown-link'>"+this.log+
              "<i class='el-icon-arrow-down el-icon--right'></i>"+
            "</span>"+
            "<el-dropdown-menu slot='dropdown'>"+
              "<el-dropdown-item id='addPlan' icon='el-icon-finished'>制订计划</el-dropdown-item>"+
              "<el-dropdown-item id='addPost' icon='el-icon-plus'>分享攻略</el-dropdown-item>"+
              "<el-dropdown-item id='ownerPost' icon='el-icon-user-solid'>我的攻略</el-dropdown-item>"+   
              "<el-dropdown-item id='removeLogin' icon='el-icon-back'>注销</el-dropdown-item>"+
            "</el-dropdown-menu>"+
          "</el-dropdown>"+
        "</el-col></li>");
        
        }else{
            window.location.href="../../index.html";
        }
        function getCookie(objName) {//获取指定名称的cookie的值
          var arrStr = document.cookie.split("; ");
          for (var i = 0; i < arrStr.length; i++) {
              var temp = arrStr[i].split("=");
              if (temp[0] == objName) return unescape(temp[1]);  //解码
          }
          return "";
        }
        //获取用户信息
        var data={value:this.log}
        axios.post("http://localhost:8080/user/selectUserByName",data)
        .then(res => {
          this.user = res.data[0]
          // sessionStorage.setItem("user",JSON.stringify(this.user));
          console.log("selectUserByName success");
        })
        .catch(err => {
          console.error(err); 
        })
       //获取当前用户所有攻略
       
       var uid = sessionStorage.getItem("uid");
       var data2 = {uid:uid}
       axios.post("http://localhost:8080/getPostByuserId",data2)
       .then(res => {
         this.ownerPost = res.data
         console.log("getPostByuserId success")
       })
       .catch(err => {
         console.error(err); 
       })
       //获取当前用户所有计划
       var data3 = {uid:uid}
       axios.post("http://localhost:8080/getPlanByUid",data3)
       .then(res => {
         this.plan = res.data
         console.log("getPlanByuserId success")
       })
       .catch(err => {
         console.error(err); 
       })

        //获取最新的5篇精品文章
        axios.get("http://localhost:8080/getJingPost")
        .then(res => {
              this.JingPost = res.data
              this.Jtotal = this.JingPost.length
              console.log("getJingPost success!")
        })
        .catch(err => {
          console.error(err); 
        })
        //获取最新的5篇文章
        axios.get("http://localhost:8080/getPost")
        .then(res => {
            this.Post = res.data
            console.log("getPost success!")
        })
        .catch(err => {
          console.error(err); 
        })
        //当前文章
        if(getCookie("postId")!=null){ 
        var pid = getCookie("postId")
        let data = {id:pid};
        axios.post("http://localhost:8080/getPostById",data)
        .then(res => {
          this.list = res.data
          console.log("getPostById success!")
        })
        .catch(err => {
        return null;
        })
        //当前文章的父级评论
        if(pid!=null){
        var data4={pid:pid}
        axios.post("http://localhost:8080/getCommentByPidPC",data4)
        .then(res => {
          this.PComment = res.data;
          this.totalPC = this.PComment.length;
          this.PCtotal = this.PComment.length;
        })
        .catch(err => {
          return null;
        })
        }
        //获取类型
        axios.get('http://localhost:8080/getType')
        .then(res => {
            this.type = res.data
            console.log("getType success")
        })
        .catch(err => {
            console.error(err); 
        })
        
      }
    },
    methods: {
      say:function(id) {
        //存cookie72小时
        function addCookie(objName, objValue, objHours) {
          var str = objName + "=" + escape(objValue); //编码
          if (objHours > 0) {//为0时不设定过期时间，浏览器关闭时cookie自动消失
              var date = new Date();
              var ms = objHours * 3600 * 1000;
              date.setTime(date.getTime() + ms);
              str += "; expires=" + date.toGMTString();
          }
          document.cookie = str;
        }
        //获取点击对象      
        addCookie("postId",id,72)
      },
      handleJCurrentChange(val){
        this.JcurrentPage = val;
      },
      handlePCurrentChange(val){
        this.PCcurrentPage = val;
      },
      //攻略点赞功能
      add_like(){
          // if (!this.liked)
            this.list[0].zanN++;
          // else
          //   this.list[0].zanN--;
          var data={id:this.list[0].id,zanN:this.list[0].zanN}
          console.log(this.list)
          console.log(data);
            axios.post("http://localhost:8080/updateZanN",data)
            .then(res => {
              console.log(res)
            })
            .catch(err => {
              console.error(err); 
            })
          // this.liked=!this.liked;
      },
      //发表评论
      submitPC(){
        var concent = editor.txt.html();
        var data = {concent:concent,user:this.user,post:this.list}
        console.log(data);
        axios.post("http://localhost:8080/insertComment",data)
        .then(res => {
          this.$message({
            message: '发表成功',
            type: 'success'
          });
          setTimeout( ()=> {
            window.location.href = 'single.html';
          },1000)

        })
        .catch(err => {
          console.error(err); 
        })
      },
      //评论点赞功能
      add_likePC(i){
        // if (!this.liked)
        this.PComment[i].zanN++;
        // else
        // this.list[0].zanN--;
        var data={id:this.PComment[i].cid,zanN:this.PComment[i].zanN}
          axios.post("http://localhost:8080/updatePCZanN",data)
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.error(err); 
          })
        // this.liked=!this.liked;
    },
      //新建攻略
      selectTypeByNM(){
        var data = {tname:this.form.type}
        console.log(data);
        axios.post("http://localhost:8080/getTypeByNM",data)
        .then(res => {
          sessionStorage.setItem("selectType",JSON.stringify(res.data));
        })
        .catch(err => {
          console.error(err); 
        })
      },
      submitForm(formName) {
        this.form.content = editor.txt.html();
        var selectType = sessionStorage.getItem('selectType')
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.form.content!='<p><br></p>'){
              this.form.indexUrl = this.imageUrl
              var type = JSON.parse(selectType)
              //console.log(type);
              var data = {title:this.form.title,content:this.form.content,indexImg:this.form.indexUrl,type:type,published:this.form.published,tag:this.form.tag,user:this.user}
              //console.log(data)
              axios.post("http://localhost:8080/addPost",data)
              .then(res => {
                  this.$message({
                    message: '创建成功，即将返回我的攻略',
                    type: 'success'
                  });
                  setTimeout( ()=> {
                      sessionStorage.removeItem("selectType");
                      window.location.href = 'page.html';
                  },1000)
                  // console.log(res)
              })
              .catch(err => {
                  this.$message.error('创建失败，请联系网站管理员!');
                  console.error(err); 
              })
            }else{
              this.$message.error('攻略不能为空!');
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //修改攻略
      updateForm(formName){
        this.updateform.content = editor.txt.html();
        var selectType = sessionStorage.getItem('selectType')
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if(this.form.content!='<p><br></p>'){
              this.updateform.indexUrl = this.imageUrl
              var type = JSON.parse(selectType)
              if(type==null){
                  type = this.updateform.type[0]
              }
              //console.log(type);
              var data = {id:pid,title:this.updateform.title,content:this.updateform.content,indexImg:this.updateform.indexUrl,type:type,published:this.updateform.published,tag:this.updateform.tag,user:this.user}
              //console.log(data)
              axios.post("http://localhost:8080/updatePost",data)
              .then(res => {
                  this.$message({
                    message: '修改成功，即将返回我的攻略',
                    type: 'success'
                  });
                  setTimeout( ()=> {
                      sessionStorage.removeItem("selectType");
                      window.location.href = 'page.html';
                  },1000)
                  // console.log(res)
              })
              .catch(err => {
                  this.$message.error('修改失败，请联系网站管理员!');
                  console.error(err); 
              })
            }else{
              this.$message.error('攻略不能为空!');
            }
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //新建计划
      addPlan(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
              var data = {title:this.addplan.plan_title,startDate:this.addplan.startDate,endDate:this.addplan.endDate,user:this.user}
              //console.log(data)
              axios.post("http://localhost:8080/insertPlan",data)
              .then(res => {
                  this.$message({
                    message: '创建成功，即将返回我的计划',
                    type: 'success'
                  });
                  setTimeout( ()=> {
                      window.location.href = 'articles-list.html';
                  },1000)
                  // console.log(res)
              })
              .catch(err => {
                  this.$message.error('创建失败，请联系网站管理员!');
                  console.error(err); 
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //修改计划
      updatePlan(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
              var data = {id:id,title:this.updateplan.plan_title,startDate:this.updateplan.startDate,endDate:this.updateplan.endDate}
              //console.log(data)
              axios.post("http://localhost:8080/updatePlan",data)
              .then(res => {
                  this.$message({
                    message: '修改成功，即将返回我的计划',
                    type: 'success'
                  });
                  setTimeout( ()=> {
                      window.location.href = 'articles-list.html';
                  },1000)
                  // console.log(res)
              })
              .catch(err => {
                  this.$message.error('修改失败，请联系网站管理员!');
                  console.error(err); 
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      resetPlan(formName) {
        this.$refs[formName].resetFields();
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = "http://localhost:8080/"+file.name
        console.log("上传成功")
      },
      beforeAvatarUpload(file) {
        
        const isJPG = file.type === 'image/jpeg'||
                      file.type === "image/jpg" ||
                      file.type === "image/JPG" ||
                      file.type === "image/png" ||
                      file.type === "image/PNG";
        const isLt20M = file.size / 1024 / 1024 < 20;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
        }
        if (!isLt20M) {
          this.$message.error('上传头像图片大小不能超过 20MB!');
        }
        
        return isJPG && isLt20M;
      },
      exit(){
        this.$message({
          message: '取消新建，稍等返回首页',
          type: 'success'
        });
        setTimeout(function () {
          window.location.href = 'index.html';
        },1000)
      },
      //修改攻略跳转
      handleEdit(index, row) {
        var data = {id:row.id}
        axios.post("http://localhost:8080/getPostById",data)
        .then(res => {
          sessionStorage.setItem("checkpost",JSON.stringify(res.data));
          this.$message({
            message: '正在转向修改攻略页',
            type: 'success'
          });
          setTimeout(function () {
            window.location.href = 'updatePost.html';
          },1000)
          console.log(res)
        })
        .catch(err => {
          console.error(err); 
        })
      },
      //删除攻略跳转
      handleDelete(index, row) {
        var data = {tid:row.id}
        axios.post("http://localhost:8080/deletePost",data)
            .then(res => {
                this.$message({
                  message: '删除成功!',
                  type: 'success'
                });
                
                setTimeout(function () {
                  window.location.href = 'page.html';
                },1000)
            })
            .catch(err => {
                console.error(err); 
            })
      },
      //修改计划跳转
      PlanEdit(index, row){
        var data = {id:row.id}
        axios.post("http://localhost:8080/getPlanById",data)
        .then(res => {
          sessionStorage.setItem("checkplan",JSON.stringify(res.data));
          this.$message({
            message: '正在转向修改攻略页',
            type: 'success'
          });
          setTimeout(function () {
            window.location.href = 'updatePlan.html';
          },1000)
          console.log(res)
        })
        .catch(err => {
          console.error(err); 
        })
      },
      //删除计划跳转
      PlanDelete(index, row){
        var data = {id:row.id}
        axios.post("http://localhost:8080/deletePlan",data)
            .then(res => {
                this.$message({
                  message: '删除成功!',
                  type: 'success'
                });
                
                setTimeout(function () {
                  window.location.href = 'articles-list.html';
                },1000)
            })
            .catch(err => {
                console.error(err); 
            })
      },
      // 发送聊天信息
      sendText() {
        let _this = this;
        _this.$refs["sendMsg"].focus();
        if (!_this.contentText) {
          return;
        }
        let params = {
          userId: _this.user.uid,
          username: _this.user.uname,
          avatar: _this.user.avatar,
          msg: _this.contentText,
          count: _this.count
        };
        _this.ws.send(JSON.stringify(params)); //调用WebSocket send()发送信息的方法
        _this.contentText = "";
        setTimeout(() => {
          _this.scrollBottm();
        }, 500);
      },
      // 进入页面创建websocket连接
      initWebSocket() {
        let _this = this;
        // 判断页面有没有存在websocket连接
        if (window.WebSocket) {
          var serverHot =  window.location.hostname;
          let sip = '房间号'
          // 填写本地IP地址 此处的 :9101端口号 要与后端配置的一致！
          var url = 'ws://' + serverHot + ':8080' + '/groupChat/' + sip + '/' + this.userId; // `ws://127.0.0.1/9101/groupChat/10086/聊天室`
          let ws = new WebSocket(url);
          _this.ws = ws;
          ws.onopen = function(e) {
            console.log("服务器连接成功: " + url);
          };
          ws.onclose = function(e) {
            console.log("服务器连接关闭: " + url);
          };
          ws.onerror = function() {
            console.log("服务器连接出错: " + url);
          };
          ws.onmessage = function(e) {
            //接收服务器返回的数据
            let resData = JSON.parse(e.data)
            _this.count = resData.count;
            _this.listmsg = [
              ..._this.listmsg,
              { userId: resData.userId, username: resData.username, avatar: resData.avatar, content: resData.msg }
            ];
          };
        }
      },
      // 滚动条到底部
      scrollBottm() {
          var el = this.$refs["msg-box"];
          setTimeout(()=>{
          el.scrollTop = el.scrollHeight;
          },0)
      }
    },
    destroyed() {
      // 离开页面时关闭websocket连接
      this.ws.onclose(undefined);
    },
    mounted() {
      this.initWebSocket();
      $("#addPlan").click(()=>{
        this.$message({
          message: '正在转向新建攻略页',
          type: 'success'
        });
        setTimeout(function () {
          window.location.href = 'addPlan.html';
        },1000)
      })
      $("#addPost").click(()=>{
        this.$message({
          message: '正在转向新建攻略页',
          type: 'success'
        });
        setTimeout(function () {
          window.location.href = 'addPost.html';
        },1000)
      })
      $("#ownerPost").click(()=>{
        this.$message({
          message: '正在转向我的攻略页',
          type: 'success'
        });
        setTimeout(function () {
          window.location.href = 'page.html';
        },1000)
      })
      $("#removeLogin").click(()=>{
        localStorage.removeItem('uname');
        this.$message({
          message: '注销成功，稍等返回首页',
          type: 'success'
        });
        setTimeout(function () {
          window.location.href = 'index.html';
        },1000)
      })
    }
};
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')