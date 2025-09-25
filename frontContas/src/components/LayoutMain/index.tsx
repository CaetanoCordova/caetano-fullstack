import { Outlet } from "react-router-dom";
import Header from "../header";
import Sidebar from "../sidebar";
import Footer from "../footer";

function LayoutMain() {
  return (
    <div className="app-container flex flex-col h-screen">
      {/* Header fixo no topo */}
      <Header />

      <div className="flex flex-1">
        {/* Sidebar à esquerda */}
        <Sidebar />

        {/* Conteúdo dinâmico (Outlet) */}
        <main className="flex-1 bg-gray-50">
          <Outlet />
        </main>
      </div>

      {/* Footer fixo embaixo */}
      <Footer />
    </div>
  );
}

export default LayoutMain;
