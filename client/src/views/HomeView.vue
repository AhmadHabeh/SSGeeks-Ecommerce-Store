<template>
  <div class="home">
    <!-- Banner Section -->
    <section class="banner text-center">
      <img src="../assets/banner.jpg" alt="SS Geeks Store Banner" class="img-fluid w-100 shadow" />
    </section>

    <!-- Hero Section -->
    <section class="hero text-center py-5 bg-primary text-white">
      <div class="container">
        <h1 class="display-4 fw-bold">Welcome to SS Geeks Store!</h1>
        <p class="lead">Your cosmic hub for all things space – from celestial wonders to cosmic collectibles..</p>
        <div class="d-flex justify-content-center gap-3 mt-3">
          <router-link to="/products" class="btn btn-warning btn-lg fw-bold">Explore the Universe</router-link>
          <router-link to="/register" class="btn btn-light btn-lg fw-bold">Become a Space Explorer</router-link>
        </div>
      </div>
    </section>

    <!-- About Us Section -->
    <section class="about text-center py-5 bg-light">
      <div class="container">
        <h2 class="fw-bold text-primary">About Us</h2>
        <p class="lead">We provide stellar-quality space-themed merchandise for passionate astronomy lovers. Whether you're into planets, galaxies, or cosmic mysteries, we have something for you!</p>
      </div>
    </section>

    <!-- Featured Products Section -->
    <section class="featured-products text-center py-5">
      <div class="container">
        <h2 class="fw-bold text-primary">Featured Products</h2>
        <div class="row g-4 mt-3 justify-content-center">
          <div v-for="product in featuredProducts" :key="product.id" class="col-md-4">
            <ProductCard :product="product" />
          </div>
        </div>
      </div>
    </section>

    <!-- Why Shop With Us? -->
    <section class="why-shop text-center py-5 bg-light">
      <div class="container">
        <h2 class="fw-bold text-primary">Why Shop With Us?</h2>
        <div class="row g-3 mt-3">
          <div class="col-md-3">
            <div class="p-4 bg-white shadow rounded">
              <font-awesome-icon icon="fa-solid fa-truck" size="2x" class="mb-2 text-warning" />
              <p class="fw-bold">Warp-Speed Shipping – Get your cosmic treasures fast!</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="p-4 bg-white shadow rounded">
              <font-awesome-icon icon="fa-solid fa-tag" size="2x" class="mb-2 text-warning" />
              <p class="fw-bold">Out-of-this-World Prices – Affordable space collectibles.</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="p-4 bg-white shadow rounded">
              <font-awesome-icon icon="fa-solid fa-fire" size="2x" class="mb-2 text-warning" />
              <p class="fw-bold">Limited Edition Cosmic Gear – Rare finds from the universe.</p>
            </div>
          </div>
          <div class="col-md-3">
            <div class="p-4 bg-white shadow rounded">
              <font-awesome-icon icon="fa-solid fa-gamepad" size="2x" class="mb-2 text-warning" />
              <p class="fw-bold">For Space Enthusiasts – Explore the cosmos with us!</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Customer Testimonials -->
    <section class="testimonials text-center py-5" style="background-color: #f4f4f4;">
      <div class="container">
        <h2 class="fw-bold" style="color: #1e1e2f;">What Our Customers Say</h2>
        <div class="row justify-content-center">
          <div class="col-md-6">
            <!-- Testimonial 1 -->
            <div class="p-4 shadow rounded mb-3 bg-white">
              <div class="mb-2 text-warning">
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
              </div>
              <p class="lead fst-italic">"Amazing products and fast delivery! Highly recommend!" - Yoda R.</p>
            </div>
            
            <!-- Testimonial 2 -->
            <div class="p-4 shadow rounded bg-white">
              <div class="mb-2 text-warning">
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star" />
                <font-awesome-icon icon="fa-solid fa-star-half-alt" />
              </div>
              <p class="lead fst-italic">"The best place to find stellar space collectibles. Love it!" - Spock T.</p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- Final Call to Action -->
    <section class="cta text-center py-5 bg-dark text-white">
      <div class="container">
        <h2 class="fw-bold">Join the Cosmic Journey Today!</h2>
        <router-link to="/register" class="btn btn-warning btn-lg mt-3 fw-bold">Sign Up</router-link>
      </div>
    </section>
  </div>

  <!-- Footer -->
  <footer class="text-center py-3 bg-dark text-white">
    <p class="mb-0">© 2024 SS Geeks Store</p>
  </footer>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";
import ProductService from "../services/ProductService";

export default {
  components: {
    ProductCard,
  },
  data() {
    return {
      featuredProducts: [],
    };
  },
  async created() {
    let allProducts = await ProductService.getProducts();
    this.featuredProducts = allProducts.map(product => ({
      id: product.productId,
      name: product.name,
      image: product.imageName,
      description: product.description || "No description available.",
      rating: Math.floor(Math.random() * 5) + 1, // Random rating for now
      reviews: Math.floor(Math.random() * 50) + 1, // Random review count
      features: [
        "High-quality material",
        "Limited edition",
        "Fast shipping available"
      ]
    })).slice(0, 4);
  }
};
</script>

<style scoped>
.banner {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px 0;
  background-image: url("../assets/pewdiepie.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: relative;
}

.banner::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.3);
}

.banner img {
  max-width: 40%;
  height: auto;
  margin: 20px 0;
  box-shadow: 0px 10px 30px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
  transition: transform 0.3s ease-in-out;
  position: relative;
  z-index: 2;
}

.banner img:hover {
  transform: scale(1.05);
}
</style>
