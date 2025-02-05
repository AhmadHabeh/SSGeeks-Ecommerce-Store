import axios from "axios";

export default {
  async getWishlists() {
    const response = await axios.get("/wishlists");
    
    return response.data.map(wishlist => ({
      ...wishlist,
      items: wishlist.items?.map(item => ({
        ...item,
        product: item.product || { name: "Unknown Product", price: 0 }, // Default product details
      })) || [],
    }));
  },
  
  async createWishlist(name) {
    const response = await axios.post("/wishlists", { name });
    return response.data;
  },
  async addToWishlist(wishlistId, productId) {
    await axios.post(`/wishlists/${wishlistId}/products/${productId}`);
  },
  async removeFromWishlist(wishlistId, productId) {
    await axios.delete(`/wishlists/${wishlistId}/products/${productId}`);
  },
  async deleteWishlist(wishlistId) {
    await axios.delete(`/wishlists/${wishlistId}`);
  },
};
