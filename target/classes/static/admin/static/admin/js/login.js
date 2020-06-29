var login= new Vue({
    el:'#login',
    data() {
        username = "",
        password = ""
    },
    methods:{
        login:function(event){
        // alert("用户名为"+this.$refs.username.value+"密码为"+this.$refs.password.value)
        var name = this.$refs.username.value;
        var pwd = this.$refs.password.value;
        let data = {uname:name,password:pwd,isIfadmin:1};
        axios.post('http://localhost:8080/user/loginByAdmin', data)
          .then((response)=> {
              // alert("登录成功");
              if(response.data=="Login success"){
              sessionStorage.isLgoin = 1;
              this.$message({
                showClose: true,
                message: '登录成功',
                type: 'success'
            });
            setTimeout(function () {
            window.location.href = 'index.html';
            },1000)        
            }else{
                this.$message({
                    showClose: true,
                    message: '登录失败',
                    type: 'failed'
                });
            }
          })
          .catch((error)=> {
            console.log(error);
          });
        // axios.post(url,params)
        // .then(res => {
        //     console.log(res)
        // })
        // .catch(err => {
        //     console.error(err); 
        // })
          // this.log=this.user.username;       
      }
        // login:function(event){
            
        //     alert("用户名为"+this.$refs.username.value+"密码为"+this.$refs.password.value)
        //     var name = this.$refs.username.value;
        //     var pwd = this.$refs.password.value;
            
        //     if(name=="admin"&&pwd=="123456"){
        //     sessionStorage.isLgoin = 1;
        //     this.$message({
        //         showClose: true,
        //         message: '登录成功',
        //         type: 'success'
        //     });
        //     setTimeout(function () {
        //     window.location.href = 'index.html';
        //     },1000)        

        //     }
        // }
    }
});
// var Ctor = Vue.extend(Main)
// new Ctor().$mount('#login')