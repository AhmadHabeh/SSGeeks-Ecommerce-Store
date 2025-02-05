<template>
  <div id="cart-app">
    <header>
      <nav>
        <ul>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/products">Products</router-link></li>
          <li v-if="isLoggedIn"><router-link to="/cart">Cart</router-link></li>
          <li v-if="isLoggedIn"><router-link to="/wishlists">Wishlists</router-link></li>
          <li v-if="isLoggedIn"><router-link to="/profile">Profile</router-link></li>
          <li v-if="isLoggedIn"><router-link to="/logout">Logout</router-link></li>
          <li v-else><router-link to="/login">Login</router-link></li>
         
        </ul>
      </nav>
    </header>

    <main>
      <router-view />
    </main>

    
  </div>

</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.token.length > 0;
    }
  }
};
</script>

<style scoped>
nav {
  background-color: #333;
  padding: 10px 0;
 
}

nav ul {
  display: flex;
  justify-content: center;
  list-style-type: none;
  padding: 0;
  
}

nav ul li {
  margin: 0 15px;
  
}

nav ul li a {
  color: white;
  text-decoration: none;
  font-size: 1.2em;
  padding: 5px 10px;
  transition: 0.3s;
  
}

nav ul li a:hover {
  background-color: #555;
  border-radius: 5px;
  
}


</style>
