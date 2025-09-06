

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