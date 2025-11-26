import { Outlet } from "react-router-dom";

function LayoutAuth() {
  return (
    <div className="d-flex vh-100 justify-content-center align-items-center bg-light">
      <div className="card shadow p-4" style={{ minWidth: "350px" }}>

        <div className="text-center mb-4">
          <h3 className="fw-bold">Minha Aplicação</h3>
        </div>
        <Outlet />
      </div>
    </div>
  );
}

export default LayoutAuth;

