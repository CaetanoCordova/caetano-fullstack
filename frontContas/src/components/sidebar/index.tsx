import { Link } from "react-router-dom";

function Sidebar() {
    return (
        <div className="bg-dark text-light d-flex flex-column p-3" style={{ width: "250px", minHeight: "60vh" }}>
            
            {/* Logo */}
            <div className="text-center mb-4">
                <img
                    src=""
                    alt="logo"
                    className="img-fluid rounded-circle border"
                    style={{ width: "100px", height: "100px", objectFit: "cover" }}
                />
            </div>

            {/* Menu */}
            <ul className="nav nav-pills flex-column mb-auto">
                <li className="nav-item">
                    <a href="/" className="nav-link text-light">
                        Home
                    </a>
                </li>

                <li>
                    <a 
                        className="nav-link text-light d-flex justify-content-between align-items-center"
                        data-bs-toggle="collapse" 
                        href="#submenucadastro"
                        role="button"
                        aria-expanded="false"
                        aria-controls="submenucadastro"
                    >
                        Cadastro
                        <span className="ms-2 bi bi-caret-down-fill"></span>
                    </a>
                    <ul className="collapse list-unstyled ps-3 mt-2" id="submenucadastro">
                        <li>
                            <Link to="usuario" className="nav-link text-white">Usuário</Link>
                            {/*
                            <a href="/usuarios" className="nav-link text-secondary">
                                Usuários
                            </a>
                            */}
                        </li>
                        <li>
                            <Link to="carrinho" className="nav-link text-white">Carrinho</Link>

                            {/*
                            <a href="/carrinho" className="nav-link text-secondary">
                                Carrinho
                            </a>
                            */}
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    );
}

export default Sidebar;



{/*
function Sidebar() {
    return (
        <div>
            <div>
                <img
                src=""
                alt="logo">
                </img>

            </div>
            <ul>
                <li>
                    <a href="/">Home</a>
                </li>
                <li>
                    <a href="#submenucadastro">Cadastro</a>
                    <ul className="collapse" id="submenucadastro">
                        <li>
                            <a href="/usuarios">Usuarios</a>
                        </li>
                        <li>
                            <a href="/carrinho">Carrinho</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    );
}

export default Sidebar;
*/}