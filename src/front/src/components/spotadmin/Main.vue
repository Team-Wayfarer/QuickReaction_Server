<template>
    <div>
        <h2>Spot Main</h2>
        <section>
            <regist></regist>
        </section>
        <section>
            <manage></manage>
        </section>
        <section>
            <issue></issue>
        </section>
    </div>
</template>

<script>
import Regist from "./Register.vue";
import Manage from "./Manage.vue";
import Issue from "./Issue.vue";

export default {
    name: "spot",
    components: {
        Regist,
        Manage,
        Issue
    }
}
</script>

<style>
    
</style>