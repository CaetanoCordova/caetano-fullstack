import { Link } from "react-router-dom";

function Sidebar() {

    return (
        <div className="bg-dark text-white h-100 p-5" style={{ width: '250px', minHeight: '88vh'}}>
            {/*<div className="text-center mb-4">
                <img
                    src="data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAwIiBoZWlnaHQ9IjYwIiB2aWV3Qm94PSIwIDAgMjAwIDYwIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPjxyZWN0IHdpZHRoPSIyMDAiIGhlaWdodD0iNjAiIGZpbGw9IiMyYzNlNTAiLz48dGV4dCB4PSIyMCIgeT0iNDAiIGZvbnQtZmFtaWx5PSJBcmlhbCIgZm9udC1zaXplPSIzMCIgZmlsbD0iI2ZmZiI+U3ViPC90ZXh0Pjx0ZXh0IHg9IjkzIiB5PSI0MCIgZm9udC1mYW1pbHk9IkFyaWFsIiBmb250LXNpemU9IjMwIiBmaWxsPSIjMzg1Zjc2Ij5GbG93PC90ZXh0Pjwvc3ZnPg=="
                    alt="SubFlow Logo"
                    className="img-fluid"
                    style={{ maxHeight: '60px' }}
                />
            </div>*/}
            <ul className="nav flex-column">
                <li className="nav-item">
                    <Link to="/home" className="nav-link text-white d-flex align-items-center">
                        <i className="bi bi-house-door-fill me-2"></i> {/* Ícone de casa */}
                        Home
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/Usuarios" className="nav-link text-white d-flex align-items-center">
                        <i className="bi bi-file-earmark-plus-fill me-2"></i> {/* Ícone de arquivo com mais */}
                        Usuarios
                    </Link>
                </li>
                <li className="nav-item">
                    <Link to="/Contas" className="nav-link text-white d-flex align-items-center">
                        <i className="bi bi-credit-card-2-front-fill me-2"></i> {/* Ícone de cartão de crédito */}
                        Contas
                    </Link>
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