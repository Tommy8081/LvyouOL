var Main = {
    data() {
      return {
        log:'登录',
        reg:'注册',
        question:'',
        list:[],
        post:[],
        JingPost:[],
        restaurants: [],
        user:{
          uid:'',
          avatar:'',
          username: '',
          password: '',
          tel: '',
          email: ''
        },
        dialogTableVisible: false,
        dialogFormVisible: false,
        restaurants: [],
        state1: '',
        state2: '',
        total:0,
        currentPage:1,
        pageSize:4,
        Jtotal:0,
        JcurrentPage:1,
        JpageSize:4,  
      };
    },
    created () {
      if(localStorage.getItem("uname")!=null){
        this.log = localStorage.getItem("uname");
        $("#log").replaceWith("<li id='log'><el-row >"+
        "<el-col :span='12'>"+
        "<el-avatar icon='el-icon-user-solid' src="+this.user.avatar+">"+
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
        var split = document.getElementById("split");
        var reg = document.getElementById("reg");
        split.remove();
        reg.remove();
        //成功提示
        if(localStorage.getItem("login")!=null){
          this.$message({
            message: '登录成功',
            type: 'success'
          });
          var name = localStorage.getItem("uname")
          var data = {value:name}
          axios.post("http://localhost:8080/user/selectUserByName",data)
          .then(res => {
            this.user = res.data[0]
            sessionStorage.setItem("uid",this.user.uid);
            console.log("selectUserByName success")
          })
          .catch(err => {
            console.error(err); 
          })
          localStorage.removeItem('login');
        }
      }else{
        this.$message({
          message: "请登录后再浏览~",
          type: 'warning'
        });
      }
      axios.get('http://localhost:8080/getType')
        .then(res => {
            this.list = res.data
            console.log("getType success")
        })
        .catch(err => {
            console.error(err); 
      })
      //获取最新的精品文章
      axios.get("http://localhost:8080/getJingPost")
      .then(res => {
            this.JingPost = res.data
            this.Jtotal = this.JingPost.length
            console.log("getJingPost success")
      })
      .catch(err => {
        console.error(err); 
      })
      //获取最新的文章
      axios.get("http://localhost:8080/getPost")
      .then(res => {
          this.post = res.data
          this.total = this.post.length
          console.log("getPost success")
      })
      .catch(err => {
        console.error(err); 
      })
      //搜索文章
      // var til = this.question
      // let data = {title:til}
      // axios.post("http://localhost:8080/selectPostByTitle",data)
      // .then(res => {
      //   this.restaurants = res.data[0].title;
      //   console.log(res)
      // })
      // .catch(err => {
      //   console.error(err); 
      // })
      
    },
    methods:{
      closeDialog(){ 
        this.$refs.username = '';//清空数据
        this.$refs.password = '';
        this.$refs.tel = '';
        this.$refs.email = '';
      },

      login:function(event){
        // alert("用户名为"+this.$refs.username.value+"密码为"+this.$refs.password.value)
        this.user.username = this.$refs.username.value;
        this.user.password = this.$refs.password.value;
        let data = {uname:this.user.username,password:this.user.password};
        axios.post('http://localhost:8080/user/login', data)
          .then((response)=> {
              // alert("登录成功");
              if(response.data=="Login success"){
              localStorage.setItem('uname',this.user.username);
              localStorage.setItem('login',"success");
              var split = document.getElementById("split");
              var reg = document.getElementById("reg");
              split.remove();
              reg.remove();
              this.dialogFormVisible=false;
              window.location.reload(true);
            }else{
              this.$message.error("登陆失败！请检查用户名或密码是否正确。");
            }
            
          })
          .catch((error)=> {
            console.log(error);
          });
          // this.log=this.user.username;       
      },

      regs:function(event){
        alert("手机号为："+this.$refs.tel.value+"邮箱为："+this.$refs.email.value+"用户名为"+this.$refs.username.value+"密码为"+this.$refs.password.value)
        var avatar = "../../images/timg.jpg";
        var tel = this.$refs.tel.value;
        var email = this.$refs.email.value;
        var name = this.$refs.username.value;
        var pwd = this.$refs.password.value;
        let data = {avatar:avatar,tel:tel,email:email,username:name,password:pwd};
        axios.post('http://localhost:8080/user/register', data)
          .then((response)=> {
            if(response.data == "Register success"){
              var split = document.getElementById("split");
              var reg = document.getElementById("reg");
              split.remove();
              reg.remove();
              this.dialogTableVisible=false;
              window.location.reload(true);
           }else{
            this.$message.error("注册失败！");
            }
          })
          .catch((error)=> {
            console.log(error);
          });
      },

      checkUserName:function(){
        var name = this.$refs.username.value;
        var data = {value:name}
        axios.post('http://localhost:8080/user/selectUserByName', data)
                  .then((response)=> {
                    if(response.data.length==0){
                      this.regs();
                      console.log(response.data);
                  }else{
                    this.$message.error("注册失败！用户名已存在。");
                    }
                  })
                  .catch((error)=> {
                    console.log(error);
                  });
      },

      say:function(id) {
        //存cookie2小时
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
        addCookie("postId",id,2)
      },
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        console.log(item);
      },
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
        };
      },
  
      handleSelect(item) {
        console.log(item);
      },
      handleCurrentChange(val){
      this.currentPage = val;
      },
      handleJCurrentChange(val){
      this.JcurrentPage = val;
      }
    },
    
    
    
    mounted () {
      // this.restaurants = this.loadAll();
      //测试数据
      this.restaurants = [{ "value": "三全鲜食（北新泾店）", "address": "长宁区新渔路144号" },
          { "value": "Hot honey 首尔炸鸡（仙霞路）", "address": "上海市长宁区淞虹路661号" },
          { "value": "新旺角茶餐厅", "address": "上海市普陀区真北路988号创邑金沙谷6号楼113" },
          { "value": "泷千家(天山西路店)", "address": "天山西路438号" },
          { "value": "胖仙女纸杯蛋糕（上海凌空店）", "address": "上海市长宁区金钟路968号1幢18号楼一层商铺18-101" },
          { "value": "贡茶", "address": "上海市长宁区金钟路633号" },
          { "value": "豪大大香鸡排超级奶爸", "address": "上海市嘉定区曹安公路曹安路1685号" },
          { "value": "茶芝兰（奶茶，手抓饼）", "address": "上海市普陀区同普路1435号" },
          { "value": "十二泷町", "address": "上海市北翟路1444弄81号B幢-107" },
          { "value": "星移浓缩咖啡", "address": "上海市嘉定区新郁路817号" },
          { "value": "阿姨奶茶/豪大大", "address": "嘉定区曹安路1611号" },
          { "value": "新麦甜四季甜品炸鸡", "address": "嘉定区曹安公路2383弄55号" },
          { "value": "Monica摩托主题咖啡店", "address": "嘉定区江桥镇曹安公路2409号1F，2383弄62号1F" },
          { "value": "浮生若茶（凌空soho店）", "address": "上海长宁区金钟路968号9号楼地下一层" },
          { "value": "NONO JUICE  鲜榨果汁", "address": "上海市长宁区天山西路119号" },
          { "value": "CoCo都可(北新泾店）", "address": "上海市长宁区仙霞西路" },
          { "value": "快乐柠檬（神州智慧店）", "address": "上海市长宁区天山西路567号1层R117号店铺" },
          { "value": "Merci Paul cafe", "address": "上海市普陀区光复西路丹巴路28弄6号楼819" },
          { "value": "猫山王（西郊百联店）", "address": "上海市长宁区仙霞西路88号第一层G05-F01-1-306" },
          { "value": "枪会山", "address": "上海市普陀区棕榈路" },
          { "value": "纵食", "address": "元丰天山花园(东门) 双流路267号" },
          { "value": "钱记", "address": "上海市长宁区天山西路" },
          { "value": "壹杯加", "address": "上海市长宁区通协路" },
          { "value": "唦哇嘀咖", "address": "上海市长宁区新泾镇金钟路999号2幢（B幢）第01层第1-02A单元" },
          { "value": "爱茜茜里(西郊百联)", "address": "长宁区仙霞西路88号1305室" },
          { "value": "爱茜茜里(近铁广场)", "address": "上海市普陀区真北路818号近铁城市广场北区地下二楼N-B2-O2-C商铺" },
          { "value": "鲜果榨汁（金沙江路和美广店）", "address": "普陀区金沙江路2239号金沙和美广场B1-10-6" },
          { "value": "开心丽果（缤谷店）", "address": "上海市长宁区威宁路天山路341号" },
          { "value": "超级鸡车（丰庄路店）", "address": "上海市嘉定区丰庄路240号" },
          { "value": "妙生活果园（北新泾店）", "address": "长宁区新渔路144号" },
          { "value": "香宜度麻辣香锅", "address": "长宁区淞虹路148号" },
          { "value": "凡仔汉堡（老真北路店）", "address": "上海市普陀区老真北路160号" },
          { "value": "港式小铺", "address": "上海市长宁区金钟路968号15楼15-105室" },
          { "value": "蜀香源麻辣香锅（剑河路店）", "address": "剑河路443-1" },
          { "value": "北京饺子馆", "address": "长宁区北新泾街道天山西路490-1号" },
          { "value": "饭典*新简餐（凌空SOHO店）", "address": "上海市长宁区金钟路968号9号楼地下一层9-83室" },
          { "value": "焦耳·川式快餐（金钟路店）", "address": "上海市金钟路633号地下一层甲部" },
          { "value": "动力鸡车", "address": "长宁区仙霞西路299弄3号101B" },
          { "value": "浏阳蒸菜", "address": "天山西路430号" },
          { "value": "四海游龙（天山西路店）", "address": "上海市长宁区天山西路" },
          { "value": "樱花食堂（凌空店）", "address": "上海市长宁区金钟路968号15楼15-105室" },
          { "value": "壹分米客家传统调制米粉(天山店)", "address": "天山西路428号" },
          { "value": "福荣祥烧腊（平溪路店）", "address": "上海市长宁区协和路福泉路255弄57-73号" },
          { "value": "速记黄焖鸡米饭", "address": "上海市长宁区北新泾街道金钟路180号1层01号摊位" },
          { "value": "红辣椒麻辣烫", "address": "上海市长宁区天山西路492号" },
          { "value": "(小杨生煎)西郊百联餐厅", "address": "长宁区仙霞西路88号百联2楼" },
          { "value": "阳阳麻辣烫", "address": "天山西路389号" },
          { "value": "南拳妈妈龙虾盖浇饭", "address": "普陀区金沙江路1699号鑫乐惠美食广场A13" }
        ];
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
