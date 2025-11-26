import { Link } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { logout } from "../../store/authSlice.ts";
import type { AppDispatch } from "../../store/store.ts";

function Headeradm() {
    const dispatch = useDispatch<AppDispatch>();

    const handleLogout = () => {
        // Isso limpa o token do Redux e do SessionStorage (via persist)
        dispatch(logout());
        console.log("LOGOUT");

    };

    return (
        <header className="bg-dark text-white py-3">
            <nav className="container d-flex justify-content-center gap-4">
                <Link
                    to="/usuarios"
                    className="text-white text-decoration-none"
                >
                    Usuarios
                </Link>
                <Link 
                    to="/auth/login" 
                    onClick={handleLogout} 
                    className="text-white text-decoration-none"
                >
                    Sair
                </Link>
            </nav>
        </header>
    );
}

export default Headeradm;