import React from "react";
import ReactDOM from "react-dom/client";
import Main from "./components/Main.jsx";
import Navbar from "./components/Navbar.jsx";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <div>
      <Navbar />
      <Main />
    </div>
  </React.StrictMode>
);
