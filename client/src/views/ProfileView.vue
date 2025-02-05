<template>
    <div class="gradient-bg">
      <div class="profile-container">
        <h1 class="profile-title">Welcome, {{ user.name || user.username }}!</h1>
  
        <div class="profile-card">
          <h2>User Information</h2>
          <ul>
            <li><strong>Username:</strong> {{ user.username }}</li>
            <li><strong>Full Name:</strong> {{ user.name }}</li>
            <li><strong>Address:</strong> {{ user.address }}</li>
            <li><strong>City:</strong> {{ user.city }}</li>
            <li><strong>State:</strong> {{ user.stateCode }}</li>
            <li><strong>Zip Code:</strong> {{ user.zip }}</li>
          </ul>
        </div>
  
        <router-link to="/products" class="back-button">Back to Shop</router-link>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    computed: {
      user() {
        return this.$store.state.user; 
      }
    }
  };
  </script>
  
  <style scoped>
  /* Moving Gradient Background */
  .gradient-bg {
    background: linear-gradient(-90deg, #7218d3, #000000, #3403a8, #a3a4be);
    background-size: 400% 400%;
    animation: gradient 10s ease infinite;
    min-height: 100vh;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  @keyframes gradient {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }
  
  /* Profile Container */
  .profile-container {
    max-width: 600px;
    padding: 30px;
    text-align: center;
    background: rgba(0, 0, 0, 0.8);
    color: #FFD700;
    border-radius: 15px;
    box-shadow: 0 0 15px rgba(255, 215, 0, 0.5);
    backdrop-filter: blur(10px);
    transition: transform 0.3s ease-in-out;
  }
  
  .profile-container:hover {
    transform: scale(1.02);
  }
  
  /* Profile Title */
  .profile-title {
    font-size: 2.5rem;
    color: #FFD700;
    margin-bottom: 20px;
    text-shadow: 0 0 15px rgba(255, 215, 0, 0.8);
  }
  
  /* Profile Card */
  .profile-card {
    background: rgba(255, 255, 255, 0.1);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(255, 215, 0, 0.5);
  }
  
  .profile-card h2 {
    color: #FFD700;
    margin-bottom: 15px;
  }
  
  .profile-card ul {
    list-style: none;
    padding: 0;
  }
  
  .profile-card li {
    font-size: 1.2rem;
    margin: 10px 0;
  }
  
  /* Back Button */
  .back-button {
    display: inline-block;
    margin-top: 20px;
    padding: 12px 24px;
    background: #FFD700;
    color: #000;
    text-decoration: none;
    font-weight: bold;
    border-radius: 30px;
    transition: all 0.3s ease-in-out;
    box-shadow: 0 0 10px rgba(255, 215, 0, 0.6);
  }
  
  .back-button:hover {
    background: #FFC000;
    transform: scale(1.08);
    box-shadow: 0 0 20px rgba(255, 215, 0, 0.8);
  }
  </style>
  