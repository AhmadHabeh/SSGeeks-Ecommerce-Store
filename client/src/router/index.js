import { createRouter, createWebHistory } from 'vue-router';
import { useStore } from 'vuex';

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import ProductsView from '../views/ProductsView.vue';
import ProductDetailsView from '../views/ProductDetailsView.vue';
import CartView from '../views/CartView.vue';
import WishlistView from '../views/WishlistView.vue';
import ProfileView from '@/views/ProfileView.vue';


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login.
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/products",
    name: "products",
    component: ProductsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/products/:id",
    name: "product-details",
    component: ProductDetailsView,
    meta: {
      requiresAuth: false
    },
    props: true
  },

  {
    path: "/cart",
    name: "cart",
    component: CartView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/wishlists",
    name: "wishlists",
    component: WishlistView,
    meta: {
      requiresAuth: true
    }
  },
  { path: '/profile',
   component: ProfileView,
    meta: {
       requiresAuth: true 
      }
  }
];


// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {
  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If authentication is required and the user is not logged in, redirect to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, allow navigation
});

export default router;
