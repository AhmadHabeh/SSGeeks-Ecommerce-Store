<template>
    <div id="product-details">
      <h1>{{ product.name }}</h1>
      <img :src="product.imageName" alt="Product Image" />
      <p>{{ product.description }}</p>
      <p>Price: {{ product.price | currency }}</p>
      <button v-if="isLoggedIn" @click="addToCart(product.productId)">Add to Cart</button>
    </div>
  </template>
  
  <script>
  import ProductService from "../services/ProductService";
  import CartService from "../services/CartService";
  
  export default {
    data() {
      return {
        product: null,
      };
    },
    computed: {
      isLoggedIn() {
        return this.$store.state.token.length > 0;
      },
    },
    async created() {
      const productId = this.$route.params.id;
      this.product = await ProductService.getProductById(productId);
    },
    methods: {
      async addToCart(productId) {
        try {
          await CartService.addToCart(productId);
          alert("Product added to cart!");
        } catch (error) {
          alert("Failed to add product to cart.");
        }
      },
    },
    filters: {
      currency(value) {
        return new Intl.NumberFormat("en-US", {
          style: "currency",
          currency: "USD",
        }).format(value);
      },
    },
  };
  </script>
  