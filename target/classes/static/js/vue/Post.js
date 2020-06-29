var Main = {
    data(){
        return{
            Post:[],
            JingPost:[]
        }; 
    },
    created () {
        function getCookie(objName) {//获取指定名称的cookie的值
            var arrStr = document.cookie.split("; ");
            for (var i = 0; i < arrStr.length; i++) {
                var temp = arrStr[i].split("=");
                if (temp[0] == objName) return unescape(temp[1]);  //解码
            }
            return "";
        }
    
      //获取最新的5篇精品文章
      axios.get("http://localhost:8080/getJingPost")
      .then(res => {
            this.JingPost = res.data
            console.log("getJingPost success")
      })
      .catch(err => {
        console.error(err); 
      })
      //获取最新的5篇文章
      axios.get("http://localhost:8080/getPost")
      .then(res => {
          this.Post = res.data
          console.log("getPost success")
      })
      .catch(err => {
        console.error(err); 
      })
    },
    methods: {
        
    }
};
var Ctor = Vue.extend(Main)
new Ctor().$mount('#app')