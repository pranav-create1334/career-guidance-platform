import API from "./api";

export const login = async (data) => {
  return API.post("/users/login", data);
};

export const register = async (data) => {
  return API.post("/users/register", data);
};