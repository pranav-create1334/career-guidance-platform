import { useEffect, useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const userId = localStorage.getItem("userId");

    if (!userId) {
      alert("User session not found");
      navigate("/");
      return;
    }

    API.get(`/users/${userId}`)
      .then((res) => setUser(res.data))
      .catch(() => alert("Error fetching user"));
  }, [navigate]);

  return (
    <div>
      <h2>Dashboard</h2>

      {user && (
        <>
          <p>Name: {user.name}</p>
          <p>Email: {user.email}</p>
        </>
      )}
    </div>
  );
}

export default Dashboard;
