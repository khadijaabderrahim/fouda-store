import { createRouter,createWebHistory } from "vue-router"
import Home from './components/pages/HomePage.vue'
import Clients from './components/pages/ClientsPage.vue'
import Orders from './components/pages/OrdersPage.vue'
import ClientDetail from './components/pages/ClientDetail.vue'
import OrderDetail from './components/pages/OrderDetail.vue'
import Products from './components/pages/ProductsPage.vue'

const router = createRouter ({
    history: createWebHistory(),
    routes: [
        {path:'/',redirect:'/home'},
        {name:'home',path:'/home',components:{default:Home}},
        {name:'clients',path:'/clients',components:{default:Clients}},
        {name:'clientDetail',path:'/clients/:clientId',component:ClientDetail,props:true},
        {name:'orders',path:'/orders',components:{default:Orders}},
        {name:'orderDetail',path:'/orders/:orderId',component:OrderDetail,props:true},
        {name:'products',path:'/products',component:Products},

    ]
})

export default router;