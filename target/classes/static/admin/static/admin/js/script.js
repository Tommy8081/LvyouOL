if(sessionStorage.isLgoin!=1&&window.location.href!="http://localhost:8080/admin/login.html"){
    alert('请先登录再访问');
    window.location.href = 'login.html';
}
if(sessionStorage.getItem("checkpost")!=null){

    var item = sessionStorage.getItem("checkpost");
    var post = JSON.parse(item);
    var pid = post.id
    var IndexUrl = post.indexImg
    var title = post.title
    var EditorTXT = post.content
    var newType = post.type
    var published = post.published
    var tag = post.tag
    var user = post.user[0]

    console.log(post)
    
}
if(sessionStorage.getItem("checkuser")!=null){

    var item = sessionStorage.getItem("checkuser");
    var user = JSON.parse(item);
    var uid = user.uid
    var Uavatar = user.avatar
    var username = user.uname
    var password = user.password
    var email = user.email
    var tel = user.tel

    console.log(user)
    
}

var vue = new Vue({
    el:'#app',
    data:{
        webname:config.webname,
        menu:[],
        address:[],
        type:[],
        newType:[],
        User:[],
        post:[],
        pid:[],
        IndexUrl:'./static/common/image/pic.png',
        title:'',
        content:'',
        tag:false,
        published:false,
        pagesum:1, //总页数
        currpage:1, //当前页数
        eachpage:6, //每页行数
        EditorTXT:'',
        oldpost:post,
        oldpid:pid,
        oldIndexUrl:IndexUrl,
        oldtitle:title,
        oldEditorTXT:EditorTXT,
        oldnewType:newType,
        oldtag:tag,
        olduser:user,
        oldpublished:published,
        Uavatar:'./static/common/image/pic.png',
        username :'',
        password :'',
        email :'',
        tel :'',
        olduid:uid,
        oldUavatar:Uavatar,
        oldusername:username,
        oldpassword:password,
        oldemail:email,
        oldtel:tel
    },
    updated () {
        layui.use('form', function(){
            var form = layui.form;
            form.render();
        });
        console.log("update success")
    },
    created:function(){
        // this.oldtitle = title
        // console.log(this.oldtitle);
        // console.log(this.selected);
        // console.log(this.olduser)
        //加载左侧菜单
        if(config.debug){
            $.ajax({
                url: config.menuUrl,
                dataType: 'text',
                success:  (menu) => {
                    menu = eval('(' + menu + ')');
                    sessionStorage.menu = JSON.stringify(menu);
                    this.menu = menu;
                    this.thisActive();
                    this.thisAttr();
                }
            })
        }else {
            let data = sessionStorage.menu;
            if (!data) {
                $.ajax({
                    url: config.menuUrl,
                    dataType: 'text',
                    success: (menu) => {
                        menu = eval('(' + menu + ')');
                        sessionStorage.menu = JSON.stringify(menu);
                        this.menu = menu;
                        this.thisActive();
                        this.thisAttr();
                    }
                })
            } else {
                this.menu = JSON.parse(data);
                this.thisActive();
                this.thisAttr();
            }
        }
        //获取类型
        axios.get("http://localhost:8080/getType")
          .then(res => {
                this.type = res.data
                this.pagesum = Math.ceil(this.post.length/this.eachpage);
                layui.use('laypage', ()=>{
                var laypage = layui.laypage;
                
                //执行一个laypage实例
                laypage.render({
                    elem: 'test' //注意，这里的 test1 是 ID，不用加 # 号
                    ,count: this.post.length //数据总数，从服务端得到
                    ,limit: [10,20,30,40,50]
                    ,skip: true
                    // ,curr: location.hash.replace('#!fenye=', '') //获取起始页
                    // ,hash: 'fenye' //自定义hash值
                    , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                    ,jump: (obj, first)=>{
                        //obj包含了当前分页的所有参数，比如：
                        // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        // console.log(obj.limit); //得到每页显示的条数
                        this.currpage=obj.curr;  //改变当前页码
                        this.eachpage=obj.limit;
                        //首次不执行
                        if(!first){
                            // loadData()  //加载数据
                            // this.pagination(obj.curr, obj.limit);
                        }
                    }
                });
              });
                console.log("getPost success")
          })
          .catch(err => {
            console.error(err); 
        })
        //获取用户
        axios.get("http://localhost:8080/user/getUser")
        .then(res => {
            this.User = res.data
            this.pagesum = Math.ceil(this.User.length/this.eachpage);
                layui.use('laypage', ()=>{
                var laypage = layui.laypage;
                
                //执行一个laypage实例
                laypage.render({
                    elem: 'test2' //注意，这里的 test1 是 ID，不用加 # 号
                    ,count: this.User.length //数据总数，从服务端得到
                    ,limit: [10,20,30,40,50]
                    ,skip: true
                    // ,curr: location.hash.replace('#!fenye=', '') //获取起始页
                    // ,hash: 'fenye' //自定义hash值
                    , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                    ,jump: (obj, first)=>{
                        //obj包含了当前分页的所有参数，比如：
                        // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        // console.log(obj.limit); //得到每页显示的条数
                        this.currpage=obj.curr;  //改变当前页码
                        this.eachpage=obj.limit;
                        //首次不执行
                        if(!first){
                            // loadData()  //加载数据
                            // this.pagination(obj.curr, obj.limit);
                        }
                    }
                });
              });
            console.log("getUser success")
        })
        .catch(err => {
            console.error(err); 
        })
        //获取Posts
        axios.get("http://localhost:8080/getPost")
        .then(res => {
            this.post = res.data
            this.pagesum = Math.ceil(this.post.length/this.eachpage);
            layui.use('laypage', ()=>{
                var laypage = layui.laypage;
                
                //执行一个laypage实例
                laypage.render({
                    elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                    ,count: this.post.length //数据总数，从服务端得到
                    ,limit: [10,20,30,40,50]
                    ,skip: true
                    // ,curr: location.hash.replace('#!fenye=', '') //获取起始页
                    // ,hash: 'fenye' //自定义hash值
                    , layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
                    ,jump: (obj, first)=>{
                        //obj包含了当前分页的所有参数，比如：
                        // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                        // console.log(obj.limit); //得到每页显示的条数
                        this.currpage=obj.curr;  //改变当前页码
                        this.eachpage=obj.limit;
                        //首次不执行
                        if(!first){
                            // loadData()  //加载数据
                            // this.pagination(obj.curr, obj.limit);
                        }
                    }
                });
              });
            console.log("getPost success")
        })
        .catch(err => {
          console.error(err); 
        })
        //图片上传
        layui.use('upload', ()=>{
            var upload = layui.upload;
            
            //执行实例
            var uploadInst = upload.render({
            elem: '#pic' //绑定元素
            ,url: 'http://localhost:8080/uploadimg' //上传接口
            ,done: (res)=>{
                //上传完毕回调
                this.IndexUrl="http://localhost:8080/"+res.data;
                this.oldIndexUrl = "http://localhost:8080/"+res.data;
                this.Uavatar = "http://localhost:8080/"+res.data;
                // console.log("this:"+this.IndexUrl);
                layui.use('layer', function(){
                var layer = layui.layer;
                // console.log(res.data);
                
                layer.msg('上传成功', {icon: 1,time: 2000});
                });              
            }
            //,accept: 'file' //允许上传的文件类型
            //,size: 50 //最大允许上传的文件大小
            //,……
            ,error: function(){
                //请求异常回调
    
            }
            });
        });
        //创建tag值
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('switch(CheckTag)', (data)=>{
                this.tag = data.elem.checked //开关是否开启，true或者false
                console.log(this.tag);
            });
        });
        //创建published值
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('switch(CheckPublished)', (data)=>{
                this.published = data.elem.checked //开关是否开启，true或者false
                console.log(this.published);
            });
        });
        //创建typeId
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('select(TypeId)', (params)=>{
                // console.log(params.elem); //得到select原始DOM对象
                // console.log(params.value); //得到TypeId
                var data = {tid:params.value}
                axios.post("http://localhost:8080/getTypeById",data)
                .then(res => {
                    this.newType = res.data;
                    console.log("获取的Type为："+this.type)
                })
                .catch(err => {
                    console.error(err); 
                })
            }); 
        });

        //修改tag值
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('switch(CheckoldTag)', (data)=>{
                this.oldtag = data.elem.checked //开关是否开启，true或者false
                console.log(this.tag);
            });
        });
        //修改published值
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('switch(CheckoldPublished)', (data)=>{
                this.oldpublished = data.elem.checked //开关是否开启，true或者false
                console.log(this.published);
            });
        });
        //修改typeId
        layui.use('form', ()=>{
            var form = layui.form;
            form.on('select(oldTypeId)', (params)=>{
                // console.log(params.elem); //得到select原始DOM对象
                // console.log(params.value); //得到TypeId
                var data = {tid:params.value}
                axios.post("http://localhost:8080/getTypeById",data)
                .then(res => {
                    this.oldnewType = res.data;
                    console.log("获取的Type为："+this.type)
                })
                .catch(err => {
                    console.error(err); 
                })
            }); 
        });
        //新增用户表单取值
        layui.use('form', ()=>{
            var form = layui.form;
            //监听提交
            form.on('submit(demo1)', (data)=>{
                // layer.alert(JSON.stringify(data.field), {
                // title: '最终的提交信息'
                // })
                this.username = data.field.username
                this.password = data.field.password
                this.email = data.field.email
                this.tel = data.field.tel
                var data = {avatar:this.Uavatar,username:this.username,password:this.password,email:this.email,tel:this.tel}
                console.log(data);
                axios.post("http://localhost:8080/user/register",data)
                .then(res => {
                    layui.use('layer', ()=> {
                        layer.msg('添加成功！', {icon: 1,time: 1000});
                    })
                    setTimeout(function () {
                        window.location.href = 'user_index.html';
                    },1000)
                    console.log(res)
                })
                .catch(err => {
                    console.error(err); 
                })
                return false;
            });
        }) 
        //修改用户表单取值
        layui.use('form', ()=>{
            var form = layui.form;
            //监听提交
            form.on('submit(demo2)', (data)=>{
                // layer.alert(JSON.stringify(data.field), {
                // title: '最终的提交信息'
                // })
                this.username = data.field.username
                this.password = data.field.password
                this.email = data.field.email
                this.tel = data.field.tel
                var data = {uid:this.olduid,avatar:this.oldUavatar,username:this.username,password:this.password,email:this.email,tel:this.tel}
                // console.log(this.title+" || "+this.EditorTXT+" || "+this.IndexUrl+" || "+this.newType+" || "+this.published+" || "+this.tag);
                console.log(data);
                axios.post("http://localhost:8080/user/updateUser",data)
                .then(res => {
                    layui.use('layer', ()=> {
                        layer.msg('修改成功！', {icon: 1,time: 1000});
                        sessionStorage.removeItem("checkuser");
                    })
                    setTimeout(function () {
                        window.location.href = 'user_index.html';
                    },1000)
                    // console.log(res)
                })
                .catch(err => {
                    layui.use('layer', ()=> {
                        layer.msg('修改失败！用户名重复！', {icon: 2,time: 1000});
                    })
                    // console.error(err); 
                })
                return false;
            });
        }) 
    },
    methods:{
        //修改标签
        updateType:function(id,i){
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.prompt(
                     {
                    // formType: 3,
                    // value: '',
                     title: '请输入修改后的类型名称',
                    // label:['密码','确认密码']
                     },
                    function(val,index){
                        // value2 = $('#val2').val();
                        layer.msg("修改后的类型名称"+val);
                        var data = {tid:id,tname:val}
                            axios.post("http://localhost:8080/updateType",data)
                            .then(res => {
                                vue.$set(vue.type[i],'tname',val)
                                console.log(res)
                            })
                            .catch(err => {
                                console.error(err); 
                            })
                        
                        layer.close(index);
                });
            });
        },
        //删除文章
        deletePost:function(id,index){
            layui.use('layer', ()=> {
                layer.confirm('确定删除该文章？', {
                    btn: ['确定','取消'] //按钮
                  }, ()=>{
                    var data = {tid:id}
                    axios.post("http://localhost:8080/deletePost",data)
                                .then(res => {

                                    // this.post = Object.assign({},res.data)
                                    this.post = res.data;
                                    console.log(res,this)
                                })
                                .catch(err => {
                                    console.error(err); 
                                })
                    layer.msg('删除成功', {icon: 1,time: 1000});
                  }, () =>{
                    layer.msg('删除取消', {icon: 2,time: 1000});
                  });
                });
        },
        //删除标签
        deleteType:function(id){
            layui.use('layer', function () {
            layer.confirm('确定删除该标签？', {
                btn: ['确定','取消'] //按钮
              }, function(){
                var data = {tid:id}
                axios.post("http://localhost:8080/deleteType",data)
                            .then(res => {
                                this.type = res.data
                                console.log(res)
                            })
                            .catch(err => {
                                console.error(err) 
                            })
                layer.msg('删除成功', {icon: 1,time: 1000});
              }, function(){
                layer.msg('删除取消', {icon: 2,time: 1000});
              });
            });
        },

        //获取type
        getType:function(){
          axios.get("http://localhost:8080/getType")
          .then(res => {
                this.type = res.data
                console.log("getJingPost success")
          })
          .catch(err => {
            console.error(err); 
            })
        },
    
        //获取Post
        getPost:function(){
            axios.get("http://localhost:8080/getPost")
            .then(res => {
                return res.data
            })
            .catch(err => {
            console.error(err); 
            })
        },

        //新建文章
        addPost:function(){
            this.EditorTXT = editor.txt.html();
            this.title = this.$refs.title.value;
            
            // console.log(this.title+" || "+this.EditorTXT+" || "+this.IndexUrl+" || "+this.newType+" || "+this.published+" || "+this.tag);
            var data = {title:this.title,content:this.EditorTXT,indexImg:this.IndexUrl,type:this.newType,published:this.published,tag:this.tag,user:{uid:6}}
            axios.post("http://localhost:8080/addPost",data)
            .then(res => {
                layui.use('layer', ()=> {
                    layer.msg('添加成功！', {icon: 1,time: 1000});
                })
                setTimeout(function () {
                    window.location.href = 'article_index.html';
                    },1000)
                // console.log(res)
            })
            .catch(err => {
                layer.msg('添加失败', {icon: 2,time: 1000});
                console.error(err); 
            })
            
        },
        //修改文章跳转
        updatePostTZ:function(item){
            // value = window.JSON.stringify(item);
            sessionStorage.setItem("checkpost",JSON.stringify(item));
            layui.use('layer', ()=> {
                layer.msg('正在跳往修改页面！', {time: 1000});
            })
            setTimeout(function () {
                window.location.href = 'article_update.html';
            },1000)                       
        },
        //修改文章逻辑
        updatePost:function(){
            this.EditorTXT = editor.txt.html();
            this.title = this.$refs.oldtitle.value;
            console.log(this.olduser)
            // console.log(this.title+" || "+this.EditorTXT+" || "+this.IndexUrl+" || "+this.newType+" || "+this.published+" || "+this.tag);
            var data = {id:this.oldpid,title:this.title,content:this.EditorTXT,indexImg:this.oldIndexUrl,type:this.oldnewType[0],published:this.oldpublished,tag:this.oldtag,user:this.olduser}
            // console.log(data);
            axios.post("http://localhost:8080/updatePost",data)
            .then(res => {
                layui.use('layer', ()=> {
                    layer.msg('修改成功！', {icon: 1,time: 1000});
                    sessionStorage.removeItem("checkpost");
                })
                setTimeout(function () {
                    window.location.href = 'article_index.html';
                    },1000)
                // console.log(res)
            })
            .catch(err => {
                layer.msg('修改失败', {icon: 2,time: 1000});
                console.error(err); 
            })
        },
        //根据关键字查询
        selectByTiele:function(){
            var title = this.$refs.selectTitle.value;
            var data = {title:title}
            axios.post("http://localhost:8080/selectPostByTitle",data)
            .then(res => {
                console.log(res.data[0])
                this.post = res.data
                // this.post = res.data[0]
                // console.log(res)
            })
            .catch(err => {
                console.error(err); 
            })
        },
        //删除人员
        deleteUser:function(id,index){
            layui.use('layer', ()=> {
                layer.confirm('确定删除该用户？', {
                    btn: ['确定','取消'] //按钮
                }, ()=>{
                var data = {uid:id}
                axios.post("http://localhost:8080/user/deleteUser",data)
                            .then(res => {
                                // this.post = Object.assign({},res.data)
                                this.User = res.data;
                                console.log(res,this)
                            })
                            .catch(err => {
                                console.error(err); 
                            })
                layer.msg('删除成功', {icon: 1,time: 1000});
                }, () =>{
                layer.msg('删除取消', {icon: 2,time: 1000});
                });
            });
        },
        //根据手机、邮箱、用户名查询
        selectByMH:function(){
            var value = this.$refs.userValue.value;
            var data = {value:value}
            console.log(value);
            axios.post("http://localhost:8080/user/selectUserMH",data)
            .then(res => {
                this.User = res.data
                // console.log(res)
            })
            .catch(err => {
                console.error(err); 
            })
        },
        updateUserTZ:function(item){
            sessionStorage.setItem("checkuser",JSON.stringify(item));
            layui.use('layer', ()=> {
                layer.msg('正在跳往修改页面！', {time: 1000});
            })
            setTimeout(function () {
                window.location.href = 'user_update.html';
            },1000)
        },
        //点击编辑
        onEdit:function(){
            layui.use('layer', function () {
                var index = layer.open({
                    type: 2,
                    content: 'http://layim.layui.com',
                    area: ['100%', '100%'],
                    maxmin: true
                });
                layer.full(index);
            })          
        },
        //记住收展
        onActive:function (pid,id=false,) {
            let data;
            if(id===false){

                data = this.menu[pid];

                if(data.url.length>0){
                    this.menu.forEach((v,k)=>{
                        v.active = false;
                        v.list.forEach((v2,k2)=>{
                            v2.active = false;
                        })
                    })

                    data.active = true;

                }

                data.hidden = !data.hidden;

            }else{

                this.menu.forEach((v,k)=>{
                    v.active = false;
                    v.list.forEach((v2,k2)=>{
                        v2.active = false;
                    })
                })

                data = this.menu[pid].list[id];
            }
            this.updateStorage();
            if(data.url.length>0){
                if(data.target){
                    if(data.target=='_blank'){
                        window.open(data.url);
                    }else{
                        window.location.href = data.url;
                    }

                }else{
                    window.location.href = data.url;
                }

            }
        },
        //更新菜单缓存
        updateStorage(){
            sessionStorage.menu = JSON.stringify(this.menu);
        },
        //菜单高亮
        thisActive:function(){
            let pathname = window.location.pathname;
            let host = window.location.host;
            let pid = false;
            let id = false;
            this.menu.forEach((v,k)=>{
                let url = v.url;
                if(url.length>0){
                    if(url[0]!='/' && url.substr(0,4)!='http'){
                        url = '/'+url;
                    }
                }
                if(pathname==url){
                    pid = k;
                }
                v.list.forEach((v2,k2)=>{
                    let url = v2.url;

                    if(url.length>0){
                        if(url[0]!='/' && url.substr(0,4)!='http'){
                            url = '/'+url;
                        }
                    }
                    if(pathname==url){
                        pid = k;
                        id = k2;
                    }
                })
            })


            if(id!==false){
                this.menu[pid].list[id].active = true;
            }else{
                if(pid!==false){
                    this.menu[pid].active = true;
                }
            }

            this.updateStorage();

        },
        //当前位置
        thisAttr:function () {
            //当前位置
            let address = [{
                name:'首页',
                url:'index.html'
            }];
            this.menu.forEach((v,k)=>{
                    v.list.forEach((v2,k2)=>{
                        if(v2.active){
                        address.push({
                            name:v.name,
                            url:'javascript:;'
                        })
                        address.push({
                            name:v2.name,
                            url:v2.url,
                        })
                        this.address = address;
                    }
                })
            })
        }
    }
})
// vue._data.EditorTXT = "222";
// console.log(vue._data.EditorTXT);

$(document).ready(function() {
    //删除
    $(".del").click(function () {
        var url = $(this).attr("href");
        var id = $(this).attr("data-id");

        layer.confirm('你确定要删除么?', {
            btn: ['确定', '取消'] //按钮
        }, function () {
            $.get(url, function (data) {
                if (data.code == 1) {
                    $(id).fadeOut();
                    layer.msg(data.msg, {icon: 1});
                } else {
                    layer.msg(data.msg, {icon: 2});
                }
            });
        }, function () {
            layer.msg("您取消了删除!");
        });
        return false;
    });
})

function delCache(){
    sessionStorage.clear();
    localStorage.clear();
}

function msg(code=1,msg='',url='',s=3) {
    if(typeof code == 'object') {
        msg = code.msg;
        url = code.url || '';
        s = code.s || 3;
        code = code.code;
    }
    code = code==1 ? 1 : 2;
    layer.msg(msg, {icon: code,offset: config.layerMsg.offset || 't',shade: config.layerMsg.shade || [0.4, '#000']});
    if(url){
        setTimeout(function () {
            window.location.href = url;
       },s*1000);
    }
}





