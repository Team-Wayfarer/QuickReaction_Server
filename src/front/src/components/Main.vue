<template>
    <div>
        <div class="main">
            <div class="section">
                <h3>Quick Reaction</h3>
                <div v-if="token===''">
                    <login></login>
                    
                </div>
                <div v-else>
                    <router-link to="/spot">메인으로</router-link>
                </div>
            </div> 
        </div>
    </div>
</template>

<script>
import Login from "./Login.vue";

export default {
    name: "home",
    data: function() {
        return {
            token: ''
        };
    },
    created() {
        // 여기서 state의 token을 가져와야함
        // this.token: ''
    },
    components: {
        Login
    }
}
</script>

<style>

</style>