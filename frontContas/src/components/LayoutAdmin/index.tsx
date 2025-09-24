import { Outlet } from "react-router-dom";

function AuthLayout() {
  return (
    <div className="d-flex vh-100 justify-content-center align-items-center bg-light">
      <div className="card shadow p-4" style={{ minWidth: "350px" }}>

        <div className="text-center mb-4">
          <h3 className="fw-bold">Minha Aplicação</h3>
          <p className="text-muted">Bem-vindo! Faça login ou crie sua conta</p>
        </div>
        {/* Aqui o Login ou Cadastro será injetado */}
        <Outlet />
      </div>
    </div>
  );
}

export default AuthLayout;


{/*}
import { Outlet } from "react-router-dom";
import Header from "../header";
import Footer from "../footer";
import Sidebar from "../sidebar";
 
function LayoutAdmin() {
    return (
            <>
                <Header />
                <div className="d-flex">
                    <Sidebar />
                    <div className="flex-grow-1 p-4">
                        <Outlet />
                    </div>
                </div>
                <Footer />
            </>
    );
}
 
export default LayoutAdmin;
*/}