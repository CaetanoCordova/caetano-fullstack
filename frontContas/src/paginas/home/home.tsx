import { Outlet } from "react-router-dom";
import Header from "../../components/header";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Home() {
  return (
    <div className="d-flex flex-column vh-100">
      <Header />
      <div className="d-flex flex-grow-1">
        <main className="flex-grow-1 p-3 bg-light">
          {/* Aqui entra o conteúdo da rota filha */}
          <Outlet />
        </main>
      </div>
      <Footer />
    </div>
  );
}

export default Home;
