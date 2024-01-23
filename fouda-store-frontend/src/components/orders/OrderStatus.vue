<template>
    <span  :class="statusColor">{{status.toLowerCase()}}</span>
</template>

<script setup>

import {defineProps,computed} from 'vue';

const props = defineProps(['status']) ;

const statusColor = computed(() => {
 return 'span span-'+ props.status.toLowerCase() ;

});





</script>


<style scoped>


.span{
  border-radius: 3px;
  padding: 4px;
  font-size: 14px;
}

.span-new {
    background: #FFFEDC;
}

.span-delivered {
    background: #01C7BE;
}

.span-canceled {
    background: #FE5551;
}

.span-forwarded {
    background: #FEEA5C;
}

</style>