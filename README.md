# **SS Geeks E-Commerce Store**

## **🛒 Overview**
SS Geeks Store is an **e-commerce web application** that allows users to:
- Browse products 🛍️
- Manage a shopping cart 🛒
- Create & manage wishlists 
- Securely log in and register 🔐

The application is built with a **Vue.js** front-end and a **Spring Boot** back-end with a **PostgreSQL** database.

---

## **📁 Project Structure**

### **Frontend (Vue.js)**
📂 **`src/`**
- **`components/`**
  - `LoadingSpinner.vue` - Displays a loading spinner.
  - `ProductCard.vue` - Displays product details with a flip animation.
- **`views/`**
  - `HomeView.vue` - Homepage with featured products.
  - `ProductsView.vue` - Displays all products with a search feature.
  - `ProductDetailsView.vue` - Displays product details and an "Add to Cart" option.
  - `CartView.vue` - Shows shopping cart items.
  - `WishlistView.vue` - Allows users to manage wishlists.
  - `ProfileView.vue` - Displays user profile details.
  - `LoginView.vue` - Handles user login.
  - `RegisterView.vue` - Handles user registration.
  - `LogoutView.vue` - Logs users out.
- **`router/`**
  - `index.js` - Vue Router setup for navigation and authentication checks.
- **`store/`**
  - `index.js` - Vuex store for managing authentication state.
- **`services/`**
  - `AuthService.js` - Handles login and registration API requests.
  - `CartService.js` - Manages cart-related API requests.
  - `ProductService.js` - Fetches product data.
  - `WishlistService.js` - Handles wishlist API calls.
- **`App.vue`** - Main layout and navigation.
- **`main.js`** - Initializes the Vue app, Vuex store, Axios, and FontAwesome.

---

### **Backend (Spring Boot)**
📂 **`src/main/java/com/techelevator/`**
- **`controller/`**
  - `AuthenticationController.java` - Handles login & registration.
  - `ProductController.java` - Manages products.
  - `CartController.java` - Manages shopping cart operations.
  - `WishlistController.java` - Manages wishlists.
- **`dao/`**
  - `UserDao.java` - Interface for user operations.
  - `ProductDao.java` - Interface for product-related database operations.
  - `CartItemDao.java` - Interface for cart item operations.
  - `WishlistDao.java` - Interface for wishlist operations.
  - `JdbcUserDao.java` - JDBC implementation for user data access.
  - `JdbcProductDao.java` - JDBC implementation for product data access.
  - `JdbcCartItemDao.java` - JDBC implementation for cart item data access.
  - `JdbcWishlistDao.java` - JDBC implementation for wishlist data access.
- **`service/`**
  - `CartService.java` - Manages cart operations.
  - `WishlistService.java` - Manages wishlist operations.
  - `TaxService.java` - Retrieves sales tax rates using an external API.
- **`model/`**
  - `Product.java` - Represents a product.
  - `CartItem.java` - Represents an item in a cart.
  - `Cart.java` - Represents a shopping cart.
  - `Wishlist.java` - Represents a wishlist.
  - `WishlistItem.java` - Represents an item in a wishlist.
  - `User.java` - Represents a user.
  - `Authority.java` - Represents user roles.
  - `LoginDto.java` - Represents login credentials.
  - `RegisterUserDto.java` - Represents registration data.
  - `LoginResponseDto.java` - Contains authentication response.
  - `TaxResponseDto.java` - Represents tax-related data.

---

## **💾 Database Schema (PostgreSQL)**
The application uses **PostgreSQL** with the following tables:
- **`users`** - Stores user details.
- **`product`** - Stores product information.
- **`wishlist`** - Stores user wishlists.
- **`wishlist_item`** - Stores items in a wishlist.
- **`cart_item`** - Stores shopping cart items.

---

## **🚀 Installation & Setup**

### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/YOUR-USERNAME/SSGeeks-Ecommerce-Store.git
cd SSGeeks-Ecommerce-Store
```

### **2️⃣ Backend Setup**
- Install **Java 17** and **PostgreSQL**.
- Update **`application.properties`** with your database credentials.
- Run the application:
  ```sh
  mvn spring-boot:run
  ```

### **3️⃣ Frontend Setup**
- Install **Node.js** and **npm**.
- Navigate to the `frontend/` folder and install dependencies:
  ```sh
  cd frontend
  npm install
  ```
- Start the Vue.js development server:
  ```sh
  npm run dev
  ```

---

## **🔐 User Authentication & Authorization**
- **JWT-based authentication** secures API endpoints.
- Routes requiring authentication are protected with **Vue Router navigation guards**.
- **Vuex state management** handles authentication tokens.

---

## **🎯 Features**
### **User Features**
✅ Browse products
✅ Add/remove products from cart
✅ Create & manage wishlists
✅ Secure login & registration
✅ View profile details
✅ Search products

### **Admin Features (Future Enhancements)**
🔹 Product management (CRUD operations)
🔹 User management
🔹 Order processing





---

## **🤝 Contributing**
Pull requests are welcome! To contribute:
1. Fork the repository 🍴
2. Create a new branch (`git checkout -b feature-name`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to your fork (`git push origin feature-name`)
5. Open a Pull Request 📌

---

## **📜 License**
This project is licensed under the **MIT License**.

---

## **📞 Contact**
For questions, reach out to:
- **GitHub:** [AhmadHabeh](https://github.com/AhmadHabeh)
- **Email:** ahmadhabaps@gmail.com

---

🔥 Built with **Vue.js, Spring Boot, and PostgreSQL** 🔥

