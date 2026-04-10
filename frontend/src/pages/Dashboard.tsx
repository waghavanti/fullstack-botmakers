import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const [userData, setUserData] = useState("");
  const [adminData, setAdminData] = useState("");
  const role = localStorage.getItem("role");

  const navigate = useNavigate();

  useEffect(() => {
    // USER API
    API.get("/user")
      .then((res) => setUserData(res.data))
      .catch(() => setUserData("Not allowed"));

    // ADMIN API
    API.get("/admin")
      .then((res) => setAdminData(res.data))
      .catch(() => setAdminData("Not allowed"));
  }, []);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">

      {/* Header */}
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold">Dashboard</h1>

        <button
          onClick={handleLogout}
          className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
        >
          Logout
        </button>
      </div>

      <h2 className="text-lg mb-6">
        Your Role: <span className="font-bold">{role}</span>
      </h2>

      {/* Cards */}
      <div className="flex gap-6 justify-center">

        {/* USER CARD */}
        <div className="bg-white p-6 rounded-xl shadow w-64 text-center">
          <h3 className="text-lg font-bold mb-2">User Section</h3>
          <p>{userData}</p>
        </div>

        {/* ADMIN CARD */}
        <div className="bg-white p-6 rounded-xl shadow w-64 text-center">
          <h3 className="text-lg font-bold mb-2">Admin Section</h3>
          <p>{adminData}</p>
        </div>

      </div>

    </div>
  );
}

export default Dashboard;