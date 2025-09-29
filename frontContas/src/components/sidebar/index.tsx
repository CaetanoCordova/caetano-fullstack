import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div className="bg-dark text-white h-100 p-5" style={{ width: "250px", minHeight: "88.3vh" }}>
      <ul className="nav flex-column">
        <li className="nav-item mb-2">
          <Link to="/usuarios" className="btn text-white d-flex align-items-center">
            Ver usuários
          </Link>
        </li>
        <li className="nav-item mb-2">
          <Link to="/contas" className="btn text-white d-flex align-items-center">
            Ver contas
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/contas/criar" className="btn text-white d-flex align-items-center">
            Criar contas
          </Link>
        </li>
      </ul>
    </div>
  );
}

export default Sidebar;
