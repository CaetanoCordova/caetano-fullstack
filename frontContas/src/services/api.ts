import axios from "axios";
import { store } from "../store/store";
import { logout } from "../store/authSlice";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080/'
})

api.interceptors.request.use(
    (config)=>{

        //consulta
        const state = store.getState();
        const token = state.auth.token;

        console.log(token, "API");

        if (token){
            config.headers.Authorization = `Bearer ${token}`;
        }else {
            delete config.headers.Authorization; 
        }

        return config
    },
    (error)=>{
        return Promise.reject(error);
    }
);

api.interceptors.response.use(
    (response) => {
        // Se deu tudo certo (200, 201), apenas retorna a resposta
        return response;
    },
    (error) => {
        if (error.response && error.response.status === 401) {
            store.dispatch(logout());
            window.location.href = "/auth/login";
            window.alert("Sessão expirada.");
        }
        return Promise.reject(error);
    }
);

export default api;