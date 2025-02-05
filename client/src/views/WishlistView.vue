<template>
  <div id="wishlist" class="container py-5">
    <!-- Page Title -->
    <h1 class="text-center text-primary fw-bold">
      <font-awesome-icon icon="fa-solid fa-heart" /> Your Wishlists
    </h1>

    <!-- Create Wishlist Form -->
    <div class="alert alert-info text-center">
      <p>Create a new wishlist to start adding your favorite products!</p>
      <div class="d-flex justify-content-center">
        <input v-model="newWishlistName" class="form-control w-50 me-2" placeholder="New Wishlist Name" />
        <button @click="createWishlist" class="btn btn-success fw-bold">
          <font-awesome-icon icon="fa-solid fa-plus" /> Create Wishlist
        </button>
      </div>
    </div>

    <!-- Wishlist Display -->
    <div v-if="wishlists.length > 0">
      <div v-for="wishlist in wishlists" :key="wishlist.wishlistId" class="wishlist card shadow-sm mb-4">
        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
          <h5 class="mb-0">{{ wishlist.name }}</h5>
          <button class="btn btn-danger btn-sm" @click="deleteWishlist(wishlist.wishlistId)">
            <font-awesome-icon icon="fa-solid fa-trash" />
          </button>
        </div>
        <div class="card-body">
          <!-- Wishlist Items -->
          <ul class="list-group">
            <li v-for="item in wishlist.items" :key="item.wishlistItemId" class="list-group-item d-flex justify-content-between align-items-center">
              <span>{{ item.product.name }} - {{ item.product.price | currency }}</span>
              <button class="btn btn-outline-danger btn-sm" @click="removeItem(wishlist.wishlistId, item.product.productId)">
                <font-awesome-icon icon="fa-solid fa-times" />
              </button>
            </li>
          </ul>

          <!-- Add Product to Wishlist -->
          <div class="input-group mt-3">
            <input v-model="newProductIds[wishlist.wishlistId]" class="form-control" placeholder="Enter Product ID" />
            <button class="btn btn-warning" @click="addItem(wishlist.wishlistId)">
              <font-awesome-icon icon="fa-solid fa-cart-plus" /> Add to Wishlist
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <footer class="text-center py-3 bg-dark text-white">
    <p class="mb-0">© 2024 SS Geeks Store</p>
  </footer>
</template>

<script>
import WishlistService from "../services/WishlistService";

export default {
  data() {
    return {
      wishlists: [],
      newWishlistName: "",
      newProductIds: {}, 
    };
  },
  methods: {
    async fetchWishlists() {
      try {
        this.wishlists = await WishlistService.getWishlists();
        console.log(this.wishlists)
      } catch (error) {
        
        alert("Failed to load wishlists.");
      }
    },
    async createWishlist() {
      if (!this.newWishlistName) {
        alert("Please enter a name for the new wishlist.");
        return;
      }
      try {
        await WishlistService.createWishlist(this.newWishlistName);
        this.newWishlistName = "";
        this.fetchWishlists();
      } catch (error) {
        alert("Failed to create wishlist.");
      }
    },
    async addItem(wishlistId) {
      const productId = this.newProductIds[wishlistId];
      if (!productId) {
        alert("Please enter a product ID to add.");
        return;
      }
      try {
        await WishlistService.addToWishlist(wishlistId, productId);
        this.newProductIds[wishlistId] = ""; 
        this.fetchWishlists();
      } catch (error) {
        alert("Failed to add product to wishlist.");
      }
    },
    async removeItem(wishlistId, productId) {
      try {
        await WishlistService.removeFromWishlist(wishlistId, productId);
        this.fetchWishlists();
      } catch (error) {
        alert("Failed to remove product from wishlist.");
      }
    },
    async deleteWishlist(wishlistId) {
      try {
        await WishlistService.deleteWishlist(wishlistId);
        this.fetchWishlists();
      } catch (error) {
        alert("Failed to delete wishlist.");
      }
    },
  },
  async created() {
    await this.fetchWishlists();
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

<style scoped>
/* Wishlist Styling */
.wishlist {
  border-radius: 8px;
}

.list-group-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* Footer Styling */
footer {
  position: absolute;
  width: 100%;
  background: #333;
  color: white;
  text-align: center;
  padding: 10px 0;
  bottom: 0;
}
</style>
