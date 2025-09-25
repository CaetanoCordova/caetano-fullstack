import { Link } from "react-router-dom";

function Header(){
    return(
        <header className="bg-dark text-white py-3">
            <nav className="container d-flex justify-content-center gap-4">
                <Link to="/home" className="text-white text-decoration-none">Home</Link>
                <Link to="/registroServico" className="text-white text-decoration-none">Registro de Serviço</Link>
                <Link to="/statusAssinatura" className="text-white text-decoration-none">Status de sua assinatura</Link>
            </nav>
        </header>
    );
}

export default Header;