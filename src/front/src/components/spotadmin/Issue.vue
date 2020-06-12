<template>
    <div>
        <h3>QR 코드 발급받기</h3>
        <div v-if="code===''">
            <button class="btn btn-primary" @click="codeissue">QR Code 발급하기</button>
        </div>
        <div v-else>
            
        </div>
    </div>
</template>

<script>
import axios from "axios";

const baseUrl = "https://localhost:8080/cfcqr/api/codes/";

export default {
    name: "codeissue",
    data() {
        return {
            id: "",
            code: ""
        }
    },
    created() {
      axios.get(baseUrl + "get/" + this.id).then(({data}) => {
        this.code = data.id;
      }).catch(()=> {
          this.code = "";
      })
    },
    methods: {
        codeissue() {
            axios.post(baseUrl +"provide/"+this.id)
            .then((data)=> {
                alert("코드 발급 성공");
                this.code = data.id;
            })
            .catch(()=> {
                alert("서버 오류! 재시도 해주세요");
            })
        }
    }
}
</script>

<style lang="">
    
</style>